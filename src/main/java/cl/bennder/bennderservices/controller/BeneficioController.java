/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.controller;

import cl.bennder.bennderservices.services.BeneficioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;

/**
 *
 * @author dyanez
 */
@RestController
public class BeneficioController {

    private static final Logger log = LoggerFactory.getLogger(BeneficioController.class);

    @Autowired
    BeneficioService beneficioService;

    /***
     * Servicio expuesto para guardar datos de beneficio
     * @param request
     * @return 
     */
    @RequestMapping(value = "beneficio/guardar", method = RequestMethod.POST)
    public @ResponseBody InfoBeneficioResponse guardarBeneficio(@RequestBody InfoBeneficioRequest request) {
        log.info("[beneficio/guardar] - inicio ");
        InfoBeneficioResponse response = beneficioService.guardarBenecifio(request);
        log.info("[beneficio/guardar] - fin ");
        
        return response;
    }
    
}
