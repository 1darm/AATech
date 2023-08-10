/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kevin.model;

import javax.swing.Icon;
import javax.swing.ImageIcon;

/**
 *
 * @author engineeringuser
 */
public class Model_Menu {
    String icon;
    String name;
    MenuType type;
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public MenuType getType() {
        return type;
    }
    
    public void setType(MenuType type) {
        this.type = type;
    }
    
    public Model_Menu(String icon, String name, MenuType type) {
        this.icon = icon;
        this.name = name;
        this.type = type;
    }
    
    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/com/kevin/icon/" + icon + ".png"));
    }
    
    public Model_Menu() {
        
    }
    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
