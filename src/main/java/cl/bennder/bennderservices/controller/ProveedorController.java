/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.controller;

import cl.bennder.bennderservices.services.ProveedorServices;
import cl.bennder.entitybennderwebrest.request.CategoriaByIdRequest;
import cl.bennder.entitybennderwebrest.request.DatosGeneralProveedorRequest;
import cl.bennder.entitybennderwebrest.request.ProveedorIdRequest;
import cl.bennder.entitybennderwebrest.request.SubCategoriaProveedorRequest;
import cl.bennder.entitybennderwebrest.request.UploadBeneficioImagenRequest;
import cl.bennder.entitybennderwebrest.response.BeneficiosCargadorResponse;
import cl.bennder.entitybennderwebrest.response.CategoriasResponse;
import cl.bennder.entitybennderwebrest.response.DatosGeneralProveedorResponse;
import cl.bennder.entitybennderwebrest.response.ProveedoresResponse;
import cl.bennder.entitybennderwebrest.response.SubCategoriaProveedorResponse;
import cl.bennder.entitybennderwebrest.response.UploadBeneficioImagenResponse;
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
public class ProveedorController {
    
    
    private static final Logger log = LoggerFactory.getLogger(ProveedorController.class);
    
    @Autowired
    private ProveedorServices proveedorServices;   
    
    
     @RequestMapping(value = "proveedor/getBeneficiosByIdCat",method = RequestMethod.POST)
    public BeneficiosCargadorResponse getBeneficiosByIdCat(@RequestBody CategoriaByIdRequest request){
        log.info("INICIO");
        BeneficiosCargadorResponse response = proveedorServices.getBeneficiosByIdCat(request);
        log.info("response ->{}",response.toString());
        log.info("FIN");
        return response;
    }
    
    @RequestMapping(value = "proveedor/getSubCategoriasProveedor",method = RequestMethod.POST)
    public SubCategoriaProveedorResponse getSubCategoriasProveedor(@RequestBody SubCategoriaProveedorRequest request){
        log.info("INICIO");
        SubCategoriaProveedorResponse response = proveedorServices.getSubCategoriasProveedor(request);
        log.info("response ->{}",response.toString());
        log.info("FIN");
        return response;
    }
    
    
    @RequestMapping(value = "proveedor/guardaDatosGenerales",method = RequestMethod.POST)
    public DatosGeneralProveedorResponse guardaDatosGeneralesProveedor(@RequestBody DatosGeneralProveedorRequest request){
        log.info("INICIO");
        DatosGeneralProveedorResponse response = proveedorServices.guardaDatosGeneralesProveedor(request);
        log.info("FIN");
        return response;
    }
    
    @RequestMapping(value = "proveedor/obtenerCategoriaByProveedor",method = RequestMethod.POST)
    public CategoriasResponse obtenerCategoriasById(@RequestBody ProveedorIdRequest request){
        log.info("INICIO");
        CategoriasResponse response = proveedorServices.obtenerCategoriaByProveedor(request);
        log.info("FIN");
        return response;
    }
    
    @RequestMapping(value = "proveedor/obtenerProveedorHabilitados",method = RequestMethod.POST)
    public ProveedoresResponse obtenerProveedorHabilitados(@RequestBody ProveedorIdRequest request){
        log.info("INICIO");
        ProveedoresResponse response = proveedorServices.obtenerProveedorHabilitados(request);
        log.info("FIN");        
        return response;
    }
    @RequestMapping(value = "proveedor/uploadImagenesBeneficios",method = RequestMethod.POST)
    public UploadBeneficioImagenResponse uploadImagenesBeneficios(@RequestBody UploadBeneficioImagenRequest request){
        log.info("INICIO");
        UploadBeneficioImagenResponse response = proveedorServices.uploadImagenesBeneficios(request);
        log.info("FIN");
        return response;
    }
    
    
    
    
}
