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

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class TemplateElement {

    protected String Name;
    protected double Rank;

    public TemplateElement(String Name, double Rank) {
        this.Name = Name;
        this.Rank = Rank;
    }

    public double getRank() {
        return this.Rank;
    }

    public String toString() {
        return this.Name;
    }

}
