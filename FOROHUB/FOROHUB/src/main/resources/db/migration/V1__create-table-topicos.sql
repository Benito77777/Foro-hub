create table topicos
(
    id      serial       not null ,
    titulo  varchar(100) not null,
    mensaje varchar(200) not null,
    autor   varchar(100) not null,
    curso   varchar(50)  not null,

    primary key (id)
)