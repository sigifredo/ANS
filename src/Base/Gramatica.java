/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Base.Elemento.Tipo;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author raziel
 */
public class Gramatica
{
    
    Produccion _producciones[];
    
    public Gramatica(String gramatica)
    {
        String producciones[] = gramatica.split("\n");
        _producciones = new Produccion[producciones.length];
        
        HashMap hm = new HashMap();
        for(int i = 0; i < _producciones.length; i++)
        {
            _producciones[i] = new Produccion(producciones[i]);
            
            NoTerminal nt = (NoTerminal)hm.get(_producciones[i].izquierda().simbolo());
            if(nt == null)
                hm.put(_producciones[i].izquierda().simbolo(), _producciones[i].izquierda());
            else
            {
                if(_producciones[i]._izquierda._anulable || nt._anulable)
                {
                    nt._anulable = true;
                }
                _producciones[i]._izquierda = nt;
            }
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
    
    public ArrayList primeros(NoTerminal noTerminal)
    {
        if(!noTerminal.primeros().isEmpty())
            return noTerminal.primeros();
        else
        {
            ArrayList prim = new ArrayList();
            for(int i = 0; i < _producciones.length; i++)
            {
                Elemento[] derecha = _producciones[i]._derecha;

                for(int j = 0; j < derecha.length; j++)
                {
                    if(derecha[j].tipo() == Tipo.terminal)
                    {
                        prim.add(derecha[j]);
                        return prim;
                    }
                    else if(derecha[j].tipo() == Tipo.no_terminal)
                    {
                        NoTerminal nt = (NoTerminal)derecha[j];
                        if(nt._anulable)
                        {
                            prim.addAll(primeros(nt));
                            continue;
                        }
                        else
                        {
                            prim.addAll(primeros(nt));
                            break;
                        }
                    }
                }
            }
            noTerminal.primeros(prim);
            return prim;
        }
    }

    public ArrayList siguientes(NoTerminal noTerminal)
    {
        if(!noTerminal.siguientes().isEmpty()) return noTerminal.siguientes();
        else
        {
            ArrayList sig = new ArrayList();
            if(_producciones[0]._izquierda.equals(noTerminal))
                sig.add("#");
            for(int i = 0; i < _producciones.length; i++)
            {
                for(int j = 0; j < _producciones[i]._derecha.length; j++)
                {
                    if(_producciones[i]._derecha[j].tipo() == Tipo.no_terminal && _producciones[i]._derecha[j].equals(noTerminal))
                    {
                        if(j == (_producciones[i]._derecha.length-1)) // último
                        {
                            if(_producciones[i]._izquierda.equals(noTerminal))
                            {
                                j++;
                            }
                            else
                            {
                                sig.addAll(siguientes(_producciones[i]._izquierda));
                            }
                        }
                        else
                        {
                            j++;
                            if(_producciones[i]._derecha[j].tipo() == Tipo.terminal)
                            {
                                sig.add(_producciones[i]._derecha[j]);
                            }
                            else
                            {
                                for(int k = j; k < _producciones[i]._derecha.length; k++)
                                {
                                    if(_producciones[i]._derecha[k].tipo() == Tipo.terminal)
                                    {
                                        sig.add(_producciones[i]._derecha[k]);
                                    }
                                    else
                                    {
                                        NoTerminal nt = (NoTerminal)_producciones[i]._derecha[k];
                                        if(nt._anulable) sig.addAll(primeros(nt));
                                        else
                                        {
                                            sig.addAll(primeros(nt));
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            noTerminal.siguientes(sig);
            return sig;
        }
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
