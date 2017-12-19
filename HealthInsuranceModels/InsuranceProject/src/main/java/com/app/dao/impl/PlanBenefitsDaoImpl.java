package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.app.dao.IPlanBenefitsDao;
import com.app.models.InsurancePlan;
import com.app.models.PlanBenefits;
@Repository("planBenefitsDao")
public class PlanBenefitsDaoImpl implements IPlanBenefitsDao {
	
	@Autowired
	private SessionFactory factory;
	
	
	@Override
	public void createPlanBenefit(PlanBenefits planBenefits) {
		Session session = null;
		Transaction tx =null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(planBenefits);
			tx.commit();
			session.flush();
			System.out.println("benefit created");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("benefit could not be created");
		} finally {
			session.close();
		}

	}

	@Override
	public void deletePlanBenefit(PlanBenefits planBenefits) {
		Session session = null;
		Transaction tx =null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.delete(planBenefits);
			tx.commit();
			session.flush();
			System.out.println("benefit deleted");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("benefit could not be deleted");
		} finally {
			session.close();
		}

	}

	@Override
	public void updatePlanBenefit(PlanBenefits planBenefits) {
		Session session = null;
		Transaction tx =null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.update(planBenefits);
			tx.commit();
			session.flush();
			System.out.println("benefit updated");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("benefit could not be updated");
		} finally {
			session.close();
		}

	}

	@Override
	public PlanBenefits getPlanBenefitById(int id) {
		Session session = null;
		Transaction tx =null;
		PlanBenefits planBenefits = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			planBenefits = (PlanBenefits) session.get(PlanBenefits.class, id);
			tx.commit();
			session.flush();
			System.out.println("benefit found");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("benefit could not be found");
		} finally {
			session.close();
		}
		return planBenefits;
	}

	@Override
	public List<PlanBenefits> getAllPlanBenefits() {
		Session session = null;
		Transaction tx =null;
		List<PlanBenefits> list = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery("from PlanBenefits");
			list = query.list();
			tx.commit();
			session.flush();
			System.out.println("list found");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("list could not be found");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public List<PlanBenefits> getPlanBenefitsByPlan(InsurancePlan insurancePlan) {
		Session session = null;
		Transaction tx =null;
		List<PlanBenefits> list = null;
		String sql = "from PlanBenefits where insurancePlan = :insurancePlan";
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			Query query = session.createQuery(sql);
			query.setParameter("insurancePlan", insurancePlan);
			list = query.list();
			tx.commit();
			session.flush();
			System.out.println("list found");
		} catch(Exception ex) {
			ex.printStackTrace();
			System.out.println("list could not be found");
		} finally {
			session.close();
		}
		return list;
	}

	@Override
	public void addPlanBenefitsToPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits) {
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			List<InsurancePlan> list = new ArrayList<InsurancePlan>();
			list.add(insurancePlan);
			planBenefits.setPlan(list);;
			session.saveOrUpdate(planBenefits);
			tx.commit();
			System.out.println("benefit added");
			session.flush();
		} catch(Exception ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		

	}

	@Override
	public void removePlanBenefitsFromPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits) {
		

	}

}
