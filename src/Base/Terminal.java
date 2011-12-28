/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author raziel
 */
public class Terminal extends Elemento {
    
    /**
     * Constructor.
     * 
     * @param simbolo Letra que tendrá el no terminal.
     */
    public Terminal(String simbolo) {
        super(simbolo, Tipo.terminal);
    }
    
    /**
     * Constructor de copia.
     * 
     * @param terminal Objeto de la clase terminal en base al cual se creará el objeto nuevo.
     */
    public Terminal(Terminal terminal) {
        super(terminal._simbolo, terminal._tipo);
    }
}
