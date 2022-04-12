/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nedel
 */
public class GoldRush {
    private FortyNiner fortyNiner;
    private File savedGame;
    private int week;
    private boolean newGame;
    GoldRush(){
        week = 1;
        newGame = true;
    }
    
    public void survive(){
        if(fortyNiner == null){
            fortyNiner = new FortyNiner();
        }
        if(!newGame){
            System.out.println("\tWeek no. " + week + "/20");
            fortyNiner.showStats();
            fortyNiner.itIsSundayAgain();
            fortyNiner.buyCradles();
            week++;
        }
        while(week <= 20){
            System.out.println("\tWeek no. " + week + "/20");            
            fortyNiner.useTools();
            fortyNiner.buyFood();
            fortyNiner.loseEndurance();
            fortyNiner.showStats();
            System.out.println("Save and exit? (Y - Yes; Other - No)");
            try{
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = new String("");
                try {
                    input = reader.readLine().toUpperCase();
                } catch (IOException ex) {
                    System.out.println("Indian ambush! :(\n Info:");
                    ex.printStackTrace();
                }
                if(input.toUpperCase().equals("Y") || input.toUpperCase().equals("YES")){
                    System.out.println("Saving...");
                    saveGame();
                    System.out.println("Exiting...");
                    return;
                }
            } catch (Exception ex) {
                System.out.println("Indian ambush! :(\n Info:");
                ex.printStackTrace();
            }
            System.out.println("The gold rush goes on...");
            fortyNiner.itIsSundayAgain();
            fortyNiner.showStats();
            fortyNiner.buyCradles();
            week++;
        }
        System.out.println("The gold rush craze comes to an end, you and the other 49-rs decide to retire...");
        System.out.println("Your total profit ended up being $" + fortyNiner.getMoney());
        System.out.println("\t-= GAME OVER =-");
        try{
            System.out.println("Press ENTER to exit");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String input = new String("");
            input = reader.readLine();
        } catch(Exception ex){
                System.out.println("Indian ambush! :(\n Info:");
                ex.printStackTrace();
        }        
    }
    public void loadGame(){
        try{
            savedGame = new File("GoldRushSaved.txt");
            Scanner scanner = new Scanner(savedGame);
            if(savedGame.canRead()){
                System.out.println("Save game found! Load save? (Y - Yes; Other - No)");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String input = new String("");
                try {
                    input = reader.readLine().toUpperCase();
                } catch (IOException ex) {
                    System.out.println("Indian ambush! :(\n Info:");
                    ex.printStackTrace();
                }
                if(input.toUpperCase().equals("Y") || input.toUpperCase().equals("YES")){
                    System.out.println("Continuing session...");
                    newGame = false;
                    ArrayList<Tool> tools;
                    if (fortyNiner == null){
                        fortyNiner = new FortyNiner();
                        tools = new ArrayList<Tool>();
                        tools.add(new Pan());
                        tools.add(new Sluice());
                    } else {
                        tools = fortyNiner.getTools();
                    }
                    //Week no. N
                    input = scanner.nextLine();
                    week = Integer.parseInt(input.substring(9));
                    //49er endurance: N%
                    input = scanner.nextLine();
                    fortyNiner.setEndurance(Integer.parseInt(input.substring(16, input.length() - 1)));
                    //49er money: $N
                    input = scanner.nextLine();
                    fortyNiner.setMoney(Integer.parseInt(input.substring(13)));
                    //Sluice durability: N%
                    input = scanner.nextLine();
                    Sluice sluice = new Sluice();
                    sluice.setDurability(Integer.parseInt(input.substring(19, input.length() - 1)));
                    tools.set(1, sluice);
                    while(scanner.hasNextLine()){
                        //Cradle durability: N%
                        input = scanner.nextLine();
                        Cradle cradle = new Cradle();
                        cradle.setDurability(Integer.parseInt(input.substring(19, input.length() - 1)));
                        tools.add(cradle);
                    }
                    fortyNiner.setTools(tools);
                }
            } else {
                System.out.println("Starting a new game...");
                return;
            }
        } catch (Exception ex){
            System.out.println("Indian ambush! :( Info:");
            ex.printStackTrace();
            System.out.println("No save present, starting a new game...");
        }
    }
    private void saveGame(){
        try {
                FileWriter writer = new FileWriter("GoldRushSaved.txt", false);
                writer.write("Week no. "+ week +"\n");
                writer.write("49er endurance: "+ fortyNiner.getEndurance() +"%\n");
                writer.write("49er money: $"+ fortyNiner.getMoney() +"\n");
                writer.write("Sluice durability: "+ fortyNiner.getTools().get(1).getDurability() +"%\n");
                for(int i = 2; i < fortyNiner.getTools().size(); i++){
                    writer.write("Cradle durability: "+ fortyNiner.getTools().get(i).getDurability() +"%\n");
                }
                writer.close();
                System.out.println("Game saved!");
              } catch (Exception ex) {
                System.out.println("Indian ambush! :(\n Info");
                ex.printStackTrace();
              }
    }
}
