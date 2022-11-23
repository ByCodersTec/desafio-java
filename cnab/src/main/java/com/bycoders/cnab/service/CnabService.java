package com.bycoders.cnab.service;

import com.bycoders.cnab.entity.Cnab;
import com.bycoders.cnab.enums.TipoTransacao;
import com.bycoders.cnab.repository.CnabRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

@Service
public class CnabService {

    @Autowired
    private CnabRepository cnabRepository;

    public ResponseEntity<Object> salvar(MultipartFile file) throws IOException {

        String content = new String(file.getBytes());
        String[] lines = content.split(System.lineSeparator());

        for(int i = 0; i < lines.length; i++) {
            System.out.println(lines[i]);
            salvarRegistro(lines[i]);
        }

        return new ResponseEntity<>("Documento Salvo", HttpStatus.OK);

    }

    private void salvarRegistro(String line) {
        System.out.println("Salvar");
        String tipo = line.substring(0, 1);
        String data = line.substring(1, 9);
        String valor = line.substring(9, 19);
        String cpf = line.substring(19, 30);
        String cartao = line.substring(30, 42);
        String hora = line.substring(42, 48);
        String dono = line.substring(48, 62);
        String nome = line.substring(62, 80);

        // Cnab cnab = new Cnab(
        //     converteTipo(tipo),
        //     LocalDate.parse(data.substring(0, 4).concat("-").concat(data.substring(4, 6).concat("-")).concat(data.substring(6, 8))),
        //     new BigDecimal((Integer.parseInt(valor)/100)),
        //     cpf,
        //     cartao,
        //     LocalTime.parse(hora.substring(0,2).concat(":").concat(hora.substring(2, 4).concat(":").concat(hora.substring(4, 6)))),
        //     dono,
        //     nome
        // );

        System.out.println("Gerando CNAB");
        Cnab cnab = new Cnab();
        System.out.println("Gerado CNAB");

        cnab.setTipo(converteTipo(tipo));
        cnab.setData(LocalDate.parse(data.substring(0, 4).concat("-").concat(data.substring(4, 6).concat("-")).concat(data.substring(6, 8))));
        cnab.setValor(new BigDecimal((Integer.parseInt(valor)/100)));
        cnab.setCpf(cpf);
        cnab.setCartao(cartao);
        cnab.setHora(LocalTime.parse(hora.substring(0,2).concat(":").concat(hora.substring(2, 4).concat(":").concat(hora.substring(4, 6)))));
        cnab.setDonoLoja(dono);
        cnab.setNomeLoja(nome);

        System.out.println(cnab.getTipo());
        System.out.println(cnab.getData());
        System.out.println(cnab.getCartao());
        System.out.println(cnab.getCpf());
        System.out.println(cnab.getDonoLoja());
        System.out.println(cnab.getNomeLoja());
        System.out.println(cnab.getValor());
        System.out.println(cnab.getHora());

        cnabRepository.save(cnab);

    }

    private TipoTransacao converteTipo(String tipo) {
        switch (tipo){
            case "1":
                return TipoTransacao.DEBITO;
            case "2":
                return TipoTransacao.BOLETO;
            case "3":
                return TipoTransacao.FINANCIAMENTO;
            case "4":
                return TipoTransacao.CREDITO;
            case "5":
                return TipoTransacao.RECEBIMENTO_EMPRESTIMO;
            case "6":
                return TipoTransacao.VENDAS;
            case "7":
                return TipoTransacao.TED;
            case "8":
                return TipoTransacao.DOC;
            case "9":
                return TipoTransacao.ALUGUEL;
            default:
                return null;

        }
    }


}
