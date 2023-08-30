drop table if exists tb_transaction_type CASCADE

drop table if exists tb_transactions CASCADE

 create table tb_transaction_type (
       id bigint generated by default as identity,
        description varchar(255),
        dt_insert timestamp,
        signal varchar(255),
        transaction_type varchar(255),
        primary key (id)
    )


    create table tb_transactions (
       id bigint generated by default as identity,
        card varchar(255),
        cpf varchar(255),
        insert_date date,
        insert_hour time,
        moviment_value double not null,
        store_name varchar(255),
        store_property_name varchar(255),
        id_transaction_type bigint,
        primary key (id)
    )

        alter table tb_transactions
           add constraint FKntqgt45g2hpue3sfbfti7xnhs
           foreign key (id_transaction_type)
           references tb_transaction_type


INSERT INTO tb_transaction_type (description, transaction_type, signal, dt_insert)
VALUES
    ('Débito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Boleto', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Financiamento', 'Saída', '-', CURRENT_TIMESTAMP),
    ('Crédito', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento Empréstimo', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Vendas', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento TED', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Recebimento DOC', 'Entrada', '+', CURRENT_TIMESTAMP),
    ('Aluguel', 'Saída', '-', CURRENT_TIMESTAMP);