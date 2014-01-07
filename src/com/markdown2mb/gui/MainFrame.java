package com.markdown2mb.gui;

import com.markdown2mb.service.ServiceGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 04/01/14
 * Time: 22.03
 * To change this template use File | Settings | File Templates.
 */
public class MainFrame extends JFrame{


    public MainFrame(){
        this.setLayout(new BorderLayout());
        this.setTitle("Md2MB");
        this.setSize(new Dimension(800, 800));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setJMenuBar(new MainMenuBar());
        this.add(new MainToolBar(), BorderLayout.NORTH);
        this.add(new TextFieldPanel(), BorderLayout.CENTER);




        this.setVisible(true);
    }
}
