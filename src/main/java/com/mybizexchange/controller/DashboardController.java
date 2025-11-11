package com.mybizexchange.controller;

import com.mybizexchange.repository.ListingRepository;
import com.mybizexchange.repository.MessageRepository;
import com.mybizexchange.repository.UserRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DashboardController {
    private final ListingRepository listingRepo;
    private final MessageRepository messageRepo;
    private final UserRepository userRepo;

    public DashboardController(ListingRepository listingRepo, MessageRepository messageRepo, UserRepository userRepo){
        this.listingRepo = listingRepo;
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
    }

    @GetMapping("/dashboard")
    public String dashboard(@AuthenticationPrincipal UserDetails userDetails, Model m){
        if (userDetails == null) return "redirect:/login";
        var user = userRepo.findByUsername(userDetails.getUsername()).orElse(null);
        m.addAttribute("user", user);
        m.addAttribute("myListings", listingRepo.findAll().stream().filter(l -> l.getOwner() != null && l.getOwner().getId().equals(user.getId())).toList());
        return "dashboard";
    }
}
