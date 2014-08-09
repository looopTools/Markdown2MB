package com.markdown2mb.gui;

import com.markdown2mb.service.ServiceGUI;

import javax.swing.*;
import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 04/01/14
 * Time: 22.03
 * To change this template use File | Settings | File Templates.
 */
public class TextFieldPanel extends JPanel {
    private JTextArea mdTxtArea, mbTexArea;
    private JSplitPane splitPane;
    private JScrollPane scrollLeft, scrollRight;

    public TextFieldPanel(){
        this.setLayout(new BorderLayout());


        setUpComponents();

    }

    private void setUpComponents(){
        mdTxtArea = new JTextArea();
        mbTexArea = new JTextArea();

        setUpMdTextArea();
        setUpMbTextArea();

        scrollLeft = new JScrollPane(mdTxtArea);
        scrollRight = new JScrollPane(mbTexArea);

        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollLeft, scrollRight);
        splitPane.setDividerLocation(400);
        this.add(splitPane);
    }

    private void setUpMdTextArea(){
        mdTxtArea.setLineWrap(true);
        ServiceGUI.addComponent("MdTextArea", mdTxtArea);

    }

    private void setUpMbTextArea(){
        mbTexArea.setEditable(false);
        mbTexArea.setLineWrap(true);
        ServiceGUI.addComponent("MbTextArea", mbTexArea);
    }




}
