create database rtkit_db;

create table tsveta
(
    id           serial primary key,
    color_number varchar(2),
    name         varchar(50)
);

insert into tsveta (color_number, name) VALUES ('01', 'Red');
insert into tsveta (color_number, name) VALUES ('02', 'Orange');
insert into tsveta (color_number, name) VALUES ('03', 'Yellow');
insert into tsveta (color_number, name) VALUES ('04', 'Green');
insert into tsveta (color_number, name) VALUES ('05', 'Blue');
insert into tsveta (color_number, name) VALUES ('06', 'Dark blue');
insert into tsveta (color_number, name) VALUES ('07', 'Violet');