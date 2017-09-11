DROP TABLE IF EXISTS household_register_catalog;
create table household_register_catalog(
	id		number(19),
    sfile 	varchar2(50) not null,
    sfonds 	varchar2(50),
    sfondsnum varchar2(50),
    sgenus varchar2(50),
    skind varchar2(50),
    syear varchar2(10),
    sdepartment varchar2(50),
    slimit varchar2(50),
    scatalog varchar2(50),
    srecords varchar2(50),
    ssecret varchar2(50),
    sfilename varchar(50),
    sstarttime varchar(50),
    sfinishtime varchar(50),
    snum varchar(10),
    spages varchar(10),
    sname varchar(20),
    sdate varchar(20),
    sremarks varchar(100),
    slocation varchar(50),
	uuid	varchar2(36),
	input_no 	number(1),
	isCompared	number(1),
    constraint pk_household_register_catalog primary key(id)
);
insert into household_register_catalog(id,sfile,sfonds,sfondsnum,sgenus,skind,syear,sdepartment,slimit,
scatalog,srecords,ssecret,sfilename,sstarttime,sfinishtime,snum,spages,sname,sdate,sremarks,slocation)
values(HOUSEHOLD_REGISTER_SEQUENCE.nextval,'abc','abc','abc','abc','abc','abc','abc','abc','abc','abc','abc','abc','abc','abc','abc',
'abc','abc','abc','abc','abc');
comment on column household_register_catalog.sfile is '档号';
comment on column household_register_catalog.sfonds is '全宗单位';
comment on column household_register_catalog.sfondsnum is '全宗号';
comment on column household_register_catalog.sgenus is '属类号';
comment on column household_register_catalog.skind is '类别号';
comment on column household_register_catalog.syear is '归档年度';
comment on column household_register_catalog.sdepartment is '归档部门';
comment on column household_register_catalog.slimit is '保管期限';
comment on column household_register_catalog.scatalog is '目录号';
comment on column household_register_catalog.srecords is '案卷号';
comment on column household_register_catalog.ssecret is '密级';
comment on column household_register_catalog.sfilename is '案卷题名';
comment on column household_register_catalog.sstarttime is '起始时间';
comment on column household_register_catalog.sfinishtime is '截止时间';
comment on column household_register_catalog.snum is '共几件';
comment on column household_register_catalog.spages is '共几页';
comment on column household_register_catalog.sname is '著录人';
comment on column household_register_catalog.sdate is '著录日期';
comment on column household_register_catalog.sremarks is '备注';
comment on column household_register_catalog.slocation is '保存位置';
comment on column household_register_catalog.uuid is 'uuid';
comment on column household_register_catalog.input_no is '第几次录入';
comment on column household_register_catalog.isCompared is '是否比对';


DROP SEQUENCE household_register_sequence;
CREATE SEQUENCE household_register_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;