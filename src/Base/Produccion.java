/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Elementos.Elemento;
import Elementos.NoTerminal;
import Elementos.Terminal;
import java.util.ArrayList;

/**
 *
 * @author raziel
 */
public class Produccion
{
    NoTerminal _izquierda;
    Elemento _derecha[];
    
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
     * @param izquierda
     * @param derecha 
     */
    public Produccion(NoTerminal izquierda, ArrayList<Elemento> derecha)
    {
        _izquierda = izquierda;
        _derecha = new Elemento[derecha.size()];
        _derecha = derecha.toArray(_derecha);
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
            _izquierda = (NoTerminal) NoTerminal.crearNoTerminal(elementos[0].substring(1, elementos[0].length()-1));
            
            // Derecha - terminales y no terminales
            {
                if(elementos[1].charAt(0) == '$')
                {
                    _derecha = new Elemento[1];
                    _derecha[0] = Terminal.crearTerminal("$");
                    _izquierda._anulable = true;
                }
                else
                {
                    ArrayList<Elemento> arr = new ArrayList<Elemento>();
                    for(int i = 0; i < elementos[1].length(); i++)
                    {
                        if(elementos[1].charAt(i) == '<') // No terminal
                        {
                            String t = "";
                            i++;
                            while(elementos[1].charAt(i) != '>')
                            {
                                t += elementos[1].charAt(i);
                                i++;
                            }
                            arr.add(NoTerminal.crearNoTerminal(t));
                        }
                        else // Terminal
                        {
                            arr.add(Terminal.crearTerminal("" + elementos[1].charAt(i)));
                        }
                    }
                    _derecha = new Elemento[arr.size()];
                    _derecha = arr.toArray(_derecha);
                }
            }
        }
        else
        {
            throw new RuntimeException("Compiled Code");
        }
    }
    
    /**
     * Busca un elemento en el lado derecho de la producción. Y si este existe retorna la posición.
     *
     * @param elemento
     * @return 
     */
    public int derechaContiene(Elemento elemento)
    {
        for(int i = 0; i < _derecha.length; i++)
        {
            if(_derecha[i].equals(elemento)) return i;
        }
        return -1;
    }
    
    public Elemento derecha(int index)
    {
        return _derecha[index];
    }
    
    public NoTerminal izquierda()
    {
        return _izquierda;
    }
    
    @Override
    public String toString()
    {
        String s = _izquierda + " -> ";
        for(int i = 0; i < _derecha.length; i++)
        {
            s += _derecha[i];
        }
        return s;
    }
}
