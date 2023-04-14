package com.abraham.cart.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abraham.cart.dl.CustomerRepositoryH2;
import com.abraham.cart.domain.Customer;

@Controller
public class CustomerController {
    
    private CustomerRepositoryH2 myRepository;

    public CustomerController(CustomerRepositoryH2 myRepository) {
        this.myRepository = myRepository;
    }

    @GetMapping("/customer")
    public String myList(Model model) {
        List<Customer> myList = myRepository.findAll();
        model.addAttribute("myList", myList);
        return "customer.html";
    }
}