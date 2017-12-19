package com.app.service;

import java.util.List;

import com.app.models.InsurancePlan;

public interface IInsurancePlanService {
	public void createPlan(InsurancePlan insurancePlan);
	public void deletePlan(int id);
	public void updatePlan(InsurancePlan plan);
	public InsurancePlan getInsurancePlanById( int id);
	public List<InsurancePlan> getAllInsurancePlan();
}
