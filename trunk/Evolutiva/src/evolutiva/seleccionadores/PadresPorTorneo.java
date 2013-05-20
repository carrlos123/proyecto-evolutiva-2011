/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.seleccionadores;

import evolutiva.Individuo;
import evolutiva.Poblacion;
import java.util.ArrayList;

/**
 *
 * @author Cristian
 */
public class PadresPorTorneo extends SeleccionPadres{

    private static final int PRIMERO = 0;
    private int cantConcursantes;
    //cantidad de individuos que compiten en el torneo. al momento de seleccionar el padre
    
    public PadresPorTorneo(int c) {
        this.cantConcursantes = c;
    }
        
    private ArrayList<Individuo> peleasDePares(ArrayList <Individuo> peleadores){
        ArrayList<Individuo> finalistas = new ArrayList<Individuo>();
        Individuo p1, p2;
        while (peleadores.size() > 1 ){
            p1 = peleadores.remove(PRIMERO);
            p2 = peleadores.remove(PRIMERO);
            if (p1.getValorFitness() > p2.getValorFitness()){
                finalistas.add(p1);
            }else{
                finalistas.add(p2);
            }           
        }
        if (peleadores.size() == 1){
            finalistas.add(peleadores.remove(PRIMERO));
        }
        return finalistas;
    }
    
    
    @Override
    public Individuo getPadre(Poblacion p) {
        // Retorna el padre seleccionado mediante la realizacion de un torneo
        ArrayList<Individuo> competidores = buscadosAleatorio(p);
        //generalmos una lista con los padres posibles a seleccionar en el torneo
        while (competidores.size() > 1){
        //System.out.println(" torneo de "+ competidores.size() +" competidores");   
            competidores = peleasDePares(competidores);
        }
        if (competidores.isEmpty())
            return null;
        else
            return competidores.get(PRIMERO); //Retornamos el ultimo individuo que queda en la lista, el ganador del torneo
    }

    private ArrayList<Individuo> buscadosAleatorio(Poblacion p) {
        //System.out.println("competidores.....");
        ArrayList<Individuo> seleccionados = new ArrayList<Individuo>();
        int aleatorio;
        int tamPoblacion = p.getTamanio();
        for (int i = 0; i<this.cantConcursantes; i++){
            aleatorio = (int)(Math.random()* tamPoblacion);
            seleccionados.add(p.getIndividuo(aleatorio));
           // System.out.println(p.getIndividuo(aleatorio).toString());
        }
        return seleccionados;
    }
    
}
