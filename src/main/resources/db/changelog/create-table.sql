create sequence hibernate_sequence start with 1 increment by 1;


create table TRADE_STORE (
 id bigint not null,
 trade_id varchar(10),
 version integer,
 counter_party_id varchar(10),
 book_id varchar(5),
 maturity_date date,
 created_date date,
 expired varchar(1),
 primary key (id)
);

