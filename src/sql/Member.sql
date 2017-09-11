drop table member;
create table member(
	mid number(10),
	name varchar2(50) default '未命名',
	age number(3),
	birthday date default sysdate,
	note varchar2(250),
	constraint 	 pk_mid primary key(mid)
);

CREATE SEQUENCE member_sequence  INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 10;
