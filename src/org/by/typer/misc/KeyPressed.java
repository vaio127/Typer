/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer.misc;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

/**
 *
 * @author owner
 */
public class KeyPressed implements NativeKeyListener {
    
    private static KeyPressed keypressed;
    
    private final boolean[] keys = new boolean[(1 << 16)];
    
    public KeyPressed() {
        keypressed = this;
    }
    
    public static KeyPressed getInstance() {
        return keypressed;
    }
    
    public static boolean isKeyPressed(int key) {
        return getInstance().isPressed(key);
    }
    
    public boolean isPressed(int key) {
        if (key >= 0 && key < keys.length)
            return keys[key];
        return false;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
        if (nke.getKeyCode() >= 0 && nke.getKeyCode() < keys.length)
            keys[nke.getKeyCode()] = true;
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        if (nke.getKeyCode() >= 0 && nke.getKeyCode() < keys.length)
            keys[nke.getKeyCode()] = false;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
    }
    
}
