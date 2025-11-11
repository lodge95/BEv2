package com.mybizexchange.repository;

import com.mybizexchange.model.Message;
import com.mybizexchange.model.Listing;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByListing(Listing listing);
}
