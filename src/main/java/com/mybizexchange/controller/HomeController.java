package com.mybizexchange.controller;

import com.mybizexchange.service.ListingService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ListingService listingService;

    public HomeController(ListingService listingService){
        this.listingService = listingService;
    }

    @GetMapping("/")
    public String index(Model model){
        model.addAttribute("featured", listingService.featured());
        return "index";
    }

    @GetMapping("/about")
    public String about(){ return "about"; }

    @GetMapping("/valuation")
    public String valuation(){ return "valuation"; }
}
