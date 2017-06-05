/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.mapper.SucursalMapper;
import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.InfoInicioSucursalRequest;
import cl.bennder.entitybennderwebrest.response.InfoInicioSucursalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ext_dayanez
 */
@Service
@Transactional
public class SucursalServiceImpl implements SucursalService{
    
    @Autowired
    private SucursalMapper sucursalMapper;

    private static final Logger log = LoggerFactory.getLogger(SucursalServiceImpl.class);
    
    @Override
    public InfoInicioSucursalResponse getInfoInicioSucursal(InfoInicioSucursalRequest request) {
        InfoInicioSucursalResponse response = new InfoInicioSucursalResponse();
        response.setValidacion(new Validacion("0","1","Problemas al obtener información de sucursal"));
        
        try {
            response.setRegiones(sucursalMapper.getRegiones());
            response.setComunas(sucursalMapper.getComunas());
            
            if(request.getIdSucursal() != null){
                response.setSucursal(sucursalMapper.getSucursal(request.getIdSucursal()));
            }
            response.setValidacion(new Validacion("0","0","Datos sucursal OK."));
        } catch (Exception e) {
            response.setValidacion(new Validacion("1","1","Error al obtener información de sucursal"));
            log.error("Exception getInfoInicioSucursal,",e);
        }
        
        
        return response;
    }
    
    
    
}
