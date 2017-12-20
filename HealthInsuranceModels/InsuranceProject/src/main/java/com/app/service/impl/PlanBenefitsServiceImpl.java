package com.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dao.IPlanBenefitsDao;
import com.app.models.InsurancePlan;
import com.app.models.PlanBenefits;
import com.app.service.IPlanBenefitsService;
@Service("planBenefitsService")
public class PlanBenefitsServiceImpl implements IPlanBenefitsService {

	@Autowired
	private IPlanBenefitsDao planBenefitsDao;
	@Override
	public void createPlanBenefit(PlanBenefits planBenefits) {
		try {
			planBenefitsDao.createPlanBenefit(planBenefits);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public void deletePlanBenefit(PlanBenefits planBenefits) {
		try {
			planBenefitsDao.deletePlanBenefit(planBenefits);;
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void updatePlanBenefit(PlanBenefits planBenefits) {
		try {
			planBenefitsDao.updatePlanBenefit(planBenefits);
		} catch(Exception ex) {
			ex.printStackTrace();
		}

	}

	@Override
	public PlanBenefits getPlanBenefitById(int id) {
		PlanBenefits planBenefits = null;
		try {
			planBenefits =planBenefitsDao.getPlanBenefitById(id);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return planBenefits;
	}

	@Override
	public List<PlanBenefits> getAllPlanBenefits() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<PlanBenefits> getPlanBenefitsByPlan(InsurancePlan insurancePlan) {
		List<PlanBenefits> list = null;
		try {
			list =planBenefitsDao.getPlanBenefitsByPlan(insurancePlan);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		return list;
	}

	@Override
	public void addPlanBenefitsToPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits) {
		try {
			planBenefitsDao.addPlanBenefitsToPlan(insurancePlan, planBenefits);
		} catch(Exception ex){
			ex.printStackTrace();
		}

	}

	@Override
	public void removePlanBenefitsFromPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits) {
		// TODO Auto-generated method stub

	}

}
