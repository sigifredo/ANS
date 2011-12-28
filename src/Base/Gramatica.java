/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.Elemento.Tipo;
import java.util.HashMap;

/**
 *
 * @author raziel
 */
public class Gramatica
{
    
    protected Produccion _producciones[];
    
    public Gramatica(String gramatica)
    {
        String producciones[] = gramatica.split("\n");
        _producciones = new Produccion[producciones.length];
        
        for(int i = 0; i < _producciones.length; i++)
        {
            _producciones[i] = new Produccion(producciones[i]);
        }
    }
    
    public boolean gramaticaS()
    {
        HashMap hm = new HashMap();
        for(int i = 0; i < _producciones.length; i++)
        {
            Elemento b = _producciones[i].derecha(0);
            if(b.tipo() != Tipo.terminal)
                return false; // es no terminal o nula

            Elemento e = (Elemento)hm.get(_producciones[i].izquierda().simbolo());
            if(e != null)
            {
               if(e.equals(b))
               {
                   return false;
               }
               else
                   hm.put(_producciones[i].izquierda().simbolo(), b);
            }
            else
                hm.put(_producciones[i].izquierda().simbolo(), b);
        }
        return true;
    }
    
    @Override
    public String toString()
    {
        String s = "";
        for(int i = 0; i < _producciones.length; i++)
        {
            s += _producciones[i] + "\n";
        }
        return s;
    }
    
}
