/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dyanez
 */
@Service
@Transactional
public class BeneficioServiceImpl implements BeneficioService{
    
    private static final Logger log = LoggerFactory.getLogger(BeneficioServiceImpl.class);

    @Override
    public InfoBeneficioResponse guardarBenecifio(InfoBeneficioRequest request) {
       InfoBeneficioResponse response = new InfoBeneficioResponse();
       response.setValidacion(new Validacion("0","1","Problemas al guardar información de beneficios"));
       log.info("inicio");
        try {
            log.info("Datos de entrada ->{}",request.toString());
            response.getValidacion().setCodigoNegocio("0");
            response.getValidacion().setMensaje("Se guarda información de beneficios correctamente");
            
        } catch (Exception e) {
            log.error("Exception guardarBenecifio,",e);
            response.getValidacion().setCodigo("1");
            response.getValidacion().setCodigoNegocio("1");
            response.getValidacion().setMensaje("Error al guardar información de beneficios");
        }
        log.info("fin");
        return response;
    }
    
    
    
}
