/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package evolutiva;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 *
 * @author Torres Cristian
 */
public class Info {
    //private String contenido;
    private static int cantNaipes;
    private static int cantColores;
    private static int cantGrupos;
    private static Vector <Naipe> cartas;
    private static Vector <String> colores;
    
    public static final String EOF = null;
    
    public Info(){}    
    
    public static int getCantColores() {
        return cantColores;
    }

    public static int getCantGrupos() {
        return cantGrupos;
    }

    public static int getCantNaipes() {
        return cantNaipes;
    }

    public static Vector<String> getColores() {
        return colores;
    }
    
    
    public static boolean tieneColor(int naipe, String c){
        return cartas.elementAt(naipe).tieneColor(c);
    }
    
    
    //funcion que abre un Archivo de una sola linea. y retorna el valor Entero.
    private static int abrirArchivoLinea(String path) throws IOException{
        File archivo = new File(path);
        int i = 0;
        try {
            BufferedReader entrada = new BufferedReader( new FileReader( archivo ) );          
            i = Integer.parseInt(entrada.readLine());
        }catch (FileNotFoundException e) {
            System.out.println("El archivo " + archivo.getName() + " no existe.");
        }
        return i;
    }
    
    public static void abrirArchivoCantColores(String rutaArchivo) throws IOException{
        cantColores = abrirArchivoLinea(rutaArchivo); //numero maximo de colores
        //System.out.println(cantColores);
    }
    
    public static void abrirArchivoCantNaipes(String rutaArchivo) throws IOException{
        cantNaipes = abrirArchivoLinea(rutaArchivo); //numero masimo de naipes
        //System.out.println(cantNaipes);
    }
    
    public static void abrirArchivoCantGrupos(String rutaArchivo) throws IOException{
        cantGrupos = abrirArchivoLinea(rutaArchivo); //Cantidad de Grupos
        //System.out.println(cantGrupos);
    }
    
    public static void AbrirArchivoColores(String rutaArchivo) throws IOException{
        //verificar que los colores correspondan en algun lugar.
        //Castea a Enteros, no es generico en caso de poseer los nombres y no los valores.
        colores = new Vector<String>();
        File archivo = new File(rutaArchivo);
        try {
            BufferedReader entrada = new BufferedReader( new FileReader( archivo ) );          
            String color = entrada.readLine();
            while (color == null ? Info.EOF != null : !color.equals(Info.EOF)){
                colores.addElement(color);  //Agrego el color a la lista de colores
                color = entrada.readLine();
                
            }
        }catch (FileNotFoundException e) {
            System.out.println("El archivo " + archivo.getName() + " no existe.");
        }
    }
    
    public static void AbrirArchivoNaipes(String rutaArchivo) throws IOException{
        //verificar que los colores pertenezcan a la lista de colores.
        //verificar que la long del arreglo al finalizar es igual a la cantidad de naipes !=.
        cartas = new Vector<Naipe>();
        File archivo = new File(rutaArchivo);   
        try {
            BufferedReader entrada = new BufferedReader( new FileReader( archivo ) );          
            String linea = entrada.readLine();                //Obtengo la primera linea
            while (linea == null ? Info.EOF != null : !linea.equals(Info.EOF)){
                nuevoNaipe(linea);              //genero la nueva carta y la agrego al contenedor
                linea = entrada.readLine();                
            }
        }catch (FileNotFoundException e) {
            System.out.println("El archivo " + archivo.getName() + " no existe.");
        }
    }

    private static void nuevoNaipe(String linea) {
        String[] l = linea.split("\t"); //separo cata --- colores
        Naipe n = new Naipe(l[0]);// nueva carta
        String list = l[1];
        n.agregarColor(list.split(";")); //Agrego en la carta la lista de colores.
        
        cartas.add(n); //Agrego la nueva carla al Contenedor de Cartas
    }
    

    public static void mostrate(){
        
        System.out.println("Lista de Cartas");
        for(int i=0; i<cartas.size(); i++){
            cartas.elementAt(i).mostrate();
        }
    }
                
}
