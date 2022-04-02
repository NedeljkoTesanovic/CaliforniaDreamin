/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

/**
 *
 * @author nedel
 */
public class Cradle extends Tool{
    private int price;
    Cradle(){
        super();
        price = 30;
    }
    public int getPrice(){
        return price;
    }
    @Override
    public int useTool(){
        if(rnd.nextInt(100) < 20){
            durability = 0;
        }
        return rnd.nextInt(31);
    }
}
