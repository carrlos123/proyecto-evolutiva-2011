/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

/**
 *
 * @author Cristian
 */
import com.sun.org.apache.xpath.internal.operations.Equals;
import java.util.Set;
import java.util.Vector;

public class Individuo {

    private Vector<Integer> genes ;
    private Float valorFitness;
    private static FuncionFitness calculadorFitness;
    
    public static void setCalculadorFitness(FuncionFitness f){
        calculadorFitness = f;
    }
    
    public Individuo(Vector<Integer> l) {
        genes = l;
        valorFitness = Individuo.calculadorFitness.calcularFitness(l);
    }

    public Float getValorFitness() {
        return valorFitness;
    }
    
    public Vector<Integer> getGenes() {
        return genes;
    }
    
    public String toString (){
        String s = "";
        for(int i=0; i<genes.size();i++){
            if(i%6 == 0)
                s = s + "\n" + "G" + ((i/6)+1) + ": " + String.valueOf(genes.elementAt(i));
            else
                s= s +"-"+ String.valueOf(genes.elementAt(i));
        }
        s = s + "\nvalor fitness: "+ valorFitness;
        return s;
    }
}
