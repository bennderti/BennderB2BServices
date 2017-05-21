/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.controller;

import cl.bennder.bennderservices.security.JwtTokenUtil;
import cl.bennder.bennderservices.services.BeneficioService;
import cl.bennder.entitybennderwebrest.request.GetTodasCategoriaRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.InfoInicioBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.UploadImagenesGenericaRequest;
import cl.bennder.entitybennderwebrest.request.CargarMantenedorBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.PublicarBeneficiosRequest;
import cl.bennder.entitybennderwebrest.response.CargarMantenedorBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.GetTodasCategoriaResponse;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.InfoInicioBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.UploadImagenesGenericaResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author dyanez
 */
@RestController
public class BeneficioController {

    private static final Logger log = LoggerFactory.getLogger(BeneficioController.class);

    @Autowired
    BeneficioService beneficioService;
    
    @Autowired
    JwtTokenUtil jwtTokenUtil;

    /***
     * Servicio expuesto para guardar datos de beneficio
     * @param request
     * @return 
     */
    @RequestMapping(value = "beneficio/guardar", method = RequestMethod.POST)
    public @ResponseBody InfoBeneficioResponse guardarBeneficio(@RequestBody InfoBeneficioRequest request,HttpServletRequest req) {
        log.info("[beneficio/guardar] - inicio ");
        request.setIdUsuario(jwtTokenUtil.getIdUsuarioDesdeRequest(req));
        InfoBeneficioResponse response = beneficioService.guardarBeneficio(request);
        log.info("[beneficio/guardar] - fin ");
        
        return response;
    }
    @RequestMapping(value = "beneficio/creaActualizaDirectorioGenericoImagenes", method = RequestMethod.POST)
    public @ResponseBody ValidacionResponse creaActualizaDirectorioGenericoImagenes() {
        log.info("[beneficio/creaActualizaDirectorioGenericoImagenes] - inicio ");
        ValidacionResponse response = beneficioService.creaActualizaDirectorioGenericoImagenes();
        log.info("[beneficio/creaActualizaDirectorioGenericoImagenes] - fin ");
        
        return response;
    }
    @RequestMapping(value = "beneficio/getTodasCategorias", method = RequestMethod.POST)
    public @ResponseBody GetTodasCategoriaResponse getTodasCategorias(@RequestBody GetTodasCategoriaRequest request) {
        log.info("[beneficio/getTodasCategorias] - inicio ");
        GetTodasCategoriaResponse response = beneficioService.getTodasCategorias(request);
        log.info("[beneficio/getTodasCategorias] - fin ");
        
        return response;
    }
     @RequestMapping(value = "beneficio/uploadImagenesGenerica", method = RequestMethod.POST)
    public @ResponseBody UploadImagenesGenericaResponse uploadImagenesGenerica(@RequestBody UploadImagenesGenericaRequest request) {
        log.info("[beneficio/uploadImagenesGenerica] - inicio ");
        UploadImagenesGenericaResponse response = beneficioService.uploadImagenesGenerica(request);
        log.info("[beneficio/uploadImagenesGenerica] - fin ");
        
        return response;
    }
    
      @RequestMapping(value = "beneficio/infoCreaActualiza", method = RequestMethod.POST)
    public @ResponseBody InfoInicioBeneficioResponse getInfoInicioCreaActualizaBeneficio(@RequestBody InfoInicioBeneficioRequest request,HttpServletRequest req) {
        log.info("[beneficio/infoCreaActualiza] - inicio ");
        
        //.- obtnener usuario desde httpserverlet usuando clase de obtencion de usuario dado token y set en request
        //-----------------------------------------------
        //---------------------------------------------
        //---------------------
        request.setIdUsuario(jwtTokenUtil.getIdUsuarioDesdeRequest(req));
        InfoInicioBeneficioResponse response = beneficioService.getInfoInicioCreaActualizaBeneficio(request);
        log.info("[beneficio/infoCreaActualiza] - fin ");
        
        return response;
    }
    
    @RequestMapping(value = "beneficio/cargarMantenedorBeneficio", method = RequestMethod.POST)
    public @ResponseBody CargarMantenedorBeneficioResponse cargarMantenedorBeneficio(HttpServletRequest req) {
        log.info("[beneficio/cargarMantenedorBeneficio] - Inicio ");        
        CargarMantenedorBeneficioRequest request = new CargarMantenedorBeneficioRequest();
        
        request.setIdUsuario(jwtTokenUtil.getIdUsuarioDesdeRequest(req));
        log.info("[beneficio/cargarMantenedorBeneficio] - idUsuario: " + request.getIdUsuario());       
        
        CargarMantenedorBeneficioResponse response = beneficioService.cargarMantenedorBeneficio(request);
        log.info("[beneficio/cargarMantenedorBeneficio] - fin ");
        
        return response;
    }
    
    @RequestMapping(value = "beneficio/publicarBeneficios", method = RequestMethod.POST)
    public @ResponseBody ValidacionResponse publicarBeneficios(@RequestBody PublicarBeneficiosRequest request, HttpServletRequest httpSerReq) {
        log.info("[beneficio/cargarMantenedorBeneficio] - Inicio ");      
        
        request.setIdUsuario(jwtTokenUtil.getIdUsuarioDesdeRequest(httpSerReq));
        log.info("[beneficio/cargarMantenedorBeneficio] - idUsuario: " + request.getIdUsuario());
        
        ValidacionResponse response = beneficioService.publicarBeneficios(request);
        
        log.info("[beneficio/cargarMantenedorBeneficio] - fin ");
        
        return response;
    }   
}
