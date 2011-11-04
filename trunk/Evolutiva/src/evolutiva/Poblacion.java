/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Cristian
 */
public class Poblacion {

    private Vector<Individuo> poblacion;
    private ComparadorPorFitness comparador;
    
    public Poblacion() {
        this.poblacion=new Vector<Individuo>();
        this.comparador = new ComparadorPorFitness();
    }

    public Poblacion(List<Individuo> list) {
        this.poblacion = new Vector<Individuo> (list);
        this.comparador = new ComparadorPorFitness();
    }
    
    public int getTamanio(){
        return poblacion.size();
    }
    
    public void ordenarPorFitness(){
        // Ordenamos por fitness
        Collections.sort(this.poblacion, comparador);
    }
    
    public Individuo getMejorIndividuo(){
        this.ordenarPorFitness();
        return this.poblacion.firstElement();
    }
    
    public void agregarIndividuo(Individuo i){
        poblacion.add(i);
    }
    
    public List<Individuo> getMejoresIndividuos(int cant){
        this.ordenarPorFitness();
        return poblacion.subList(0, cant);
    }
    
    public void mostrarIndividuos(){
        for(int i =0;i<poblacion.size();i++)
        System.out.println(poblacion.elementAt(i).toString());
    }

    public Individuo getIndividuo(int pos) {
        return poblacion.elementAt(pos);
    }
}
