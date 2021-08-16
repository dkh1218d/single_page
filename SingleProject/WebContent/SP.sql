create table sp_board(
idx number primary key,
subject varchar2(100),
contents varchar2(2000),
name varchar2(30),
readcnt number default 0,
regdate date default sysdate,
id_no number references sp_member(id_no),
board_div char(1) references sp_board_division(board_div),
answer varchar2(2000),
answer_name varchar2(30),
refer_id varchar2(30),
parent_no number,
photo varchar2(100),
filename varchar2(100),
important char(1),
questions varchar2(45)
);