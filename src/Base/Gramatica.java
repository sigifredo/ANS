/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

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
    
}
