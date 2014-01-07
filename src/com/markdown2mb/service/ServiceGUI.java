package com.markdown2mb.service;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: tools
 * Date: 05/01/14
 * Time: 09.31
 * To change this template use File | Settings | File Templates.
 */
public class ServiceGUI {

    private static HashMap<String, JComponent> allUiComponents = new HashMap<String, JComponent>();

    public static boolean addComponent(String key, JComponent component){

        if(allUiComponents.containsKey(key)){
            return false;
        }else{
            allUiComponents.put(key, component);
            return true;
        }
    }

    public static JComponent getComponentByKey(String key){
        return allUiComponents.get(key);
    }
}
