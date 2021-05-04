
CREATE TABLE IF NOT EXISTS person_phone (
    person_id INTEGER NOT NULL ,
    phone_id INTEGER NOT NULL ,
    UNIQUE(person_id,phone_id)
);