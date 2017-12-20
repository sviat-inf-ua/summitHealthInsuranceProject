package com.app.controller;

<<<<<<< HEAD
import java.util.Map;

=======
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
<<<<<<< HEAD
=======
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HttpServletBean;
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.ICustomerDao;
import com.app.models.Customer;
<<<<<<< HEAD
import com.app.service.ICustomerService;
=======
import com.app.models.InsurancePlan;
import com.app.models.PlanBenefits;
import com.app.service.ICustomerService;
import com.app.service.IInsurancePlanService;
import com.app.service.IPlanBenefitsService;
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
import com.app.validators.UserValidator;

@Controller
public class customerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
<<<<<<< HEAD
	private UserValidator customerValidator; 
	
=======
	private IInsurancePlanService insurancePlanService; 
	
	@Autowired
	private IPlanBenefitsService planBenefitsService;
	
	private HttpSession session;
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
	@RequestMapping(value="registerUser", method=RequestMethod.GET)
	public String register(ModelMap model) {
		model.addAttribute("customer", new Customer());
		return "registerUser";
//		return  new ModelAndView("registerUser", "customer", new Customer());
	}
	@RequestMapping(value="create", method=RequestMethod.POST)
	public ModelAndView create(@ModelAttribute("customer")@Validated Customer customer, BindingResult bindingResult) {
//		ModelAndView model = new ModelAndView();
		
		if (bindingResult.hasErrors()) {
			return new ModelAndView("registerUser");
		} else {
//		
		System.out.println(customer);
		customerService.createCustomer(customer);
		
		return new ModelAndView("login", "msg", "welcome \n you need to login now");
		}
	}
	
	@RequestMapping(value="dologin", method=RequestMethod.POST)
<<<<<<< HEAD
	public ModelAndView login(@ModelAttribute("customer")Customer customer) {
=======
	public ModelAndView login(@ModelAttribute("customer")Customer customer,HttpServletRequest request ) {
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
		Customer cus = customerService.getCustomerByEmail(customer.getEmail());
		ModelAndView model = new ModelAndView();
		if(cus!=null) {
			if(cus.getPswd().equals(customer.getPswd())) {
<<<<<<< HEAD
				model.setViewName("home");
				model.addObject("customer", cus);
=======
				session = request.getSession();
				session.setAttribute("userName", cus.getEmail());
				model.setViewName("home");
				model.addObject("customer", cus);
				System.out.println(cus.getPswd() + " " + customer.getPswd());
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
			} else {
				model.setViewName("login");
				model.addObject("msg", "Username or password invalid");
			}
		} else {
			model.addObject("msg", "enter a valid username");
			model.setViewName("login");
		}
		return model;
	}
	@RequestMapping("login")
	public String login() {
		return "login";
	}
<<<<<<< HEAD
=======
	@RequestMapping("showall") 
	public ModelAndView showall() {
		
		List<Customer> list = null;
		if(session.getAttribute("userName") == null) {
			return new ModelAndView("login", "msg", "You need to login first");
		} else {
			list = customerService.getAllCustomers();
			System.out.println(list);
			return new ModelAndView("home", "msg", list);
		}
	}
	@RequestMapping("plan")
	public String plan() {
		return "plan";
	}
	
	@RequestMapping(value="createPlan", method=RequestMethod.POST)
	public ModelAndView createPlan(@ModelAttribute ("insurancePlan") InsurancePlan plan) {
		insurancePlanService.createPlan(plan);
		return new ModelAndView("plan", "msg", "plan was created");
	}
	@RequestMapping("apply")
	public ModelAndView apply(ModelMap model) {
		if (session != null){
		model.addAttribute("insurancePlan", new InsurancePlan());
		List<InsurancePlan> list= insurancePlanService.getAllInsurancePlan();
		return new ModelAndView("apply", "list", list);
		} else {
			return new ModelAndView("login", "msg", "You need to login first");
		}
		
	}
	@RequestMapping(value="doapply", method=RequestMethod.POST)
	public ModelAndView doapply(@ModelAttribute("insurancePlan") InsurancePlan plan, BindingResult bindingResult) {
		System.out.println(plan.getId());
		InsurancePlan iplan = insurancePlanService.getInsurancePlanById(plan.getId());
		Customer customer = customerService.getCustomerByEmail((String) session.getAttribute("userName"));
		System.out.println(customer.getEmail());
		customerService.addPlan(iplan, customer);
		
		return new ModelAndView("apply", "msg", "the plan was added");
	}
	@RequestMapping(value="benefits")
	public String benefits(ModelMap model) {
		List<InsurancePlan> list= insurancePlanService.getAllInsurancePlan();
		model.addAttribute("insurancePlan", new InsurancePlan());
		model.addAttribute("list", list);
		model.addAttribute("benefit", new PlanBenefits());
		return "benefits";
	}
	@RequestMapping(value="createBenefit")
	public ModelAndView createBenefit(@ModelAttribute ("benefit") PlanBenefits planBenefits) {
		System.out.println(planBenefits);
		
		planBenefitsService.createPlanBenefit(planBenefits);
		return new ModelAndView("benefits", "msg", "Benefit created");
	}
	
	@RequestMapping("addPlan")
	public ModelAndView addplan(@ModelAttribute ("benefit") PlanBenefits planBenefits) {
		
		InsurancePlan insurancePlan = insurancePlanService.getInsurancePlanById(1);
		
		planBenefitsService.addPlanBenefitsToPlan(insurancePlan, planBenefits);
		return new ModelAndView("benefits", "msg", "Benefit created");
	}
>>>>>>> a1ee89d9040a9695346f0b0680388f889afde9a2
}
