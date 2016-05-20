/*
 * Copyright (C) 2016 SINA Corporation
 *  
 *  
 * 
 * This script is firstly created at 2016-05-20.
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
import org.apache.log4j.Logger;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.PatternLayout;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class RunMe {

    protected static RunMe Singleton;

    protected SortedSet<TemplateElement> Set;
    protected Logger logger;

    protected RunMe() {
        this.Set = Collections.synchronizedSortedSet(new TreeSet(new TemplateComparator()));
        this.Set.add(new TemplateElement("FIRST", 0));
        this.Set.add(new TemplateElement("LAST", 65535));
        this.logger = Logger.getLogger("test");
        this.logger.addAppender(new ConsoleAppender(new PatternLayout("%-4r [%t] %-5p %c %x - %m%n")));
    }

    public static RunMe getInstance() {
        if (Singleton == null) {
            synchronized (RunMe.class) {
                if (Singleton == null) {
                    Singleton = new RunMe();
                }
            }
        }
        return Singleton;
    }

    public void add(String Symbol, float Rank) {
        try {
            synchronized (this.Set) {
//                this.logger.debug("Add " + Symbol + " " + Rank + " - Old " + Set);
                this.Set.add(new TemplateElement(Symbol, Rank));
//                this.logger.debug("Add " + Symbol + " " + Rank + " - New " + Set);
//                this.logger.info("Run Add Op");
            }
        } catch (Exception E) {
            this.logger.error("Add Error " + E);
        }
    }

    public SortedSet getEmptySet(boolean WithHead, boolean WithTail) {
        SortedSet Temp = Collections.synchronizedSortedSet(new TreeSet(new TemplateComparator()));
        if (WithHead) {
            Temp.add(new TemplateElement("FIRST", 0));
        }
        if (WithTail) {
            Temp.add(new TemplateElement("LAST", 65535));
        }
        return Temp;
    }

    public void tail(float Rank) {
        try {
            synchronized (this.Set) {
                if (!this.Set.isEmpty() && this.Set.size() > 2) {
//                    this.logger.debug("Rank " + Rank + " - Old " + Set);
                    SortedSet Temp = this.getEmptySet(true, false);
                    Temp.addAll(this.Set.tailSet(new TemplateElement("null", Rank)));
                    this.Set = Temp;
//                    this.logger.debug("Rank " + Rank + " - New " + Set);
//                    this.logger.info("Run Tail Op");
                } else {
                    this.logger.info("Set Empty - Rank " + Rank);
                }
            }
        } catch (Exception E) {
            this.logger.error("Tail Error " + E);
        }
    }

    public void getNum() {
        synchronized (this.Set) {
            int Num = this.Set.size();
            Num -= 2;
            this.logger.info("Total Num - " + Num);
        }
    }
}
