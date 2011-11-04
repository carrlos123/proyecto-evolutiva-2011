/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.operadores;

import evolutiva.Individuo;
import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class CruceDeArcos extends OperadorCruce{
    
    public CruceDeArcos(double probabilidad){
        super(probabilidad);
    }

    @Override
    public ArrayList<Individuo> cruzar2(Individuo p1, Individuo p2) {
        throw new UnsupportedOperationException("Not supported yet.");
    }


    
}
