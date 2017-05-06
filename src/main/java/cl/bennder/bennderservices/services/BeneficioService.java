/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.request.GetTodasCategoriaRequest;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.UploadImagenesGenericaRequest;
import cl.bennder.entitybennderwebrest.response.GetTodasCategoriaResponse;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.UploadImagenesGenericaResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;

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
    
    /***
     * Servicio encargado de generar directorio de imagenes para categoria/subcategoria, creando o actualizando carpetas
     * utilizando identificador de 
     * @return Validación de creación de directorios
     */
    public ValidacionResponse creaActualizaDirectorioGenericoImagenes();
    
    
    /***
     * Obtiene todas las categorias/subcategorias con datos simples.
     * @param request
     * @return Lista categoroias
     */
    public GetTodasCategoriaResponse getTodasCategorias(GetTodasCategoriaRequest request);
    
    /***
     * Método que consume servicio de guardar y subir las iamgenes generecias de categoria/subcategoria seleccionada
     * @param request
     * @return 
     */
    public UploadImagenesGenericaResponse uploadImagenesGenerica(UploadImagenesGenericaRequest request);
    
}
