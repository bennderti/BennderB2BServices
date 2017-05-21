/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

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

/**
 *
 * @author dyanez
 */
public interface ProveedorServices {
    
    /***
     * Obtiene la lista de categorias de un proveedor
     * @author dyanez
     * @param request Identificar de proveedor
     * @return Lista de categorias para proveedor
     */
    public CategoriasResponse obtenerCategoriaByProveedor(ProveedorIdRequest request);
    
    /***
     * Proveedores habilitados
     * @param request
     * @return 
     */
    public ProveedoresResponse obtenerProveedorHabilitados(ProveedorIdRequest request);
    
    /***
     * Servicio que permite guardar/actualizar los datos generales de proveedor
     * @param request Datos generales de proveedor
     * @return Validación de guardado de proveedor
     */
    public DatosGeneralProveedorResponse guardaDatosGeneralesProveedor(DatosGeneralProveedorRequest request);
    
    /***
     * Método encargado de generar y guardar logo de imagen de proveedor en sistema de archivos de servidor
     * DY - 19.04.2017 
     * @param imagen Logo de proveedor
     * @param idProveedor Identificador de proveedor
     * @param extension Extensión del logo
     * @return Ruta de logo en sistema de archivos
     */
    public String guardaLogoImagenSistemaArchivos(byte[] imagen, Integer idProveedor,  String extension);
    
    
    public SubCategoriaProveedorResponse getSubCategoriasProveedor(SubCategoriaProveedorRequest request);
    BeneficiosCargadorResponse getBeneficiosByIdCat(CategoriaByIdRequest request);
    
    
    /***
     * Método que permite cargar una lista de imagenes a un beneficio en particular, en donde orden 1 indica que es imagen
     * principal
     * @param request Listado de imagenes asociadas al baneficio
     * @return Respuesta de carga de imagen
     * @author dyanez
     */
    public UploadBeneficioImagenResponse uploadImagenesBeneficios(UploadBeneficioImagenRequest request);
    
    
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
    
}
