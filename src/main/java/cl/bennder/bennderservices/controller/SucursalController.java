/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.controller;

import cl.bennder.bennderservices.services.SucursalService;
import cl.bennder.entitybennderwebrest.request.InfoInicioSucursalRequest;
import cl.bennder.entitybennderwebrest.response.InfoInicioSucursalResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author dyanez
 */
@RestController
public class SucursalController {
    
    
    private static final Logger log = LoggerFactory.getLogger(SucursalController.class);
    
    @Autowired
    private SucursalService sucursalService;   
    
    
     @RequestMapping(value = "sucursal/getInfoInicio",method = RequestMethod.POST)
    public InfoInicioSucursalResponse getInfoInicioSucursal(@RequestBody InfoInicioSucursalRequest request){
        log.info("INICIO");
        InfoInicioSucursalResponse response = sucursalService.getInfoInicioSucursal(request);
        log.info("response ->{}",response.toString());
        log.info("FIN");
        return response;
    }
    
}
