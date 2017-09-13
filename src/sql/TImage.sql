drop table T_Image;
create table T_Image(
	id				number(19),
	fileNo			varchar2(50),		--文档UUID
	image_name		varchar2(100),		--文件名
	image_path		varchar2(250),
    constraint pk_T_Image primary key(id)
);

CREATE SEQUENCE T_Image_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;