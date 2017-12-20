package com.app.dao;

import java.util.List;

import com.app.models.InsurancePlan;
import com.app.models.PlanBenefits;

public interface IPlanBenefitsDao {
	public void createPlanBenefit(PlanBenefits planBenefits);
	public void deletePlanBenefit(PlanBenefits planBenefits);
	public void updatePlanBenefit(PlanBenefits planBenefits);
	public PlanBenefits getPlanBenefitById(int id);
	public List<PlanBenefits> getAllPlanBenefits();
	public List<PlanBenefits> getPlanBenefitsByPlan(InsurancePlan insurancePlan);
	public void addPlanBenefitsToPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits);
	public void removePlanBenefitsFromPlan(InsurancePlan insurancePlan, PlanBenefits planBenefits);
}
