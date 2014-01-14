/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer;

import org.by.typer.misc.ExtendedKeyCodes;
import java.awt.event.KeyEvent;
import java.util.HashMap;

public class Utils {
    
    public static final String[] TOP_LEVEL_KEYS = { 
        "F1", "F2", "F3", "F4", "F5", "F6", "F7", "F8", "F9", "F10", "F11", "F12", 
        "F13", "F14", "F15", "F16", "F17", "F18", "F19", "F20", "F21", "F22", 
        "F23", "F24", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", 
        "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", 
        "0", "1", "2", "3", "4", "5", "6", "7", "8", 
        "9", "Space", "Page Up", "Page Down", "End", "Home", "Left", "Up", "Right", "Down", 
        "Comma", "Minus", "Period", "Slash", "Semicolon", "Equals", 
        "Open Bracket", "Back Slash", "Close Bracket", "NumPad-0", "NumPad-1", "NumPad-2", 
        "NumPad-3", "NumPad-4", "NumPad-5", "NumPad-6", "NumPad-7", "NumPad-8", "NumPad-9", 
        "NumPad *", "NumPad +", "NumPad ,", "NumPad -", "NumPad .", "NumPad /","Delete",
        "Cancel", "Backspace", "Tab", "Enter", "Clear", "Pause", "Caps Lock", "Kana", 
        "Final", "Kanji", "Escape", "Convert", "No Convert", "Accept", "Mode Change", 
        "Dead Grave", "Dead Acute", "Dead Circumflex", "Dead Tilde", "Dead Macron", "Dead Breve", 
        "Dead Above Dot", "Dead Diaeresis", "Dead Above Ring", "Dead Double Acute", "Dead Caron", 
        "Dead Cedilla", "Dead Ogonek", "Dead Iota", "Dead Voiced Sound", "Dead Semivoiced Sound", 
        "Num Lock", "Scroll Lock", "Ampersand", "Asterisk", "Double Quote", "Less", "Print Screen", 
        "Insert", "Help", "Greater", "Left Brace", "Right Brace", "Back Quote", "Quote", "Up", 
        "Down", "Left", "Right", "Alphanumeric", "Katakana", "Hiragana", "Full-Width", "Half-Width", 
        "Roman Characters", "All Candidates", "Previous Candidate", "Code Input", "Japanese Katakana", 
        "Japanese Hiragana", "Japanese Roman", "Kana Lock", "Input Method On/Off", "At", "Colon", 
        "Circumflex", "Dollar", "Euro", "Exclamation Mark", "Inverted Exclamation Mark", 
        "Left Parenthesis", "Number Sign", "Plus", "Right Parenthesis", "Underscore", "Windows", 
        "Context Menu",  "Compose", "Begin", "Alt Graph", "Stop", "Again", "Props", "Undo", 
        "Copy", "Paste", "Find", "Cut" 
    };
 
    public static int[] getKeysFromString(String s) {
        return getKeysFromString(s.toCharArray());
    }
    
    public static int[] getKeysFromString(char[] c) {
        int[] a = new int[c.length];
        for (int i = 0; i < a.length; i++) {
            a[i] = ExtendedKeyCodes.getExtendedKeyCodeForChar(c[i]);
        }
        return a;
    }

    public static boolean isNonCapitalizedSpecialCharacter(char c) {
        char[] a = {'/', '=', '-', '`', ',', '.', ';', '\'', '[', ']'};
        for (char b : a) {
            if (b == c) {
                return true;
            }
        }
        return false;
    }

    public static int getSpecialCharacterEquivalent(int key, int c) {
        HashMap<Integer, Integer> keyMap = new HashMap<Integer, Integer>();
        keyMap.put(KeyEvent.VK_EXCLAMATION_MARK, KeyEvent.VK_1);
        keyMap.put(KeyEvent.VK_UNDERSCORE, KeyEvent.VK_MINUS);
        
        
        HashMap<Character, Integer> charMap = new HashMap<Character, Integer>();
        charMap.put('?', KeyEvent.VK_SLASH);
        charMap.put('@', KeyEvent.VK_2);
        charMap.put('#', KeyEvent.VK_3);
        charMap.put('$', KeyEvent.VK_4);
        charMap.put('%', KeyEvent.VK_5);
        charMap.put('^', KeyEvent.VK_6);
        charMap.put('&', KeyEvent.VK_7);
        charMap.put('*', KeyEvent.VK_8);
        charMap.put('(', KeyEvent.VK_9);
        charMap.put(')', KeyEvent.VK_0);
        charMap.put('{', KeyEvent.VK_BRACELEFT);
        charMap.put('}', KeyEvent.VK_BRACERIGHT);
        charMap.put('~', ExtendedKeyCodes.getExtendedKeyCodeForChar('`'));
        charMap.put(':', KeyEvent.VK_SEMICOLON);
        charMap.put('"', KeyEvent.VK_QUOTE);
        charMap.put('<', KeyEvent.VK_COMMA);
        charMap.put('>', KeyEvent.VK_PERIOD);
        charMap.put('|', KeyEvent.VK_BACK_SLASH);
        
        
        if (keyMap.containsKey((Integer) key)) {
            return keyMap.get((Integer) key);
        }
        if (charMap.containsKey((Character) (char) c)) {
            return charMap.get((Character) (char) c);
        }
        return key;
    }
    
    public static int getKeyForName(String name) {
        for (int i = 0; i < (1 << 16); i++) {
            String s = KeyEvent.getKeyText(i);
            if (s.equals(name)) {
                return i;
            }
        }
        return 0;
    }
    
    public static enum MODIFIER_KEYS {
        SHIFT(1, "Shift"),
        CTRL(2, "Ctrl"),
        META(4, "Meta"),
        ALT(8, "Alt");
        
        private final int mask;
        private final String text;
        private MODIFIER_KEYS(int mask, String text) {
            this.mask = mask;
            this.text = text;
        }
        
        public String getText() {
            return text;
        }
        
        public int getMask() {
            return mask;
        }
    }
}
