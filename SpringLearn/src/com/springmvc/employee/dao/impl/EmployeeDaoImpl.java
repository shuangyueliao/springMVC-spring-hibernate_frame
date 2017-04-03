package com.springmvc.employee.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Component;

import com.springmvc.employee.dao.EmployeeDao;
import com.springmvc.entity.Employee;

@Component
public class EmployeeDaoImpl implements EmployeeDao {
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	@Resource
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Employee findByUsernameAndPassword(Employee employee) {
		String hql="from Employee where username=? and password=?";
		Query query=sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, employee.getUsername());
		query.setString(1, employee.getPassword());
		List<Employee>list=query.list();
		if(list.size()>0){
			return list.get(0);
		}
		return null;
	}

	@Override
	public int findCount() {
		String hql="select count(*) from Employee";
		List<Long> list=sessionFactory.getCurrentSession().createQuery(hql).list();
		if(list.size()>0){
			return list.get(0).intValue();
		}
		return 0;
	}

	@Override
	public List<Employee> findByPage(int begin, int pageSize) {
		Query query=sessionFactory.getCurrentSession().createQuery("from Employee");
		query.setFirstResult(begin);
		query.setMaxResults(3);
		List list=query.list();
		return list;
	}

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().save(employee);
	}

	@Override
	public Employee findById(Integer eid) {
		return (Employee) sessionFactory.getCurrentSession().get(Employee.class, eid);
	}

	@Override
	public void update(Employee employee) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(employee);
		
	}

	@Override
	public void delete(Employee employee) {
		// TODO Auto-generated method stub
		Session s=sessionFactory.getCurrentSession();
		s.delete(employee);
		s.flush();
	}
	@Override
	public List<Employee> getAllEmployee() {
		// TODO Auto-generated method stub
		List<Employee> list=sessionFactory.getCurrentSession().createQuery("from Employee").list();
		return list;
	}
}
