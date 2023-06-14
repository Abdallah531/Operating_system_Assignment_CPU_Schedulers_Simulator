package com.company;

import java.util.LinkedList;

public class Priorty {

    static void WaitingTime(Process process[], int n, int waitingTime[]) {
        int remainingTime[] = new int[n];
        int br[] = new int[n];
        LinkedList<Process> processes =new LinkedList<Process>();
        for (int i = 0; i < n; i++) {
            remainingTime[i] = process[i].getBurstTime();
             br[i]=process[i].getPriority();


        }


        int finished = 0;
        int time = 0;
        int min = Integer.MAX_VALUE;
        int smallest = 0;
        int end_time;
        boolean found = false;
        int count=0;

        while (finished != n) {

            for (int j = 0; j < n; j++) {
                if ((process[j].getArrivalTime() <= time) &&  (br[j] < min) && remainingTime[j] > 0) {
                    min = br[j];
                    smallest = j;
                    found = true;
                }
            }

            if (found == false) {
                time++;
                continue;
            }
            remainingTime[smallest]--;
            count++;
            if(count%5==0){
                for(int i=0;i<process.length;i++){
                        if(process[i]==process[smallest])
                            continue;
                        if(process[i].getPriority()>1)
                            process[i].setPriority(process[i].getPriority()-1);
                }

                }


            if (min == 0)
                min = Integer.MAX_VALUE;

            processes.add(process[smallest]);
            if (remainingTime[smallest] == 0) {
                min=Integer.MAX_VALUE;
                finished++;
                found = false;
                end_time = time + 1;
                waitingTime[smallest] =  end_time - (process[smallest].getBurstTime() + process[smallest].getArrivalTime());

                if (waitingTime[smallest] < 0)
                    waitingTime[smallest] = 0;
            }
            time++;
        }
        System.out.println("Processes Execution Order");

        for(int i=0;i<processes.size();i++) {
            if (i == 0)
                System.out.println(processes.get(i).getName());
            else{
                if(processes.get(i)==processes.get(i-1))
                    continue;
                else
                    System.out.println(processes.get(i).getName());

            }
        }
    }

    static void turnAroundTime(Process process[], int n, int waitingTime[], int turnaround[])
    {
        for (int i = 0; i < n; i++)
            turnaround[i] =  process[i].getBurstTime() + waitingTime[i];
    }

    static void avgTime(Process process[], int n)
    {
        int waitingTime[] = new int[n], turnaround[] = new int[n];
        int  total_waitingTime = 0, total_turnaround = 0;

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
                (double)total_waitingTime / (double)n);
        System.out.println("Average turn around time = " +
                (double)total_turnaround / (double)n);
    }
}
