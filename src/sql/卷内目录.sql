drop table inner_catalog;
create table inner_catalog(
	id  		number(19),		--id
	fileNo		varchar2(50),	--档号
	content		clob,			--内容
	inputNo		number(1),
	isCompared	number(1),
	errors		varchar2(4000),	
	images_count	number(5),	
	constraint inner_catalog primary key(id)
);
alter table inner_catalog add (
 	errors			varchar2(4000)
);
alter table inner_catalog add (
 	images_count	number(5)
);
CREATE SEQUENCE inner_catalog_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;