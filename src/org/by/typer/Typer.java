/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer;

import org.by.typer.gui.UI;
import org.by.typer.misc.KeyPressed;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import org.by.typer.job.Job;
import org.by.typer.job.JobQueue;
import org.jnativehook.GlobalScreen;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class Typer implements NativeKeyListener {

    private static UI ui;
    private static Typer typer;
    private static Robot robot;
    private static final ArrayList<TypeEvent> events = new ArrayList<TypeEvent>();
    private static String loc;
    private static boolean debug;
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        if (args.length == 1) {
            typer = new Typer(args[0]);
        } else {
            typer = new Typer("typer.dat");
            //throw new java.lang.IllegalArgumentException("Arguments must include location of config file.");
        }
        /*SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                ui = new UI(getInstance());
                ui.setVisible(true);
            }
        });*/
    }
    
    private Typer(String loc) {
        Typer.loc = loc;
        try {
            GlobalScreen.registerNativeHook();
            GlobalScreen.getInstance().addNativeKeyListener(new KeyPressed());
            GlobalScreen.registerNativeHook();
            GlobalScreen.getInstance().addNativeKeyListener(this);
            robot = new Robot();
            if (loc.equals("--test")) {
                
            } else
                loadFromFile(loc);
            
            try {
                for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                    if ("Nimbus".equals(info.getName())) {
                        UIManager.setLookAndFeel(info.getClassName());
                        break;
                    }
                }
            } catch (Exception e) { }
            ui = new UI();
            ui.initListeners();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Typer getInstance() {
        return typer;
    }
    
    public static UI getUI() {
        return ui;
    }
    
    public static ArrayList<TypeEvent> getEvents() {
        return events;
    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent nke) {
       //System.out.println(nke.getKeyCode());
        //System.out.println(nke.getModifiers());
        for (TypeEvent t : events) {
            if (t.getHotkey() == nke.getKeyCode() && nke.getModifiers() == t.getMask()) {
                if (t.isActive()) {
                    
                    //typeString(t.getText());
                    if (!JobQueue.contains(t.getJob()))
                        JobQueue.addJob(t.getJob());
                }
            }
        }
    }
    
    public void typeString(Job j) {
        typeString(j.getText());
    }
    
    public synchronized void typeString(String s) {
        char[] c = s.toCharArray();
        int[] keys = Utils.getKeysFromString(c);
        for (int i = 0; i < c.length; i++) {
            if (Character.isUpperCase(c[i])) {
                robot.keyPress(KeyEvent.VK_SHIFT);
                typeKey(keys[i]);
                robot.keyRelease(KeyEvent.VK_SHIFT);
            } else if (c[i] == '\\') {
                if (i < c.length - 1) {
                    i++;
                    if (c[i] == 'n')
                        typeKey(KeyEvent.VK_ENTER);
                    if (c[i] == '\\')
                        typeKey(KeyEvent.VK_BACK_SLASH);
                    if (c[i] == 't')
                        typeKey(KeyEvent.VK_TAB);
                    if (c[i] == 'b')
                        typeKey(KeyEvent.VK_BACK_SPACE);
                } else
                    typeKey(KeyEvent.VK_BACK_SLASH);
            } else if (Character.isLowerCase(c[i]) || Character.isDigit(c[i]) || Utils.isNonCapitalizedSpecialCharacter(c[i])) {
                 typeKey(keys[i]);
            } else {
                robot.keyPress(KeyEvent.VK_SHIFT);
                typeKey(Utils.getSpecialCharacterEquivalent(keys[i], c[i]));
                robot.keyRelease(KeyEvent.VK_SHIFT);
            }
            //System.out.println((int)c[i]);
        }
    }
    
    public void typeKey(int i) {
        
        robot.keyPress(i);
        robot.keyRelease(i);
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent nke) {
        if (JobQueue.hasJob()) {
            while (!isControlKeyPressed()) {
                if (JobQueue.hasJob()) {
                    typeString(JobQueue.getLatestJob());
                    JobQueue.removeJob();
                } else
                    break;
            }
        }
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent nke) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void loadFromFile(String loc) {
        if (loc.equals("--test"))
            return;
        try {
            BufferedReader d = new BufferedReader(new FileReader(loc));
            String s;
            while ((s = d.readLine()) != null) {
                if (s.contains("\t")) {
                    String[] a = s.split("\t");
                    TypeEvent t = new TypeEvent(a[0], Integer.parseInt(a[1]), Integer.parseInt(a[2]), Boolean.parseBoolean(a[3]));
                    System.out.println(t.getText() + "\t" + t.getHotkeyString() + "\t" + t.getMask() + "\t" + t.isActive());
                    events.add(t);
                }
            }
            d.close();
            if (events.isEmpty())
                throw new Exception();
            System.out.println(events.size() + " events loaded.");
        } catch (java.io.FileNotFoundException fnf) {
            System.err.println("Dat file not found.  Please create it as 'typer.dat' in the same directory as this jar.");
        } catch (Exception e) {
            System.out.println("No events found.");
            System.out.println("Use the following format:");
            System.out.println("<Message Text>\t<Hotkey number>\t<Modifier mask>\t<active>");
        }
    }
    
    public static void saveToFile() {
        if (loc.equals("--test"))
            return;
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(loc));
            for (TypeEvent t : getEvents()) {
                bw.write(t.getText() + '\t');
                bw.write(Integer.toString(t.getHotkey()) + '\t');
                bw.write(Integer.toString(t.getMask()) + '\t');
                bw.write(Boolean.toString(t.isActive()));
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static String getFileLocation() {
        return loc;
    }
    
    public static void setFileLocation(String loc) {
        Typer.loc = loc;
    }
    
    public static boolean isControlKeyPressed() {
        int[] k = { 
            KeyEvent.VK_CONTROL, KeyEvent.VK_META,
            KeyEvent.VK_SHIFT, KeyEvent.VK_ALT
        };
        for (int a : k) {
            if (KeyPressed.isKeyPressed(a))
                return true;
        }
        return false;
    }
    
    public static void out(String out) {
        out(out, true);
    }
    
    public static void out(String out, boolean debug) {
        if ((Typer.debug && debug) || !debug) {
            System.out.println(out);
        }
    }
}
