--�������ݿ�
create database salaryManager
go

--ʹ��salaryManagerΪ��ǰ���ݿ�
use salaryManager
go

--�������ʱ�
create table salary
(
paydate char(6) not null,		--����������Ϣ
sid char(8) not null,			--Ա����
sname varchar(20) not null,		--Ա������
gongzi  decimal(9,2) not null,	--��������
jintie  decimal(9,2) null		--����
)
go

--������¼ϵͳʱ�ĵ�¼��Ϣ��
create table loginInfo
(
uid varchar(20) not null,	--��¼��
upw varchar(20) not null	--����
)
go

insert into loginInfo values('hdu','123')
insert into salary values('201501','95001','p1',3000,2500)
insert into salary values('201501','95002','p2',2000,1000)
insert into salary values('201501','95003','p3',3300,3000)
insert into salary values('201501','95004','p4',2400,1500)
insert into salary values('201501','95005','p5',2800,1500)
insert into salary values('201502','95001','p1',3000,1500)


--������ѯ��䣬���ο�
select * from salary		--��ѯ���е�Ա������
select * from salary where sid = '95001'		--��ѯĳλԱ���Ĺ���
select * from salary where paydate between '201501' and '201502'  --��ѯ201501��201502�ڼ��Ա������

update logininfo set upw = '321' where upw = '123'  --��ԭ����123��Ϊ321
update salary set jintie = 3000 where sid = '95001'	--��Ա������Ϊ95001��Ա��������Ϊ3000
	
delete from salary where sid = '95001'	--��Ա������Ϊ95001��Ա����Ϣɾ��

insert salary values('201504','95001','p1',3500,1200)  --��һ�����ʼ�¼�������ݿ�