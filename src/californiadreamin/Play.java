/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package californiadreamin;

/**
 *
 * @author nedel
 */
public class Play {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("\t-= California Dreamin' =-");
        System.out.println("A text-based gold rush survival game\n\n");
        
        GoldRush goldRush = new GoldRush();
        goldRush.loadGame();
        goldRush.survive();
    }
    
}
