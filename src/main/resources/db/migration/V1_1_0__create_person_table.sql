
CREATE TABLE IF NOT EXISTS person (
    id SERIAL NOT NULL PRIMARY KEY ,
    name VARCHAR(50) NOT NULL ,
    surname VARCHAR(150) NOT NULL ,
    cpf VARCHAR(13) NOT NULL UNIQUE ,
    birthdate DATE
);