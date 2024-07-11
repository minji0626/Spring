-- 회원 관리
create table spmember(
mem_num number not null,
id varchar2(12) unique not null,
nick_name varchar2(30),
auth number(1) default 2 not null, -- 0 탈퇴, 1 정지, 2 일반, 9 관리자
constraint spmember_pk primary key(mem_num)
);

create table spmember_detail(
mem_num number not null,
au_id varchar2(36) unique, -- 자동 로그인에 사용되는 식별값
name varchar2(30) not null,
passwd varchar2(35) not null,
phone varchar2(15) not null,
email varchar2(50) not null,
zipcode varchar2(5) not null,
address1 varchar2(90) not null,
address2 varchar2(90) not null,
photo blob,
photo_name varchar2(100),
reg_date date default sysdate not null,
modify_date date,
constraint spmember_detail_pk primary key (mem_num),
constraint spmember_detail_fk foreign key (mem_num) references spmember (mem_num)
);

create sequence spmember_seq;

-- 게시판
create table spboard(
board_num number not null,
category char(1) not null,
title varchar2(90) not null,
content clob not null,
hit number(8) default 0 not null,
reg_date date default sysdate not null,
modify_date date,
filename varchar2(400),
ip varchar2(40) not null,
mem_num number not null,
constraint spboard_pk primary key (board_num),
constraint spboard_fk foreign key (mem_num) references spmember(mem_num)
);

create sequence spboard_seq;


-- 게시판 좋아요
create table spboard_fav(
board_num number not null,
mem_num number not null,
constraint spboard_fav_fk1 foreign key (board_num) references spboard (board_num)
constraint spboard_fav_fk2 foreign key (mem_num) references spmember (mem_num)
);

-- 댓글 테이블
create table spboard_reply(
re_num number not null,
re_content varchar2(900) not null,
re_date date default sysdate not null,
re_mdate date,
re_ip varchar2(40) not null,
board_num number not null,
mem_num number not null,
constraint spboard_reply_pk primary key (re_num),
constraint reply_spboard_fk1 foreign key (board_num) references spboard (board_num),
constraint reply_spboard_fk2 foreign key (mem_num) references spmember (mem_num)
);

create sequence spboard_reply_seq;


-- 댓글 좋아요
create table spreply_fav(
re_num number not null,
mem_num number not null,
constraint refav_fk1 foreign key (re_num) references spboard_reply (re_num),
constraint refav_fk2 foreign key (mem_num) references spmember (mem_num)
);

-- 대댓글 달기
create table spboard_response(
te_num number not null,
te_content varchar2(900) not null,
te_date date default sysdate not null,
te_mdate date,
te_parent_num number not null, -- 부모글의 번호가 들어간다. 자식글이 아니라 부모글의 경우 0을 넣어준다.
te_depth number not null, -- 자식글의 깊이. 부모글의 자식글A 1, 자식글 A의 자식글 B 2, 부모글일 경우 0
te_ip varchar2(40) not null,
re_num number not null,
mem_num number not null,
constraint spboard_treply_pk primary key (te_num),
constraint treply_reply_fk1 foreign key(re_num) references spboard_reply (re_num),
constraint treply_spmember_fk2 foreign key(mem_num) references spmember(mem_num)
);
create sequence response_seq;


-- 그룹채팅
create table sptalkroom(
  talkroom_num number not null,
  basic_name varchar2(900) not null, -- 채팅 멤버를 추가할 때 채팅방 이름를 basic_name에서 가져다 씀
  talkroom_date date default sysdate not null,
  constraint sptalkroom_pk primary key (talkroom_num)
);

create sequence sptalkroom_seq;

create table sptalk_member(
  talkroom_num number not null,
  mem_num number not null,
  room_name varchar2(900) not null,
  member_date date default sysdate not null, 
  constraint sptalkmember_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
  constraint sptalkmember_fk2 foreign key (mem_num) references spmember (mem_num)
);

create table sptalk(
  talk_num number not null,
  talkroom_num number not null,  -- 수신그룹
  mem_num number not null, -- 발신자
  message varchar2(4000) not null,
  chat_date date default sysdate not null,
  constraint sptalk_pk primary key (talk_num),
  constraint sptalk_fk1 foreign key (talkroom_num) references sptalkroom (talkroom_num),
  constraint sptalk_fk2 foreign key (mem_num) references spmember (mem_num)
);


create sequence sptalk_seq;

create table sptalk_read(
  talkroom_num number not null,
  talk_num number not null,
  mem_num number not null,
  constraint read_fk foreign key (talkroom_num) references sptalkroom (talkroom_num),
  constraint read_fk2 foreign key (talk_num) references sptalk (talk_num),
  constraint read_fk3 foreign key (mem_num) references spmember (mem_num)
);















