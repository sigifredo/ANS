/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Elementos;

import java.util.Map;

/**
 *
 * @author raziel
 */
public class Elemento
{
    public static Map<String, Elemento> mElementos;

    public enum Tipo { terminal, no_terminal, terminal_nula, simbolo_inicial, regla_de_produccion };
    
    protected String _simbolo;
    protected Tipo _tipo;
    
    /**
     * Constructor de la clase.
     * 
     * @param simbolo Simbolo o letra asociado al elemento de la gramática.
     * @param tipo Tipo de elemento de la gramágica.
     */
    protected Elemento(String simbolo, Tipo tipo)
    {
        this._simbolo = simbolo;
        this._tipo = tipo;
    }
    
    /**
     * Constructor de copia.
     * 
     * @param e 
     */
    protected Elemento(Elemento e)
    {
        this._simbolo = e._simbolo;
        this._tipo = e._tipo;
    }
    
    public String simbolo()
    {
        return this._simbolo;
    }
    
    public Tipo tipo()
    {
        return this._tipo;
    }
    
    public static Elemento obtenerElemento(String simbolo)
    {
        return mElementos.get(simbolo);
    }
    
    @Override
    public boolean equals(Object o)
    {
        Elemento e = (Elemento)o;
        if(_simbolo.equals(e._simbolo) && _tipo == e._tipo) return true;
        else return false;
    }
    
    public String toString()
    {
        return _simbolo;
    }
}
