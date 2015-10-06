CREATE SEQUENCE USERS_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE ROLE_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE COMMENT_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE QUESTION_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE TEST_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE ANSWER_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE CORRECT_ANSWER_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE QUESTION_ANSWER_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE USER_GROUP_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE SUMMARY_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE SEQUENCE SUBJECT_ID_SEQ
START WITH 1
INCREMENT 1
NO MINVALUE
NO MAXVALUE
CACHE 1;

CREATE TABLE ROLE (
  ID        BIGINT DEFAULT NEXTVAL('ROLE_ID_SEQ'),
  ROLE_NAME VARCHAR(20),
  CREATE_DATE DATE,
  CONSTRAINT ROLE_PKEY PRIMARY KEY (ID)
);

CREATE TABLE USERS (
  ID           BIGINT DEFAULT NEXTVAL('USERS_ID_SEQ'),
  FIRST_NAME   VARCHAR(20),
  LAST_NAME    VARCHAR(20),
  INDEX_NUMBER INT,
  CREATE_DATE  DATE,
  PASSWORD     VARCHAR(20),
  STATUS       VARCHAR(20),
  ROLE_ID      BIGINT REFERENCES ROLE (ID),
  CONSTRAINT USERS_PKEY PRIMARY KEY (ID)
);

CREATE TABLE USER_GROUP (
  ID BIGINT DEFAULT NEXTVAL('USER_GROUP_ID_SEQ'),
  NAME VARCHAR(20),
  CREATOR_ID BIGINT REFERENCES USERS (ID),
  CREATE_DATE DATE,
  CONSTRAINT USER_GROUP_PKEY PRIMARY KEY (ID)
);

CREATE TABLE QUESTION (
  ID BIGINT DEFAULT NEXTVAL('QUESTION_ID_SEQ'),
  TYPE VARCHAR(20),
  USER_ID BIGINT REFERENCES USERS (ID),
  NAME VARCHAR(100),
  CREATE_DATE DATE,
  CONSTRAINT QUESTION_PKEY PRIMARY KEY(ID)
);

CREATE TABLE CORRECT_ANSWER (
  ID BIGINT DEFAULT NEXTVAL('CORRECT_ANSWER_ID_SEQ'),
  NAME VARCHAR(100),
  CREATE_DATE DATE,
  USER_ID BIGINT REFERENCES USERS (ID),
  QUESTION_ID BIGINT REFERENCES QUESTION (ID),
  CONSTRAINT CORRECT_ANSWER_PKEY PRIMARY KEY (ID)
);

CREATE TABLE ANSWER (
  ID BIGINT DEFAULT NEXTVAL('ANSWER_ID_SEQ'),
  QUESTION_ID BIGINT REFERENCES QUESTION (ID),
  USER_ID BIGINT REFERENCES USERS (ID),
  CREATE_DATA DATE,
  NAME VARCHAR(100),
  CONSTRAINT ANSWER_PKEY PRIMARY KEY (ID)
);

CREATE TABLE QUESTION_ANSWER (
  ID BIGINT DEFAULT  NEXTVAL('QUESTION_ANSWER_ID_SEQ'),
  USER_ID BIGINT REFERENCES USERS (ID),
  QUESTION_ID BIGINT REFERENCES QUESTION (ID),
  ANSWER_ID BIGINT REFERENCES ANSWER (ID),
  CREATE_DATE DATE,
  CONSTRAINT QUESTION_ANSWER_PKEY PRIMARY KEY (ID)
);

CREATE TABLE SUBJECT (
  ID BIGINT DEFAULT NEXTVAL('SUBJECT_ID_SEQ'),
  NAME VARCHAR(20),
  DESCRIPTIO VARCHAR(400),
  CREATOR_ID BIGINT REFERENCES USERS (ID),
  CREATE_DATE DATE,
  TEACHER_ID BIGINT REFERENCES USERS (ID),
  CONSTRAINT SUBJECT_PKEY PRIMARY KEY (ID)
);

CREATE TABLE TEST (
  ID BIGINT DEFAULT NEXTVAL('TEST_ID_SEQ'),
  USER_GROUP_ID BIGINT REFERENCES USER_GROUP (ID),
  USER_ID BIGINT REFERENCES USERS (ID),
  CREATE_DATE DATE,
  NAME VARCHAR(20),
  DESCRIPTION VARCHAR(60),
  SUBJECT_ID BIGINT REFERENCES SUBJECT (ID),
  CONSTRAINT TEST_PKEY PRIMARY KEY (ID)
);

CREATE TABLE TEST_QUESTION (
  TEST_ID BIGINT REFERENCES TEST (ID),
  QUESTION_ID BIGINT REFERENCES QUESTION (ID)
);

CREATE TABLE SUMMARY (
  ID BIGINT DEFAULT NEXTVAL('SUMMARY_ID_SEQ'),
  USER_ID BIGINT REFERENCES USERS (ID),
  DEGREE VARCHAR(15),
  TEST_ID BIGINT REFERENCES  TEST (ID),
  CREATE_DATE DATE,
  CONSTRAINT SUMMARY_PKEY PRIMARY KEY (ID)
);

CREATE TABLE COMMENT (
  ID BIGINT DEFAULT NEXTVAL('COMMENT_ID_SEQ'),
  USER_ID BIGINT REFERENCES USERS (ID),
  QUESTION_ID BIGINT REFERENCES QUESTION (ID),
  QUESTION_ANSWER_ID BIGINT REFERENCES QUESTION_ANSWER (ID),
  CONTENT VARCHAR(400),
  CREATE_DATE DATE,
  TITLE VARCHAR(100),
  SUMMARY_ID BIGINT REFERENCES SUMMARY (ID),
  CONSTRAINT COMMENT_PKEY PRIMARY KEY (ID)
);






