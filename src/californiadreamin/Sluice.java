/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

/**
 *
 * @author nedel
 */
public class Sluice extends Tool{
    private int price;
    Sluice(){
        super();
        setType("Sluice");
        price = 100;
    }
    public int getPrice(){
        return price;
    }
    @Override
    public int useTool(){
        int retVal = 0;
        if (durability > 0){
            retVal = rnd.nextInt(501);
        }
        
        durability -= (20 + rnd.nextInt(31));
        if(durability < 0){
            durability = 0;
        }
        return retVal;
    }
    public void repair(){
        durability = 100;
    }
        
}
