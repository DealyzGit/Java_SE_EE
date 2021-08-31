package com.example.service;

import org.springframework.stereotype.Component;

@Component
public class TicketServiceImpl implements TicketService{
    @Override
    public String getTicket() {
        return "《Tong》";
    }
}
