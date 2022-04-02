/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package californiadreamin;

import java.io.File;

/**
 *
 * @author nedel
 */
public class GoldRush {
    private FortyNiner fortyNiner;
    private File savedGame;
    GoldRush(){
        
    }
    public survive(){
        fortyNiner = new fortyNiner();
        //TODO
        
    }
    public loadGame(){
        try{
            savedGame = new File("GoldRushSaved.txt");
            //TODO
        } catch (){
            //TODO
        }
    }
    private saveGame(){
        //TODO
    }
}
