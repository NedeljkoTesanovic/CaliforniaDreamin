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
        rnd = new Random();
        tools = new ArrayList<Tool>();
        tools.add(new Pan());
        tools.add(new Sluice());
    }
    
    public void showStats(){
        System.out.println("\t49-r status");
        System.out.println("Endurance: " + endurance + "%");
        System.out.println("Money: " + money);
        System.out.println("Sluice durability:" + tools.get(1).getDurability() + "%");
        System.out.println("Number of cradles:" + (tools.size() - 2));
    }     
    
    public void useTools(){
        if(endurance == 0){
            System.out.println("You find yourself unable to work and earn nothing this week. You try to relax, but it doesn't help.");
            System.out.println("You conclude that only a visit to the saloon would recuperate you.");

            return;
        }
        int profit = 0;
        int tempProfit = 0;
        ArrayList<Tool> toRemove = new ArrayList<Tool>();
        for(Tool t: tools){
            if(t.getDurability() == 0){
                System.out.println("This " + t.getType() + " is unusable! (Durability = 0%)");
            } else {
            tempProfit = t.useTool();
            System.out.println("Used " + t.getType() + " and earned $" + tempProfit + ". " + t.getType() + " new durability is " + t.getDurability() + "%");
            }
            if(t.getType().equals("Cradle") && t.getDurability() == 0){
                    toRemove.add(t);
                }
            profit += tempProfit;
        }
        tools.removeAll(toRemove);
        if(!toRemove.isEmpty()){
            System.out.println("Discarded " + toRemove.size() + " broken cradles");
            System.out.println("Cradles left: " + (tools.size() - 2));
        }
        System.out.println("Total profit: " + profit);
        money += profit;
    }
    
    public void buyFood(){
        int m = 30 + rnd.nextInt(21);
        money -= m;
        System.out.println("Bought food for $" + m);
    }
    
    public void loseEndurance(){
        if(endurance == 0){
            return;
        }
        int n = 10 + rnd.nextInt(16);
        endurance -= n;
        if(endurance < 0)
            endurance = 0;
        System.out.println("Endurance loss: " + n + "%");
    }
    
    private void fixSluice(){
        if(tools.get(1) instanceof Sluice sluice){
            sluice.repair();
            money -= sluice.getPrice();
            System.out.println("Spent $" + sluice.getPrice() + " to repair the sluice; Sluice durability = 100%");
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
        System.out.println("Endurance raised by " + pleasure +"%; Spent $" + spendings);
    }
    
    public void showStatus(){
        System.out.println("Endurance = " + endurance + "%");
        System.out.println("Money = $" + money);
    }
    
    public void buyCradles(){
        System.out.println("You pass by a tool vendor that recognizes you as a 49-r.");
        System.out.println("He asks you how many cradles you'd like to buy - you answer (integer):");
        int n = 0;
        while(true){
            try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            n = Integer.parseInt(reader.readLine());
            } catch (Exception ex) {
                System.out.println("Indian ambush! :(\n Info:");
                ex.printStackTrace();
                System.out.println("(Input the number of cradles you'd like to buy (integer): ");
                continue;
                }
        if(n < 0){
            System.out.println("The shopkeeper isn't interested in buying cradles, only in selling them. (Input positive number):");
            continue;
        }
        if (n == 0){
            System.out.println("You decide to resist impulse shopping...");
            return;
        }
            System.out.println("You decide to buy " + n + " new cradles...");
        if (money < new Cradle().getPrice() * n){
            System.out.println("...but the shopkeeper points out that you'd need more money and he's not willing to haggle.");
            System.out.println("You try again:");
            continue;
        }
        
        int expense;
        expense = 0;
        for(int i = n; i > 0; i--){
            Cradle cradle = new Cradle();
            tools.add(cradle);
            expense += cradle.getPrice();
        }
        money -= expense;
        System.out.println("Bought " + n + " cradles for $" + expense);
        System.out.println("Money left: $" + money);
        return;
        }
    }
    
    public void itIsSundayAgain(){

        int opcija = 9;
        while(opcija == 9){
            System.out.println("It is Sunday again! You choose to:");
            System.out.println("1. put your feet up and relax (Continue to next week)");
            System.out.println("2. go to the saloon and have some fun (5-50% endurance gain for $50-200)");
            System.out.println("3. repair your trusty sluice (Sluice gets fully repaired for $100)");

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
                        System.out.println("... and you have a grand time there.");
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
                default:
                    System.out.println("Invalid option!");
                    opcija = 9;
            }
        }
    }
}
