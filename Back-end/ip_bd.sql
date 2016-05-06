-- tables
-- Table: Admins
CREATE TABLE Admins (
    id_admin int NOT NULL,
    Users_email varchar(25) NOT NULL,
    CONSTRAINT Admins_pk PRIMARY KEY (id_admin,Users_email)
);

-- Table: Article
CREATE TABLE Article (
    id_article int NOT NULL,
    title varchar(50) NOT NULL,
    year int NOT NULL,
    Journals_id_journal int NOT NULL,
    CONSTRAINT Article_pk PRIMARY KEY (id_article)
);

-- Table: Article_Authors
CREATE TABLE Article_Authors (
    Article_id_article int NOT NULL,
    Others_id_others int NOT NULL,
    Teachers_id_teacher int NOT NULL,
    CONSTRAINT Article_Authors_pk PRIMARY KEY (Article_id_article,Others_id_others,Teachers_id_teacher)
);

-- Table: Conferences
CREATE TABLE Conferences (
    id_conf int NOT NULL,
    name varchar(50) NOT NULL,
    conference_date date NOT NULL,
    details varchar(128) NOT NULL,
    score int NOT NULL,
    CONSTRAINT Conferences_pk PRIMARY KEY (id_conf)
);

-- Table: Conferences_attending
CREATE TABLE Conferences_attending (
    Teachers_id_teacher int NOT NULL,
    Conferences_id_conf int NOT NULL,
    CONSTRAINT Conferences_attending_pk PRIMARY KEY (Teachers_id_teacher,Conferences_id_conf)
);

-- Table: Departament_head
CREATE TABLE Departament_head (
    Departaments_id_departament int NOT NULL,
    Teachers_id_teacher int NOT NULL,
    CONSTRAINT Departament_head_pk PRIMARY KEY (Departaments_id_departament,Teachers_id_teacher)
);

-- Table: Departaments
CREATE TABLE Departaments (
    id_departament int NOT NULL,
    name varchar(15) NOT NULL,
    CONSTRAINT Departaments_pk PRIMARY KEY (id_departament)
);

-- Table: Emails
CREATE TABLE Emails (
    other_email varchar(25) NOT NULL,
    Users_email varchar(25) NOT NULL,
    CONSTRAINT Emails_pk PRIMARY KEY (other_email,Users_email)
);

-- Table: Journals
CREATE TABLE Journals (
    id_journal int NOT NULL,
    name varchar(50) NOT NULL,
    score int NOT NULL,
    CONSTRAINT Journals_pk PRIMARY KEY (id_journal)
);

-- Table: Others
CREATE TABLE Others (
    id_others int NOT NULL,
    first_name varchar(15) NOT NULL,
    last_name varchar(25) NOT NULL,
    CONSTRAINT Others_pk PRIMARY KEY (id_others)
);

-- Table: Project_Authors
CREATE TABLE Project_Authors (
    Teachers_id_teacher int NOT NULL,
    Others_id_others int NOT NULL,
    Projects_id_project int NOT NULL,
    CONSTRAINT Project_Authors_pk PRIMARY KEY (Teachers_id_teacher,Others_id_others,Projects_id_project)
);

-- Table: Projects
CREATE TABLE Projects (
    id_project int NOT NULL,
    title varchar(128) NOT NULL,
    project_domain varchar(15) NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL,
    description varchar(50) NOT NULL,
    budget int NOT NULL,
    score int NOT NULL,
    CONSTRAINT Projects_pk PRIMARY KEY (id_project)
);

-- Table: Quotation
CREATE TABLE Quotation (
    text varchar(128) NOT NULL,
    year int NOT NULL,
    Article_id_article int NOT NULL,
    Journals_id_journal int NOT NULL,
    CONSTRAINT Quotation_pk PRIMARY KEY (year,Article_id_article,Journals_id_journal)
);

-- Table: Teachers
CREATE TABLE Teachers (
    id_teacher int NOT NULL,
    teacher_type varchar(10) NOT NULL,
    Users_email varchar(25) NOT NULL,
    Departaments_id_departament int NOT NULL,
    CONSTRAINT Teachers_pk PRIMARY KEY (id_teacher)
);

-- Table: Users
CREATE TABLE Users (
    email varchar(25) NOT NULL,
    password varchar(32) NOT NULL,
    first_name varchar(15) NOT NULL,
    last_name varchar(25) NOT NULL,
    other_email varchar(128) NOT NULL,
    CONSTRAINT id_user PRIMARY KEY (email)
);

-- foreign keys
-- Reference: Admins_Users (table: Admins)
ALTER TABLE Admins ADD CONSTRAINT Admins_Users FOREIGN KEY Admins_Users (Users_email)
    REFERENCES Users (email);

-- Reference: Article_Authors_Article (table: Article_Authors)
ALTER TABLE Article_Authors ADD CONSTRAINT Article_Authors_Article FOREIGN KEY Article_Authors_Article (Article_id_article)
    REFERENCES Article (id_article);

-- Reference: Article_Authors_Others (table: Article_Authors)
ALTER TABLE Article_Authors ADD CONSTRAINT Article_Authors_Others FOREIGN KEY Article_Authors_Others (Others_id_others)
    REFERENCES Others (id_others);

-- Reference: Article_Authors_Teachers (table: Article_Authors)
ALTER TABLE Article_Authors ADD CONSTRAINT Article_Authors_Teachers FOREIGN KEY Article_Authors_Teachers (Teachers_id_teacher)
    REFERENCES Teachers (id_teacher);

-- Reference: Article_Journals (table: Article)
ALTER TABLE Article ADD CONSTRAINT Article_Journals FOREIGN KEY Article_Journals (Journals_id_journal)
    REFERENCES Journals (id_journal);

-- Reference: Conferences_attending_Conferences (table: Conferences_attending)
ALTER TABLE Conferences_attending ADD CONSTRAINT Conferences_attending_Conferences FOREIGN KEY Conferences_attending_Conferences (Conferences_id_conf)
    REFERENCES Conferences (id_conf);

-- Reference: Conferences_attending_Teachers (table: Conferences_attending)
ALTER TABLE Conferences_attending ADD CONSTRAINT Conferences_attending_Teachers FOREIGN KEY Conferences_attending_Teachers (Teachers_id_teacher)
    REFERENCES Teachers (id_teacher);

-- Reference: Departament_head_Departaments (table: Departament_head)
ALTER TABLE Departament_head ADD CONSTRAINT Departament_head_Departaments FOREIGN KEY Departament_head_Departaments (Departaments_id_departament)
    REFERENCES Departaments (id_departament);

-- Reference: Departament_head_Teachers (table: Departament_head)
ALTER TABLE Departament_head ADD CONSTRAINT Departament_head_Teachers FOREIGN KEY Departament_head_Teachers (Teachers_id_teacher)
    REFERENCES Teachers (id_teacher);

-- Reference: Emails_Users (table: Emails)
ALTER TABLE Emails ADD CONSTRAINT Emails_Users FOREIGN KEY Emails_Users (Users_email)
    REFERENCES Users (email);

-- Reference: Project_Authors_Others (table: Project_Authors)
ALTER TABLE Project_Authors ADD CONSTRAINT Project_Authors_Others FOREIGN KEY Project_Authors_Others (Others_id_others)
    REFERENCES Others (id_others);

-- Reference: Project_Authors_Projects (table: Project_Authors)
ALTER TABLE Project_Authors ADD CONSTRAINT Project_Authors_Projects FOREIGN KEY Project_Authors_Projects (Projects_id_project)
    REFERENCES Projects (id_project);

-- Reference: Project_Authors_Teachers (table: Project_Authors)
ALTER TABLE Project_Authors ADD CONSTRAINT Project_Authors_Teachers FOREIGN KEY Project_Authors_Teachers (Teachers_id_teacher)
    REFERENCES Teachers (id_teacher);

-- Reference: Quotation_Article (table: Quotation)
ALTER TABLE Quotation ADD CONSTRAINT Quotation_Article FOREIGN KEY Quotation_Article (Article_id_article)
    REFERENCES Article (id_article);

-- Reference: Quotation_Journals (table: Quotation)
ALTER TABLE Quotation ADD CONSTRAINT Quotation_Journals FOREIGN KEY Quotation_Journals (Journals_id_journal)
    REFERENCES Journals (id_journal);

-- Reference: Teachers_Departaments (table: Teachers)
ALTER TABLE Teachers ADD CONSTRAINT Teachers_Departaments FOREIGN KEY Teachers_Departaments (Departaments_id_departament)
    REFERENCES Departaments (id_departament);

-- Reference: Teachers_Users (table: Teachers)
ALTER TABLE Teachers ADD CONSTRAINT Teachers_Users FOREIGN KEY Teachers_Users (Users_email)
    REFERENCES Users (email);

-- End of file.

