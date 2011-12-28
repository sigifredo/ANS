/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author raziel
 */
public class Produccion
{
    
    protected NoTerminal _izquierda;
    protected Elemento _derecha[];
    
    /**
     * Constructor de la clase.
     * 
     * @param izquierda
     * @param derecha 
     */
    public Produccion(NoTerminal izquierda, Elemento derecha[])
    {
        _izquierda = izquierda;
        _derecha = derecha;
    }
    
    /**
     * Constructor de la clase.
     * 
     * @param produccion 
     */
    public Produccion(String produccion)
    {
        String elementos[] = produccion.split("->");
        if(elementos.length == 2)
        {
            
        }
        else
        {
            throw new RuntimeException("Compiled Code");
        }
    }
}
