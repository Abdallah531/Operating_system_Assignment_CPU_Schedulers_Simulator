package com.company;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class Preemptive_SJF {


    // Method to find the waiting time for all
    // processes
    static int context;
    static void WaitingTime(Process process[], int n, int waitingTime[]) {
        int remainingTime[] = new int[n];
        LinkedList<Process> processes = new LinkedList<Process>();
        for (int i = 0; i < n; i++)
            remainingTime[i] = process[i].getBurstTime();


        int finished = 0;
        int time = 0;
        int min = Integer.MAX_VALUE;
        int smallest = 0;
        int end_time;
        boolean found = false;

        while (finished != n) {

            for (int j = 0; j < n; j++) {
                if ((process[j].getArrivalTime() <= time) && (remainingTime[j] < min) && remainingTime[j] > 0) {
                    min = remainingTime[j];
                    smallest = j;
                    found = true;
                }
            }

            if (found == false) {
                time++;
                continue;
            }
            remainingTime[smallest]--;

            min = remainingTime[smallest];
            if (min == 0)
                min = Integer.MAX_VALUE;

            processes.add(process[smallest]);

            if (remainingTime[smallest] == 0) {
                finished++;
                found = false;
                end_time = time + context;
                waitingTime[smallest] = end_time - (process[smallest].getBurstTime() + process[smallest].getArrivalTime());

                if (waitingTime[smallest] < 0)
                    waitingTime[smallest] = 0;
            }
            time++;
        }
        System.out.println("Processes Execution Order");

        for (int i = 0; i < processes.size(); i++) {
            if (i == 0)
                System.out.println(processes.get(i).getName());
            else {
                if (processes.get(i) == processes.get(i - 1))
                    continue;
                else
                    System.out.println(processes.get(i).getName());

            }
        }
    }

    static void turnAroundTime(Process process[], int n, int waitingTime[], int turnaround[]) {
        for (int i = 0; i < n; i++)
            turnaround[i] = process[i].getBurstTime() + waitingTime[i];
    }

    static void avgTime(Process process[], int n,int c) {
        int waitingTime[] = new int[n], turnaround[] = new int[n];
        int total_waitingTime = 0, total_turnaround = 0;
        context = c;
        WaitingTime(process, n, waitingTime);

        turnAroundTime(process, n, waitingTime, turnaround);

        System.out.println("Processes " + " Burst time " + " Waiting time " + " Turn around time");

        for (int i = 0; i < n; i++) {
            total_waitingTime = total_waitingTime + waitingTime[i];
            total_turnaround = total_turnaround + turnaround[i];
            System.out.println(" " + process[i].getName() + "\t\t\t\t"
                    + process[i].getBurstTime() + "\t\t\t " + waitingTime[i]
                    + "\t\t\t\t" + turnaround[i]);
        }

        System.out.println("Average waiting time = " +
                (double) total_waitingTime / (double) n);
        System.out.println("Average turn around time = " +
                (double) total_turnaround / (double) n);
    }


}











    // Driver Method





