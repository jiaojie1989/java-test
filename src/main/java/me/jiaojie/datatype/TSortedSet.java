/*
 * Copyright (C) 2016 SINA Corporation
 *  
 *  
 * 
 * This script is firstly created at 2016-05-19.
 * 
 * To see more infomation,
 *    visit our official website http://jiaoyi.sina.com.cn/.
 */
package me.jiaojie.datatype;

import java.util.TreeSet;
import java.util.HashMap;
import java.util.SortedSet;
import java.util.Iterator;
import me.jiaojie.datatype.TemplateComparator;
import me.jiaojie.datatype.TemplateElement;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import me.jiaojie.datatype.RunMe;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class TSortedSet {

    protected Logger Logger;

    public static void amain(String[] args) {
        System.out.println("--start--");

//        Iterator It = Set.tailSet(new TemplateElement("null", 3)).iterator();
//        while (It.hasNext()) {
//            System.out.println(Set.remove(It.next()));
//        }
        ScheduledExecutorService scheduledThreadPool1 = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);

        scheduledThreadPool1.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Faker MyFaker = new Faker();
                Name MyName = MyFaker.name();
                String Symbol = MyName.lastName();
                double Rank = MyFaker.number().randomDouble(2, 0, 1000);
                RunMe.getInstance().add(Symbol, (float) Rank);
            }
        }, 1, 100, TimeUnit.MICROSECONDS);
        scheduledThreadPool1.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Faker MyFaker = new Faker();
                Name MyName = MyFaker.name();
                String Symbol = MyName.lastName();
                double Rank = MyFaker.number().randomDouble(2, 0, 1000);
                RunMe.getInstance().add(Symbol, (float) Rank);
            }
        }, 1, 100, TimeUnit.MICROSECONDS);
        scheduledThreadPool1.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Faker MyFaker = new Faker();
                Name MyName = MyFaker.name();
                String Symbol = MyName.lastName();
                double Rank = MyFaker.number().randomDouble(2, 0, 1000);
                RunMe.getInstance().add(Symbol, (float) Rank);
            }
        }, 1, 100, TimeUnit.MICROSECONDS);
        scheduledThreadPool1.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                Faker MyFaker = new Faker();
                double Rank = MyFaker.number().randomDouble(2, 0, 1000);
                RunMe.getInstance().tail((float) Rank);
            }
        }, 0, 10, TimeUnit.MICROSECONDS);
        scheduledThreadPool1.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                RunMe.getInstance().getNum();
            }
        }, 1, 1, TimeUnit.SECONDS);
        System.out.println("-- end --");
    }
    
    public static void main(String[] args) {
        HashMap<TemplateElement, TemplateElement> map = new HashMap();
        String a = "a";
        String b = "a";
        map.put(new TemplateElement(a, 1), new TemplateElement(a, 1));
        System.out.println(map.containsKey(new TemplateElement(a, 1)));
        
    }
    
//    public static void main(String[] args) {
//        
//    }
}
