package com.irctc.entities;

import java.time.DateTimeException;
import java.util.*;

public class Ticket {
    private String ticketId;
    private String userId;
    private String sourceStation;
    private String destinationStation;
    private String dateOfTravel;
    private Train train;

    public Ticket(String ticketId, String userId, String sourceStation, String destinationStation, String dateOfTravel,
                        Train train){
        this.ticketId = ticketId;
        this.train = train;
        this.userId = userId;
        this.sourceStation = sourceStation;
        this.destinationStation = destinationStation;
        this.dateOfTravel = dateOfTravel;
    }

    public Ticket(){}

    //setters
    public void setTicketId(String ticketId){
        this.ticketId = ticketId;
    }

    public void setUserId(String userId){
        this.userId = userId;
    }
    public void setSourceStation(String sourceStation){
        this.sourceStation = sourceStation;
    }
    public void setDestinationStation(String destinationStation){
        this.destinationStation = destinationStation;
    }
    public void setDateOfTravel(String dateOfTravel){
        this.dateOfTravel = dateOfTravel;
    }
    public void setTrain(Train train){
        this.train = train;
    }

    //getters
    public String getTicketId(){
        return this.ticketId;
    }
    public String getUserId(){
        return this.userId;
    }
    public String getSourceStation(){
        return this.sourceStation;
    }
    public String getDestinationStation(){
        return this.destinationStation;
    }
    public String getDateOfTravel(){
        return this.dateOfTravel;
    }
    public Train getTrain(){
        return train;
    }

    //ticketinfo
    public String getTicketInfo(){
        return String.format("Ticked ID: %s belongs to User %s with travel from %s to %s on %s", ticketId, userId, sourceStation, destinationStation, dateOfTravel);
    }


}
