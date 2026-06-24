package com.irctc.entities;

import java.util.*;
import java.util.UUID;

public class User {
    private String name;
    private String hashedPassword;
    private List<Ticket> ticketsBooked;
    private String userId;
    private String password;

    public User(String name, String password, String hashedPassword, List<Ticket> ticketsBooked, String userId){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.userId = userId;
        this.ticketsBooked = ticketsBooked;
    }

    public User(){}

    public String getName(){
        return this.name;
    }
    public String getHashedPassword(){
        return this.hashedPassword;
    }
    public List<Ticket> getTicketsBooked(){
        return this.ticketsBooked;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getPassword(){
        return this.password;
    }

    public void printTickets(){
        int n = ticketsBooked.size();
        for(int i = 0 ; i < n; i++){
            System.out.println(ticketsBooked.get(i).getTicketInfo());
        }
    }

    public void setName(String name){
        this.name = name;
    }
    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public void setHashedPassword(){
        this.hashedPassword = hashedPassword;
    }
    public void setTicketsBooked(List<Ticket> ticketsBooked){
        this.ticketsBooked = ticketsBooked;
    }

}













