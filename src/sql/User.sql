drop table t_user;
create table t_user(
  id    number(19) , 
  name	VARCHAR2(32),  
  username  VARCHAR2(32) ,
  password   VARCHAR2(32) ,
  order_type	number(1),
  constraint pk_id primary key(id)
);
CREATE SEQUENCE t_user_sequence  
INCREMENT BY 1   -- 每次加几个 
START WITH 1     -- 从1开始计数
NOMAXVALUE       -- 不设置最大值
NOCYCLE          -- 一直累加，不循环
CACHE 20;
insert into t_user(id,name,username,password,order_type) values(t_user_sequence.nextval,'张三','zhangsan','E10ADC3949BA59ABBE56E057F20F883E',1);
insert into t_user(id,name,username,password,order_type) values(t_user_sequence.nextval,'李四','lisi','E10ADC3949BA59ABBE56E057F20F883E',2);
commit;