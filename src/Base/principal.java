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
        String sGram = "<S>-><S>c\n<S>->c<S><A><B>\n<A>->a<A>\n<A>->$\n<B>->b";
        Gramatica g = new Gramatica(sGram);

        System.out.println("Gramatica:\n" + g + "S: " +g.gramaticaS());

        // System.out.println("" + g.siguientes(g._producciones[4]._izquierda) + " - " + g._producciones[0]._izquierda);
    }
    
}
