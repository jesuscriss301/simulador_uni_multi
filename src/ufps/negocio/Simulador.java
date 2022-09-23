/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import ufps.modelo.Proceso;

/**
 *
 * @author jesus Cristancho
 */
public class Simulador {

    private Proceso cadena_procesos[];

    public Simulador(Object[] cadena_procesos) {
        super();

        this.cadena_procesos = new Proceso[cadena_procesos.length];
        short i = 0;
        for (Object datos : cadena_procesos) {
            Proceso p = new Proceso();
            p.setId_proceso(i);
            p.setCadena_ejecucion(datos.toString());
            this.cadena_procesos[i] = p;
            i++;
        }
    }

    public char[][] uniproceso() {
        
        char[][] n = new char[this.cadena_procesos.length][secuenciasCadena()];
        int l = 0;
        for (Proceso datos : cadena_procesos) {
            char[] array = datos.getCadena_ejecucion().toCharArray();

            for (char k : array) {
                n[datos.getId_proceso()][l] = k;
                l++;
            }
        }
        return n;
    }
    
     private int secuenciasCadena(){
         int j = 0;
        for (Proceso datos : cadena_procesos) {
            j += datos.getCadena_ejecucion().length();
        }
        return j;
     }
    
    public char[][] multiproceso() {
        
        char[][] n = new char[this.cadena_procesos.length][secuenciasCadena()];
        int l = 0;
        for (Proceso datos : cadena_procesos) {
            char[] array = datos.getCadena_ejecucion().toCharArray();

            for (char k : array) {
                n[datos.getId_proceso()][l] = k;
                l++;
            }
        }
        return n;
    }
     
}
