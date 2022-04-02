/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nedel
 */
public class FortyNiner {
    private int endurance;
    private int money;
    private ArrayList<Tool> tools;
    private Random rnd;
    
    public int getEndurance() {return endurance;}
    public int getMoney() {return money;}
    public ArrayList<Tool> getTools(){return tools;}
    public void setEndurance(int e) {endurance = e;}
    public void setMoney(int m) {money = m;}
    public void setTools(ArrayList<Tool> t) {tools = t;}
    
    FortyNiner(){
        endurance = 100;
        money = 100;
        tools = new ArrayList<Tool>();
        tools.add(new Pan());
        tools.add(new Sluice());
        //tools.add(new Cradle());
    }
    
    public void useTools(){
        int profit = 0;
        int tempProfit = 0;
        for(Tool t: tools){
            if(t.getDurability() == 0){
                System.out.println("This " + t.getType() + " is unusable! (Durability = 0%)");
            }
            if (t.getType().equals("Cradle")){
                tools.remove(t);
                System.out.println("Cradle discarded.");
                continue;
            }
            tempProfit = t.useTool();
            System.out.println("Used " + t.getType() + " and earned $" + tempProfit + ". " + t.getType() + " durability is " + t.getDurability() + "%");
            profit += tempProfit;
        }
        System.out.println("Total profit: " + profit);
        money += profit;
    }
    
    public void buyFood(){
        int m = 30 + rnd.nextInt(21);
        money -= m;
        System.out.println("Bought food for $" + m + ". Money left: $" + money);
    }
    
    public void loseEndurance(){
        endurance -= (10 + rnd.nextInt(16));
        if(endurance < 0)
            endurance = 0;
        System.out.println("Current endurance = " + endurance + "%");
    }
    
    private void fixSluice(){
        if(tools.get(1) instanceof Sluice sluice){
            sluice.repair();
            money -= sluice.getPrice();
            System.out.println("Spent $" + sluice.getPrice() + " to repair the sluice; Sluice durability = 100%; Money = $" + money);
            tools.set(1, sluice);
        }
    }
    
    private void goToSaloon(){
        int pleasure = 5 + rnd.nextInt(46);
        int spendings = 50 + rnd.nextInt(151);
        
        if (spendings > money){
            spendings = money;
        }
        
        if (endurance + pleasure > 100){
            pleasure = 100 - endurance;
        }
        
        endurance += pleasure;
        money -= spendings;
        System.out.println("Went to saloon; Endurance raised by " + pleasure + "% (Endurance = " + endurance + "%); Spent $" + spendings + " (Money = $" + money + ").");
    }
    
    public void showStatus(){
        System.out.println("Endurance = " + endurance + "%");
        System.out.println("Money = $" + money);
    }
    
    public void itIsSundayAgain(){

        int opcija = 9;
        while(opcija == 9){
            System.out.println("It is Sunday again! You choose to:");
            System.out.println("1. put your feet up and relax (No endurance loss)");
            System.out.println("2. go to the saloon and have some fun (5-50% endurance gain for $50-200)");
            System.out.println("3. repair your trusty sluice (Sluice gets fully repaired for $100)");
            System.out.println("4. write a new entry into your journal (Save game)");
            System.out.println("0. retire (Exit)");

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                try {
                    opcija = Integer.parseInt(reader.readLine());
                } catch (Exception ex) {
                    System.out.println("Invalid option!");
                    opcija = 9;
                }

            switch(opcija){
                case 1:
                    System.out.println("You laze about as the day goes by...");               
                    break;
                case 2:
                    System.out.println("You decide to visit the Canyon Inn...");
                    if(money >= 50){
                        goToSaloon();
                    } else {
                        System.out.println("... but your wallet protests.");
                        opcija = 9;
                    }
                    break;
                case 3:
                    System.out.println("You decide to repair your trusty sluice...");
                    if(money>=100){
                        fixSluice();
                    } else{
                        System.out.println("... but your wallet protests.");
                        opcija = 9;
                    }
                    break;
                case 4:
                    //TODO
                    break;
                default:
                    System.out.println("Invalid option!");
                    opcija = 9;
            }
        }
    }
}
