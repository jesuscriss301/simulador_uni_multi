/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package ufps.modelo;

/**
 *
 *
 *
 * @author Jesus Cristancho
 *
 */
public class Proceso implements Comparable<Proceso> {

    //P0... Pn
    private int id_proceso;

    //RRIIRR...
    private String cadena_ejecucion;

    public Proceso() {

        this.id_proceso = 0;

        this.cadena_ejecucion = "";

    }

    public int getId_proceso() {

        return id_proceso;

    }

    public void setId_proceso(int id_proceso) {

        this.id_proceso = id_proceso;

    }

    public String getCadena_ejecucion() {

        return cadena_ejecucion;

    }

    public void setCadena_ejecucion(String cadena_ejecucion) {
        
        this.cadena_ejecucion = cadena_ejecucion;
        
    }

    public int getcantidadR() {
        int aux = 0;
        for (char caracter : cadena_ejecucion.toCharArray()) {
            if (caracter == 'R') {
                aux++;
            }
        }
        return aux;
    }

    public int getcantidadI() {
        return cadena_ejecucion.length() - getcantidadR();
    }

    @Override
    public int compareTo(Proceso o) {
        if (this.getcantidadI() > o.getcantidadI()) {
            return -1;
        }
        if (this.getcantidadI() < o.getcantidadI()) {
            return 1;
        }
        return 0;

    }

    @Override
    public String toString() {
        return "Proceso{" +id_proceso +  cadena_ejecucion + this.getcantidadI()+ '}';
    }

}
