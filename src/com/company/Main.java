package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Number of process : ");
        int num = input.nextInt();
        System.out.println("Enter Round robin Time Quantum : ");
        int qun = input.nextInt();
        System.out.println("Enter Context switching : ");
        int Context_switching = input.nextInt();
        Round_robin RB = new Round_robin(num, Context_switching, qun);
        AG ag = new AG(num);
        Process []PPP=new Process[num];
        Process []PPPP=new Process[num];
        for (int i = 0; i < num; i++) {
            System.out.println("process name: ");
            String name = input.next();
            System.out.print("arrival time: ");
            int at = input.nextInt();
            System.out.println("BurstTime : ");
            int bt= input.nextInt();
            System.out.println("Priority: ");
            int pri= input.nextInt();
            System.out.println("Quantum: ");
            int q= input.nextInt();
            Process p=new Process(name,bt,at,pri,q);
            Process pp=new Process(name,bt,at,pri,q);
            Process qqq=new Process(name,bt,at,pri,q);
            Process qq=new Process(name,bt,at,pri,q);
            PPP[i]=qqq;
            PPPP[i]=qq;
            RB.setter(p, i);
            ag.setterprocess(pp,i);
        }
        System.out.println("----Preemptive SJF----  ");
        Preemptive_SJF.avgTime(PPP,num,Context_switching);
        System.out.println();
        RB.Cpu_time();
        System.out.println("----Preemptive Priority----  ");
        System.out.println();
        Priorty.avgTime(PPPP,num);
        System.out.println();
        ag.Schidling();

    }
}
