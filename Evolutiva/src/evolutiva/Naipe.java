/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.util.Vector;

/**
 *
 * @author Cristian
 */
class Naipe {
    private String id;
    private Vector<String> colores;
    
    public Naipe (String id){this.id = id;}
    
    public void agregarColor (String[] list){
        colores = new Vector<String>();
        for (int i = 0; i<list.length; i++)
            colores.add(list[i]);
    }
    
    public boolean tieneColor(String c){return colores.contains(c);}
    
    @Override
    public String toString(){return this.id;}
    
    public void mostrate(){
        String c = "carta: " + id + "  ---  Colores: " + colores.elementAt(0);
        for (int i = 1; i<colores.size(); i++){
            c = c + "; "+ colores.elementAt(i);
        }
        System.out.println(c);
        
    }
    
}
