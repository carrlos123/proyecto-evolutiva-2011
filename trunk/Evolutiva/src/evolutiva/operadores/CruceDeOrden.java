/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva.operadores;

import evolutiva.Individuo;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Cristian
 */
public class CruceDeOrden extends OperadorCruce{
    
    public CruceDeOrden(double probabilidad){
        super(probabilidad);
    }

    @Override
    public ArrayList<Individuo> cruzar2(Individuo p1, Individuo p2) {  
        Vector<Integer> genesP1 = p1.getGenes();
        Vector<Integer> genesP2 = p2.getGenes();
        int nroCartas = genesP1.size();
        int pos1, pos2, aux;
        do{ //calculo dos posiciones que tengan mas de un numero de distancia
            pos1 = (int) (Math.random() * nroCartas); 
            pos2 = (int) (Math.random() * nroCartas);
        }while((Math.abs(pos1 - pos2) > nroCartas-2)||(Math.abs(pos1 - pos2) < 2));
        if (pos2 < pos1){//busco la pos menor
            aux = pos1;
            pos1 = pos2;
            pos2 = aux;
        }
        Individuo hijo1 = generarHijo(genesP1, genesP2, pos1, pos2);
        Individuo hijo2 = generarHijo(genesP2, genesP1, pos1, pos2);
        ArrayList<Individuo> hijos = new ArrayList<Individuo>();
        hijos.add(hijo1);
        hijos.add(hijo2);
        return hijos;
    }
    
    private Individuo generarHijo(Vector<Integer> genesP1, Vector<Integer> genesP2, int pos1, int pos2){
        int nroCartas = genesP1.size();
        Vector<Integer> genesHijo = (Vector<Integer>) genesP1.clone();
        Vector<Integer> restantesP1 = new Vector<Integer>();
        restantesP1.addAll(genesP1.subList(0, pos1));
        restantesP1.addAll(genesP1.subList(pos2, nroCartas));
        int j = pos2;
        for(int i = pos2; i < pos1 + nroCartas; i++){
            Integer gen;
            do{
                gen = genesP2.elementAt(j%nroCartas);
                j++;
            }while(!restantesP1.contains(gen));
            genesHijo.set(i%nroCartas, gen);
        }
        return new Individuo(genesHijo);
    }
    
}
