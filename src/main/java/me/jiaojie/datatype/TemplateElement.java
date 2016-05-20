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

import java.util.Objects;

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

    @Override
    public String toString() {
        return this.Name;
    }

    @Override
    public int hashCode() {
        return Name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TemplateElement other = (TemplateElement) obj;
        if (!Objects.equals(this.Name, other.Name)) {
            return false;
        }
        if (Double.doubleToLongBits(this.Rank) != Double.doubleToLongBits(other.Rank)) {
            return false;
        }
        return true;
    }

}
