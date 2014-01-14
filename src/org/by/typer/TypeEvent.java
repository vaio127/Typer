/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer;

import java.awt.event.KeyEvent;
import org.by.typer.job.Job;

public class TypeEvent/* implements Serializable*/ {
    
    private String text;
    private int hotkey;
    private boolean active;
    private int mask;
    private final Job job;
    
    public TypeEvent(String text, int hotkey, int mask, boolean active) {
        this.text = text;
        this.hotkey = hotkey;
        this.mask = mask;
        this.active = active;
        job = new Job(text);
    }
    
    public void setText(String text) {
        this.text = text;
    }
    
    public void setHotkey(int hotkey) {
        this.hotkey = hotkey;
        
    }
    
    public void setMask(int mask) {
        this.mask = mask;
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }
    
    public String getText() {
        return text;
    }
    
    public int getHotkey() {
        return hotkey;
    }
    
    public int getMask() {
        return mask;
    }
    
    public boolean isActive() {
        return active;
    }
    
    public String getHotkeyString() {
        return KeyEvent.getKeyText(hotkey);
    }
    
    public Job getJob() {
        return job;
    }
    
    
}
