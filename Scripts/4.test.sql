create or replace view vw_full_employee
as
select e.empno,
	   e.empname, 
	   t.tno as title_no,
	   t.tname as title_name,
	   e.manager as manager_no,
	   m.empname as manager_name,
	   e.salary,
	   d.deptNo, 
	   d.deptName,
	   floor
	from employee e join title t on e.title = t.tno
		 left join employee m on e.manager  = m.empno 
		join department d on e.dept  = d.deptNo;
		
-- 3/12 해당 직책을 가지고 있는 사원목록 검색
select * from employee;

-- 검색하는 주가 employee 이니 거기애 잇어야한다.
select empname, empno
	from employee e 
	join 
	title t on e.title = t.tno
	where tno = 5;
	
select *
	from employee
	where empno = 1003;
	
select empno, empname 
	from employee e join department d on e.dept = d.deptNo
	where deptno = 2;

-- password 길이 확인 (해쉬 함수를 저곳에 넣어주면 숫자로 바꿔준다 ) -- 해쉬 함수는 단방향 함수이다 --
-- 단방향 함수(Hash:MDS)
select password('*A02AA727CF2E8C5E6F07A382910C4028D65A053A')
	, length(password('*A02AA727CF2E8C5E6F07A382910C4028D65A053A')) from dual;

-- 길이비교
select passwrod('1234')

-- 
insert into erp_detail(empno, pic, gendel, startDate, pass) values(?,?,?,?,?);

select * from erp_detail; -- 이미지 파일없으면 null 뜬다.

delete from erp_detail where empno = 1365;
--  

select empno, pic, gendel, startDate,pass from erp_detail where empno = 1003;

update erp_detail  set empno=1004, pic= 1, gendel = '여', startDate = 20200308, pass = 1111  where empno = 1003;

select * from title;

select empname, empno from vw_full_employee where title_no = 2;

select empname, empno from employee e join title t on e.title  = t.tno where title_no = 2;