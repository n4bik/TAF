create table if not exists "exhibits"
(
    id        integer primary key,
    name      varchar(255),
    num_acres decimal(4, 1)
);

alter table "exhibits"
    owner to postgres;

create table if not exists "names"
(
    id         integer primary key,
    species_id integer references exhibits (id),
    name       varchar(255)
);

alter table "names"
    owner to postgres;

INSERT INTO exhibits
VALUES (1, 'African Elephant', 7.5);
INSERT INTO exhibits
VALUES (2, 'Zebra', 1.2);

INSERT INTO names
VALUES (1, 1, 'Elsa');
INSERT INTO names
VALUES (2, 2, 'Zelda');
INSERT INTO names
VALUES (3, 1, 'Ester');
INSERT INTO names
VALUES (4, 1, 'Eddie');
INSERT INTO names
VALUES (5, 2, 'Zoe');