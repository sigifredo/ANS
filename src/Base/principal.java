/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author raziel
 */
public class principal
{
    
    public static void main(String args[])
    {
        String sGram = "<A>->a<B>\n<A>-><B>\n<B>->d\n<B>->┤";
        Gramatica g = new Gramatica(sGram);
        NoTerminal n1 = new NoTerminal("A");
        NoTerminal n2 = new NoTerminal("A");
        
        if(n1.equals(n2)) System.out.println("Iguales");
        else System.out.println("Diferentes");
        
        System.out.println("Gramatica:\n" + g + "S: " +g.gramaticaS());
    }
    
}
