drop table T_FILE;
create table T_FILE(
	id				number(19),
	document_UUID			varchar2(36),		--文档UUID
	file_name		varchar2(100),		--文件名
	file_size		number(10),		--文件大小
	file_path		varchar2(250),
    constraint pk_T_FILE primary key(id)
);

CREATE SEQUENCE T_FILE_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;