CREATE USER ipuser
IDENTIFIED BY ipuserpwd
DEFAULT TABLESPACE tbs_perm_01
TEMPORARY TABLESPACE tbs_temp_01
QUOTA 50M ON tbs_perm_01;

GRANT create session TO ipuser;
GRANT create table TO ipuser;
GRANT create view TO ipuser;
GRANT create any trigger TO ipuser;
GRANT create any procedure TO ipuser;
GRANT create sequence TO ipuser;
GRANT create synonym TO ipuser;
GRANT EXECUTE ON DBMS_CRYPTO TO ipuser;