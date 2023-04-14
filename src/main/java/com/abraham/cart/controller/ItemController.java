package com.abraham.cart.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.abraham.cart.dl.ItemRepositoryH2;
import com.abraham.cart.domain.Item;

@Controller
public class ItemController {
    
    private ItemRepositoryH2 myRepository;

    public ItemController(ItemRepositoryH2 myRepository) {
        this.myRepository = myRepository;
    }

    @GetMapping("/item")
    public String myList(Model model) {
        List<Item> myList = myRepository.findAll();
        model.addAttribute("myList", myList);
        return "item.html";
    }
}
