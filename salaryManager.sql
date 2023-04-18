--创建数据库
create database salaryManager
go

--使用salaryManager为当前数据库
use salaryManager
go

--创建工资表
create table salary
(
paydate char(6) not null,		--工资年月信息
sid char(8) not null,			--员工号
sname varchar(20) not null,		--员工姓名
gongzi  decimal(9,2) not null,	--基本工资
jintie  decimal(9,2) null		--津贴
)
go

--创建登录系统时的登录信息表
create table loginInfo
(
uid varchar(20) not null,	--登录名
upw varchar(20) not null	--密码
)
go

insert into loginInfo values('hdu','123')
insert into salary values('201501','95001','p1',3000,2500)
insert into salary values('201501','95002','p2',2000,1000)
insert into salary values('201501','95003','p3',3300,3000)
insert into salary values('201501','95004','p4',2400,1500)
insert into salary values('201501','95005','p5',2800,1500)
insert into salary values('201502','95001','p1',3000,1500)


--基本查询语句，供参考
select * from salary		--查询所有的员工工资
select * from salary where sid = '95001'		--查询某位员工的工资
select * from salary where paydate between '201501' and '201502'  --查询201501到201502期间的员工工资

update logininfo set upw = '321' where upw = '123'  --将原密码123改为321
update salary set jintie = 3000 where sid = '95001'	--将员工工号为95001的员工津贴改为3000
	
delete from salary where sid = '95001'	--将员工工号为95001的员工信息删除

insert salary values('201504','95001','p1',3500,1200)  --将一条工资记录插入数据库