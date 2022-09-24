/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import java.util.PriorityQueue;
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

    private int secuenciasCadena() {
        int j = 0;
        for (Proceso datos : cadena_procesos) {
            j += datos.getCadena_ejecucion().length();
        }
        return j;
    }

    public char[][] multiproceso() {
        int cadena = this.cadena_procesos.length;
        char[][] n = new char[cadena][secuenciasCadena()];
        PriorityQueue cola = new PriorityQueue<Proceso>(cadena);
        for (Proceso dato : cadena_procesos) {
            cola.add(dato);
        }
        int i = 0;

        while (!cola.isEmpty()) {
            int k = 0;
            Proceso dato = new Proceso();
            do {
                dato = (Proceso) cola.poll();
            } while (dato.getCadena_ejecucion().equals(""));
            char caracter;
            String c = new String();
            do {
                if (c.equals("")&&dato.getCadena_ejecucion().equals("")) {
                    
                    dato = (Proceso) cola.poll();
                }
                while(n[dato.getId_proceso()][i]=='I'){
                Proceso aux=new Proceso();
                aux = (Proceso) cola.poll();
                cola.add(dato);
                dato= aux;
                }
                c = dato.getCadena_ejecucion().substring(k);
                caracter = c.charAt(0);
                n[dato.getId_proceso()][i] = caracter;
                k++;
                i++;

        }
        while (caracter == 'R');
        i--;
        int j = i;
        //cola.add(dato);
        //caracter=c.charAt(0);
        while (caracter == 'I') {

            n[dato.getId_proceso()][j] = caracter;
            c = dato.getCadena_ejecucion().substring(k);
            //cola.add(dato);
            k++;
            j++;
            caracter = c.charAt(0);
        }
        dato.setCadena_ejecucion(c);
        cola.add(dato);

    }

    return n ;
}

}
