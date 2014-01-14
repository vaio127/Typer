/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer.job;

import java.util.ArrayList;

/**
 *
 * @author owner
 */
public class JobQueue {
    
    private static final ArrayList<Job> queue = new ArrayList<Job>();
    
    public static void addJob(Job j) {
        queue.add(j);
    }
    
    public static Job getLatestJob() {
        return queue.isEmpty() ? null : queue.get(0);
    }
    
    public static boolean hasJob() {
        return !queue.isEmpty();
    }
    
    public static void removeJob() {
        queue.remove(0);
    }
    
    public static void clear() {
        queue.clear();
    }
    
    public static boolean contains(Job j) {
        return queue.contains(j);
    }
    
}
