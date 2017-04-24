/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.model;

/**
 *
 * @author dyanez
 */
public class PlantillaCorreo {
    private  Integer idPlantilla;
    private String nombre;
    private String asunto;

    public PlantillaCorreo(Integer idPlantilla, String nombre, String asunto) {
        this.idPlantilla = idPlantilla;
        this.nombre = nombre;
        this.asunto = asunto;
    }

    public PlantillaCorreo() {
    }

    public Integer getIdPlantilla() {
        return idPlantilla;
    }

    public void setIdPlantilla(Integer idPlantilla) {
        this.idPlantilla = idPlantilla;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    @Override
    public String toString() {
        return "PlantillaCorreo{" + "idPlantilla=" + idPlantilla + ", nombre=" + nombre + ", asunto=" + asunto + '}';
    }
    
}

