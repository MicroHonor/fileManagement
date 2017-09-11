create table household_catalog(
	id			number(19),
	file_no			varchar2(50),		--档号
	organization	varchar2(50),		--全宗单位
	archive_no		varchar2(50),		--全宗号
	genus_no		varchar2(50),		--属类号
	kind_no		varchar2(50),			--类别号
	file_year		number(5),			--归档年度
	file_department		varchar2(50),	--归档部门
	retention_period	varchar2(50),	--保管期限
	catalog_no			varchar2(50),	--目录号
	dossier_no			varchar2(50),	--案卷号
	security_level		varchar2(20),	--保密级别
	dossier_title		varchar2(50),	--案卷题名
	begin_time			date,			--起始时间
	end_time			date,			--截止时间
	pieces_count		number(5),		--共几件
	page_count			number(5),		--共几页
	recorder			varchar2(32),	--著录人
	record_date			date,			--著录时间
	remarks				varchar2(255),	--备注
	location			varchar2(100),	--存放位置
	create_time			date,			--录入时间
	update_time			date,			--更新时间
	uuid	varchar2(36)	default sys_guid(),
	input_no 	number(1),
	isCompared	number(1) default 0,
	errors		varchar2(2000)
    constraint pk_household_catalog primary key(id)
);
 alter table household_catalog add (
 	errors	varchar2(2000)
 );
--DROP SEQUENCE household_catalog_sequence;
CREATE SEQUENCE household_catalog_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;


comment on column household_catalog.file_no is '档号';
comment on column household_catalog.organization is '全宗单位';
comment on column household_catalog.archive_no is '全宗号';
comment on column household_catalog.genus_no is '属类号';
comment on column household_catalog.kind_no is '类别号';
comment on column household_catalog.file_year is '归档年度';
comment on column household_catalog.file_department is '归档部门';
comment on column household_catalog.retention_period is '保管期限';
comment on column household_catalog.catalog_no is '目录号';
comment on column household_catalog.dossier_no is '案卷号';
comment on column household_catalog.security_level is '密级';
comment on column household_catalog.dossier_title is '案卷题名';
comment on column household_catalog.begin_time is '起始时间';
comment on column household_catalog.end_time is '截止时间';
comment on column household_catalog.pieces_count is '共几件';
comment on column household_catalog.page_count is '共几页';
comment on column household_catalog.recorder is '著录人';
comment on column household_catalog.record_date is '著录日期';
comment on column household_catalog.remarks is '备注';
comment on column household_catalog.location is '保存位置';
comment on column household_catalog.uuid is 'uuid';
comment on column household_catalog.input_no is '第几次录入';
comment on column household_catalog.isCompared is '是否比对';


insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd0001','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,1,0);

insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd0002','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,1,0);

insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd0003','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,1,0);

insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd1001','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,2,0);

insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd1002','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,2,0);

insert into household_catalog(id,file_no,organization,archive_no,genus_no,kind_no,file_year,file_department,
retention_period,catalog_no,dossier_no,security_level,dossier_title,begin_time,end_time,pieces_count,page_count,
recorder,record_date,remarks,location,create_time,update_time,input_no,iscompared)
values(HOUSEHOLD_CATALOG_SEQUENCE.nextval,'abcd1003','abc123','abc123','abc123','abc123',2017,'depart111',
'fsdfsf','abc123','23233','a','abcsdsd',to_date('2017-12-14','YYYY-MM-DD'),to_date('2037-12-14','YYYY-MM-DD'),
5,20,'recorder11',to_date('2017-12-14','YYYY-MM-DD'),'abc','abc',sysdate,sysdate,2,0);

commit;