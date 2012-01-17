/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


package evolutiva;

import evolutiva.operadores.CruceDeCiclos;
import evolutiva.operadores.CruceDeOrden;
import evolutiva.operadores.MutacionPorInsercion;
import evolutiva.operadores.MutacionPorIntercambio;
import evolutiva.operadores.MutacionPorInversion;
import evolutiva.operadores.OperadorCruce;
import evolutiva.operadores.OperadorMutacion;
import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Vector;
import evolutiva.seleccionadores.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author Cristian
 */

public class Algoritmo {
    
    private static int COMPETIDORES = 4;
    //cantidad de competidores en la seleccion por torneo, para seleccionar padres.
    private static int TAM_POBLACION = 100;
    //cantidad de individuos en la poblacion.
    private static float PORCENTAJE_SOBREVIVIENTES = 0.2f;
    //% de sobreviviente de la vieja generacion.
    private static double PROB_CRUCE = 1.0f;
    //probabilidad de realizar un cruce entre 2 individuos seleccionados como padres.
    private static double PROB_MUTAR = 0.5f;
    //probabilidad de mutar un individuo despues de su nacimiento.

    //----COMPONENTES:
    FuncionFitness f;
    GeneradorPoblacion g;
    SeleccionPadres s;
    OperadorMutacion m;
    OperadorCruce c;
    SeleccionSobrevivientes vivos;
        
    
    public Algoritmo(String ACC, String ACG, String ACN, String AC, String AN) throws IOException {
       
        //abrimos todos los archivos.
        Info.abrirArchivoCantColores(ACC);
        Info.abrirArchivoCantGrupos(ACG);
        Info.abrirArchivoCantNaipes(ACN);
        Info.AbrirArchivoColores(AC);
        Info.AbrirArchivoNaipes(AN);
        
        this.f = new FitnessConHistorial();//Inicializamos la funcion de Fitness Seleccionada.
        Individuo.setCalculadorFitness(f);//Asignmos la funcion de Fitness dentro de los Individuos. 
        
        this.s = new PadresPorTorneo(COMPETIDORES);
        this.m = new MutacionPorIntercambio(PROB_MUTAR);
        this.c = new CruceDeCiclos(PROB_CRUCE);
        this.vivos = new SobrevivientesStadyState(PORCENTAJE_SOBREVIVIENTES);
        
        
    }

    public void setCruce(OperadorCruce c) {
        this.c = c;
    }

    public void setFuncionFitnes(FuncionFitness f) {
        this.f = f;
    }

    public void setGeneradorPobalcional(GeneradorPoblacion g) {
        this.g = g;
    }

    public void setMutacion(OperadorMutacion m) {
        this.m = m;
    }

    public void setSeleccionadorPadres(SeleccionPadres s) {
        this.s = s;
    }

    public void setSobrevivientes(SeleccionSobrevivientes vivos) {
        this.vivos = vivos;
    }

    
    public static void setCompetidores(int COMPETIDORES) {
        Algoritmo.COMPETIDORES = COMPETIDORES;
    }

    public static void setPorsentajeSobrevivientes(float PORCENTAJE_SOBREVIVIENTES) {
        Algoritmo.PORCENTAJE_SOBREVIVIENTES = PORCENTAJE_SOBREVIVIENTES;
    }

    public static void setProbCruce (double PROB_CRUCE) {
        Algoritmo.PROB_CRUCE = PROB_CRUCE;
    }

    public static void setProbMutar(double PROB_MUTAR) {
        Algoritmo.PROB_MUTAR = PROB_MUTAR;
    }

    public static void setTamPoblacion (int TAM_POBLACION) {
        Algoritmo.TAM_POBLACION = TAM_POBLACION;
    }
    
    public void ejecutar() throws IOException{
        this.g= new GPConjunto();//Inicializamos el Generador Poblacional Seleccionado.
        Poblacion p;
       
        System.out.println("xxxxxxx   PROBANDO EJECUCION GENERICA   xxxxxxx" );
        for(int vueltas = 0; vueltas < 20; vueltas++){
            p = g.generar(TAM_POBLACION);
            float mejorFitness;
            int numIteracion = 0;
            Individuo padre1, padre2, mejor;
            ArrayList <Individuo> listaDeHijos;
            ArrayList <Individuo> nuevosHijos;
            ArrayList<Individuo> hijos;
            Poblacion poblacionHijos;
            Calendar tiempo0 = Calendar.getInstance();
            do{ //Realizo los diferentes pasos del algoritmo genetico.
                listaDeHijos = new ArrayList<Individuo>();
                for (int i = 0; i < (int)(p.getTamanio()/2); i++){
                    nuevosHijos = new ArrayList<Individuo>();
                    padre1 = s.getPadre(p);//obtengo nuevo padre
                    padre2 = s.getPadre(p);//obtengo el otro padre
                    //se generan dos nuevos hijos o se dejan los padres, dependiendo de la probabiliad de cruce
                    hijos = c.cruzar(padre1, padre2);      
                    //se mutan o se devuelve el mismo individuo, dependiendo de la probabiliadad de cruce
                    nuevosHijos.add(m.mutar(hijos.get(0)));
                    nuevosHijos.add(m.mutar(hijos.get(1)));        
                    listaDeHijos.addAll(nuevosHijos); //agrego los nuevos hijos a la lista
                    }

                poblacionHijos = new Poblacion(listaDeHijos);
                p = vivos.getSobrevivientes(p, poblacionHijos);
                poblacionHijos = null;
                mejor = p.getMejorIndividuo();
                mejorFitness = mejor.getValorFitness();
    //            System.out.println( "En la iteracion "+ numIteracion+" el mejor Ftness fue : "+mejorFitness);
                numIteracion++;
            }while((mejorFitness < Info.getCantColores())&& (numIteracion < 3000)); //tenemos un limite de iteraciones
            System.out.println(mejor.toString());
            System.out.println("almacenados: " + f.cantidadAlmacenados());
            System.out.println("reutilizados: " + f.cantidadReutilizados());
            System.out.println("cantidad total de fitness: " + f.total());
            System.out.println("numero de iteraciones: " + numIteracion);
            Calendar tiempoFin = Calendar.getInstance();
            long tiempoTotal = tiempoFin.getTimeInMillis() - tiempo0.getTimeInMillis();
            System.out.println("tiempo de ejecucion: " + tiempoTotal);
            System.out.println("ejecucion numero: " + vueltas);
        }
    
        
    }
}
