-- -----------------------------------------------------
-- Table `MEMBER`
-- -----------------------------------------------------
DROP TABLE MEMBER;

CREATE SEQUENCE seq_member;
CREATE TABLE MEMBER (
  no NUMBER,
  id VARCHAR2(45) UNIQUE NOT NULL,
  email VARCHAR2(45) UNIQUE NOT NULL,
  password VARCHAR2(45) NOT NULL,
  name VARCHAR2(45) NOT NULL,
  nickname VARCHAR2(45) UNIQUE NOT NULL,
  loc VARCHAR2(4000) NOT NULL,
  PRIMARY KEY (no)
);

ALTER TABLE member
ADD manager NUMBER(1) DEFAULT 0 NOT NULL
ADD CONSTRAINT member_manager_ck CHECK (manager IN (0, 1));

-- -----------------------------------------------------
-- Table `MOUNTAIN`
-- -----------------------------------------------------
DROP TABLE MOUNTAIN;

CREATE SEQUENCE seq_mountain;
CREATE TABLE MOUNTAIN (
  no NUMBER,
  mName VARCHAR2(45) NOT NULL UNIQUE,
  mLoc VARCHAR2(200) NOT NULL,
  height NUMBER DEFAULT 0 NOT NULL,
  status NUMBER(1) DEFAULT 0 NOT NULL,
  description VARCHAR2(5000) NOT NULL,
  PRIMARY KEY (no),
  CONSTRAINT mountain_status_ck CHECK (status IN (0,1))
);

-- for image upload
ALTER TABLE mountain
ADD filename VARCHAR2(200);

-- -----------------------------------------------------
-- Table `FREEBOARD`
-- -----------------------------------------------------
DROP TABLE FREEBOARD;

CREATE SEQUENCE seq_freeboard;
CREATE TABLE FREEBOARD (
  no NUMBER,
  title VARCHAR2(100) NOT NULL,
  content VARCHAR2(4000) NOT NULL,
  regdate DATE DEFAULT sysdate NOT NULL,
  cnt NUMBER DEFAULT 0 NOT NULL,
  member_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);

CREATE INDEX pk_freeboard ON Freeboard(no);

ALTER TABLE freeboard
ADD user_nickname VARCHAR2(45);

-- -----------------------------------------------------
-- Table `COURSE`
-- -----------------------------------------------------
DROP TABLE COURSE;

CREATE SEQUENCE seq_course;
CREATE TABLE COURSE (
  no NUMBER,
  difficulty VARCHAR2(45) NOT NULL,
  time VARCHAR2(45) NOT NULL, -- form: __H __M
  points VARCHAR2(200) NOT NULL,
  mountain_no NUMBER NOT NULL,
  PRIMARY KEY (no),
  CONSTRAINT course_level_ck CHECK (difficulty IN ('low', 'medium', 'high'))
 );
 
 -----> 수정
CREATE TABLE COURSE (
  no NUMBER,
  course VARCHAR2(500) NOT NULL,
  coursename VARCHAR2(100) NOT NULL,
  difficulty VARCHAR2(45) NOT NULL,
  time VARCHAR2(45) NOT NULL, -- form: __H __M
  mountain_no NUMBER NOT NULL,
  PRIMARY KEY (no),
  CONSTRAINT course_level_ck CHECK (difficulty IN ('low', 'medium', 'high'))
 );

-- -----------------------------------------------------
-- Table `WISH`
-- -----------------------------------------------------
DROP TABLE WISH;

CREATE SEQUENCE seq_wish;
CREATE TABLE WISH (
  no NUMBER,
  member_no NUMBER NOT NULL,
  mountain_no NUMBER NOT NULL,
  PRIMARY KEY (no)
 );


-- -----------------------------------------------------
-- Table `NOTICE`
-- -----------------------------------------------------
DROP TABLE NOTICE ;

CREATE SEQUENCE seq_notice;
CREATE TABLE NOTICE (
  no NUMBER,
  title VARCHAR2(100) NOT NULL,
  content VARCHAR2(4000) NOT NULL,
  regdate DATE DEFAULT sysdate NOT NULL,
  cnt NUMBER DEFAULT 0 NOT NULL,
  member_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);

ALTER TABLE notice 
ADD category VHARCHAR2(45) DEFAULT 'notice' NOT NULL
ADD CONSTRAINT notice_category_ck CHECK (category IN ('notice','event'));

CREATE VIEW noticeInfo 
AS
SELECT n.no, n.category, n.title, n.content, n.regdate, n.cnt, 
	   n.member_no, m.nickname, n.reply, n.replycnt
FROM notice n, member m
WHERE n.member_no = m.no;

-- 댓글 가능 여부
ALTER TABLE notice
ADD reply NUMBER(1) DEFAULT 1 NOT NULL CHECK (reply IN (0, 1));

-- 댓글 개수
ALTER TABLE notice
ADD replycnt NUMBER DEFAULT 0 NOT NULL;


-- -----------------------------------------------------
-- Table `FREPLY`
-- -----------------------------------------------------
DROP TABLE FREPLY;

CREATE SEQUENCE seq_freply;
CREATE TABLE FREPLY (
  no NUMBER,
  reply VARCHAR2(500) NOT NULL,
  replyer VARCHAR2(45) NOT NULL,
  regdate DATE DEFAULT sysdate NOT NULL,
  board_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);


-- -----------------------------------------------------
-- Table `NREPLY`
-- -----------------------------------------------------
DROP TABLE NREPLY;

CREATE SEQUENCE seq_nreply;
CREATE TABLE NREPLY (
  no NUMBER,
  reply VARCHAR2(500) NOT NULL,
  regdate DATE DEFAULT sysdate NOT NULL,
  updatedate DATE DEFAULT sysdate NOT NULL,
  notice_no NUMBER NOT NULL,
  member_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);

CREATE VIEW nreplyInfo
AS
SELECT n.no, n.reply, m.nickname replyer, n.regdate, n.updatedate, n.notice_no, n.member_no
FROM nreply n JOIN member m
ON n.member_no = m.no;


-- -----------------------------------------------------
-- Table `FESTIVAL`
-- -----------------------------------------------------
DROP TABLE FESTIVAL;

CREATE SEQUENCE seq_festival;
CREATE TABLE FESTIVAL (
  no NUMBER,
  eName VARCHAR2(45) NOT NULL,
  description VARCHAR2(1000) NOT NULL,
  month NUMBER NOT NULL,
  mountain_no NUMBER NOT NULL,
  filename VARCHAR2(200),
  PRIMARY KEY (no),
  CONSTRAINT festival_month_ck CHECK (month BETWEEN 1 AND 12)
);


-- -----------------------------------------------------
-- Table `RESTAURANT`
-- -----------------------------------------------------
DROP TABLE RESTAURANT;

CREATE SEQUENCE seq_restaurant;
CREATE TABLE RESTAURANT (
  no NUMBER,
  rName VARCHAR2(45) NOT NULL,
  rLoc VARCHAR2(200) NOT NULL,
  contact VARCHAR2(45) NOT NULL,
  menu VARCHAR2(500) NOT NULL,
  description VARCHAR2(1000) NOT NULL,
  mountain_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);

-- likecnt 추가
ALTER TABLE restaurant 
ADD (likecnt NUMBER DEFAULT 0 NOT NULL, dislikecnt NUMBER DEFAULT 0 NOT NULL);

-- like table
CREATE SEQUENCE seq_res_like;
CREATE TABLE res_like(
no NUMBER PRIMARY KEY NOT NULL,
likeno NUMBER(1) DEFAULT 0 NOT NULL,
dislikeno NUMBER(1) DEFAULT 0 NOT NULL,
userno NUMBER NOT NULL,
resno NUMBER NOT NULL
);

-- restaurant view

CREATE VIEW res_view 
AS
SELECT r.no, r.mountain_no, r.rname, r.rloc, r.contact, r.menu, r.description, r.likecnt, r.dislikecnt, m.mname 
FROM restaurant r, mountain m 
WHERE r.mountain_no = m.no;

-- filename 추가
ALTER TABLE restaurant 
ADD (filename VARCHAR2(200));

-- -----------------------------------------------------
-- Table `PLACE`
-- -----------------------------------------------------
DROP TABLE PLACE;

CREATE SEQUENCE seq_place;
CREATE TABLE PLACE (
  no NUMBER,
  eName VARCHAR2(45) NOT NULL,
  description VARCHAR2(1000) NOT NULL,
  pLoc VARCHAR2(200) NOT NULL,
  mounain_no NUMBER NOT NULL,
  PRIMARY KEY (no)
);


-- -----------------------------------------------------
-- Table `CONQUEST`
-- -----------------------------------------------------
DROP TABLE CONQUEST;

CREATE SEQUENCE seq_conquest;
CREATE TABLE CONQUEST (
  no NUMBER,
  member_no NUMBER NOT NULL,
  mountain_no NUMBER NOT NULL,
  conquestcnt NUMBER NOT NULL,
  condate DATE DEFAULT sysdate NOT NULL,
  PRIMARY KEY(no)
);

