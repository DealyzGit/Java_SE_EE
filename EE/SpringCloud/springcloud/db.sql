create database db01;


create table dept
(
    deptno    bigint auto_increment
        primary key,
    dname     varchar(60) null,
    db_source varchar(60) null
)
    comment 'dept table';

insert into dept (dname, db_source) value ('开发部',DATABASE());
insert into dept (dname, db_source) value ('人事部',DATABASE());
insert into dept (dname, db_source) value ('财务部',DATABASE());
insert into dept (dname, db_source) value ('市场部',DATABASE());
insert into dept (dname, db_source) value ('运维部',DATABASE());

select * from dept;

commit ;