/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.company;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * @author Shereen
 */
public class Round_robin {

    Queue<Process> Ready_queue;
    Queue<String> queuee;
    int num, Context_switching, quntum, count, in,cont2;
    Process[] pp;
    float avgWait, avgTT;
    int[] completion,TT;
    int[] excution;
    String[] ee;

    public Round_robin(int n, int c, int q) {
        num = n;
        in = 0;
        cont2=0;
        Context_switching = c;
        quntum = q;
        pp = new Process[num];
        completion = new int[num];
        avgWait = 0;
        avgTT = 0;
        count = 0;
        ee = new String[num];
        for (int i = 0; i < num; i++) {
            completion[i] = 0;
            ee[i] = " ";

        }
        excution = new int[num];
        TT = new int[num];
        Ready_queue = new LinkedList<Process>();
        queuee = new LinkedList<String>();
    }

    public void setter(Process p, int index) {
        pp[index] = p;
    }

    public void sortt() {
        Process temp = new Process("n", 0, 0, 0,0);
        for (int i = 0; i < num; i++) {
            for (int j = 1; j < num - i; j++) {
                if (pp[j - 1].arrivalTime > pp[j].arrivalTime) {
                    temp = pp[j - 1];
                    pp[j - 1] = pp[j];
                    pp[j] = temp;
                }
            }
        }
        for (int i = 0; i < num; i++) {
            Ready_queue.add(pp[i]);
            excution[i] = pp[i].burstTime;

        }
    }

    public void Cpu_time() {
        sortt();
        while (!Ready_queue.isEmpty()) {
            Process now = Ready_queue.remove();
            if (now.arrivalTime > cont2) {
                Ready_queue.add(now);
            }
            else {
                queuee.add(now.name);
                if (now.burstTime <= quntum) {
                    count += now.burstTime + Context_switching;
                     cont2 += now.burstTime;
                    now.burstTime = 0;
                    for (int i = 0; i < num; i++) {
                        if (pp[i].name.equals(now.name)) {
                            completion[i] = count;
                            ee[in] = now.name;
                            in++;
                        }
                    }
                }
                else {
                    count += quntum + Context_switching;
                    cont2 += quntum;
                    now.burstTime -= quntum;
                    Ready_queue.add(now);
                }
            }

        }
        print();
    }

    public void print() {
        System.out.println("----Round Robin----  ");
        System.out.println("Execution order one:  ");
        while (!queuee.isEmpty()) {
            System.out.print(queuee.remove() + "  ");
        }
        System.out.println("\n Execution order two:  ");
        for (int i = 0; i < num; i++) {
            System.out.print(ee[i] + "  ");
        }
        System.out.println("\n Turnaround time for each process");
        for (int i = 0; i < num; i++) {
            TT[i] = completion[i] - pp[i].arrivalTime;
            avgTT += TT[i];
            System.out.println(pp[i].name + "  " + TT[i]);
        }
        System.out.println("\n average Turnaround time:  " + (avgTT / num));
        System.out.println("Wait time for each process");
        for (int i = 0; i < num; i++) {
            avgWait += TT[i] - excution[i];
            System.out.println(pp[i].name + "  " + (TT[i] - excution[i]));
        }
        System.out.println("average Wait time:  " + (avgWait / num));
    }
}
