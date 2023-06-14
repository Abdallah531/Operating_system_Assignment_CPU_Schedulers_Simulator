package com.company;

public class Process
{
     String name;
     int arrivalTime;
     int burstTime;
     int priority;
     int Quintum;

    public void setName(String name) {
        this.name = name;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public void setBurstTime(int burstTime) {
        this.burstTime = burstTime;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Process(String name, int burstTime, int arrivalTime, int priority,int quintum) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.priority = priority;
        this.Quintum=quintum;
    }

    public String getName() {
        return name;
    }

    public int getQuintum() {
        return Quintum;
    }

    public void setQuintum(int quintum) {
        Quintum = quintum;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }


    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }



}
