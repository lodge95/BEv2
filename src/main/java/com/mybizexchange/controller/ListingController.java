package com.mybizexchange.controller;

import com.mybizexchange.model.Listing;
import com.mybizexchange.model.User;
import com.mybizexchange.repository.UserRepository;
import com.mybizexchange.service.ListingService;
import jakarta.validation.Valid;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ListingController {
    private final ListingService listingService;
    private final UserRepository userRepo;

    public ListingController(ListingService listingService, UserRepository userRepo){
        this.listingService = listingService;
        this.userRepo = userRepo;
    }

    @GetMapping("/browse")
    public String browse(@RequestParam(value="q", required=false, defaultValue="") String q,
                         @RequestParam(value="p", required=false, defaultValue="0") int p,
                         Model model){
        model.addAttribute("listings", listingService.search(q, p, 20));
        model.addAttribute("q", q);
        return "browse";
    }

    @GetMapping("/listing/{id}")
    public String view(@PathVariable Long id, Model model){
        Listing l = listingService.findById(id).orElse(null);
        model.addAttribute("listing", l);
        return "listing";
    }

    @GetMapping("/sell")
    public String sellForm(Model m){
        m.addAttribute("listing", new Listing());
        return "sell";
    }

    @PostMapping("/sell")
    public String submitSell(@ModelAttribute @Valid Listing listing, Authentication auth){
        // owner stub: attach current user if logged in
        if (auth != null && auth.getName() != null){
            User u = userRepo.findByUsername(auth.getName()).orElse(null);
            if (u != null) listing.setOwner(u);
        }
        listingService.save(listing);
        return "redirect:/browse";
    }
}
