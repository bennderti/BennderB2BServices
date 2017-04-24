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
public class ParametroSistema {
    private Integer idParametro;
    private String tipoParametro;
    private String clave;
    private String valorA;
    private String valorB;

    public ParametroSistema() {
    }

    public ParametroSistema(Integer idParametro, String tipoParametro, String clave, String valorA, String valorB) {
        this.idParametro = idParametro;
        this.tipoParametro = tipoParametro;
        this.clave = clave;
        this.valorA = valorA;
        this.valorB = valorB;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public String getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(String tipoParametro) {
        this.tipoParametro = tipoParametro;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValorA() {
        return valorA;
    }

    public void setValorA(String valorA) {
        this.valorA = valorA;
    }

    public String getValorB() {
        return valorB;
    }

    public void setValorB(String valorB) {
        this.valorB = valorB;
    }
    
    
}
