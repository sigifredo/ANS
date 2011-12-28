/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Base;

/**
 *
 * @author raziel
 */
public class NoTerminal extends Elemento {
    
    /**
     * Constructor.
     * 
     * @param simbolo Letra que tendrá el terminal.
     */
    public NoTerminal(String simbolo) {
        super(simbolo, Tipo.no_terminal);
    }
    
    public NoTerminal(NoTerminal noTerminal) {
        super(noTerminal._simbolo, noTerminal._tipo);
    }
}
