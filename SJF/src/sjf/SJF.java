/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sjf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author MY
 */
public class SJF {

    public static int last_finish = 0, flag = 0;

    public static void main(String[] args) {
        // TODO code application logic here
        
        
        
        Scanner in = new Scanner(System.in);
        int n, i, j, k,simpleRun=0, e, a;
        for(i=0;i<10;i++){
            System.out.print(i+"--");
        }
        System.out.print("Enter no of Processes:");
        n = in.nextInt();
        Process p[] = new Process[n];
        ArrayList<Process> proc=new ArrayList<>();
        for (i = 0; i < n; i++) {
            System.out.print("Execution Time of Process" + (i + 1) + " :");
            e = in.nextInt();
            System.out.print("Arrival Time of Process" + (i + 1) + " :");
            a = in.nextInt();
            p[i] = new Process(i + 1, e, a);
            proc.add(p[i]);
        }
        ArrayList<Process> list = new ArrayList();Process tempRun;
        p[simpleRun++].runProcess();
        
        for (i = 0; i < (n - 1); i++) {
            if (flag == 0) {
                for (j = 1; j < proc.size(); j++) {
                    if (!list.contains(proc.get(j)) && proc.get(j).ar <= last_finish) {
                        list.add(proc.get(j));
                    }
                }
                
                if(list.isEmpty()){
                    list.add(proc.get(simpleRun));
                    list.remove(proc.get(simpleRun++));
                }
                else{
                    Collections.sort((List)list,new Comparator<Process>() {
                    @Override
                    public int compare(Process o1, Process o2) {
                        if(o1.ex==o2.ex)
                            return o1.ar-o2.ar;
                        else
                            return o1.ex-o2.ex;
                    }
                });
                tempRun = list.get(0);
                tempRun.runProcess();
                proc.remove(tempRun);
                list.remove(tempRun);
                }
                
            }
            flag = (list.size() == (n - simpleRun-1)) ? 1 : 0;
            int tempSize=list.size();
            if(flag==1){
                do{
                    tempRun=list.get(0);
                    tempRun.runProcess();
                    proc.remove(tempRun);
                    list.remove(tempRun);
                    tempSize--;
                }while(tempSize>0);
                break;
            }
            
        }
        
        
        
        System.out.println("-------------------------------------------");
        System.out.println("Pro  |  T.T  |  wait Time  |  Utilization Time");
        System.out.println("-------------------------------------------");
        for (i = 0; i < n; i++) {
            System.out.println("P" + p[i].id + "  |  " + p[i].t_time + "  |  " + p[i].wt_time + "  |  " + p[i].ut_time + "%");
        }

    }

}
