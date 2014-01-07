package com.markdown2mb.gui;

import com.markdown2mb.core.ConverStrings;
import com.markdown2mb.service.ServiceGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 05/01/14
 * Time: 10.28
 * To change this template use File | Settings | File Templates.
 */
public class MainMenuBar extends JMenuBar {
    private JMenu actionsMenu;
    private JMenuItem clearMenuItem, convertMenuItem, copyMenuItem;

    private MenuActions ma;

    public MainMenuBar(){
        ma = new MenuActions();
        setUpComponents();
        this.add(actionsMenu);
    }


    private void setUpComponents(){
        actionsMenu = new JMenu("Actions");

        clearMenuItem = new JMenuItem("Clear");
        clearMenuItem.addActionListener(ma);
        clearMenuItem.setMnemonic(KeyEvent.VK_C);
        clearMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        convertMenuItem = new JMenuItem("Convert");
        convertMenuItem.addActionListener(ma);
        convertMenuItem.setMnemonic(KeyEvent.VK_K);
        convertMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.CTRL_MASK));

        copyMenuItem = new JMenuItem("Copy");
        copyMenuItem.addActionListener(ma);
        copyMenuItem.setMnemonic(KeyEvent.VK_M);
        copyMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK));



        actionsMenu.add(clearMenuItem);
        actionsMenu.add(convertMenuItem);
        actionsMenu.add(copyMenuItem);
    }

    private class MenuActions implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
           if(e.getSource() == clearMenuItem){
               ((JTextArea) ServiceGUI.getComponentByKey("MdTextArea")).setText("");
               ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).setText("");
           }else if(e.getSource() == convertMenuItem){
               String text =  ((JTextArea) ServiceGUI.getComponentByKey("MdTextArea")).getText();
               ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).setText(new ConverStrings().convertString(text));
           }else if(e.getSource() == copyMenuItem){
               String text = ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).getText();
               StringSelection stringSelection = new StringSelection(text);
               Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
               clipboard.setContents(stringSelection, null);
           }
        }
    }
}
