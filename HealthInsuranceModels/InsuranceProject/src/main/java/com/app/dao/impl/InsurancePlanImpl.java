package com.app.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.IInsurancePlanDao;
import com.app.models.Customer;
import com.app.models.InsurancePlan;
@Repository("insurancePlanDao")
public class InsurancePlanImpl implements IInsurancePlanDao {
	
	@Autowired
	SessionFactory factory;
	
	
	@Override
	public void createPlan(InsurancePlan insurancePlan) {
	Session session = null;
	Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(insurancePlan);
			tx.commit();
			session.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
	}

	@Override
	public void deletePlan(int id) {
		Session session = null;
		Transaction tx = null;
		String sql = "from insurancePlan where id = "+id;
		Criteria criteria = null;
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				criteria = session.createCriteria(InsurancePlan.class, sql);
				InsurancePlan plan = (InsurancePlan) criteria.uniqueResult();
				session.delete(plan);
				tx.commit();
				session.flush();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				session.close();
			}
	} 

	@Override
	public void updatePlan(InsurancePlan plan) {
		Session session = null;
		Transaction tx = null;
			try {
				session = factory.openSession();
				tx = session.beginTransaction();
				InsurancePlan pl = (InsurancePlan) session.get(InsurancePlan.class, plan.getId());
				session.update(pl);
				tx.commit();
				session.flush();
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				session.close();
			}

	}

	@Override
	public InsurancePlan getInsurancePlanById(int id) {
		Session session = null;
		Transaction tx = null;;
		InsurancePlan plan = null;
		String sql = "from InsurancePlan where id= :id";
		try {
			session = factory.openSession();
			tx= session.beginTransaction();
			Query query= session.createQuery(sql);
			query.setParameter("id", id);
			plan = (InsurancePlan) query.uniqueResult();
			tx.commit();
			session.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return plan;
	}

	@Override
	public List<InsurancePlan> getAllInsurancePlan() {
		Session session = null;
		Transaction tx = null;
		Criteria criteria = null;
		List<InsurancePlan> list = null;
		String sql = "from InsurancePlan";
		try {
			session = factory.openSession();
			tx= session.beginTransaction();
			criteria = session.createCriteria(InsurancePlan.class, sql);
			list = criteria.list();
			tx.commit();
			session.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}

		return list;
	}

}
