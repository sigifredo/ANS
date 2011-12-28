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
        String sProduccion = "<A>->a<B>";
        Produccion prod = new Produccion(sProduccion);
        System.out.println("" + prod);
    }
    
}
