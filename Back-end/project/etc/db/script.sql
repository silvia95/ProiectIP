ALTER TABLE IPUSER.QUOTATIONS DROP CONSTRAINT QUOTATIONS_FK;
ALTER TABLE IPUSER.PROJECT_AUTHORS DROP CONSTRAINT PROJECT_AUTHORS_FK1;
ALTER TABLE IPUSER.PROJECT_AUTHORS DROP CONSTRAINT PROJECT_AUTHORS_FK2;
ALTER TABLE IPUSER.EMAILS DROP CONSTRAINT EMAILS_FK;
ALTER TABLE IPUSER.DEPARTMENTS DROP CONSTRAINT DEPARTMENTS_FK;
ALTER TABLE IPUSER.ARTICLE_AUTHORS DROP CONSTRAINT ARTICLE_AUTHORS_FK1;
ALTER TABLE IPUSER.ARTICLE_AUTHORS DROP CONSTRAINT ARTICLE_AUTHORS_FK2;
ALTER TABLE IPUSER.TEACHERS DROP CONSTRAINT TEACHERS_FK;
ALTER TABLE IPUSER.ARTICLES DROP CONSTRAINT ARTICLES_FK;
DROP TABLE IPUSER.QUOTATIONS;
DROP TABLE IPUSER.PROJECT_AUTHORS;
DROP TABLE IPUSER.EMAILS;
DROP TABLE IPUSER.DEPARTMENTS;
DROP TABLE IPUSER.ARTICLE_AUTHORS;
DROP TABLE IPUSER.TEACHERS;
DROP TABLE IPUSER.ARTICLES;
DROP TABLE IPUSER.USERS;
DROP TABLE IPUSER.PROJECTS;
DROP TABLE IPUSER.JOURNALS;

DROP SEQUENCE ARTICLES_SEQ;
DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE projects_seq;


CREATE TABLE Users (
    user_id      NUMBER(10) NOT NULL UNIQUE,
    email varchar(25) NOT NULL,
    password varchar(32) NOT NULL,
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
    first_name varchar(10) NOT NULL,
    last_name varchar(25) NOT NULL,
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

CREATE TABLE Journals (
    ISSN VARCHAR2(20),
    journal_name VARCHAR2(50),
    score NUMBER(1),
    CONSTRAINT Journals_pk PRIMARY KEY (ISSN)
);

CREATE TABLE Articles (
    article_id NUMBER(10),
    title VARCHAR2(50),
    year VARCHAR2(4),
    journal_issn VARCHAR2(20),
    CONSTRAINT Articles_pk PRIMARY KEY (article_id),
    CONSTRAINT Articles_fk FOREIGN KEY (journal_issn) REFERENCES Journals(ISSN)
);

CREATE SEQUENCE articles_seq;

CREATE OR REPLACE TRIGGER articles_increment
BEFORE INSERT ON Articles
FOR EACH ROW

    BEGIN
        SELECT articles_seq.NEXTVAL
        INTO   :new.article_id
        FROM   dual;
    END;

CREATE TABLE Article_Authors (
    article_id NUMBER(10),
    user_id      NUMBER(10) NOT NULL,
    CONSTRAINT Article_Authors_pk PRIMARY KEY (article_id, user_id),
    CONSTRAINT Article_Authors_fk1 FOREIGN KEY(article_id) REFERENCES Articles(article_id),
    CONSTRAINT Article_Authors_fk2 FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Quotations (
    article_id NUMBER(10),
    text VARCHAR2(128),
    year VARCHAR2(4),
    articleName VARCHAR2(128),
    location VARCHAR2(128),
    authors INT,

    CONSTRAINT Quotations_fk FOREIGN KEY(article_id) REFERENCES Articles(article_id)
);


INSERT INTO Users(email, password)
VALUES ('mmoruz@info.uaic.ro', 'a');

INSERT INTO Users(email, password)
VALUES ('adiftene@info.uaic.ro', 'a');

INSERT INTO TEACHERS (EMAIL, FIRST_NAME, LAST_NAME, TYPE)
VALUES ('adiftene@info.uaic.ro','Iftene', 'Adrian', 'Conf. dr.');
INSERT INTO TEACHERS (EMAIL, FIRST_NAME, LAST_NAME, TYPE)
VALUES ('mmoruz@info.uaic.ro', 'Moruz', 'Mihai Alex', 'Lect. dr.');

INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D1');
INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D2');
INSERT INTO Departments VALUES ('adiftene@info.uaic.ro', 'D1');

INSERT INTO Emails VALUES ('mmoruz@info.uaic.ro', 'mmoruz@gmail.com');
INSERT INTO Emails VALUES ('adiftene@info.uaic.ro', 'adiftene@gmail.com');

INSERT INTO Journals VALUES ('03600300', 'ACM Computing Surveys', 8);
INSERT INTO Journals VALUES ('01636804', 'IEEE Communications Magazine', 8);

INSERT INTO Articles (title, year, journal_issn) VALUES ('Title1' , '2016', '03600300');
INSERT INTO Articles (title, year, journal_issn) VALUES ('Title2' , '2016', '03600300');
INSERT INTO Articles (title, year, journal_issn) VALUES ('Title3' , '2015', '01636804');

INSERT INTO Article_Authors (article_id, user_id) VALUES ('1', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('2', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('3', '1');

CREATE TABLE Projects (
    project_id NUMBER(10),
    director VARCHAR2(128),
    title VARCHAR2(128),
    domain VARCHAR2(128),
    start_date VARCHAR2(128),
    finish_date VARCHAR2(128),
    description VARCHAR2(512),
    budget NUMBER(10),

    CONSTRAINT Projects_pk PRIMARY KEY (project_id)
);

CREATE SEQUENCE projects_seq;

CREATE OR REPLACE TRIGGER projects_increment
BEFORE INSERT ON Projects
FOR EACH ROW

    BEGIN
        SELECT projects_seq.NEXTVAL
        INTO   :new.project_id
        FROM   dual;
    END;

CREATE TABLE Project_Authors (
    project_id NUMBER(10),
    user_id      NUMBER(10) NOT NULL,
    CONSTRAINT Project_Authors_pk PRIMARY KEY (project_id, user_id),
    CONSTRAINT Project_Authors_fk1 FOREIGN KEY(project_id) REFERENCES Projects(project_id),
    CONSTRAINT Project_Authors_fk2 FOREIGN KEY (user_id) REFERENCES Users(user_id)
);
