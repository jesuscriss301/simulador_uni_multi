package ufps.vistas;
import ufps.negocio.Simulador;
import ufps.util.varios.*;

/**
 * Write a description of class Test_archivo_http here.
 * 
 * @author marco adarme
 * @version (a version number or a date)
 */
public class Test_archivo_http
{
   
    public static void main(String args[])
    {
        String url="https://madarme.co/persistencia/process.txt";
        ArchivoLeerURL file=new ArchivoLeerURL(url);
        Object v[]=file.leerArchivo();
        
        Simulador simulacion=new Simulador(v);
            
    }
    

    
}
