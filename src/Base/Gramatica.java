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
import java.util.Map;

/**
 *
 * @author raziel
 */
public class Gramatica
{
    Produccion _producciones[];
    Map<String, String> _siguientes;
    
    public Gramatica(String gramatica)
    {
        String producciones[] = gramatica.split("\n");
        _producciones = new Produccion[producciones.length];
        _siguientes = new HashMap<String, String>();
        
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
//		System.out.println("Obteniendo Siguientes de : " + noTerminal);
		for(int i = 0; i < _producciones.length; i++)
		{
                    Produccion p = _producciones[i];

                    int posicion;
                    if((posicion = p.derechaContiene(noTerminal)) > 0)
                    {
                        if (posicion == (p._derecha.length-1)) // Si no es ultimo
                        {
                            // en la produccion
                            // String aux = obtenerLaFormaSentencialDerechaDesde(p.rightpart, noTerminal);
                            if (!isAnulableLaFormaSentencialDerecha(p.rightpart, noTerminal))
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
                                    primeros = primeros((NoTerminal)p.derecha(posicion+1));
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
						for (Character c : siguientesDeLaIzq.toCharArray()) {
							if (!siguientes.contains(Character.toString(c))) {
								siguientes += c;
							}
						}
					}
				} else {
					if (isUltimo(p.rightpart, noTerminal)) {
						// ahora es que el noterminal este al ultimo de una
						// produccion
						if (!p.noterminal.equals(noTerminal)) {
							// para evitar la recursion infinita F->AF
							String siguientesDelNoTerminal = siguientesDe(p.noterminal);
							if (!siguientesDelNoTerminal.contains(noTerminal)) {
								for (Character c : siguientesDelNoTerminal
										.toCharArray()) {
									if (!siguientes.contains(Character
											.toString(c))) {
										siguientes += c;
									}
								}
							}
						}
					}
				}
			}
		}
		if (noTerminal.equals(initSymbol)) {
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
    
    public boolean esAnulable(String noTerminal)
    {
        return true;
    }
    
    /**
     * Este es si es anulable la parte sentencial derecha dese el no terminal
     *
     * @param formaSentencialDerecha
     * @param noTerminal
     * @return Si es anulable o no
     */
    private boolean esAnulableLaFormaSentencialDerecha(String formaSentencialDerecha, String noTerminal)
    {
        for (int i = 0; i < formaSentencialDerecha.length(); i++)
        {
            if (formaSentencialDerecha.charAt(i) == noTerminal.charAt(0))
            {
                String c = "";
                c += formaSentencialDerecha.charAt(i+1);
                if (_sTerminales.contains(c))
                {
                    return false;
                }
                else
                {
                    return esAnulable(Character.toString(formaSentencialDerecha.charAt(i + 1)));
                }
            }
        }
        return false;
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
