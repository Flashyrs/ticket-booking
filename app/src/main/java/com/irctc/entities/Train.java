package com.irctc.entities;

import java.util.*;


public class Train {
    private String trainName;
    private int trainNo;
    private String trainId;
    private List<String> coaches;
    private List<List<Integer>> seats;
    private String arrTime;
    private String deptTime;
    private Map<String, String> stationSchedules;
    private List<String> stations;

    public Train(String trainName, int trainNo, String trainId, List<String> coaches,
                 List<List<Integer>> seats, String arrTime, String deptTime,
                 Map<String, String> stationSchedules, List<String> stations) {
        this.trainName = trainName;
        this.trainNo = trainNo;
        this.trainId = trainId;
        this.coaches = coaches;
        this.seats = seats;
        this.arrTime = arrTime;
        this.deptTime = deptTime;
        this.stationSchedules = stationSchedules;
        this.stations = stations;
    }

    public Train(){}

    public String getTrainName() {
        return this.trainName;
    }
    public void setTrainName(String trainName) {
        this.trainName = trainName;
    }


    public int getTrainNo() {
        return this.trainNo;
    }
    public void setTrainNo(int trainNo) {
        this.trainNo = trainNo;
    }


    public String getTrainId() {
        return this.trainId;
    }
    public void setTrainId(String trainId) {
        this.trainId = trainId;
    }


    public List<String> getCoaches() {
        return this.coaches;
    }
    public void setCoaches(List<String> coaches) {
        this.coaches = coaches;
    }


    public List<List<Integer>> getSeats() {
        return this.seats;
    }
    public void setSeats(List<List<Integer>> seats) {
        this.seats = seats;
    }


    public String getArrTime() {
        return this.arrTime;
    }
    public void setArrTime(String arrTime) {
        this.arrTime = arrTime;
    }


    public String getDeptTime() {
        return this.deptTime;
    }
    public void setDeptTime(String deptTime) {
        this.deptTime = deptTime;
    }


    public Map<String, String> getStationSchedules() {
        return this.stationSchedules;
    }
    public void setStationSchedules(Map<String, String> stationSchedules) {
        this.stationSchedules = stationSchedules;
    }


    public List<String> getStations() {
        return this.stations;
    }
    public void setStations(List<String> stations) {
        this.stations = stations;
    }

    public String getTrainInfo(){
        return String.format("Train ID: %s Train No: %s", trainId, trainNo);
    }

}


