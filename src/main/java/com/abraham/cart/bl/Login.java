package com.abraham.cart.bl;


import com.abraham.cart.dl.CustomerRepositoryH2;
import com.abraham.cart.domain.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Login {

    @Autowired
    private CustomerRepositoryH2 customerRepositoryH2;

    @PostMapping("/login")
    public String authenticate(@RequestParam String email, @RequestParam String password, HttpSession session) {
        Customer customer = customerRepositoryH2.findByEmail(email);
        if (customer == null) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        if (!customer.getPassword().equals(password)) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        session.setAttribute("customer", customer);


        return "redirect:/cart";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView showRegister() {
        ModelAndView mav = new ModelAndView("register");
        mav.addObject("customer", new Customer(0, "", "", "", "", ""));
        return mav;
    }

    @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
    public String addUser(Customer customer) {
        customerRepositoryH2.save(customer);
        return "redirect:/login";
    }

}
