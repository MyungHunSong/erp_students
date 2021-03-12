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
select * from Title;

-- 검색하는 주가 employee 이니 거기애 잇어야한다.
select empname, empno
	from employee e 
	join 
	title t on e.title = t.tno
	where tno = 5;