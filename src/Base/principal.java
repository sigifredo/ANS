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
        String sGram = "<A>->a<B>\n<A>-><B>\n<B>->d";
        Gramatica g = new Gramatica(sGram);
        System.out.println("Gramatica:\n" + g + "S: " +g.gramaticaS());
    }
    
}
