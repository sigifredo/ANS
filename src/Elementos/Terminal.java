/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

/**
 *
 * @author raziel
 */
public class Terminal extends Elemento
{
    
    /**
     * Constructor.
     * 
     * @param simbolo Letra que tendrá el no terminal.
     */
    private Terminal(String simbolo)
    {
        super(simbolo, Tipo.terminal);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param terminal Objeto de la clase terminal en base al cual se creará el objeto nuevo.
     */
    private Terminal(Terminal terminal)
    {
        super(terminal._simbolo, terminal._tipo);
    }

    public static Elemento crearTerminal(String simbolo)
    {
        if(mElementos.get(simbolo) == null)
        {
            return new Terminal(simbolo);
        }
        else return mElementos.get(simbolo);
    }
}
