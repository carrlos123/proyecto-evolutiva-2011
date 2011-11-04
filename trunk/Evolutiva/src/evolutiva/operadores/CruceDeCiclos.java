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
public class CruceDeCiclos extends OperadorCruce{
    
    public CruceDeCiclos(double probabilidad){
        super(probabilidad);
    }

    @Override
    public ArrayList<Individuo> cruzar2(Individuo p1, Individuo p2) {
        Vector<Integer> genesP1 = p1.getGenes();
        Vector<Integer> genesP2 = p2.getGenes();
        int nroCartas = genesP1.size();
        Vector<Integer> genesH1 = (Vector<Integer>) genesP1.clone();
        Vector<Integer> genesH2 = (Vector<Integer>) genesP1.clone();
        int nroCiclo = 0;
        boolean usados[] = new boolean[nroCartas];
        for(int i = 0; i < nroCartas; i++){
            usados[i] = false;
        }
        while(primeroSinUsar(usados) != -1){
            Vector<Integer> ciclo = generarCiclo(genesP1, genesP2, usados);
            if(nroCiclo%2 == 0){
                copiar(ciclo, genesP1, genesH1);
                copiar(ciclo, genesP2, genesH2);
            }
            else {
                copiar(ciclo, genesP2, genesH1);
                copiar(ciclo, genesP1, genesH2);
            }
            nroCiclo++;
        }
        Individuo hijo1 = new Individuo(genesH1);
        Individuo hijo2 = new Individuo(genesH1);
        ArrayList<Individuo> hijos = new ArrayList<Individuo>();
        hijos.add(hijo1);
        hijos.add(hijo2);
        return hijos;
    }

    private int primeroSinUsar(boolean[] usados) {
        int i = 0;
        while((i < usados.length) && (usados[i] != false))
            i++;
        if(i != usados.length)
            return i;
        return -1;
        
    }

    private Vector<Integer> generarCiclo(Vector<Integer> genesP1, Vector<Integer> genesP2, boolean[] usados) {
        int primerIndice, indice, sigIndice;
        int genP2;
        Vector<Integer> ciclo = new Vector<Integer>();
        //devuelve la posicion del primer elemento en genesP1 que todavia no fue usado:
        primerIndice = primeroSinUsar(usados);
        usados[primerIndice] = true;//lo marco como usado
        indice = primerIndice;
        sigIndice = -1;
        //ciclo.add(primerIndice);
        while(primerIndice != sigIndice){
            genP2 = genesP2.elementAt(indice);
            sigIndice = genesP1.indexOf(genP2);
            usados[sigIndice] = true;
            ciclo.add(sigIndice);
            indice = sigIndice;
        }
      //  ciclo.remove(ciclo.size() - 1);
        return ciclo;
    }

    private void copiar(Vector<Integer> ciclo, Vector<Integer> genesP, Vector<Integer> genesH) {
        for(int i = 0; i < ciclo.size(); i++){
            genesH.set(ciclo.elementAt(i), genesP.elementAt(ciclo.elementAt(i)));
        }
    }
    
}
