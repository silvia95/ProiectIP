-- Table: Users
CREATE TABLE Users (
    email varchar(25) NOT NULL,
    password varchar(32) NOT NULL,
    first_name varchar(10) NOT NULL,
    last_name varchar(25) NOT NULL,
    CONSTRAINT id_user PRIMARY KEY (email)
);