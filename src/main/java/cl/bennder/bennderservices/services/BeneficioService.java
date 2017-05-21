/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import cl.bennder.entitybennderwebrest.model.ImagenGenerica;
import cl.bennder.entitybennderwebrest.model.Beneficio;
import cl.bennder.entitybennderwebrest.request.CargarMantenedorBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.GetTodasCategoriaRequest;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.InfoInicioBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.UploadImagenesGenericaRequest;
import cl.bennder.entitybennderwebrest.response.CargarMantenedorBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.GetTodasCategoriaResponse;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.InfoInicioBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.UploadImagenesGenericaResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;
import java.util.ArrayList;
import java.util.List;

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
    public InfoBeneficioResponse guardarBeneficio(InfoBeneficioRequest request);
    
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
    
    
    /***
     * Obtiene los datos de inicio necesarios para la creación/edición de beneficio
     * @param request datos de inicio como categorias, sucursales, info de imagenes genéricas
     * @return 
     */
    public InfoInicioBeneficioResponse getInfoInicioCreaActualizaBeneficio(InfoInicioBeneficioRequest request);
    
    
    public ValidacionResponse guardarImagenesBeneficios(List<ImagenGenerica> imagenesGenericas,List<BeneficioImagen> beneficioImagenes,Integer idProveedor,Integer idBeneficio);
    
    
        /***
     * Método que permite guardar la imagen en sistema de archivos, es decir directorio del Sistema Operativo,
     * definido en standalone.xml location:ImagesDirHandlerLinux
     * @param idProveedor Identificador directorio de proveedor
     * @param imagen Image en byte array
     * @param idMagen identificador de imagen
     * @param extension Extensión de la imagen
     * @param idBeneficio Identificador de beneficio (utilizado para directorio)
     * @return Ruta del archivo almacenado
     */
    public String guardaImagenSistemaArchivos(byte[] imagen, Integer idProveedor, Integer idMagen,String extension,Integer idBeneficio);
    
    /**
     * Método para obtener todo lo necesario para cargar el mantenedor de Beneficios
     * Autor: mgutierrez 21-5-2017
     * @param request Dentro debe estar el id de usuario
     * @return el objeto que contiene una lista`para el mantenedor de beneficios
     */
    public CargarMantenedorBeneficioResponse cargarMantenedorBeneficio(CargarMantenedorBeneficioRequest request);
    }
