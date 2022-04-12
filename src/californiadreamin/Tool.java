/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

import java.util.Random;

/**
 *
 * @author nedel
 */
public abstract class Tool {
    protected int durability;
    protected String type;
    protected Random rnd;
    
    Tool(){
        durability = 100;
        type = "Unknown tool";
        rnd = new Random();
    }
    public String getType(){ return type;}
    public void setType(String s) {type = s;}
    public int getDurability(){return durability;}
    public void setDurability(int d) {durability = d;}
    
    abstract public int useTool();
}
