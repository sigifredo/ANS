/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

import Elementos.Elemento;
import Elementos.Elemento.Tipo;
import Elementos.NoTerminal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 *
 * @author raziel
 */
public class Gramatica
{
    ArrayList<Produccion> _producciones;
    Map<String, ArrayList> _primeros;
    Map<String, ArrayList> _siguientes;

    public Gramatica(String gramatica)
    {
        String producciones[] = gramatica.split("\n");
        _producciones = new ArrayList<Produccion>();
        _siguientes = new HashMap<String, ArrayList>();
        _primeros = new HashMap<String, ArrayList>();

        for(int i = 0; i < producciones.length; i++)
        {
            _producciones.add(new Produccion(producciones[i]));
        }
    }

    public boolean gramaticaS()
    {
        HashMap hm = new HashMap();
        for(int i = 0; i < _producciones.size(); i++)
        {
            Elemento b = _producciones.get(i).derecha(0);
            if(b.tipo() != Tipo.terminal)
                return false; // es no terminal o nula

            Elemento e = (Elemento)hm.get(_producciones.get(i).izquierda().simbolo());
            if(e != null)
            {
                if(e.equals(b))
                {
                    return false;
                }
                else
                    hm.put(_producciones.get(i).izquierda().simbolo(), b);
            }
            else
                hm.put(_producciones.get(i).izquierda().simbolo(), b);
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
            for(int i = 0; i < _producciones.size(); i++)
            {
                Elemento[] derecha = _producciones.get(i)._derecha;

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

    /**
     * Calcula los siguientes de un no terminal.
     *
     * @param noTerminal No terminal al que deseamos encontrar los siguientes.
     *
     * @return Siguientes del no terminal.
     *
     */
    public ArrayList siguientes(NoTerminal noTerminal)
    {
        String siguientes = "";
        Iterator<Produccion> it = _producciones.iterator();
        while(it.hasNext())
        {
            Produccion p = it.next();

            int posicion;
            if((posicion = p.derechaContiene(noTerminal)) > 0)
            {
                if (posicion == (p._derecha.length-1)) // Si no es ultimo
                {
                    // en la produccion
                    // String aux = obtenerLaFormaSentencialDerechaDesde(p.rightpart, noTerminal);
                    if (!esAnulableLaFormaSentencialDerecha(p._derecha, posicion))
                    {
                        if (p.derecha(posicion+1).tipo() == Tipo.terminal)
                            // if (isTerminal(aux.charAt(0))) // no anulable y no
                        {
                            // terminal
                            if (!siguientes.contains(p.derecha(posicion+1).simbolo()))
                            {
                                siguientes += p.derecha(posicion+1).simbolo();
                            }
                        }
                        else // no es terminal y no es anulable
                        {
                            ArrayList primeros;
                            primeros = primeros((NoTerminal) p.derecha(posicion+1));
                            for (Character c : aux2.toCharArray())
                            {
                                if (!siguientes.contains(Character.toString(aux.charAt(0))))
                                {
                                    siguientes += c;
                                }
                            }
                        }
                    }
                    else
                    {
                        // el NT que tengo es anulable
                        // son los primeros del nt que me llego y los sig de la
                        // parte iza
                        ArrayList primeros = primeros((NoTerminal) p.derecha(posicion+1));
                        // String aux2 = removeLambda(primeros);
                        for(int j = 0; j < primeros.size(); j++)
                            // for (Character c : aux2.toCharArray())
                        {
                            Elemento e = (Elemento) primeros.get(i);
                            if (!siguientes.contains(e.simbolo()))
                            {
                                siguientes += e.simbolo();
                            }
                        }
                        String siguientesDeLaIzq = siguientesDe(p.noterminal);
                        for (Character c : siguientesDeLaIzq.toCharArray())
                        {
                            if (!siguientes.contains(Character.toString(c)))
                            {
                                siguientes += c;
                            }
                        }
                    }
                }
                else
                {
                    if (isUltimo(p.rightpart, noTerminal))
                    {
                        // ahora es que el noterminal este al ultimo de una
                        // produccion
                        if (!p.noterminal.equals(noTerminal))
                        {
                            // para evitar la recursion infinita F->AF
                            String siguientesDelNoTerminal = siguientesDe(p.noterminal);
                            if (!siguientesDelNoTerminal.contains(noTerminal))
                            {
                                for (Character c : siguientesDelNoTerminal.toCharArray())
                                {
                                    if (!siguientes.contains(Character.toString(c)))
                                    {
                                        siguientes += c;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (noTerminal.equals(initSymbol))
        {
            siguientes += EOF;
        }
        return siguientes;
        /*
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
        */
    }

    public ArrayList seleccion()
    {
        return null;
    }

    public void factIzq()
    {
        // Recorremos todas las producciones
        int i = 0; // indice, que indica en qué elemento del arreglo estamos.
        Iterator<Produccion> it = _producciones.iterator();
        while(it.hasNext())
        {
            // Ponemos un alias a la producción.
            Produccion p1 = it.next();
            
            // Buscamos las producciones cuyo primer simbolo del lado
            // derecho es un no terminal
            if(p1.derecha(0).tipo() == Tipo.no_terminal)
            {
                // Ponemos un alias al no terminal.
                NoTerminal nt = (NoTerminal) p1.derecha(0);
                
                // Creamos una lista donde guardaremos las nuevas producciones que
                // saldrán de reemplazar el primer no terminal de la derecha.
                ArrayList nuevasProducciones = new ArrayList();

                // Recorremos de nuevo las producciones en busca del no terminal
                // encontrado anteriormente para reemplazarlo
                Iterator<Produccion> jt = _producciones.iterator();
                while(jt.hasNext())
                {
                    // Ponemos un alias a la producción
                    Produccion p2 = jt.next();
                    
                    // Verificamos que la producción que vamos a eliminar sea
                    // la que estamos buscando para reemplazarla
                    if(nt.equals(p2.izquierda()))
                    {
                        // Creamos la derecha de la nueva producción.
                        ArrayList<Elemento> nuevaDerecha = new ArrayList<Elemento>();

                        for(int k = 0; k < p2._derecha.length; k++)
                        {
                            nuevaDerecha.add(p2.derecha(k));
                        }
                        for(int k = 1; k < p1._derecha.length; k++)
                        {
                            nuevaDerecha.add(p1.derecha(k));
                        }

                        Produccion nuevaProduccion = new Produccion(nt, nuevaDerecha);
                        nuevasProducciones.add(nuevaProduccion);
                    }
                }
                _producciones.addAll(i, nuevasProducciones);
            }
            i++;
        }
    }

    public void sustitucionIzq()
    {
        
    }

    /**
     * Este es si es anulable la parte sentencial derecha dese el no terminal
     *
     * @param formaSentencialDerecha
     * @param indice Índice a partir del cual comenzaremos a ver si la forma sentencial derecha es o no anulable.
     * @return Si es anulable o no
     */
    private boolean esAnulableLaFormaSentencialDerecha(Elemento elementos[], int indice)
    {
        for(int i = indice+1; i < elementos.length; i++)
        {
            if(elementos[i].tipo() == Tipo.terminal) return false;
            else
            {
                NoTerminal nt = (NoTerminal) elementos[i];
                if(!nt._anulable) return false;
            }
        }
        return true;
    }

    @Override
    public String toString()
    {
        String s = "";
        System.out.println("--" + _producciones.size());
        for(int i = 0; i < _producciones.size(); i++)
        {
            System.out.println("---");
            s += _producciones.get(i) + "\n";
        }
        return s;
    }

}
