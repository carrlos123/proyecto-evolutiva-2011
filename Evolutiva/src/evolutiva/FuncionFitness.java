/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.util.Vector;

/**
 *
 * @author Cristian
 */
public abstract class FuncionFitness {
      
    public abstract float calcularFitness(Vector<Integer> v);
    public abstract int cantidadAlmacenados();
    public abstract int cantidadReutilizados();
    public abstract int total();
}
