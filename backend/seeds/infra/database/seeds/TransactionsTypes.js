import { uuid } from "uuidv4";
import TransactionsTypes from "../schemas/TransactionsTypes";


const data = [
  {
    id: uuid(),
    code: 1,
    label: "Débito",
    type: "in"
  },
  {
    id: uuid(),
    code: 2,
    label: "Boleto",
    type: "out"
  },
  {
    id: uuid(),
    code: 3,
    label: "Financiamento",
    type: "out"
  },
  {
    id: uuid(),
    code: 4,
    label: "Crédito",
    type: "in"
  },
  {
    id: uuid(),
    code: 5,
    label: "Recebimento Empréstimo",
    type: "in"
  },
  {
    id: uuid(),
    code: 6,
    label: "Vendas",
    type: "in"
  },
  {
    id: uuid(),
    code: 7,
    label: "Recebimento TED",
    type: "in"
  },
  {
    id: uuid(),
    code: 8,
    label: "Recebimento DOC",
    type: "in"
  },
  {
    id: uuid(),
    code: 9,
    label: "Aluguel",
    type: "out"
  },
];

export default () => {
  for (let d of data) {
    try {
      TransactionsTypes.create({...d});
    } catch (err) {
      console.log(d);
      console.error(err.message);
    }
  }
}