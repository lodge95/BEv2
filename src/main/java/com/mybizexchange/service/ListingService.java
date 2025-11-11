package com.mybizexchange.service;

import com.mybizexchange.model.Listing;
import com.mybizexchange.repository.ListingRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ListingService {
    private final ListingRepository repo;

    public ListingService(ListingRepository repo){
        this.repo = repo;
    }

    public List<Listing> featured(){
        return repo.findTop10ByPublishedTrueOrderByIdDesc();
    }

    public List<Listing> search(String q, int page, int size){
        return repo.findByTitleContainingIgnoreCaseOrIndustryContainingIgnoreCase(q, q, PageRequest.of(page, size));
    }

    public Listing save(Listing listing){
        return repo.save(listing);
    }

    public Optional<Listing> findById(Long id){
        return repo.findById(id);
    }
}
