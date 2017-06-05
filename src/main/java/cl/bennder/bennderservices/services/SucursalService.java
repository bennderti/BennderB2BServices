/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.request.InfoInicioSucursalRequest;
import cl.bennder.entitybennderwebrest.response.InfoInicioSucursalResponse;

/**
 *
 * @author ext_dayanez
 */
public interface SucursalService {
    
    public InfoInicioSucursalResponse getInfoInicioSucursal(InfoInicioSucursalRequest request);
}
