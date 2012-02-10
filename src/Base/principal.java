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
        String sGram = "<A>->a\n<A>-><B>c\n<B>->a<A>\n<B>->b<B>\n<C>-><D>a\n<D>->a<B>\n<D>->c<C>a";
        Gramatica g = new Gramatica(sGram);

        // System.out.println("Gramatica:\n" + g + "S: " +g.gramaticaS());

        g.sustitucionIzq();
        
        System.out.println("" + g);

        // System.out.println("" + g.siguientes(g._producciones[4]._izquierda) + " - " + g._producciones[0]._izquierda);
    }
    
}
