/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.request.InfoInicioSucursalRequest;
import cl.bennder.entitybennderwebrest.request.InfoSucursalRequest;
import cl.bennder.entitybennderwebrest.request.SucursalesRequest;
import cl.bennder.entitybennderwebrest.response.InfoInicioSucursalResponse;
import cl.bennder.entitybennderwebrest.response.InfoSucursalResponse;
import cl.bennder.entitybennderwebrest.response.SucursalesResponse;

/**
 *
 * @author ext_dayanez
 */
public interface SucursalService {
    
    public InfoInicioSucursalResponse getInfoInicioSucursal(InfoInicioSucursalRequest request);
    
    public InfoSucursalResponse guardarSucursal(InfoSucursalRequest request);
    
    public SucursalesResponse getSucursalesProveedor(SucursalesRequest request);
}
