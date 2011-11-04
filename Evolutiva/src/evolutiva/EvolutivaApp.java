/*
 * EvolutivaApp.java
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
 * The main class of the application.
 */
public class EvolutivaApp extends SingleFrameApplication {

    private static final int COMPETIDORES = 4;
    //cantidad de competidores en la seleccion por torneo, para seleccionar padres.
    private static final int TAM_POBLACION = 100;
    //cantidad de individuos en la poblacion.
    private static final float PORCENTAJE_SOBREVIVIENTES = 0.2f;
    //% de sobreviviente de la vieja generacion.
    private static final double PROB_CRUCE = 1.0f;
    //probabilidad de realizar un cruce entre 2 individuos seleccionados como padres.
    private static final double PROB_MUTAR = 0.5f;
    //probabilidad de mutar un individuo despues de su nacimiento.
    
    
    /**
     * At startup create and show the main frame of the application.
     */
    @Override protected void startup() {
        show(new EvolutivaView(this));
    }

    /**
     * This method is to initialize the specified window by injecting resources.
     * Windows shown in our application come fully initialized from the GUI
     * builder, so this additional configuration is not needed.
     */
    @Override protected void configureWindow(java.awt.Window root) {
    }

    /**
     * A convenient static getter for the application instance.
     * @return the instance of EvolutivaApp
     */
    public static EvolutivaApp getApplication() {
        return Application.getInstance(EvolutivaApp.class);
    }

     private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    /**
     * Main method launching the application.
     */
    
    public static void main(String[] args) throws IOException{
        launch(EvolutivaApp.class, args);
                    // TODO code application logic here
        String ACC = "..\\instanciaN120G20\\cantColores.txt";
        String ACG ="..\\instanciaN120G20\\cantGrupos.txt";
        String ACN ="..\\instanciaN120G20\\cantNaipes.txt";
        String AC ="..\\instanciaN120G20\\Colores.txt";
        String AN = "..\\instanciaN120G20\\Naipes.txt";
        
        //abrimos todos los archivos.
        Info.abrirArchivoCantColores(ACC);
        Info.abrirArchivoCantGrupos(ACG);
        Info.abrirArchivoCantNaipes(ACN);
        Info.AbrirArchivoColores(AC);
        Info.AbrirArchivoNaipes(AN);

        FuncionFitness f = new FitnessConHistorial();//Inicializamos la funcion de Fitness Seleccionada.
        Individuo.setCalculadorFitness(f);//Asignmos la funcion de Fitness dentro de los Individuos. 
        
        GeneradorPoblacion g= new GPConjunto();//Inicializamos el Generador Poblacional Seleccionado.
        Poblacion p;
       
    //    p.mostrame();  //mostramo la lista total de individuos con su fitness asociado.
        
        SeleccionPadres s = new PadresPorTorneo(COMPETIDORES);
        OperadorMutacion m = new MutacionPorIntercambio(PROB_MUTAR);
        OperadorCruce c = new CruceDeCiclos(PROB_CRUCE);
        SeleccionSobrevivientes vivos = new SobrevivientesStadyState(PORCENTAJE_SOBREVIVIENTES);
        
 /*       
        **** * PARTE PARA TESTEAR OPERADORES DE CRUCE Y MUTACION***** 
         * descomentar esto y comentar todo lo de abajo * /
        Individuo p1 = s.getPadre(p);
        System.out.println("p1 : \n " + p1.toString());
        Individuo p2 = s.getPadre(p);
        System.out.println("p2 : \n " + p2.toString());
        
        
        System.out.println("Comienza mutacion de padre1");
        System.out.println(m.mutar(p1).toString());
        
        System.out.println("El mejor fitness lo tine: \n" + p.getMejorIndividuo());
        System.out.println("----- POBLACION ORDENADA -----" );
        p.mostrarIndividuos();   
        */
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
