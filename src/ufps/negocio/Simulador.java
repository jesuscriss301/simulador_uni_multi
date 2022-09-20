/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ufps.negocio;

import ufps.modelo.Proceso;

/**
 *
 * @author madarme
 */
public class Simulador {
 
    private Proceso cadena_procesos[];

	public Simulador(Object[] cadena_procesos) {
		super();
		
		this.cadena_procesos = new Proceso[cadena_procesos.length];
		short i=0;
		for(Object datos:cadena_procesos) {
			Proceso p =new Proceso();
            p.setId_proceso(i);
            p.setCadena_ejecucion(datos.toString()); 
            this.cadena_procesos[i]=p;
            i++;
        }
		
	}
  
    
}
