package com.mybizexchange.repository;

import com.mybizexchange.model.Listing;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ListingRepository extends JpaRepository<Listing, Long> {
    List<Listing> findTop10ByPublishedTrueOrderByIdDesc();
    List<Listing> findByTitleContainingIgnoreCaseOrIndustryContainingIgnoreCase(String titleQ, String industryQ, Pageable pageable);
}
