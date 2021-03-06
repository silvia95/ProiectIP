ALTER TABLE IPUSER.SCIENTIFIC_EVENTS_ATTENDING DROP CONSTRAINT SCIENTIFIC_EVENTS_ATTENDING_FK;
ALTER TABLE IPUSER.QUOTATIONS DROP CONSTRAINT QUOTATIONS_FK;
ALTER TABLE IPUSER.PROJECT_AUTHORS DROP CONSTRAINT PROJECT_AUTHORS_FK1;
ALTER TABLE IPUSER.PROJECT_AUTHORS DROP CONSTRAINT PROJECT_AUTHORS_FK2;
ALTER TABLE IPUSER.EMAILS DROP CONSTRAINT EMAILS_FK;
ALTER TABLE IPUSER.DEPARTMENTS DROP CONSTRAINT DEPARTMENTS_FK;
ALTER TABLE IPUSER.CONFERENCES_ATTENDING DROP CONSTRAINT CONFERENCES_ATTENDING_FK1;
ALTER TABLE IPUSER.CONFERENCES_ATTENDING DROP CONSTRAINT CONFERENCES_ATTENDING_FK2;
ALTER TABLE IPUSER.BOOK_AUTHORS DROP CONSTRAINT BOOK_AUTHORS_FK;
ALTER TABLE IPUSER.ARTICLE_OTHER_AUTHORS DROP CONSTRAINT ARTICLE_OTHER_AUTHORS_FK1;
ALTER TABLE IPUSER.ARTICLE_AUTHORS DROP CONSTRAINT ARTICLE_AUTHORS_FK1;
ALTER TABLE IPUSER.ARTICLE_AUTHORS DROP CONSTRAINT ARTICLE_AUTHORS_FK2;
ALTER TABLE IPUSER.TEACHERS DROP CONSTRAINT TEACHERS_FK;
DROP TABLE IPUSER.VISITATIONS;
DROP TABLE IPUSER.SCIENTIFIC_EVENTS_ATTENDING;
DROP TABLE IPUSER.QUOTATIONS;
DROP TABLE IPUSER.PROJECT_AUTHORS;
DROP TABLE IPUSER.EMAILS;
DROP TABLE IPUSER.DEPARTMENTS;
DROP TABLE IPUSER.CONFERENCES_ATTENDING;
DROP TABLE IPUSER.BOOK_AUTHORS;
DROP TABLE IPUSER.ARTICLE_OTHER_AUTHORS;
DROP TABLE IPUSER.ARTICLE_AUTHORS;
DROP TABLE IPUSER.TEACHERS;
DROP TABLE IPUSER.USERS;
DROP TABLE IPUSER.PROJECTS;
DROP TABLE IPUSER.CONFERENCES;
DROP TABLE IPUSER.ARTICLES;
DROP TABLE IPUSER.SCIENTIFIC_EVENTS;
DROP TABLE IPUSER.JOURNALS;
DROP TABLE IPUSER.BOOKS;
DROP TABLE IPUSER.ADMINS;

DROP SEQUENCE ARTICLES_SEQ;
DROP SEQUENCE USERS_SEQ;
DROP SEQUENCE projects_seq;
DROP SEQUENCE conferences_seq;
DROP SEQUENCE admins_seq;
DROP SEQUENCE events_seq;
DROP SEQUENCE books_seq;
DROP SEQUENCE visitations_seq;

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
    CONSTRAINT Teachers_fk FOREIGN KEY (email) REFERENCES Users(email) ON DELETE CASCADE
);

CREATE TABLE Departments (
    email varchar(25) NOT NULL,
    deparmentName varchar(15) NOT NULL,
    CONSTRAINT Departments_pk PRIMARY KEY (email, deparmentName),
    CONSTRAINT Departments_fk FOREIGN KEY (email) REFERENCES Teachers(email) ON DELETE CASCADE
);

CREATE TABLE Journals (
    ISSN VARCHAR2(20),
    journal_name VARCHAR2(1000),
    score NUMBER(1)
);

CREATE TABLE Articles (
    article_id NUMBER(10),
    title VARCHAR2(1024),
    year VARCHAR2(4),
    journal_issn VARCHAR2(20),
    CONSTRAINT Articles_pk PRIMARY KEY (article_id)
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
    CONSTRAINT Article_Authors_fk1 FOREIGN KEY(article_id) REFERENCES Articles(article_id),
    CONSTRAINT Article_Authors_fk2 FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Article_Other_Authors (
    article_id NUMBER(10),
    name VARCHAR2(100),

    CONSTRAINT Article_Other_Authors_fk1 FOREIGN KEY(article_id) REFERENCES Articles(article_id)
);

CREATE TABLE Quotations (
    article_id NUMBER(10),
    text VARCHAR2(128),
    year VARCHAR2(4),
    articleName VARCHAR2(128),
    location VARCHAR2(1024),
    authors VARCHAR2(512),

    CONSTRAINT Quotations_fk FOREIGN KEY(article_id) REFERENCES Articles(article_id)
);

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

CREATE TABLE CONFERENCES
(
    CONFERENCE_ID NUMBER(10),
    CONFERENCE_NAME VARCHAR2(200),
    YEAR VARCHAR2(100),
    LOCATION VARCHAR2(200),
    DETAILS VARCHAR2(3200),

    CONSTRAINT CONFERENCE_PK PRIMARY KEY (CONFERENCE_ID)
);

CREATE SEQUENCE conferences_seq;

CREATE OR REPLACE TRIGGER conferences_increment
BEFORE INSERT ON CONFERENCES
FOR EACH ROW

    BEGIN
        SELECT conferences_seq.NEXTVAL
        INTO   :new.CONFERENCE_ID
        FROM   dual;
    END;

CREATE TABLE CONFERENCES_ATTENDING (
    user_id NUMBER(10),
    CONFERENCE_ID NUMBER(10),

    CONSTRAINT CONFERENCES_ATTENDING_pk PRIMARY KEY (user_id, CONFERENCE_ID),
    CONSTRAINT CONFERENCES_ATTENDING_fk1 FOREIGN KEY (user_id) REFERENCES USERS(user_id),
    CONSTRAINT CONFERENCES_ATTENDING_fk2 FOREIGN KEY (CONFERENCE_ID) REFERENCES CONFERENCES(CONFERENCE_ID)
);

CREATE TABLE ADMINS(
    user_id      NUMBER(10) NOT NULL UNIQUE,
    email varchar(25) NOT NULL,
    password varchar(32) NOT NULL,
    CONSTRAINT ADMINS_pk PRIMARY KEY (email)
);

CREATE SEQUENCE admins_seq;

CREATE OR REPLACE TRIGGER admins_increment
BEFORE INSERT ON ADMINS
FOR EACH ROW

    BEGIN
        SELECT admins_seq.NEXTVAL
        INTO   :new.user_id
        FROM   dual;
    END;

INSERT INTO ADMINS (email, password) VALUES ('admin', 'admin');

create table scientific_events(
    event_id number(3, 0),
    event_name varchar2(200),
    event_year number(4, 0),
    event_link varchar2(200)
);

CREATE SEQUENCE events_seq;

CREATE OR REPLACE TRIGGER events_increment
BEFORE INSERT ON scientific_events
FOR EACH ROW

    BEGIN
        SELECT events_seq.NEXTVAL
        INTO   :new.event_id
        FROM   dual;
    END;

create table scientific_events_attending(
    event_id number(3, 0),
    user_id NUMBER(10),
    score number(2, 0),

    CONSTRAINT scientific_events_attending_fk FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

create table books(
    book_id number(3, 0),
    book_name varchar2(200),
    book_year number(4, 0),
    score number(2, 0)
);

CREATE SEQUENCE books_seq;

CREATE OR REPLACE TRIGGER books_increment
BEFORE INSERT ON books
FOR EACH ROW

    BEGIN
        SELECT books_seq.NEXTVAL
        INTO   :new.book_id
        FROM   dual;
    END;


create table book_authors(
    book_id number(3, 0),
    user_id NUMBER(10),

    CONSTRAINT book_authors_fk FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

create table visitations(
    visit_id number(3, 0),
    user_id number(10, 0),
    university_name varchar2(200),
    purpose varchar2(255),
    year number(4, 0),
    rank NUMBER(10, 0),
    nr_of_months number(3, 0),
    score number(2, 0),

    CONSTRAINT visitations_fk FOREIGN KEY (user_id) REFERENCES USERS(user_id)
);

CREATE SEQUENCE visitations_seq;

CREATE OR REPLACE TRIGGER visitations_increment
BEFORE INSERT ON visitations
FOR EACH ROW

    BEGIN
        SELECT visitations_seq.NEXTVAL
        INTO   :new.visit_id
        FROM   dual;
    END;


INSERT INTO Users (email, password)
VALUES ('mmoruz@info.uaic.ro', 'a');

INSERT INTO Users (email, password)
VALUES ('adiftene@info.uaic.ro', 'a');

INSERT INTO TEACHERS (EMAIL, FIRST_NAME, LAST_NAME, TYPE)
VALUES ('adiftene@info.uaic.ro', 'Adrian', 'Iftene', 'Conf. dr.');
INSERT INTO TEACHERS (EMAIL, FIRST_NAME, LAST_NAME, TYPE)
VALUES ('mmoruz@info.uaic.ro', 'Mihai', 'Moruz', 'Lect. dr.');

INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D1');
INSERT INTO Departments VALUES ('mmoruz@info.uaic.ro', 'D2');
INSERT INTO Departments VALUES ('adiftene@info.uaic.ro', 'D1');

INSERT INTO Emails VALUES ('mmoruz@info.uaic.ro', 'mmoruz@gmail.com');
INSERT INTO Emails VALUES ('adiftene@info.uaic.ro', 'adiftene@gmail.com');

INSERT INTO Journals VALUES ('03600300', 'ACM Computing Surveys', 8);
INSERT INTO Journals VALUES ('01636804', 'IEEE Communications Magazine', 8);
INSERT INTO Journals
VALUES ('01636801', 'Notebook Paper for the CLEF 2013 LABs Workshop - QA4MRE, 23-26 September, Valencia, Spain.', 8);

INSERT INTO Journals VALUES ('01632304', 'Proceedings of TAC, Gaithersburg, Maryland', 8);


INSERT INTO Articles (title, year, journal_issn) VALUES
    ('A Procedural DTD Project for Dictionary Entry Parsing Described with Parameterized Grammars', '2016', '03600300');
INSERT INTO Articles (title, year, journal_issn) VALUES
    ('An Optimal and Portable Parsing Method for Romanian, French, and German Large Dictionaries', '2016', '03600300');
INSERT INTO Articles (title, year, journal_issn) VALUES
    ('Extracting Sense Trees from the Romanian Thesaurus by Sense Segmentation & Dependency Parsing', '2015',
     '01636804');
INSERT INTO Articles (title, year, journal_issn) VALUES ('UAIC Participation at QA@CLEF2008', '2012', '01636801');

INSERT INTO Article_Authors (article_id, user_id) VALUES ('1', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('2', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('3', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('4', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('4', '2');

INSERT INTO Projects (director, title, domain, start_date, finish_date, description, budget)
VALUES ('Mihai Moruz', 'MetaNet4U (Enhancing the European Linguistic Infrastructure), CIP-270893', 'IT', '2010', '2015',
        'Project', 240000);
INSERT INTO Project_Authors (project_id, user_id) VALUES (1, 1);

INSERT INTO Projects (director, title, domain, start_date, finish_date, description, budget)
VALUES ('Adrian Iftene', 'eDTLR – PNCDI II, 910013/18.09.2007', 'IT', '2007', '2009', 'Project', 246520);
INSERT INTO Project_Authors (project_id, user_id) VALUES (2, 2);
INSERT INTO Project_Authors (project_id, user_id) VALUES (2, 1);

INSERT INTO CONFERENCES (CONFERENCE_NAME, YEAR, LOCATION, DETAILS)
VALUES ('Parameterized Grammars for Dependency Hypergraphs', 2013, 'Bucharest', 'Thesaurus-Dictionary Parsing, ORDA');
INSERT INTO CONFERENCES_ATTENDING (user_id, CONFERENCE_ID) VALUES (1, 1);

INSERT INTO scientific_events (event_name, event_year, event_link)
VALUES ('Scoala de vara Eurolan 2007, Iasi', '2007', 'http://eurolan.info.uaic.ro/html/people.html');
INSERT INTO scientific_events_attending (event_id, user_id, score) VALUES (1, 1, 1);
INSERT INTO scientific_events(event_name, event_year, event_link)
VALUES ('Consilr 2006, Iasi', '2006', 'http://consilr.info.uaic.ro/ro/atelier2006/index.php?showpage=0402');
INSERT INTO scientific_events_attending (event_id, user_id, score) VALUES (2, 1, 2);


INSERT INTO books( book_name, book_year, score)
VALUES('Linguistic Resources And Tools For Processing The Romanian Language', '2013', 3);
INSERT INTO book_authors(book_id, user_id) VALUES (1, 1);
INSERT INTO books( book_name, book_year, score)
VALUES('Linguistic Resources And Tools For Processing The Romanian Language', '2012', 3);
INSERT INTO book_authors(book_id, user_id) VALUES (2, 1);

INSERT INTO visitations(user_id, university_name, purpose, rank, nr_of_months, score, year)
VALUES (1, 'Albert Ludwigs Universitat, Freiburg im Breisgau', 'Alcatuirea unei editii online a dictionarului Tiktin', 154, 1, 4, 2013);
INSERT INTO visitations(user_id, university_name, purpose, rank, nr_of_months, score, year)
VALUES (1, 'National University of Singapore', 'Imbunatatirea unui motor de rezolutia anaforei folosind dezambiguizare semantica', 116, 4, 16, 2013);


INSERT INTO Articles(title, year, journal_issn)
VALUES ('Uaic participation at rte5', '2009', '01632304');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('5', '1');
INSERT INTO Article_Authors (article_id, user_id) VALUES ('5', '2');

INSERT INTO Quotations(article_id, text, year, articleName, location, authors)
VALUES (5, '', 2010, 'A Machine Learning Approach for Recognizing Textual Entailment in Spanish',
        'Proceedings of the NAACL HLT 2010 Young Investigators Workshop on Computational Approaches to Languages of the Americas, Los Angeles, California',
        'Adrian Iftene, Moruz Mihai');

INSERT INTO Quotations(article_id, text, year, articleName, location, authors)
VALUES (5, '', 2010, 'Mining Wikipedia for Large-scale Repositories of Context-Sensitive Entailment Rules',
        'LREC-2010',
        'Adrian Iftene, Moruz Mihai');
