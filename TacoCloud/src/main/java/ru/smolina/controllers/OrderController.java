package ru.smolina.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
import ru.smolina.domains.User;
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
	public String orderForm(@AuthenticationPrincipal User user, @ModelAttribute Order order) {

		if (order.getName() == null)
			order.setName(user.getFullname());
		if (order.getState() == null)
			order.setStreet(user.getStreet());
		if (order.getCity() == null)
			order.setCity(user.getCity());
		if (order.getState() == null)
			order.setState(user.getState());
		if (order.getZip() == null)
			order.setZip(user.getZip());
		
		return "orderForm";
	}

	@PostMapping
	public String proccessOrder(@Valid @ModelAttribute("order") Order order, Errors errors, SessionStatus sessionStatus,
			@AuthenticationPrincipal User user) {

		if (errors.hasErrors())
			return "orderForm";
		
		order.setUser(user);

		orderRepo.save(order);
		sessionStatus.setComplete();

		return "redirect:/";
	}

}