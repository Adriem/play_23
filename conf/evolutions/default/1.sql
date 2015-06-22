# --- Created by Slick DDL
# To stop Slick DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table "BEER" ("name" VARCHAR NOT NULL PRIMARY KEY,"details" VARCHAR NOT NULL,"img" VARCHAR NOT NULL,"provenance" VARCHAR NOT NULL,"vol" DOUBLE NOT NULL);

# --- !Downs

drop table "BEER";

