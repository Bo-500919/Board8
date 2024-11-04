-- 메뉴 목록
CREATE  TABLE   MENUS (
     MENU_ID     VARCHAR2(6)   PRIMARY KEY
    ,MENU_NAME   VARCHAR2(100)
    ,MENU_SEQ    NUMBER(5)
);

INSERT  INTO   MENUS  VALUES ('MENU01', 'JAVA', 1 );
COMMIT;

-- 회원정보
CREATE  TABLE  TUSER (
      USERID      VARCHAR2(12)   PRIMARY KEY
    , PASSWD      VARCHAR2(12)   NOT NULL
    , USERNAME    VARCHAR2(30)   NOT NULL
    , EMAIL       VARCHAR2(320)   
    , UPOINT      NUMBER(9)      DEFAULT  1000
    , REGDATE     DATE           DEFAULT  SYSDATE
) 

-- 멀티 게시판 
DROP    TABLE  BOARD;
CREATE  TABLE  BOARD (
     IDX          NUMBER(8, 0)         PRIMARY KEY
   , MENU_ID      VARCHAR2(6)
      REFERENCES  MENUS ( MENU_ID )
   , TITLE        VARCHAR2(300 BYTE)   NOT NULL
   , CONTENT      VARCHAR2(4000 BYTE)
   , WRITER       VARCHAR2(12)
   , REGDATE      DATE                 DEFAULT  SYSDATE
   , HIT          NUMBER(9, 0)         DEFAULT  0
);

INSERT INTO BOARD (IDX, MENU_ID, TITLE, CONTENT, WRITER)
 VALUES (
   ( SELECT NVL(MAX(IDX),0)+1 FROM BOARD  )
   , 'MENU01', '반갑습니다', '인사드립니다', 'ADMIN');
COMMIT;   








