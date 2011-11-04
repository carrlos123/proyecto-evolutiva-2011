/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.lang.Float;
import java.util.HashMap;
import java.util.Vector;

/**
 *
 * @author Cristian
 */
public class FitnessConHistorial extends FuncionFitness{

    HashMap <Vector<Integer>, Float> historial;
    private final int PRIMER_ELEMENTO =0;
    private int tam1, tam2, cant1, cant2, primerElementoParcial; 
    private Vector<String> colores;
    private int almacenados;
    private int reutilizados;
    private int totales;
    
    public FitnessConHistorial() {
        historial = new HashMap<Vector<Integer>, Float>();
        colores = Info.getColores();
        //calcular valores de las var..... con la formula del enunciado
        //la cantidad de grupos de tamaño n... 
        int parteEntera = Info.getCantNaipes()/Info.getCantGrupos();
        //System.out.println("esto tendriA que ser un Z = " + parteEntera);
        cant1 = (Info.getCantNaipes()-(parteEntera*Info.getCantGrupos()));
        tam1 = (parteEntera + 1);
        cant2 = (Info.getCantGrupos() - cant1);
        tam2 = parteEntera;
        //System.out.println("Tengo " + cant1 + "grupos de tamaño " + tam1 );
        //System.out.println(" y tambien tengo " + cant2 + "grupos de tamaño " + tam2 );
        primerElementoParcial = PRIMER_ELEMENTO+(tam1*cant1); 
        //sisi es re feo esto, pero asi se calcula solo una vez. se maneja la 
        //posibilidad que se calcule dentro de INF... tambien trabajar directamente
        //con los get de esa clase, eliminando toda variable numerica interna....
        almacenados = 0;
        reutilizados = 0;
        totales = 0;
    }
    
    @Override
    public float calcularFitness(Vector<Integer> v) {
        if (historial.containsKey(v)){
            reutilizados++;
            totales++;
            return historial.get(v);//retorno un fitness ya calculado.
        }
        else {
            almacenados++;
            totales++;
            float valorFitness = calcularPromedioGrupos(v);
            historial.put(v, valorFitness);//agrego el nuevo fitness al mapa.
            return (valorFitness);//retorno el promedio de los fitnes grupales.
        }
    }
    
    public int cantidadAlmacenados(){
        return almacenados;
    }
    
    public int cantidadReutilizados(){
        return reutilizados;
    }
    
    public int total(){
        return totales;
    }
    
    private float calcularPromedioGrupos(Vector <Integer> v){
        float sumaParcial = 0.0f;
        for (int i=0;i<cant1;i++){//por la cantidad de grupos a analizar del tamaño1.
            sumaParcial += calcularFitnessGrupo(v, PRIMER_ELEMENTO+(i*tam1), tam1);
        }
        for (int j = 0; j < cant2; j++) {//por la cantidad de grupos a analizar del tamaño2.
            sumaParcial += calcularFitnessGrupo(v, primerElementoParcial+(j*tam2), tam2);
        }
        return (sumaParcial/(float)Info.getCantGrupos());//retorno el promedio de los fitness de los grupos.
        //el mejor es cantColores. porq todos tienen que tener dicho valor.
    }
    
    private float calcularFitnessGrupo(Vector <Integer> v, int inic, int tam){
        //la pos de inicio del grupo, y el tamaño del mismo.
        int contadorRepetidoColor;
        int valorParcial = 0;
        for (int i = 0; i < colores.size(); i++) {//por todo los colores.
            contadorRepetidoColor = 0;
            for (int j = inic; j < (inic+tam); j++) {//por todos los elementos del grupo.
                if (Info.tieneColor(v.elementAt(j), colores.elementAt(i))) 
                    contadorRepetidoColor++;//suma 1 cada vez que aparece el mismo color.
            }
            valorParcial += analisisDeColor(contadorRepetidoColor);//valor obtenido de la suma de todos los colores
        }
        return (valorParcial);
        //devolvemos el valor del grupo, el mejor es cantColores y despues baja hasta numeros negativos.
    }
    
    private int analisisDeColor(int contadorRepetidoColor){
        if (contadorRepetidoColor == 0) //si el color no aparece en un grupo.
            return -2;
        else if (contadorRepetidoColor == 1)//si aparece una sola vez.
            return 1;
        else return (-contadorRepetidoColor);//si se repite mas de una vez.
    }//la cantidad de veces que aparece un color no puede ser negativa.
    
}

