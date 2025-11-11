package com.mybizexchange.config;

import com.mybizexchange.model.Listing;
import com.mybizexchange.model.User;
import com.mybizexchange.repository.ListingRepository;
import com.mybizexchange.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {
    @Bean
    CommandLineRunner init(UserRepository userRepo, ListingRepository listingRepo){
        return args -> {
            // Create demo users
            if (userRepo.count() == 0) {
                User seller = new User();
                seller.setUsername("seller");
                seller.setPasswordHash("$2a$10$7fR9G...stub"); // not a usable password but placeholder
                seller.setDisplayName("Demo Seller");
                seller.setEmail("seller@example.com");
                seller.setRole("SELLER");
                userRepo.save(seller);

                User buyer = new User();
                buyer.setUsername("buyer");
                buyer.setPasswordHash("$2a$10$7fR9G...stub");
                buyer.setDisplayName("Demo Buyer");
                buyer.setEmail("buyer@example.com");
                buyer.setRole("USER");
                userRepo.save(buyer);

                listingRepo.save(new Listing() {{
                    setTitle("Local Cafe for Sale");
                    setDescription("Profitable café with loyal customers and catering contracts.");
                    setIndustry("Food & Beverage");
                    setPrice(250000.0);
                    setLocation("Eastern Ontario");
                    setOwner(seller);
                    setImagePath("/static/img/cafe.jpg");
                }});

                listingRepo.save(new Listing() {{
                    setTitle("Auto Repair Shop");
                    setDescription("Established garage with experienced technicians and steady revenue.");
                    setIndustry("Automotive");
                    setPrice(350000.0);
                    setLocation("Kingston");
                    setOwner(seller);
                    setImagePath("/static/img/garage.jpg");
                }});
            }
        };
    }
}
