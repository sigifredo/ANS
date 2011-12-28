/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import java.util.ArrayList;

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
        _izquierda = new NoTerminal(izquierda);
        // falta
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
            // Izquierda - NoTerminal
            _izquierda = new NoTerminal(elementos[0].substring(1, elementos[0].length()-1));
            
            // Derecha - terminales y no terminales
            {
                ArrayList<Elemento> arr = new ArrayList<Elemento>();
                boolean noTerminal = false;
                for(int i = 0; i < elementos[1].length(); i++)
                {
                    if(elementos[1].charAt(i) == '<' && !noTerminal) // No terminal
                    {
                        noTerminal = true;
                        String t = "";
                        i++;
                        while(elementos[1].charAt(i) != '>')
                        {
                            t += elementos[1].charAt(i);
                            i++;
                        }
                        arr.add(new NoTerminal(t));
                    }
                    else // Terminal
                    {
                        arr.add(new Terminal("" + elementos[1].charAt(i)));
                    }
                }
                _derecha = new Elemento[arr.size()];
                _derecha = arr.toArray(_derecha);
            }
        }
        else
        {
            throw new RuntimeException("Compiled Code");
        }
    }
    
    public String toString()
    {
        String s = _izquierda + "->";
        for(int i = 0; i < _derecha.length; i++)
        {
            s += _derecha[i];
        }
        return s;
    }
}
