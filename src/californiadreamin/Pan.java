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
public class Pan extends Tool{
    Pan(){
        super();
        setType("Pan");
    }
    @Override
    public int useTool(){
        return rnd.nextInt(61); //$0-60
    }
}
