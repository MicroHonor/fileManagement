
drop table t_user;
create table t_user(
  id    number(19) ,   
  name  VARCHAR(50) ,
  age   int ,
  sex   VARCHAR(10) ,
  email   VARCHAR(50) ,
  birthday  DATE ,
  deposit   long,
  isIT    int,
  constraint pk_id primary key(id)
);
insert into t_user(id,name,age,sex,email,deposit,isIT) values(1,'张三',20,'男','123@sina.com',200,1);
insert into t_user(id,name,age,sex,email,deposit,isIT) values(2,'李四',20,'男','123@sina.com',200,1);
insert into t_user(id,name,age,sex,email,deposit,isIT) values(3,'王五',20,'男','123@sina.com',200,1);
commit;