/*
 * EvolutivaApp.java
 */

package evolutiva;

import org.jdesktop.application.Application;
import org.jdesktop.application.SingleFrameApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * The main class of the application.
 */
public class EvolutivaApp extends SingleFrameApplication {
    
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
        
        
        String ACC = "C:\\instanciaN120G20cantColores.txt";
        String ACG = "C:\\instanciaN120G20\\cantGrupos.txt";
        String ACN = "C:\\instanciaN120G20\\cantNaipes.txt";
        String AC  = "C:\\instanciaN120G20\\Colores.txt";
        String AN  = "C:\\instanciaN120G20\\Naipes.txt";
        
        Algoritmo a = new Algoritmo(ACC, ACG, ACN, AC, AN);
        a.ejecutar();
        
    }
}
