package com.angular.rest.test.database;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.JDBCConnectionException;

import com.angular.rest.test.model.Employee;

public class EmployeeDao {

	static Configuration config;
	static SessionFactory sf;
	static Session s;

	static Log logger;

	static {

		try {
			Class.forName("oracle.jdbc.OracleDriver");
			config = new Configuration().configure("hib-config.xml");
			sf = config.buildSessionFactory();
			logger = LogFactory.getLog(EmployeeDao.class);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public List<Employee> getAllEmployees() {
		try {

			s = sf.openSession();
			logger.info("New Session created");
			Transaction t = s.beginTransaction();
			logger.info("Transaction Begined");
			Criteria c = s.createCriteria(Employee.class);
			List<Employee> employees = c.list();
			logger.info("Returning Employees List");
			t.commit();
			s.close();
			return employees;
		} catch (JDBCConnectionException e) {
			e.getMessage();
			e.printStackTrace();
			return null;
		}

	}

	public Employee getEmployee(int eNo) {
		s = sf.openSession();
		logger.info("New Session created");
		s.beginTransaction();
		logger.info("Transaction Begined");
		Criteria c = s.createCriteria(Employee.class);
		Employee e = (Employee) c.add(Restrictions.eq("id", eNo)).uniqueResult();
		logger.info("Closing session and returning Employee");
		s.close();
		return e;
	}

	public Employee checkCredentials(String uname) {
		s = sf.openSession();
		logger.info("New Session created");
		s.beginTransaction();
		logger.info("Transaction Begined");
		Criteria c = s.createCriteria(Employee.class);
		Employee e = (Employee) c.add(Restrictions.like("uname", uname)).uniqueResult();
		if (!e.equals(null)) {
			logger.info("Data pulled using username");
			s.close();
			return e;
		} else {
			logger.error("Something went wrong while checking credentials");
			s.close();
			return null;
		}
	}

	public boolean addEmployee(Employee employee) {
		s = sf.openSession();
		logger.info("New Session created");
		Transaction t = s.beginTransaction();
		logger.info("Transaction Begined");
		int i = (int) s.save(employee);
		if (i > 0) {
			logger.info("Employee " + employee.getName() + " details inserted succesfully");
			t.commit();
			logger.info("Persisting the transaction");
			return true;
		} else {
			logger.error("Employee insertion failed");
			logger.error("Closing the transaction");
			t.rollback();
			return false;
		}
	}

	public boolean deleteEmployee(int eNo) {
		s = sf.openSession();
		logger.info("New Session created");
		Transaction t = s.beginTransaction();
		logger.info("Transaction Begined");
		Employee e = (Employee) s.createCriteria(Employee.class).add(Restrictions.eq("id", eNo)).uniqueResult();
		if (e.equals(null)) {
			logger.info("Employee " + e.getName() + " details deleted failed");
			t.rollback();
			logger.info("rollbacking the transaction");
			return false;
		}
		s.delete(e);
		logger.info("Employee " + e.getName() + " details deleted succesfully");
		t.commit();
		logger.info("Persisting the transaction");
		return true;

	}

	public boolean updateEmployee(Employee employee) {
		s = sf.openSession();
		logger.info("New Session created");
		Transaction t = s.beginTransaction();
		logger.info("Transaction Begined");
		Employee e = (Employee) s.createCriteria(Employee.class).add(Restrictions.eq("name", employee.getName()))
				.uniqueResult();
		t.commit();
		s.close();

		s = sf.openSession();
		t = s.beginTransaction();
		logger.info("e " + e);
		employee.setId(e.getId());
		logger.info("employee: " + employee);
		try {
			logger.info("Employee " + e.getName() + " details updated successfully");
			s.update(employee);
			logger.info("persisting the transaction");
			t.commit();
			s.close();
			return true;
		} catch (HibernateException excep) {
			logger.info("Employee " + e.getName() + " details updation failed due to HibernateException");
			t.rollback();
			logger.info("rollbacking the transaction");
			excep.printStackTrace();
			s.close();
			return false;
		}
	}

}
