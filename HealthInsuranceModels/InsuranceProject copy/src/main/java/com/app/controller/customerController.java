package com.app.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.HttpServletBean;
import org.springframework.web.servlet.ModelAndView;

import com.app.dao.ICustomerDao;
import com.app.models.Customer;
import com.app.models.InsurancePlan;
import com.app.service.ICustomerService;
import com.app.service.IInsurancePlanService;
import com.app.validators.UserValidator;

@Controller
public class customerController {
	
	@Autowired
	private ICustomerService customerService;
	
	@Autowired
	private IInsurancePlanService insurancePlanService; 
	
	HttpSession session;
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
	public ModelAndView login(@ModelAttribute("customer")Customer customer,HttpServletRequest request ) {
		Customer cus = customerService.getCustomerByEmail(customer.getEmail());
		ModelAndView model = new ModelAndView();
		if(cus!=null) {
			if(cus.getPswd().equals(customer.getPswd())) {
				session = request.getSession();
				session.setAttribute("userName", cus.getEmail());
				model.setViewName("home");
				model.addObject("customer", cus);
				System.out.println(cus.getPswd() + " " + customer.getPswd());
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
	@RequestMapping("showall") 
	public ModelAndView showall() {
		
		
		if(session.getAttribute("userName") == null) {
			return new ModelAndView("login", "msg", "You need to login first");
		} else {
			return new ModelAndView("home", "msg", customerService.getAllCustomers());
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
//	public static void main(String args[]){
//		Session session = new SessionFactory().openSession().
//	}
}
