package com.irctc.entities;

import java.util.*;
import java.util.UUID;

public class User {
    private String name;
    private String hashedPassword;
    private String username;
    private List<Ticket> bookedTickets;
    private String userId;
    private String password;

    public User(String name, String password, String hashedPassword, String username, List<Ticket> bookedTickets, String userId){
        this.name = name;
        this.password = password;
        this.hashedPassword = hashedPassword;
        this.username = username;
        this.userId = userId;
        this.bookedTickets = bookedTickets;
    }

    public User(){}

    public String getName(){
        return this.name;
    }
    public String getHashedPassword(){
        return this.hashedPassword;
    }
    public String getUserName(){
        return this.username;
    }
    public List<Ticket> getBookedTickets(){
        return this.bookedTickets;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getPassword(){
        return this.password;
    }

    public void printTickets(){
        int n = bookedTickets.size();
        for(int i = 0 ; i < n; i++){
            System.out.println(bookedTickets.get(i).getTicketInfo());
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
    public void setUsername(String username){
        this.username = username;
    }
    public void setBookedTickets(List<Ticket> bookedTickets){
        this.bookedTickets = bookedTickets;
    }

}













