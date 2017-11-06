/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.albarregas.CRUDAlex.beans;

import java.util.Date;

/**
 *
 * @author Alex
 */
public class Ave {
    
    private String anilla;
    private String especie;
    private String lugar;
    private Date fecha;

    public Ave(String anilla, String especie, String lugar, Date fecha) {
        this.anilla = anilla;
        this.especie = especie;
        this.lugar = lugar;
        this.fecha = fecha;
    }

    public String getAnilla() {
        return anilla;
    }

    public void setAnilla(String anilla) {
        this.anilla = anilla;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    
    
}
