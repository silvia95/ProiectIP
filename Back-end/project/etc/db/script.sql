CREATE TABLE Users (
    user_id      NUMBER(10),
    email varchar(25) NOT NULL,
    password varchar(32) NOT NULL,
    first_name varchar(10) NOT NULL,
    last_name varchar(25) NOT NULL,
    CONSTRAINT Users_pk PRIMARY KEY (email)
);

CREATE SEQUENCE users_seq;

CREATE OR REPLACE TRIGGER users_increment
BEFORE INSERT ON USERS
FOR EACH ROW

    BEGIN
        SELECT users_seq.NEXTVAL
        INTO   :new.user_id
        FROM   dual;
    END;

CREATE TABLE Emails (
    email varchar(25) NOT NULL,
    other_email varchar(25) NOT NULL,
    CONSTRAINT Emails_pk PRIMARY KEY (email, other_email),
    CONSTRAINT Emails_fk FOREIGN KEY (email) REFERENCES Users(email)
);

CREATE TABLE Teachers (
    email varchar(25) NOT NULL,
    type varchar(10) NOT NULL,
    CONSTRAINT Teachers_pk PRIMARY KEY (email),
    CONSTRAINT Teachers_fk FOREIGN KEY (email) REFERENCES Users(email)
);

CREATE TABLE Departments (
    email varchar(25) NOT NULL,
    deparmentName varchar(15) NOT NULL,
    CONSTRAINT Departments_pk PRIMARY KEY (email, deparmentName),
    CONSTRAINT Departments_fk FOREIGN KEY (email) REFERENCES Teachers(email)
);

INSERT INTO Users(email, password, first_name, last_name)
VALUES ('mmoruz@info.uaic.ro', 'a', 'Moruz', 'Mihai Alex');

INSERT INTO Users(email, password, first_name, last_name)
VALUES ('adiftene@info.uaic.ro', 'a', 'Iftene', 'Adrian');

INSERT INTO TEACHERS VALUES ('adiftene@info.uaic.ro', 'Conf. dr.');
INSERT INTO TEACHERS VALUES ('mmoruz@info.uaic.ro', 'Lect. dr.');

INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D1');
INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D2');
INSERT INTO Departments VALUES ('adiftene@info.uaic.ro', 'D1');

INSERT INTO Emails VALUES ('mmoruz@info.uaic.ro', 'mmoruz@gmail.com');
INSERT INTO Emails VALUES ('adiftene@info.uaic.ro', 'adiftene@gmail.com');
