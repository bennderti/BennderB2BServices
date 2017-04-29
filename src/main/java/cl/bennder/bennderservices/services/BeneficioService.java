/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;

/**
 * Servicios relacionados con beneficios
 * @author dyanez
 */
public interface BeneficioService {
    /***
     * Guarda/actualiza información de beneficio
     * @param request Datos de beneficio
     * @return 
     */
    public InfoBeneficioResponse guardarBenecifio(InfoBeneficioRequest request);
}
