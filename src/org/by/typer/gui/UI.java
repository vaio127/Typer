/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package org.by.typer.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFileChooser;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.table.DefaultTableModel;
import org.by.typer.TypeEvent;
import org.by.typer.Typer;
import org.by.typer.Utils;

public final class UI extends javax.swing.JFrame implements ActionListener, MouseListener {

    private final DefaultTableModel dtm;
    private final JPopupMenu menu;
    private final JMenuItem newOption;
    private final JMenuItem editOption;
    private final JMenuItem deleteOption;
    private EventFrame eventFrame;
    private HelpFrame helpFrame;
    /**
     * Creates new form UI
     * @param cm
     */
    public UI() {
        initComponents();
        setSize(getPreferredSize());
        dtm = (DefaultTableModel)jTable1.getModel();
        dtm.addColumn("Id");
        dtm.addColumn("Text");
        dtm.addColumn("Hotkeys");
        dtm.addColumn("Active");
        jTable1.getColumn("Id").setMaxWidth(40);
        jTable1.getColumn("Active").setMaxWidth(70);
        
        menu = new JPopupMenu();
        newOption = menu.add("New");
        editOption = menu.add("Edit");
        deleteOption = menu.add("Delete");
        menu.pack();
        
        
        loadEvents();
        
        setVisible(true);
    }
    
    public void initListeners() {
        newOption.addActionListener(this);
        editOption.addActionListener(this);
        deleteOption.addActionListener(this);
        jTable1.addMouseListener(this);
        jScrollPane2.addMouseListener(this);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Typer");

        jTable1.setModel(new TyperTableModel());
        jTable1.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(jTable1);

        jMenu1.setText("File");

        jMenuItem2.setText("Open File");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem2);

        jMenuItem3.setText("Save as");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem3);

        jMenuItem4.setText("Close");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Help");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        jMenuItem5.setText("Help");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem5);

        jMenuItem1.setText("About");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 277, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
        
    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        JOptionPane.showMessageDialog(this, "Written by Ben Young\nPlease let me know of any bugs or ideas for improvement.\n"
                + "byoung@instructure.com\nMuch wow, many awesome, so convenient!", "Typer", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.setMultiSelectionEnabled(false);
        if (f.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            Typer.getEvents().clear();
            Typer.setFileLocation(f.getSelectedFile().getAbsolutePath());
            Typer.loadFromFile(Typer.getFileLocation());
            loadEvents();
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        JFileChooser f = new JFileChooser();
        f.setFileSelectionMode(JFileChooser.FILES_ONLY);
        f.setMultiSelectionEnabled(false);
        if (f.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            Typer.setFileLocation(f.getSelectedFile().getAbsolutePath());
            Typer.saveToFile();
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        if (helpFrame == null || !helpFrame.isVisible()) {
            helpFrame = new HelpFrame();
            helpFrame.setVisible(true);
        } else {
            helpFrame.toFront();
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (eventFrame == null || !eventFrame.isVisible()) {
            if (ae.getSource() == newOption) {
                eventFrame = new EventFrame();
            }
            if (jTable1.getSelectedRow() >= 0) {
                if (ae.getSource() == editOption) {
                    TypeEvent t = Typer.getEvents().get(jTable1.getSelectedRow());
                    eventFrame = new EventFrame(t.getText(), t.getHotkey(), t.getMask(), jTable1.getSelectedRow(), t.isActive());
                }
                if (ae.getSource() == deleteOption) {
                    int r = (Integer)jTable1.getValueAt(jTable1.getSelectedRow(), 0);
                    Typer.getEvents().remove(r);
                    loadEvents();
                    Typer.saveToFile();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please finish what you're doing before attempting this.");
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mousePressed(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        if (me.getSource() == jTable1) {
            int row = jTable1.rowAtPoint(me.getPoint());
            int column = jTable1.columnAtPoint(me.getPoint());
            if (row >= 0) {
                if (jTable1.getSelectedRow() != row)
                    jTable1.changeSelection(row, column, false, false);
            }
        }
        if (me.getButton() == MouseEvent.BUTTON3 || me.getClickCount() == 2)
            menu.show(me.getComponent(), me.getX(), me.getY());
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

    public void loadEvents() {
        dtm.setRowCount(0);
        for (int i = 0; i < Typer.getEvents().size(); i++) {
            TypeEvent t = Typer.getEvents().get(i);
            String a = t.getHotkeyString();
            for (Utils.MODIFIER_KEYS u : Utils.MODIFIER_KEYS.values()) {
                if ((t.getMask() & u.getMask()) != 0)
                    a += " + " + u.getText();
            }
            dtm.addRow(new Object[] { i, t.getText(), a, t.isActive() });
        }
    }
    
}