/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import java.util.Arrays;
import java.util.PriorityQueue;
import ufps.modelo.Proceso;

/**
 *
 * @author jesus Cristancho
 */
public class Simulador  {

    private Proceso cadena_procesos[];
    private int procesoUni;
    private int procesoMult;

    public Simulador() {
        this.cadena_procesos=null;
        this.procesoMult=0;
        this.procesoUni=0;
        
    }

    
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
        this.procesoUni = secuenciasCadena();
        Simulador s=new Simulador();
        s.setCadena_procesos(cadena_procesos);
        this.procesoMult=s.getProcesoMult();
        
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

    public int secuenciasCadena() {
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
        cola.addAll(Arrays.asList(cadena_procesos));
        int i = 0;

        while (cola.size() != 0) {
            int k = 0;
            Proceso dato = new Proceso();
            do {
                dato = (Proceso) cola.poll();
            } while (dato.getCadena_ejecucion().equals(""));
            char caracter;
            String c = new String();
            do {
                int x=0;
                while (n[dato.getId_proceso()][i] == 'I'&&x>=cola.size()) {
                    
                    Proceso aux = new Proceso();
                    aux = (Proceso) cola.poll();
                    cola.add(dato);
                    dato = aux;
                    x++;
                    
                }
                while(x>=cola.size()&&n[dato.getId_proceso()][i] == 'I'){
                    i++;
                    }
                if (c.length() == 1) {
                    if(cola.size() != 0){
                     this.procesoMult = i+1;
                    }
                    caracter = 'E';
                    i ++;
                    
                    break;
                } else {
                    c = dato.getCadena_ejecucion().substring(k);
                    caracter = c.charAt(0);
                    n[dato.getId_proceso()][i] = caracter;
                }
                k++;
                i++;

            } while (caracter == 'R');
            i--;
            int j = i;
            while (caracter == 'I') {

                n[dato.getId_proceso()][j] = caracter;
                c = dato.getCadena_ejecucion().substring(k);
                k++;
                j++;
                caracter = c.charAt(0);
            }
            if (caracter != 'E') {
                dato.setCadena_ejecucion(c);
                cola.add(dato);
            }
        }
        
        return n;
    }

    public int getProcesoUni() {
        return procesoUni;
    }

    public int getProcesoMult() {
        if(procesoMult==0)multiproceso();
        return procesoMult;
    }

    public String procesar() throws CloneNotSupportedException {
        String procesar = "";
        procesar = "El uso de CPU se describe a continuación:\n \n";

        procesar += "% CPU Uniprograming: (";
        String verdes = "";
        String amarillos = "";
        float verde = 0;
        float amarillo = 0;
        //Simulador clon = (Simulador) this.clone();
        for (int i = 0; i < cadena_procesos.length; i++) {
            verdes += cadena_procesos[i].getcantidadR() + "";
            amarillos += cadena_procesos[i].getcantidadI() + "";
            verde += cadena_procesos[i].getcantidadR();
            amarillo += cadena_procesos[i].getcantidadI();
            if (i != (cadena_procesos.length - 1)) {
                verdes += "+";
                amarillos += "+";
            }
        }
        procesar += verdes + ")  /   (" + verdes + "+" + amarillos + ")    =   " + verde + "/" + (verde + amarillo) + "   =   " + (verde / (verde + amarillo))*100+"% \n";
        procesar += "% CPU Multiprograming: (";
        procesar += verdes + ")  /   (" + verdes + "+" + (getProcesoMult()-verde)+")  =   "+verde + "/"+procesoMult+ "  =   "+(verde/procesoMult)*100+"% \n";
        return procesar;
    }

    @Override
    public String toString() {
        String rta = "";
        if (cadena_procesos.length > 0) {
            rta = "Los procesos de cargaron con exito, con la siguente informacion \n \n";
            for (int i = 0; i < this.cadena_procesos.length; i++) {
                rta += cadena_procesos[i].toString() + "\n";
            }
        } else {
            rta = "Dijite por favor la URL donde tiene la cadena de procesos";
        }

        return rta;
    }

    public void setCadena_procesos(Object[] cadena_procesos) {
        this.cadena_procesos = new Proceso[cadena_procesos.length];
        short i = 0;
        for (Object datos : cadena_procesos) {
            Proceso p = new Proceso();
            p.setId_proceso(i);
            p.setCadena_ejecucion(datos.toString());
            this.cadena_procesos[i] = p;
            i++;
        }
        this.procesoUni = secuenciasCadena();
    }

}
