/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.util.Set;
import java.util.Vector;

/**
 *
 * @author Cristian
 */
public class GPConjunto extends GeneradorPoblacion {
    Vector<Integer> todos;
    int cantNaipes;
    
    public GPConjunto(){
        todos = new Vector<Integer>();
        cantNaipes = Info.getCantNaipes();
        for(int i =0; i<cantNaipes; i++){
            todos.add(i);
           }
    }
    
    @Override
    public Individuo generarIndividuoRamndon() {
       Vector<Integer> subConj = (Vector<Integer>) todos.clone();
       Vector<Integer> genes = new Vector<Integer>(cantNaipes);
       int aleatorio;
       for(int i =(cantNaipes-1); i>=0; i--){
        aleatorio = (int)(Math.random()*i);
        //System.out.println(aleatorio);
        genes.add(subConj.elementAt(aleatorio));
        subConj.remove(aleatorio);        
       }
       return (new Individuo(genes));
    }

    public Poblacion generar(int tamañoPoblacion) {
        Poblacion p = new Poblacion();
        for (int i = 0; i< tamañoPoblacion ;i++){
            p.agregarIndividuo(generarIndividuoRamndon());
        }
        return p;
    }
}
