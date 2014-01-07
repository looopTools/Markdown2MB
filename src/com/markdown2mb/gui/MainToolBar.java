package com.markdown2mb.gui;

import com.markdown2mb.core.ConverStrings;
import com.markdown2mb.service.ServiceGUI;
import com.ngf.core.NyxImageIconFunctions;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 05/01/14
 * Time: 09.43
 * To change this template use File | Settings | File Templates.
 */
public class MainToolBar extends JToolBar {

    private JButton clearBtn, convertBtn, copyBtn;
    private ButtonController bc;

    public MainToolBar(){
        this.setFloatable(false);
        setUpComponents();
    }

    public void setUpComponents(){

        bc = new ButtonController();
        int width = 30, height = 30;

        URL imageUrl = MainToolBar.class.getResource("images/close.png");
        System.out.println(imageUrl.toString());
        clearBtn = createJButton(imageUrl, width, height);
        clearBtn.addActionListener(bc);
        clearBtn.setToolTipText("Clear");

        imageUrl = MainToolBar.class.getResource("images/convert.png");
        convertBtn = createJButton(imageUrl, width, height);
        convertBtn.addActionListener(bc);

        imageUrl = MainToolBar.class.getResource("images/copy.png");
        copyBtn = createJButton(imageUrl, width, height);
        copyBtn.addActionListener(bc);

        this.add(clearBtn);
        this.add(convertBtn);
        this.add(copyBtn);




    }

    private JButton createJButton(URL pathToImageIcon, int width, int height){


        ImageIcon iconImage = new ImageIcon(pathToImageIcon);

        Image firstImage = iconImage.getImage();

        Image secondImage = firstImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);


        return new JButton(new ImageIcon(secondImage));

        //return new JButton(NyxImageIconFunctions.createImageIcon(pathToImageIcon, width, height));
    }

    private class ButtonController implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
          if(e.getSource() == clearBtn){
              ((JTextArea) ServiceGUI.getComponentByKey("MdTextArea")).setText("");
              ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).setText("");
          }else if(e.getSource() == convertBtn){
              String text =  ((JTextArea) ServiceGUI.getComponentByKey("MdTextArea")).getText();
              ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).setText(new ConverStrings().convertString(text));
          }else if(e.getSource() == copyBtn){
              String text = ((JTextArea) ServiceGUI.getComponentByKey("MbTextArea")).getText();
              StringSelection stringSelection = new StringSelection(text);
              Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
              clipboard.setContents(stringSelection, null);
          }
        }
    }
}
