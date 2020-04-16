BEGIN TRANSACTION;

DROP TABLE IF EXISTS beer_reviews    CASCADE;
drop sequence if exists seq_beer_reviews_id;
create sequence seq_beer_reviews_id;

CREATE TABLE beer_reviews (
        beer_reviews_id integer not null DEFAULT NEXTVAL('seq_beer_reviews_id'),
        reviewer_name varchar(30) NOT NULL,
        rating integer NOT NULL,
        beer_review varchar(1000) NOT NULL,
        beer_id integer not null,

        CONSTRAINT pk_beer_reviews_beer_reviews_id PRIMARY KEY (beer_reviews_id)
);

alter table beer_reviews add foreign key(beer_id) references beer(beer_id);

commit;




