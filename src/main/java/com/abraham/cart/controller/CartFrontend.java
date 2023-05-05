package com.abraham.cart.controller;

import com.abraham.cart.dl.ItemRepositoryH2;
import com.abraham.cart.domain.Item;
import com.abraham.cart.domain.ShoppingCart;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@Controller
public class CartFrontend {

    @Autowired
    private ItemRepositoryH2 itemRepository;

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }
        model.addAttribute("title", "Shopping Cart");
        model.addAttribute("cartItems", cart.getItems().entrySet());
        model.addAttribute("cartTotal", cart.getTotal());
        model.addAttribute("availableItems", itemRepository.findAll());


        return "cart";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Login");
        return "login";
    }


    @PostMapping("/cart/add")
    public String addToCart(@RequestParam int itemId, @RequestParam BigDecimal quantity, HttpSession session) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            cart = new ShoppingCart();
            session.setAttribute("cart", cart);
        }

        cart.AddItemToCart(item, quantity);

        return "redirect:/cart";
    }

    @PostMapping("/cart/remove")
    public String removeFromCart(@RequestParam int itemId,@RequestParam BigDecimal quantity, HttpSession session) {
        Item item = itemRepository.findById(itemId);
        if (item == null) {
            throw new IllegalArgumentException("Invalid item id: " + itemId);
        }

        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart == null) {
            throw new IllegalStateException("Shopping cart not found in session");
        }

        BigDecimal cartQuantity = cart.getItems().get(item);
        if (cartQuantity == null || quantity.compareTo(BigDecimal.ZERO) <= 0 || quantity.compareTo(cartQuantity) > 0) {
            throw new IllegalArgumentException("Invalid quantity: " + quantity);
        }

        cart.RemoveItemFromCart(item, quantity);

        return "redirect:/cart";
    }

    @GetMapping("/about")
    public String about(Model model) {
        model.addAttribute("title", "About Us");
        return "about";
    }

    @GetMapping("/faq")
    public String faq(Model model) {
        model.addAttribute("title", "FAQ");
        return "faq";
    }

    @GetMapping("/support")
    public String support(Model model) {
        model.addAttribute("title", "support");
        return "support";
    }


}
