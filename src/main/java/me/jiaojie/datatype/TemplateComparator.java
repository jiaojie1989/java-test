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

import java.util.Comparator;
import me.jiaojie.datatype.TemplateElement;

/**
 *
 * @author jiaojie <jiaojie@staff.sina.com>
 */
public class TemplateComparator implements Comparator<TemplateElement> {

    @Override
    public int compare(TemplateElement A, TemplateElement B) {
        if (A.getRank() >= B.getRank()) {
            return 1;
        } else {
            return -1;
        }
    }
}
