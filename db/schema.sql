create table rtkit_db
(
    id           serial primary key,
    color_number varchar(2),
    name         varchar(50)
);

insert into rtkit_db (color_number, name) VALUES ('01', 'Red');
insert into rtkit_db (color_number, name) VALUES ('01', 'Orange');
insert into rtkit_db (color_number, name) VALUES ('01', 'Yellow');
insert into rtkit_db (color_number, name) VALUES ('01', 'Green');
insert into rtkit_db (color_number, name) VALUES ('01', 'Blue');
insert into rtkit_db (color_number, name) VALUES ('01', 'Dark blue');
insert into rtkit_db (color_number, name) VALUES ('01', 'Violet');