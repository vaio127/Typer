/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer.job;

/**
 *
 * @author owner
 */
public class Job {
    
    private final String text;
    public Job(String text) {
        this.text = text;
    }
    
    public String getText() {
        return text;
    }
}
