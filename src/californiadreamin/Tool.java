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
    protected Random rnd;
    
    Tool(){
        durability = 100;
        rnd = new Random();
    }
    
    public int getDurability(){
        return durability;
    }
    abstract public int useTool();
}
