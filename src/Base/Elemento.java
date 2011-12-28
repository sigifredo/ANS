/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author raziel
 */
public class Elemento
{
    
    public enum Tipo { terminal, no_terminal, simbolo_inicial, regla_de_produccion };
    
    protected String _simbolo;
    protected Tipo _tipo;
    
    /**
     * Constructor de la clase.
     * 
     * @param simbolo Simbolo o letra asociado al elemento de la gramática.
     * @param tipo Tipo de elemento de la gramágica.
     */
    public Elemento(String simbolo, Tipo tipo)
    {
        this._simbolo = simbolo;
        this._tipo = tipo;
    }
    
    public String simbolo()
    {
        return this._simbolo;
    }
    
    public Tipo tipo()
    {
        return this._tipo;
    }
    
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
