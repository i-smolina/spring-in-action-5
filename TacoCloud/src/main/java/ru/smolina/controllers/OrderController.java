package ru.smolina.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;
import ru.smolina.domains.Order;
import ru.smolina.repositories.jdbc.OrderRepository;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
	
	private OrderRepository orderRepo;
	
	@Autowired
	public OrderController(OrderRepository orderRepo) {
		this.orderRepo = orderRepo;
	}
	
	@GetMapping("/current")
	public String orderForm(Model model) {
		return "orderForm";
	}
	
	@PostMapping
	public String proccessOrder(@Valid @ModelAttribute("order") Order order, Errors errors, 
			SessionStatus sessionStatus) {
	
		if (errors.hasErrors())
			return "orderForm";
		
		orderRepo.save(order);
		sessionStatus.setComplete();
		
		return "redirect:/";
	}

}