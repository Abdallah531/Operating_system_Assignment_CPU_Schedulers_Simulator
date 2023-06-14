package com.company;



import java.util.LinkedList;
import java.util.*;
import java.lang.Math;

public class AG {
    int n,cont,remain,index;
    float avgWait, avgTT;
    int[] completion,TT;
    Process[] pp;
    Queue<Process> Ready_queue,QUEUE;
    Queue<String> queuee;
    int[] excution,quan;
    public AG(int num) {
        n=num;
        cont =0;
        index=0;
        completion = new int[num];
        for (int i = 0; i < num; i++) {
            completion[i] = 0;
        }
        TT = new int[num];
        quan = new int[num];
        pp = new Process[num];
        Ready_queue = new LinkedList<Process>();
        QUEUE = new LinkedList<Process>();
        queuee = new LinkedList<String>();
        excution = new int[num];
    }
    public void setterprocess(Process p, int index) {
        pp[index] = p;
    }
    public Process Fcfs(Process P)
    {
        double i = P.getQuintum();
        if(P.burstTime-(Math.ceil(i/4))<=0) {
            cont += P.burstTime;
            P.burstTime = 0;
        }
        else
        {
            cont+=Math.ceil(i/4.0);
            P.burstTime-= Math.ceil(i/4);
        }
        remain = (int) (i - Math.ceil(i/4));
        return P;
    }

    public void updatequeue(Process now1)
    {
        int a = Ready_queue.size();
        for(int i = 0 ; i<a;i++)
        {
            Process E=Ready_queue.remove();
            if(E != now1 &&E.burstTime>0)
            {
                Ready_queue.add(E);
            }
        }
        if(now1.burstTime>0)Ready_queue.add(now1);

    }
    public Process Prii(Process P)
    {
        double i = Math.ceil(P.Quintum/2.0);
        double ii = Math.ceil(P.Quintum/4.0);
        if(P.burstTime-(i-ii)<=0) {
            cont += P.burstTime;
            P.burstTime = 0;
        }
        else
        {
            cont+=i-ii;
            P.burstTime-=i-ii;
        }
        remain =P.Quintum - (int)i;
        return P;
    }
    public Process Pri(Process P)
    {
        for(Process E:Ready_queue)
        {
            if(E.priority < P.priority)
            {
                P=E;
            }

        }
        return P;
    }
    public void sortt() {
        Process temp = new Process("n", 0, 0, 0,0);
        for (int i = 0; i < n; i++) {
            for (int j = 1; j < n - i; j++) {
                if (pp[j - 1].arrivalTime > pp[j].arrivalTime) {
                    temp = pp[j - 1];
                    pp[j - 1] = pp[j];
                    pp[j] = temp;

                }
            }
        }

        for (int i = 0; i < n; i++) {
            excution[i] = pp[i].burstTime;
        }
    }
    public void addtoqueue()
    {
        for(int i = index ; i < n;i++)
        {
            if(pp[i].arrivalTime<=cont && pp[i].burstTime>0)
            {
                Ready_queue.add(pp[i]);index++;
            }
            else break;

        }
    }
    public void search(Process P)
    {
        for(int i = 0 ; i<n;i++)
        {
            if(P.name.equals(pp[i].name)) {P.Quintum = quan[i]+2;quan[i]+=2;}
        }
    }
    public void printquan()
    {
        System.out.print("Quantum (");
        for(int i = 0 ; i<n;i++)
        {
            quan[i]=pp[i].Quintum;
            System.out.print(quan[i]);
            if(i!=n-1) System.out.print(",");
        }
        System.out.println(")");
    }
    public void Schidling()
    {
        System.out.println("----AG Scheduling----  ");
        System.out.println("History Update of Quantum time of AG");
        sortt();
        QUEUE.add(pp[0]);
        cont=pp[0].arrivalTime;
        index++;
        do
        {
            printquan();
            Process NOW = Fcfs(QUEUE.remove());
            addtoqueue();
            if(NOW.burstTime==0)
            {
                for (int i = 0; i < n; i++) {
                    if (pp[i].name.equals(NOW.name)) {
                        completion[i] = cont;
                    }
                }
                NOW.Quintum=0;
                QUEUE.add((Ready_queue.remove()));
                queuee.add(NOW.name);
                continue;

            }
            if(remain==0)
            {
                queuee.add(NOW.name);
                search(NOW);
                if(NOW.burstTime>0) updatequeue(NOW);
                QUEUE.add(Ready_queue.remove());
                continue;
            }
            Process now1=Pri(NOW);
            if(now1.priority != NOW.priority)
            {
                queuee.add(NOW.name);
                QUEUE.add(now1);
                NOW.Quintum += Math.ceil(remain/2.0);
                if(NOW.burstTime>0) updatequeue(NOW);
                updatequeue(now1);
                continue;
            }
            NOW = Prii(NOW);
            addtoqueue();
            if(NOW.burstTime==0)
            {
                for (int i = 0; i < n; i++) {
                    if (pp[i].name.equals(NOW.name)) {
                        completion[i] = cont;
                    }
                }
                NOW.Quintum=0;
                QUEUE.add((Ready_queue.remove()));
                queuee.add(NOW.name);
                continue;

            }
            if(remain==0)
            {
                queuee.add(NOW.name);
                search(NOW);
                if(NOW.burstTime>0) updatequeue(NOW);
                QUEUE.add(Ready_queue.remove());
                continue;
            }
            now1 = SJF(NOW);
            if(now1.name.equals("-1"))
            {
                queuee.add(NOW.name);
                continue;
            }
            if(!NOW.name.equals(now1.name))
            {
                queuee.add(NOW.name);
                QUEUE.add(now1);
                NOW.Quintum += remain;
                if(NOW.burstTime>0) updatequeue(NOW);
                updatequeue(now1);
                continue;
            }
            for (int i = 0; i < n; i++) {
                if (pp[i].name.equals(NOW.name)) {
                    completion[i] = cont;
                }
            }
            NOW.Quintum=0;
            queuee.add(NOW.name);
            while (Ready_queue.isEmpty())
            {
                cont++;
                addtoqueue();
            }
            QUEUE.add(Ready_queue.remove());
        }while (!Ready_queue.isEmpty());
        printquan();
        print();

    }

    public Process SJF(Process P) {
        Process PP=P;
        Process C = new Process("-1",0,0,0,0);
        boolean found = false;
        while (P==PP) {
            for (Process E : Ready_queue) {
                if ( E.burstTime < P.burstTime && E.burstTime > 0) {
                    PP = E;
                    found = true;
                }
            }
            if(found)continue;
            PP.burstTime--;
            remain--;
            if(remain==0)
            {
                search(PP);
                if(PP.burstTime>0) updatequeue(PP);
                QUEUE.add(Ready_queue.remove());
                return C;
            }
            if (P.burstTime == 0) {
                updatequeue(P);
                cont++;
                addtoqueue();
                return P;
            }
            cont++;
            addtoqueue();
        }
        return PP;
    }
    public void print() {
        System.out.println("Execution order one:  ");
        while (!queuee.isEmpty()) {
            System.out.print(queuee.remove() + "  ");
        }
        System.out.println("\nTurnaround time for each process");
        for (int i = 0; i < n; i++) {
            TT[i] = completion[i] - pp[i].arrivalTime;
            avgTT += TT[i];
            System.out.println(pp[i].name + "  " + TT[i]);
        }
        System.out.println("average Turnaround time:  " + (avgTT / n));
        System.out.println("Wait time for each process");
        for (int i = 0; i < n; i++) {
            avgWait += TT[i] - excution[i];
            System.out.println(pp[i].name + "  " + (TT[i] - excution[i]));
        }
        System.out.println("average Wait time:  " + (avgWait / n));
    }
}