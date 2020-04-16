BEGIN TRANSACTION;

DROP TABLE IF EXISTS beer CASCADE;
drop sequence if exists seq_beer_id;
create sequence seq_beer_id;

CREATE TABLE beer (
beer_id integer not null DEFAULT NEXTVAL('seq_beer_id'),
beer_name varchar(50) NOT NULL,
description varchar(1000) NOT NULL,
abv decimal(3,1) NOT NULL,
beer_style varchar(20) NOT NULL,
brewery_id integer not null,

    CONSTRAINT pk_beer_beer_id PRIMARY KEY (beer_id)
);

alter table beer add foreign key(brewery_id) references brewery(brewery_id);
commit;