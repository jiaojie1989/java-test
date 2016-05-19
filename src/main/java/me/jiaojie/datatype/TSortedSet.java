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
import java.util.SortedSet;
import java.util.Iterator;
import me.jiaojie.datatype.TemplateComparator;
import me.jiaojie.datatype.TemplateElement;
import java.util.Collections;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class TSortedSet {

    public static void main(String[] args) {
        System.out.println("--start--");
        SortedSet<TemplateElement> Set = Collections.synchronizedSortedSet(new TreeSet(new TemplateComparator()));
        Set.add(new TemplateElement("bidu", 1.0));
        Set.add(new TemplateElement("sina", 2.0));
        Set.add(new TemplateElement("baba", 3.0));
        Set.add(new TemplateElement("sohu", 4.0));
        Set.add(new TemplateElement("taob", 5.0));
        Set.add(new TemplateElement("mi", 6.2));
        Set.add(new TemplateElement("letv", 7.2));
//        Iterator It = Set.tailSet(new TemplateElement("null", 3)).iterator();
//        while (It.hasNext()) {
//            System.out.println(Set.remove(It.next()));
//        }
        synchronized (Set) {
            SortedSet<TemplateElement> Temp = Set.headSet(new TemplateElement("null", 4.0));
            Set = Collections.synchronizedSortedSet(Set.tailSet(new TemplateElement("null", 4.0)));
        }

        System.out.println(Set);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
//        scheduledThreadPool.schedule(new Runnable() {
//            public void run() {
//                System.out.println("delay 3 seconds");
//            }
//        }, 3, TimeUnit.SECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            public void run() {
//                synchronized (Set) {
//                    SortedSet<TemplateElement> Temp = Set.headSet(new TemplateElement("null", 4.0));
//                    Set = Collections.synchronizedSortedSet(Set.tailSet(new TemplateElement("null", 4.0)));
//                }
            }
        }, 1, 1000, TimeUnit.MICROSECONDS);
        scheduledThreadPool.scheduleWithFixedDelay(new Runnable() {
            public void run() {
                System.out.println("delay 3 seconds");
            }
        }, 1, 1000, TimeUnit.MICROSECONDS);
        System.out.println("-- end --");
    }
}
