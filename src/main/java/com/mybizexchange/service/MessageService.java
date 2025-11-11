package com.mybizexchange.service;

import com.mybizexchange.model.Listing;
import com.mybizexchange.model.Message;
import com.mybizexchange.repository.MessageRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    private final MessageRepository repo;

    public MessageService(MessageRepository repo){
        this.repo = repo;
    }

    public Message send(Message m){
        return repo.save(m);
    }

    public List<Message> forListing(Listing l){
        return repo.findByListing(l);
    }
}
