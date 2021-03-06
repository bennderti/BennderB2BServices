/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.constantes.MantencionAccionBeneficio;
import cl.bennder.bennderservices.constantes.PropertiesDirectorioImagen;
import cl.bennder.bennderservices.constantes.TiposBeneficio;
import cl.bennder.bennderservices.mapper.BeneficioMapper;
import cl.bennder.bennderservices.mapper.CategoriaMapper;
import cl.bennder.bennderservices.mapper.ProveedorMapper;
import cl.bennder.bennderservices.util.ImagenUtil;
import cl.bennder.entitybennderwebrest.model.Adicional;
import cl.bennder.entitybennderwebrest.model.Beneficio;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import cl.bennder.entitybennderwebrest.model.Categoria;
import cl.bennder.entitybennderwebrest.model.Descuento;
import cl.bennder.entitybennderwebrest.model.ImagenEscalable;
import cl.bennder.entitybennderwebrest.model.ImagenGenerica;
import cl.bennder.entitybennderwebrest.model.Producto;
import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.CargarMantenedorBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.GetTodasCategoriaRequest;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.InfoInicioBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.PublicarBeneficiosRequest;
import cl.bennder.entitybennderwebrest.request.UploadImagenesGenericaRequest;
import cl.bennder.entitybennderwebrest.response.CargarMantenedorBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.GetTodasCategoriaResponse;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.InfoInicioBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.UploadImagenesGenericaResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;
import cl.bennder.entitybennderwebrest.utils.UtilsBennder;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.annotation.Resource;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.ListObjectsRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dyanez
 */
@PropertySource("classpath:bennder.properties")
@Service
@Transactional
public class BeneficioServiceImpl implements BeneficioService{
    
    private static final Logger log = LoggerFactory.getLogger(BeneficioServiceImpl.class);

    @Resource
    private Environment env;
    
    @Autowired
    private CategoriaMapper categoriaMapper;

    @Autowired
    ImagenUtil imagenUtil;
    
    @Autowired
    private BeneficioMapper beneficioMapper;
    
    @Autowired
    private ProveedorMapper proveedorMapper;

    @Value("${bucketImagenes}")
    private String bucketImagenes;

    @Value("${S3Server}")
    private String S3Server;

    @Value("${environment}")
    private String environment;

    @Value("${directorio.imagen.generica.location.server}")
    private String imagenGenericaLocation;

    @Value("${directorio.imagen.location.server}")
    private String dirProveedor;

    @Override
    public ImagenEscalable esImagenEscalable(String nombreImagen, List<ImagenEscalable> imagenes) {
        if(imagenes!=null && imagenes.size()>0){
            for(ImagenEscalable img:imagenes){
                if (img.getNombre().compareTo(nombreImagen) == 0){
                    log.info("La imágene->{} se necesita escalar...",nombreImagen);
                    return img;
                }
            }
        }
        
        return null;
    }

     
    @Override
    public String guardaImagenSistemaArchivos(byte[] imagen, Integer idProveedor, Integer idMagen, String extension, Integer idBeneficio) {
         log.info("inicio");
        BufferedOutputStream stream = null;
        String ruta = null;
        try {            
            String rutaRaiz = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.proveedor");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_PROVEEDOR;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.proveedor");// env.getProperty("directorio.imagen.proveedor");
            log.info("rutaRaiz->{}",rutaRaiz);
            log.info("idMagen->{}",idMagen);
            log.info("extension->{}",extension);
            log.info("idProveedor(directorio) ->{} ",idProveedor.toString());
            File directorioProveedor = new File(rutaRaiz + File.separator + idProveedor.toString());
            if (!directorioProveedor.exists()){
                log.info("Directorio proveedor ->{}, no existe, por tanto se crea",idProveedor.toString());
                directorioProveedor.mkdirs();
            }   // Create the file on server
            File directorioBeneficio = new File(rutaRaiz + File.separator + idProveedor.toString()+ File.separator + idBeneficio.toString());
            if (!directorioBeneficio.exists()){
                log.info("Directorio beneficio ->{}, no existe, por tanto se crea",idBeneficio.toString());
                directorioBeneficio.mkdirs();
            }
            
            File serverFile = new File(directorioBeneficio.getAbsolutePath()+ File.separator + idMagen.toString() + "."+extension);
            log.info("serverFile.getAbsolutePath() ->{}",serverFile.getAbsolutePath());
            stream = new BufferedOutputStream(new FileOutputStream(serverFile));            
            if(stream != null){
                log.info("Escribiendo imagen correctamente...");
                String locationServer = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.location.server");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_LOCATION_SERVER;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.location.server");//env.getProperty("directorio.imagen.location.server");
                log.info("locationServer(handlerLocation)->{}",locationServer);
                ruta = locationServer  + idProveedor.toString()+ "/" + idBeneficio.toString() + "/" + idMagen.toString()+ "."+extension;
                //ruta = serverFile.getAbsolutePath();
                stream.write(imagen);
                stream.close();
            }
        }catch (FileNotFoundException ex) {
                log.error("Error IOException en guardaImagenSistemaArchivos",ex);
                ruta  = null;
                
        } catch (IOException ex) {
            log.error("Error FileNotFoundException en guardaImagenSistemaArchivos",ex);
            ruta  = null;
        } finally {
            try {
                if(stream != null){
                    stream.close();
                }
            } catch (IOException ex) {
                log.error("Error IOException en guardaImagenSistemaArchivos",ex);
                ruta  = null;
            }
        }
        log.info("fin");
        return ruta;
    }



    @Override
    public ValidacionResponse guardarImagenesBeneficios(List<ImagenGenerica> imagenesGenericas,List<BeneficioImagen> beneficioImagenes, Integer idProveedor, Integer idBeneficio,List<ImagenEscalable> imagenesEscalables) {
        ValidacionResponse response = new ValidacionResponse();
        response.setValidacion(new Validacion("0","1","Problemas al guardar imagenes"));
        log.info("inicio");
        try {

            Integer maxImagenes = proveedorMapper.getMaxImagenPlanProveedor(idProveedor);
            log.info("máximo de imagenes permitidas ->{}",maxImagenes);

            if(beneficioImagenes != null && beneficioImagenes.size() > 0){
                log.info("request.getBeneficioImagenes().size() ->{}",beneficioImagenes.size() );
                if(beneficioImagenes.size() > maxImagenes){
                    response.getValidacion().setCodigoNegocio("2");
                    response.getValidacion().setMensaje("Ud ha no puede cargar más de "+maxImagenes+" configuradas para su comercio (plan básico) al crear/editar promoción.");
                    log.info("Ud ha no puede cargar más de "+maxImagenes+" configuradas para su comercio (plan básico) al crear/editar promoción.",idBeneficio);
                    return response;
                }





                //Integer idBeneficio = beneficioImagenes.get(0).getIdBeneficio();
                log.info("Cargando imagenes a beneficio(id) ->{}",idBeneficio);
                log.info("Eliminando imagenes anteriores(base datos)");
                beneficioMapper.eliminarImagenesBeneficio(idBeneficio);
                log.info("Eliminando imagenes de beneficio del proveedor");
                String rutaRaiz = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.proveedor");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_PROVEEDOR;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.proveedor");//env.getProperty("directorio.imagen.proveedor");
                String directorioBeneficio = environment + dirProveedor + idProveedor.toString() + "/" + idBeneficio.toString();
                log.info("Eliminando imagenes directorio de beneficio ->{}",directorioBeneficio);
//               UtilsBennder.cleanDirectory(directorioBeneficio, false);
                imagenUtil.eliminarImagenAmazonS3(directorioBeneficio);

                int i = 1;
                boolean datosBeneficioOk = true;
                for(BeneficioImagen bImg : beneficioImagenes){
                    log.info("Datos beneficio iamgen a cargar ->{}",bImg.toString());
                    Integer idImagen = beneficioMapper.getSeqIdImagen();
                    String extension = FilenameUtils.getExtension(bImg.getNombre());
                    String path = "";
                    ImagenEscalable imgEscable = esImagenEscalable(bImg.getNombre(), imagenesEscalables);
                    if(imgEscable != null){
                        //String locationServer = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.location.server");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_LOCATION_SERVER;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.location.server");//env.getProperty("directorio.imagen.location.server");
                        //log.info("locationServer(escalable)->{}",locationServer);
                        //String ruta = locationServer  + idProveedor.toString()+ "/" + idBeneficio.toString() + "/" + idImagen.toString()+ "."+extension;

                        //String pathTemporal = System.getProperty("java.io.tmpdir")+idBeneficio.toString() + File.pathSeparator +idImagen.toString() + "."+extension;
                        //String pathTemporal = System.getProperty("java.io.tmpdir")+idImagen.toString() + "."+extension;
                        //log.info("Guardando imagen escalada en sistema de archivos ruta temporal->{}",pathTemporal);
                        //path = this.guardaImagenSistemaArchivos(UtilsBennder.resizeImageGetByte(pathTemporal,imgEscable.getAnchoEscalable() , imgEscable.getAltoEscalable(), extension), idProveedor, idImagen,extension,idBeneficio);

//                      path = this.guardaImagenSistemaArchivos(bImg.getImagen(), idProveedor, idImagen,extension,idBeneficio);
//                      String pathFileSystem = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.raiz")+path;
//                      UtilsBennder.resizeImage(pathFileSystem,imgEscable.getAnchoEscalable() , imgEscable.getAltoEscalable(),extension);

                        path = imagenUtil.guardarImagenEnAmazonS3(bImg.getImagen(), idProveedor, idImagen,extension,idBeneficio, Optional.of(imgEscable));
                    }
                    else{
                        log.info("Guardando imagen normal en sistema de archivos");
//                      path = this.guardaImagenSistemaArchivos(bImg.getImagen(), idProveedor, idImagen,extension,idBeneficio);
                        path = imagenUtil.guardarImagenEnAmazonS3(bImg.getImagen(), idProveedor, idImagen,extension,idBeneficio, Optional.empty());
                    }
                    log.info("path de imagen sistema archivos->{}",path);
                    if(path != null){
                        bImg.setIdBeneficio(idBeneficio);
                        bImg.setIdImagen(idImagen);
                        bImg.setPath(path);

                        //if(bImg.getOrden() == null){
                        // log.info("Sin orden definido por tanto se aplica según posición en lista ->{}",i);
                        bImg.setOrden(i);
                        i++;
                        //}
                        if(bImg.getNombre() == null || bImg.getNombre() == null){
                            log.info("Sin nombre de imagen",i);
                            datosBeneficioOk = false;
                            break;
                        }
                        else{
                            log.info("Guandando información de imagen en db ->{}",bImg.toString());
                            beneficioMapper.guardaImagenBeneficio(bImg);
                        }
                    }
                }
                if(datosBeneficioOk){
                    response.getValidacion().setCodigoNegocio("0");
                    response.getValidacion().setMensaje("Imagenes cargadas correctamente");
                    log.info("Imagenes cargadas correctamente para beneficio(id) ->{}",idBeneficio);
                }
                else{
                    response.getValidacion().setCodigoNegocio("1");
                    response.getValidacion().setMensaje("Faltan datos de imagen a guardar");
                    log.info("Faltan datos de imagen a guardar para beneficio(id) ->{}",idBeneficio);
                }
            }
            else{
                log.info("Validando si se agregaron imagenes genéricas...");
                if(imagenesGenericas!=null && imagenesGenericas.size() > 0)
                {

                    if(imagenesGenericas.size() > maxImagenes){
                        response.getValidacion().setCodigoNegocio("2");
                        response.getValidacion().setMensaje("Ud ha no puede cargar más de "+maxImagenes+" configuradas para su comercio (plan básico) al crear/editar promoción.");
                        log.info("Ud ha no puede cargar más de "+maxImagenes+" configuradas para su comercio (plan básico) al crear/editar promoción.",idBeneficio);
                        return response;
                    }

                    int i = 1;
                    BeneficioImagen bImg = null;
                    //boolean datosBeneficioOk = true;
                    log.info("Eliminando imagenes anteriores(base datos) de beneficio ->{}",idBeneficio);
                    beneficioMapper.eliminarImagenesBeneficio(idBeneficio);
                    for(ImagenGenerica imgGenerica : imagenesGenericas){
                        log.info("Datos beneficio iamgen a cargar ->{}",imgGenerica.toString());
                        Integer idImagen = beneficioMapper.getSeqIdImagen();
                        bImg  = new BeneficioImagen();
                        //if(bImg.getOrden() == null){
                        //log.info("Sin orden definido por tanto se aplica según posición en lista ->{}",i);
                        bImg.setOrden(i);
                        i++;
                        //}
                        String[] urls = imgGenerica.getUrlImagen().split("[/]+");
                        bImg.setNombre(urls[urls.length-1]);
//                        if(bImg.getNombre() == null || imgGenerica.getUrlImagen() == null){
//                            log.info("Sin nombre/path de imagen",i);
//                            datosBeneficioOk = false;
//                            break;
//                        }
//                        else{
                        //Diego Riveros - cambio a S3Server
                        String server = env.getProperty("server");//PropertiesDirectorioImagen.SERVER;//env.getProperty("server");
                        String urlSinServer = imgGenerica.getUrlImagen().replaceAll(S3Server+bucketImagenes+ "/" + environment, "");
                        log.info("urlSinServer ->{}",urlSinServer);
                        bImg.setIdBeneficio(idBeneficio);
                        bImg.setIdImagen(idImagen);
                        bImg.setPath(urlSinServer);
                        //bImg.setNombre(imgGenerica.getNombre());
                        log.info("Guandando información de imagen en db ->{}",bImg.toString());
                        beneficioMapper.guardaImagenBeneficio(bImg);
                        //}

                    }
//                    if(datosBeneficioOk){
                    response.getValidacion().setCodigoNegocio("0");
                    response.getValidacion().setMensaje("Imagenes cargadas correctamente");
                    log.info("Imagenes cargadas correctamente para beneficio(id) ->{}",idBeneficio);
//                    }
//                    else{
//                        response.getValidacion().setCodigoNegocio("1");
//                        response.getValidacion().setMensaje("Faltan datos de imagen a guardar");
//                        log.info("Faltan datos de imagen a guardar para beneficio(id) ->{}",idBeneficio);
//                    }
                }
                else{
                    response.getValidacion().setCodigoNegocio("1");
                    response.getValidacion().setMensaje("Sin imagenes para cargar a beneficio");
                    log.info("Sin imagenes para cargar a beneficio");
                }

            }
        } catch (Exception e) {
            response.setValidacion(new Validacion("1","1","Error al guardar imagenes"));
            log.error("Exception guardarImagenesBeneficios,",e);
        }
        log.info("fin");
        return response;
    }
     
     
@Override
    public InfoInicioBeneficioResponse getInfoInicioCreaActualizaBeneficio(InfoInicioBeneficioRequest request) {
       InfoInicioBeneficioResponse response = new InfoInicioBeneficioResponse();
       response.setValidacion(new Validacion("0","1","Problemas al obtener datos para creación/actualización de beneficio"));
       log.info("inicio");
        try {
            if(request != null){
                
                //log.info("env.getProperty(os) ->{}",env.getProperty("os"));
                if(request.getIdUsuario()!=null){
                    Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
                    log.info("idProveedor ->{}",idProveedor);
                    response.setMaxImagenes(proveedorMapper.getMaxImagenPlanProveedor(idProveedor));
                    log.info("cantidad maxima de imagenes a cargar ->{}",response.getMaxImagenes());
                    log.info("obteniendo todas las categorias y subcategorias asociadas.");
                    response.setCategorias(categoriaMapper.getAllCategorias());
                    log.info("obteniendo sucursusales del proveedor");
                    response.setSucursales(proveedorMapper.getSucursalProveedor(idProveedor));
                    response.setRegionesSucursal(proveedorMapper.getRegionesSucursales(idProveedor));
                    response.setComunasSucursales(proveedorMapper.getComunasSucursales(idProveedor));
                    
                    if(response.getSucursales()!=null && response.getSucursales().size() > 0){
                        log.info("Generando rutas de imagenes genéricas");
                        //.- recorriendo categoria/subcategorias
                        //.- ¿existe diretorio categoria sub-categoria?
                        //.si: listamos y guardamos las rutas de imagenes de dicho directorio

                        if(response.getCategorias()!=null && response.getCategorias().size() > 0){
                            generarRutaImagenesGenericasS3(request, response);

                            if(request.getIdBeneficio()!=null){
                                log.info("obteniendo datos de beneficio ->{}",request.getIdBeneficio());
                                response.setDatosBeneficio(this.getDatosBeneficio(request.getIdBeneficio()));
                                if(response.getDatosBeneficio()!=null){//
                                    log.info("Datos de beneficio OK");
                                    response.getValidacion().setCodigoNegocio("0");
                                    response.getValidacion().setMensaje("Datos de beneficio OK");
                                }
                                else{
                                    log.info("Problemas al obtener datos de beneficio");
                                    response.getValidacion().setCodigoNegocio("5");
                                    response.getValidacion().setMensaje("Problemas al obtener datos de beneficio");
                                }
                            }
                            else{
                                log.info("Datos inicio OK");
                                response.getValidacion().setCodigoNegocio("0");
                                response.getValidacion().setMensaje("Datos inicio OK");
                            }

                        }
                        else{
                            log.info("Sin categorias");
                            response.getValidacion().setCodigoNegocio("3");
                            response.getValidacion().setMensaje("Sin categorias");
                        }
                        
                    }
                    else{
                        log.info("Sin sucursales habilitadas para comercio proveedor");
                        response.getValidacion().setCodigoNegocio("2");
                        response.getValidacion().setMensaje("Sin sucursales habilitadas para comercio proveedor");
                    }
                }
                else{
                    log.info("Usuario no existe");
                    response.getValidacion().setCodigoNegocio("1");
                    response.getValidacion().setMensaje("Usuario no existe");
                    
                }
            }
            
        } catch (Exception e) {
            log.error("Exception getInfoInicioCreaActualizaBeneficio,",e);
            response.getValidacion().setCodigo("1");
            response.getValidacion().setCodigoNegocio("1");
            response.getValidacion().setMensaje("Error al obtener datos para creación/actualización de beneficio");
        }
        log.info("fin");
        return response;
    }

    @Override
    public UploadImagenesGenericaResponse uploadImagenesGenerica(UploadImagenesGenericaRequest request) {
       UploadImagenesGenericaResponse response = new UploadImagenesGenericaResponse();
       response.setValidacion(new Validacion("0","1","Problemas al cargar imagenes"));
       log.info("inicio");
        try {
            if(request!=null){
                log.info("Datos de entrada ->{}",request.toString());
                if(request.getImagenes()!=null && request.getImagenes().size() > 0){
                    log.info("Comenzando a subir imagenes genéricas de cageoria ->{},subcategoria ->{}",request.getImagenes().get(0).getIdCategoria(),request.getImagenes().get(0).getIdSubCategoria());
                    String rutaRaiz = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_GENERICA_CATEGORIA;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//env.getProperty("directorio.imagen.generica.categoria");
                    BufferedOutputStream stream = null;
                    for(ImagenGenerica img : request.getImagenes()){
                        File dirCategoria = new File(rutaRaiz + File.separator + img.getIdCategoria().toString());
                        if(!dirCategoria.exists()){
                            log.info("Directorio categoria ->{}, no existe, por tanto se crea",img.getIdCategoria().toString());
                            dirCategoria.mkdirs();
                        }
                        File dirSubCategoria = new File(dirCategoria.getAbsolutePath() + File.separator + img.getIdSubCategoria().toString());
                        if(!dirSubCategoria.exists()){
                            log.info("Directorio de sub categoria ->{}, no existe, por tanto se crea",img.getIdSubCategoria().toString());
                            dirSubCategoria.mkdirs();
                        }
                        File rutaImagengenerica = new File(dirSubCategoria.getAbsolutePath() + File.separator +  img.getNombre());
                        log.info("Ruta de imagenen genérica ->{}",rutaImagengenerica.getAbsoluteFile());
                        stream = new BufferedOutputStream(new FileOutputStream(rutaImagengenerica.getAbsoluteFile()));            
                        if(stream != null){
                            log.info("Escribiendo imagen correctamente...");
                            stream.write(img.getImagen());
                            stream.close();
                        }
                    }
                    log.info("Imagenes cargadas correctamente");
                    response.getValidacion().setCodigoNegocio("0");
                    response.getValidacion().setMensaje("Imagenes cargadas correctamente");
                }
                else{
                    log.info("inicio");
                    response.getValidacion().setCodigoNegocio("1");
                    response.getValidacion().setMensaje("Sin iamgenes a cargar");
                }
            }
            
        } catch (Exception e) {
            log.error("Exception uploadImagenesGenerica,",e);
            response.getValidacion().setCodigo("1");
            response.getValidacion().setCodigoNegocio("1");
            response.getValidacion().setMensaje("Error al cargar imagenes");
        }
        log.info("fin");
        return response;
    }

    
    
    @Override
    public GetTodasCategoriaResponse getTodasCategorias(GetTodasCategoriaRequest request) {
        GetTodasCategoriaResponse response = new GetTodasCategoriaResponse();
        response.setValidacion(new Validacion("0","0","Lista de categorias/subcategorias OK"));
        response.setCategorias(categoriaMapper.getAllCategorias());
        return response;
    }
    
    

    @Override
    public ValidacionResponse creaActualizaDirectorioGenericoImagenes() {
        ValidacionResponse response = new ValidacionResponse();
        response.setValidacion(new Validacion("0","1","Problemas actualizar/crear directorio de categorias/subcategorias"));
        try {
            List<Categoria> categorias = categoriaMapper.getAllCategorias();
            if(categorias!=null && categorias.size() > 0){
                String rutaRaiz = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_GENERICA_CATEGORIA;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//env.getProperty("directorio.imagen.generica.categoria");
                log.info("rutaRaiz de imágenes genéricas de categorias->{}",rutaRaiz);
                
                for(Categoria c : categorias){
                    File dirCategoria = new File(rutaRaiz + File.separator + c.getIdCategoria().toString());
                    if(!dirCategoria.exists()){
                        log.info("Directorio categoria ->{}, no existe, por tanto se crea",c.getIdCategoria().toString());
                        dirCategoria.mkdirs();
                    }
                    log.info("Directorio categoria ->{}",dirCategoria.getAbsolutePath());
                    if(c.getSubCategorias()!=null && c.getSubCategorias().size() > 0){
                        for(Categoria sc : c.getSubCategorias()){
                            File dirSubCategoria = new File(dirCategoria.getAbsolutePath() + File.separator + sc.getIdCategoria().toString());
                            if(!dirSubCategoria.exists()){
                                log.info("Directorio de sub categoria ->{}, no existe, por tanto se crea",sc.getIdCategoria().toString());
                                dirSubCategoria.mkdirs();
                            }
                            log.info("Directorio sub-categoria ->{}",dirSubCategoria.getAbsolutePath());
                        }
                    }
                    else{
                        log.info("sin subcategorias para categoria({}) ->{}",c.getIdCategoria(),c.getNombre());
                    }
                }
                log.info("Directorio categoria/subcategoria actualizados OK");
                response.getValidacion().setCodigoNegocio("0");
                response.getValidacion().setMensaje("Directorio categoria/subcategoria actualizados OK");
            }
            else{
                log.info("Sin categorias");
                response.getValidacion().setCodigoNegocio("1");
                response.getValidacion().setMensaje("Sin categorias");
            }
            
            
        } catch (Exception e) {
            log.error("Exception guardarBenecifio,",e);
            response.getValidacion().setCodigo("1");
            response.getValidacion().setCodigoNegocio("1");
            response.getValidacion().setMensaje("Error actualizar/crear directorio de categorias/subcategorias");
        }
        return response;
    }

    @Override
    public Validacion validaDatosBeneficio(InfoBeneficioRequest request) {
       Validacion validacion = new Validacion("0","1","Problemas al validar datos de beneficio");
       log.info("inicio");
       try{
          log.info("validando datos de entrada..."); 
          if(request == null){
              validacion.setCodigoNegocio("2");
              validacion.setMensaje("Favor completar datos");
              log.info("Favor completar datos");
              log.info("fin");
              return validacion;              
          }
        log.info("Datos de entrada ->{}",request.toString()); 
        String mensajeLog ="[idUsuarioProveedor ->"+request.getIdUsuario()+"] ";
        if(request.getIdCategoria() == null){
              validacion.setCodigoNegocio("3");
              validacion.setMensaje("Favor completar categoría");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }
        if(request.getIdUsuario()==null){
              validacion.setCodigoNegocio("3");
              validacion.setMensaje("No existe usuario: "+request.getIdUsuario());
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
        if(idProveedor == null){
              validacion.setCodigoNegocio("3");
              validacion.setMensaje("No existe comercio asociado a usuario: "+request.getIdUsuario());
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }
        if(request.getTitulo()== null  || request.getTitulo().trim().equals("")){
              validacion.setCodigoNegocio("4");
              validacion.setMensaje("Favor completar nombre de beneficio");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }   
        if(request.getDescripcion()== null  || request.getDescripcion().trim().equals("")){
              validacion.setCodigoNegocio("5");
              validacion.setMensaje("Favor completar descripción de beneficio");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }            
        if(request.getFechaInicial()== null){
              validacion.setCodigoNegocio("6");
              validacion.setMensaje("Favor completar fecha de inicio");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }     
        if(request.getFechaExpiracion()== null){
              validacion.setCodigoNegocio("7");
              validacion.setMensaje("Favor completar fecha de expiración");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }   
        if(request.getStock()== null || request.getStock() <= 0){
              validacion.setCodigoNegocio("8");
              validacion.setMensaje("Favor ingresar un stock válido");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }    
        if(request.getLimiteStock()== null || request.getLimiteStock() <= 0){
              validacion.setCodigoNegocio("9");
              validacion.setMensaje("Favor ingresar un limite de stock válido");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }
        if(request.getLimiteStock() >= request.getStock()){
              validacion.setCodigoNegocio("10");
              validacion.setMensaje("El límite de stock no puede ser mayor e igual a stock");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         }
        if(request.getCondiciones() == null || request.getCondiciones().isEmpty()){
              validacion.setCodigoNegocio("11");
              validacion.setMensaje("Favor ingresar condiciones para beneficio");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;              
         } 
        log.info("{} Validando tipo de beneficio...",mensajeLog);
        if(request.getTipoBeneficio() == null || request.getTipoBeneficio().getIdTipoBeneficio() == null 
          ||request.getTipoBeneficio().getIdTipoBeneficio() < 0 || request.getTipoBeneficio().getIdTipoBeneficio() >3 ){
              validacion.setCodigoNegocio("12");
              validacion.setMensaje("Tipo de beneficio no válido");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        if(TiposBeneficio.DESCUENTO.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0
          &&(request.getPorcentajeDescuento() == null ||  request.getPorcentajeDescuento() < 0)){
              validacion.setCodigoNegocio("13");
              validacion.setMensaje("Favor ingresar un descuento mayor a 0");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        
        }
        if(TiposBeneficio.PRODUCTO_OFERTA.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
            
            if(request.getPrecioNormal() == null || request.getPrecioNormal()== 0){
              validacion.setCodigoNegocio("14");
              validacion.setMensaje("Favor ingresar un precio normal mayor a 0");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;
            }
            if(request.getPrecioOferta() == null || request.getPrecioOferta()== 0){
              validacion.setCodigoNegocio("15");
              validacion.setMensaje("Favor ingresar un precio oferta mayor a 0");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;
            }
            if(request.getPrecioOferta() > request.getPrecioNormal()){
              validacion.setCodigoNegocio("16");
              validacion.setMensaje("El precio de oferta no puede ser mayor a precio normal");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion;           
            }
        }
        if(TiposBeneficio.PRODUCTO_ADICIONAL.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
            if(request.getAdicionales() == null || request.getAdicionales().isEmpty()){
              validacion.setCodigoNegocio("17");
              validacion.setMensaje("Favor ingresar al menos un producto adicional");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
            }
        }
        log.info("{} Validando imágenes privadas o genéricas...",mensajeLog);
        
        if((request.getImagenesGenericas() == null || request.getImagenesGenericas().size() == 0)&&
           (request.getImagenesBeneficio() == null || request.getImagenesBeneficio().size() == 0)){
              validacion.setCodigoNegocio("18");
              validacion.setMensaje("Favor cargar imágenes genéricas o propias para beneficio");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        log.info("{} Validando cantidad obligatoria de 2 imágenes privadas o genéricas...",mensajeLog);
        
        if((request.getImagenesGenericas().size() < 2)&&
           (request.getImagenesBeneficio().size() < 2)){
              validacion.setCodigoNegocio("19");
              validacion.setMensaje("Favor cargar un mínimo de 2 imágenes");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        log.info("{} Validando fecha expiración sea mayor que fecha de inicio...",mensajeLog);
        
        if(request.getFechaInicial().getTime() > request.getFechaExpiracion().getTime()){
              validacion.setCodigoNegocio("20");
              validacion.setMensaje("Fecha inicial no puede ser mayor que fecha de expiración");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        log.info("{} Validando selección de sucursales...",mensajeLog);        
        if(request.getSucursales() == null || request.getSucursales().size() == 0){
              validacion.setCodigoNegocio("21");
              validacion.setMensaje("Favor seleccionar por lo menos una sucursal");
              log.info("{} {}",validacion.getMensaje(),mensajeLog);
              log.info("fin");
              return validacion; 
        }
        validacion.setCodigo("0");
        validacion.setCodigoNegocio("0");
        validacion.setMensaje("Validación de datos de beneficio OK");
        log.info("{} {}",validacion.getMensaje(),mensajeLog);
        
        
        } catch (Exception e) {
            log.error("Exception validaDatosBeneficio,",e);
            validacion.setCodigo("1");
            validacion.setCodigoNegocio("1");
            validacion.setMensaje("Error al validar datos de beneficio");
        }
       log.info("fin");
       return validacion;
    }
  @Override
    public InfoBeneficioResponse guardarBeneficio(InfoBeneficioRequest request) {
       InfoBeneficioResponse response = new InfoBeneficioResponse();
       response.setValidacion(new Validacion("0","1","Problemas al guardar información de beneficios"));
       log.info("inicio");
        try {
            
             //.- obtener sequencia OK
             //.- validar datos de entrada OK 
            //.- guardar datos geneleras (conocer si es creación/edicion) OK
            //.- guardar sucursales de beneficio
            //.- guardar información tipo de beneficio OK
            //.- guardar condiciones OK
            //.- guardar imagenes de beneficio 
            
            Validacion validacionBeneficio = this.validaDatosBeneficio(request);
            if(validacionBeneficio.getCodigo().compareTo("0") == 0 && validacionBeneficio.getCodigoNegocio().compareTo("0") == 0){
                
                    log.info("Datos de entrada ->{}",request.toString());
                    String mensajeLog ="[idUsuarioProveedor ->"+request.getIdUsuario()+"] ";
                    Integer idBeneficio = request.getIdBeneficio();
                    log.info("{} Datos de entrada ->{}",mensajeLog,request.toString());
                    Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
                    log.info("{} Validando datos generales...",mensajeLog);
                    Beneficio beneficio = new Beneficio(idBeneficio,request.getTitulo(), request.getDescripcion(), request.getFechaInicial(), request.getFechaExpiracion(), null, null, null, request.getStock(), idProveedor, request.getIdCategoria(), request.getTipoBeneficio(), request.getLimiteStock(), 0,request.isTieneImagenGenerica());
                    if(idBeneficio!=null && idBeneficio!=0){
                        log.info("{} Actualizando datos generales de beneficio ->{}.",mensajeLog,idBeneficio);
                        beneficioMapper.updateDatosGeneralesBeneficio(beneficio);
                        log.info("{} insertando log (edita beneficio).",mensajeLog,idBeneficio);
                        beneficioMapper.insertarLogBeneficio(idBeneficio, request.getIdUsuario(), MantencionAccionBeneficio.EDITA_BENEFICIO);
                    }
                    else{
                        idBeneficio = beneficioMapper.getSeqIdBeneficio();
                        beneficio.setIdBeneficio(idBeneficio);
                        beneficioMapper.insertDatosGeneralesBeneficio(beneficio);
                        log.info("{} Beneficio en creación ,por tanto se obtiene identificador ->{}.",mensajeLog,idBeneficio);
                        log.info("{} insertando log (crea beneficio).",mensajeLog,idBeneficio);
                        beneficioMapper.insertarLogBeneficio(idBeneficio, request.getIdUsuario(), MantencionAccionBeneficio.CREA_BENEFICIO);
                        
                    }
                    log.info("{} Actualizando condiciones({}) de beneficio->{}",mensajeLog,request.getCondiciones().size(),idBeneficio);
                    beneficioMapper.deleteCondiciones(idBeneficio);
                    for(int i = 0;i < request.getCondiciones().size();i++){
                        beneficioMapper.insertCondiciones(request.getCondiciones().get(i), idBeneficio);
                    }

                    log.info("{} Actualizando sucursales({}) de beneficio->{}",mensajeLog,request.getSucursales().size(),idBeneficio);
                    beneficioMapper.deleteSucursales(idBeneficio);
                    for(int i = 0;i < request.getSucursales().size();i++){
                        beneficioMapper.insertSucursal(request.getSucursales().get(i), idBeneficio);
                    }

                    //.-Guardar imagenes de beneficio
                    ValidacionResponse vGuardarImagen = this.guardarImagenesBeneficios(request.getImagenesGenericas(),request.getImagenesBeneficio(),idProveedor,idBeneficio,request.getImagenesEscalables());
                    if(vGuardarImagen == null){
                        vGuardarImagen = new ValidacionResponse(new Validacion("1", "1", "Error al guardar imágenes de beneficio"));
                    }
                        if(vGuardarImagen.getValidacion()!=null && "0".equals(vGuardarImagen.getValidacion().getCodigo())  
                           && "0".equals(vGuardarImagen.getValidacion().getCodigoNegocio())){

                            log.info("{} Validando tipo de beneficio ->{}",mensajeLog,request.getTipoBeneficio().getIdTipoBeneficio());
                            if(TiposBeneficio.DESCUENTO.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
                                Integer existeBdescto = beneficioMapper.existeBeneficioDescuento(idBeneficio);
                                if(existeBdescto > 0){
                                    log.info("{} Actualizando descuento de beneficio ->{}",mensajeLog,idBeneficio);
                                    beneficioMapper.updateBeneficioDescuento(request.getPorcentajeDescuento(), idBeneficio);

                                }
                                else{
                                    log.info("{} Insertando descuento de beneficio ->{}",mensajeLog,idBeneficio);
                                    beneficioMapper.insertBeneficioDescuento(request.getPorcentajeDescuento(), idBeneficio);
                                }
                            }
                            else if(TiposBeneficio.PRODUCTO_OFERTA.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){

                                Integer existeBPrecioNormalOfer = beneficioMapper.existeBeneficioProducto(idBeneficio);
                                if(existeBPrecioNormalOfer > 0){
                                    log.info("{} Actualizando precio oferta/normal ->{}",mensajeLog,idBeneficio);
                                    beneficioMapper.updateBeneficioProducto(request.getPrecioOferta(), request.getPrecioNormal(), idBeneficio);
                                }
                                else{
                                    log.info("{} Insertando precio oferta/normal ->{}",mensajeLog,idBeneficio);
                                    beneficioMapper.insertBeneficioProducto(request.getPrecioOferta(),request.getPrecioNormal(), idBeneficio);
                                }
                            }
                            else if(TiposBeneficio.PRODUCTO_ADICIONAL.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
                                log.info("{} Actualizando información adicional(cantidad:{}) de servicio/producto adicional, para beneficio->{}",mensajeLog,request.getAdicionales().size(),idBeneficio);
                                beneficioMapper.deleteInfoAdicionales(idBeneficio);
                                for(int i = 0;i < request.getAdicionales().size();i++){
                                    beneficioMapper.insertInfoAdicionales(request.getAdicionales().get(i), idBeneficio);
                                }
                            }
                            log.info("{} Datos de beneficio guardados correctamente",mensajeLog);
                            response.getValidacion().setCodigo("0");
                            response.getValidacion().setCodigoNegocio("0");
                            response.getValidacion().setMensaje("Datos de beneficio guardados correctamente");
                            
                    }
                    else{
                            log.info("{} validación ->{}",mensajeLog,vGuardarImagen.getValidacion().getMensaje());
                            response.setValidacion(vGuardarImagen.getValidacion());
                    }

            }
            else{
                response.setValidacion(validacionBeneficio);
            }            
        } catch (Exception e) {
            log.error("Exception guardarBenecifio,",e);
            response.getValidacion().setCodigo("1");
            response.getValidacion().setCodigoNegocio("1");
            response.getValidacion().setMensaje("Error al guardar información de beneficios");
        }
        log.info("fin");
        return response;
    }
    
   
//   @Override
//    public InfoBeneficioResponse guardarBeneficio(InfoBeneficioRequest request) {
//       InfoBeneficioResponse response = new InfoBeneficioResponse();
//       response.setValidacion(new Validacion("0","1","Problemas al guardar información de beneficios"));
//       log.info("inicio");
//        try {
//            
//             //.- obtener sequencia OK
//             //.- validar datos de entrada OK 
//            //.- guardar datos geneleras (conocer si es creación/edicion) OK
//            //.- guardar sucursales de beneficio
//            //.- guardar información tipo de beneficio OK
//            //.- guardar condiciones OK
//            //.- guardar imagenes de beneficio            
//            if(request!=null){
//                log.info("Datos de entrada ->{}",request.toString());
//                String mensajeLog ="[idUsuarioProveedor ->"+request.getIdUsuario()+"] ";
//                Integer idBeneficio = request.getIdBeneficio();
//                if(request.getIdUsuario()!=null){
//                    log.info("{} Datos de entrada ->{}",mensajeLog,request.toString());
//                    Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
//                    if(idProveedor !=null){
//                        log.info("{} Validando datos generales...",mensajeLog);
//                        if(request.getIdCategoria()!=null && request.getTitulo()!=null && request.getDescripcion() != null
//                           && request.getFechaInicial()!= null && request.getFechaExpiracion()!=null && request.getStock()!=null  
//                           && request.getStock()> 0 && request.getCondiciones()!=null && request.getCondiciones().size() > 0
//                           && request.getSucursales()!=null && request.getSucursales().size() > 0 && request.getLimiteStock()!=null  
//                           && request.getLimiteStock()> 0 && request.getStock() > request.getLimiteStock()){
//                           
//                            Beneficio beneficio = new Beneficio(idBeneficio,request.getTitulo(), request.getDescripcion(), request.getFechaInicial(), request.getFechaExpiracion(), null, null, null, request.getStock(), idProveedor, request.getIdCategoria(), request.getTipoBeneficio(), request.getLimiteStock(), 0,request.isTieneImagenGenerica());
//                             if(request.getTipoBeneficio()!=null && request.getTipoBeneficio().getIdTipoBeneficio()!=null
//                               && request.getTipoBeneficio().getIdTipoBeneficio() <= 3){
//                                 
//                                 
//                                if(idBeneficio!=null && idBeneficio!=0){
//                                    log.info("{} Actualizando datos generales de beneficio ->{}.",mensajeLog,idBeneficio);
//                                    beneficioMapper.updateDatosGeneralesBeneficio(beneficio);
//                                }
//                                else{
//                                    idBeneficio = beneficioMapper.getSeqIdBeneficio();
//                                    beneficio.setIdBeneficio(idBeneficio);
//                                    beneficioMapper.insertDatosGeneralesBeneficio(beneficio);
//                                    log.info("{} Beneficio en creación ,por tanto se obtiene identificador ->{}.",mensajeLog,idBeneficio);
//                                }
//                                log.info("{} Actualizando condiciones({}) de beneficio->{}",mensajeLog,request.getCondiciones().size(),idBeneficio);
//                                beneficioMapper.deleteCondiciones(idBeneficio);
//                                for(int i = 0;i < request.getCondiciones().size();i++){
//                                    beneficioMapper.insertCondiciones(request.getCondiciones().get(i), idBeneficio);
//                                }
//                                
//                                log.info("{} Actualizando sucursales({}) de beneficio->{}",mensajeLog,request.getCondiciones().size(),idBeneficio);
//                                beneficioMapper.deleteSucursales(idBeneficio);
//                                for(int i = 0;i < request.getSucursales().size();i++){
//                                    beneficioMapper.insertSucursal(request.getSucursales().get(i), idBeneficio);
//                                }
//                                
//                                //.-Guardar imagenes de beneficio
//                                ValidacionResponse vGuardarImagen = this.guardarImagenesBeneficios(request.getImagenesGenericas(),request.getImagenesBeneficio(),idProveedor,idBeneficio);
//                                if(vGuardarImagen == null){
//                                    vGuardarImagen = new ValidacionResponse(new Validacion("1", "1", "Error al guardar imágenes de beneficio"));
//                                }
//                                    if(vGuardarImagen.getValidacion()!=null && "0".equals(vGuardarImagen.getValidacion().getCodigo())  
//                                       && "0".equals(vGuardarImagen.getValidacion().getCodigoNegocio())){
//                                        
//                                        boolean tipoBeneficioOk = false;
//                                        log.info("{} Validando tipo de beneficio ->{}",mensajeLog,request.getTipoBeneficio().getIdTipoBeneficio());
//                                        if(TiposBeneficio.DESCUENTO.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
//                                            if(request.getPorcentajeDescuento()!=null && request.getPorcentajeDescuento() > 0){
//                                                Integer existeBdescto = beneficioMapper.existeBeneficioDescuento(idBeneficio);
//                                                if(existeBdescto > 0){
//                                                    log.info("{} Actualizando descuento de beneficio ->{}",mensajeLog,idBeneficio);
//                                                    beneficioMapper.updateBeneficioDescuento(request.getPorcentajeDescuento(), idBeneficio);
//
//                                                }
//                                                else{
//                                                    log.info("{} Insertando descuento de beneficio ->{}",mensajeLog,idBeneficio);
//                                                    beneficioMapper.insertBeneficioDescuento(request.getPorcentajeDescuento(), idBeneficio);
//                                                }
//                                                tipoBeneficioOk = true;
//                                            }
//                                            else{
//                                                log.info("{} Favor ingresar % desceuento válido o mayor a 0",mensajeLog);
//                                                response.getValidacion().setCodigoNegocio("4");
//                                                response.getValidacion().setMensaje("Favor ingresar % desceuento válido o mayor a 0");
//                                            }
//                                        }
//                                        else if(TiposBeneficio.PRODUCTO_OFERTA.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
//
//                                            if(request.getPrecioNormal()!=null &&  
//                                               request.getPrecioOferta()!=null && 
//                                               request.getPrecioNormal() > request.getPrecioOferta()){
//                                               Integer existeBPrecioNormalOfer = beneficioMapper.existeBeneficioProducto(idBeneficio);
//                                               if(existeBPrecioNormalOfer > 0){
//                                                   log.info("{} Actualizando precio oferta/normal ->{}",mensajeLog,idBeneficio);
//                                                   beneficioMapper.updateBeneficioProducto(request.getPrecioOferta(), request.getPrecioNormal(), idBeneficio);
//                                               }
//                                               else{
//                                                   log.info("{} Insertando precio oferta/normal ->{}",mensajeLog,idBeneficio);
//                                                   beneficioMapper.insertBeneficioProducto(request.getPrecioOferta(),request.getPrecioNormal(), idBeneficio);
//                                               } 
//                                               tipoBeneficioOk = true;
//                                            }
//                                            else{
//                                                log.info("{} Favor ingresar precio normal/oferta válidos",mensajeLog);
//                                                response.getValidacion().setCodigoNegocio("5");
//                                                response.getValidacion().setMensaje("Favor ingresar precio normal/oferta válidos");
//                                            }
//                                        }
//                                        else if(TiposBeneficio.PRODUCTO_ADICIONAL.compareTo(request.getTipoBeneficio().getIdTipoBeneficio()) == 0){
//                                            if(request.getAdicionales()!=null && request.getAdicionales().size() > 0){
//                                                log.info("{} Actualizando información adicional(cantidad:{}) de servicio/producto adicional, para beneficio->{}",mensajeLog,request.getAdicionales().size(),idBeneficio);
//                                                beneficioMapper.deleteInfoAdicionales(idBeneficio);
//                                                for(int i = 0;i < request.getAdicionales().size();i++){
//                                                    beneficioMapper.insertInfoAdicionales(request.getAdicionales().get(i), idBeneficio);
//                                                }
//                                                tipoBeneficioOk = true;
//                                            }
//                                            else{
//                                                log.info("{} Favor ingresar descripción de productos/servicios adicionales",mensajeLog);
//                                                response.getValidacion().setCodigoNegocio("6");
//                                                response.getValidacion().setMensaje("Favor ingresar descripción de productos/servicios adicionales");
//                                            }
//                                        }
//                                        
//                                        if(tipoBeneficioOk){
//                                            log.info("{} Datos de beneficio guardados correctamente",mensajeLog);
//                                            response.getValidacion().setCodigo("0");
//                                            response.getValidacion().setCodigoNegocio("0");
//                                            response.getValidacion().setMensaje("Datos de beneficio guardados correctamente");
//                                        }
//                                }
//                                else{
//                                        log.info("{} validación ->{}",mensajeLog,vGuardarImagen.getValidacion().getMensaje());
//                                        response.setValidacion(vGuardarImagen.getValidacion());
//                                }
//                            }
//                            else{
//                                log.info("{} Tipo de beneficio no válido o no existe",mensajeLog);
//                                response.getValidacion().setCodigoNegocio("3");
//                                response.getValidacion().setMensaje("Tipo de beneficio no válido o no existe");
//                            }                             
//                        }
//                        else{
//                            log.info("{} Favor completar los datos generales de beneficio (nombre, descripcion fecha inicio/fin,limite stock,sotck, categoria,condiciones,sucursales)",mensajeLog);
//                            response.getValidacion().setCodigoNegocio("6");
//                            response.getValidacion().setMensaje("Favor completar los datos generales de beneficio (nombre, descripcion fecha inicio/fin,limite stock,sotck, categoria,condiciones,sucursales)");
//                        }
//                  
//                    }
//                    else{
//                        log.info("{} No existe comercio proveedor para usuario conectado",mensajeLog);
//                        response.getValidacion().setCodigoNegocio("5");
//                        response.getValidacion().setMensaje("No existe comercio proveedor para usuario conectado");
//                    }
//                    
//                }
//                else{
//                    log.info("{} No existe usuario proveedor en sesión",mensajeLog);
//                    response.getValidacion().setCodigoNegocio("2");
//                    response.getValidacion().setMensaje("Datos vacios");
//                }
//            }
//            else{
//                
//                response.getValidacion().setCodigoNegocio("1");
//                response.getValidacion().setMensaje("Datos vacios");
//            }
//            
//        } catch (Exception e) {
//            log.error("Exception guardarBenecifio,",e);
//            response.getValidacion().setCodigo("1");
//            response.getValidacion().setCodigoNegocio("1");
//            response.getValidacion().setMensaje("Error al guardar información de beneficios");
//        }
//        log.info("fin");
//        return response;
//    }
    @Override
    public CargarMantenedorBeneficioResponse cargarMantenedorBeneficio(CargarMantenedorBeneficioRequest request) {
       
        log.info("inicio");
        CargarMantenedorBeneficioResponse response = new CargarMantenedorBeneficioResponse();
        
        //Obtener proveedor
        Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
        log.info("Id de Proveedor {}", idProveedor);
        
        //Obtener lista de beneficios
        response.setListaBeneficios(beneficioMapper.obtenetListaBeneficiosMantenedor(idProveedor));        
        log.info("Cantidad de Beneficios {}", response.getListaBeneficios().size());
        
        log.info("fin");
        return response;
    }

    @Override
    public ValidacionResponse publicarBeneficios(PublicarBeneficiosRequest request) {
        log.info("inicio");
        ValidacionResponse response = new ValidacionResponse();  
        response.setValidacion(new Validacion("0","1","Problemas al publicar promociones"));
        
        Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
        
        //Obtener cantidad máxima de beneficios publicados permitidos
        Integer cantMaxBeneficios = proveedorMapper.getCantBeneficiosPublicados(idProveedor);
        
        if(request.getListaIdBeneficios() != null)
        {
            log.info("Cantidad de lista de beneficios {}", request.getListaIdBeneficios().size());
            log.info("Cantidad máxima de beneficios a publicar {}", cantMaxBeneficios);
            
            if(request.getListaIdBeneficios().size() <= cantMaxBeneficios)
            {
                //Dejar todos los beneficios habilitado = 0
                beneficioMapper.deshabilitarBeneficios(idProveedor);
                log.info("Se deshabilitan los beneficios con idProveedor {}", idProveedor);
                
                //recorrer lista y habilitar sus beneficios
                for (Integer idBeneficio : request.getListaIdBeneficios()) 
                {
                    beneficioMapper.habilitarBeneficio(idBeneficio);
                    log.info("Se habilita el beneficio id({})", idBeneficio);
                    
                    //beneficioMapper.insertarLogBeneficio(idBeneficio, request.getIdUsuario(), AccionBeneficio.PUBLICA_BENEFICIO);
                }
                response.getValidacion().setCodigo("0");
                response.getValidacion().setMensaje("Operación realizada con éxito.");
                
            }
            else   {         
                response.getValidacion().setMensaje("La cantidad de beneficios a publicar supera los permitidos (" + cantMaxBeneficios + ").");  
            }
        }
        else{
            response.getValidacion().setMensaje("No existen beneficios a publicar");
        }
       
        log.info(response.getValidacion().getMensaje());
        log.info("fin");
        return response; 
    }
    
    @Override
    public InfoBeneficioRequest getDatosBeneficio(Integer idBeneficio) {
        log.info("inicio");
        InfoBeneficioRequest datosBeneficio = new InfoBeneficioRequest();
        try {
            
            //.- obtener datos generales
            //.- obtener condiciones
            //.- obter imagenes
            log.info("obteniendo datos generales de beneficio ->{}",idBeneficio);
            Beneficio beneficio = beneficioMapper.obtenerDetalleBeneficio(idBeneficio);
            datosBeneficio.setTitulo(beneficio.getTitulo());
            datosBeneficio.setIdBeneficio(idBeneficio);
            datosBeneficio.setDescripcion(beneficio.getDescripcion());
            datosBeneficio.setStock(beneficio.getStock());
            datosBeneficio.setLimiteStock(beneficio.getLimiteStock());
            //SimpleDateFormat formatoDelTexto = new SimpleDateFormat("yyyy-MM-dd");
            datosBeneficio.setFechaInicial(beneficio.getFechaInicial());
            datosBeneficio.setFechaExpiracion(beneficio.getFechaExpiracion());
            datosBeneficio.setIdSubCategoria(beneficio.getIdCategoria());
            datosBeneficio.setIdCategoria(categoriaMapper.getCategoriaBySubCat(beneficio.getIdCategoria()).getIdCategoria());
            //adicionales            
//            if(beneficio.getTipoBeneficio()!=null && beneficio.getTipoBeneficio().getIdTipoBeneficio()!=null && beneficio.getTipoBeneficio().getIdTipoBeneficio().compareTo(TiposBeneficio.PRODUCTO_ADICIONAL) == 0){
//               log.info("obteniendo información adicional...");
//                datosBeneficio.setAdicionales(beneficioMapper.getAdicionales(idBeneficio));
//            }
            log.info("obteniendo sucursales/condiciones...");
            datosBeneficio.setSucursales(beneficioMapper.getSucursalesBeneficio(idBeneficio));
            datosBeneficio.setCondiciones(beneficio.getCondiciones());
            if(beneficio instanceof Descuento){
                log.info("tipo descuento...");
                Descuento d = (Descuento)beneficio;
                datosBeneficio.setPorcentajeDescuento(d.getPorcentajeDescuento());
                log.info("d.getPorcentajeDescuento()->{}",d.getPorcentajeDescuento());
            }
            if(beneficio instanceof Producto ){
                log.info("tipo producto...");
                Producto p = (Producto)beneficio;
                datosBeneficio.setPrecioNormal(p.getPrecioNormal());
                datosBeneficio.setPrecioOferta(p.getPrecioOferta());
                 log.info("p.getPrecioNormal()->{},p.getPrecioOferta()->{}",p.getPrecioNormal(),p.getPrecioOferta());
            }
            if(beneficio instanceof Adicional ){
                log.info("tipo adicionales...");
                Adicional a = (Adicional)beneficio;
                datosBeneficio.setAdicionales(a.getDescripciones());
                log.info("a.getDescripciones()->{}",a.getDescripciones());
                
            }
            
            datosBeneficio.setTipoBeneficio(beneficio.getTipoBeneficio());
            datosBeneficio.setTieneImagenGenerica(beneficio.isTieneImagenGenerica());       
            String server = env.getProperty("server");//PropertiesDirectorioImagen.SERVER;//env.getProperty("server");
//            ImagenUtil.setUrlImagenesBenecio(S3Server, beneficio);
            imagenUtil.setUrlImagenesBenecioS3(S3Server, beneficio);
            datosBeneficio.setImagenesBeneficio(beneficio.getImagenesBeneficio());

            
            
            log.info("Datos de datosBeneficio ->{}",datosBeneficio.toString());
            
        } catch (Exception e) {
            datosBeneficio = null;
            log.error("Error exception",e);
        }
        log.info("fin");
        return datosBeneficio;
    }

    private void generarRutaImagenesGenericas(InfoInicioBeneficioRequest request, InfoInicioBeneficioResponse response){

        String rutaRaiz = ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_GENERICA_CATEGORIA;//ImagenUtil.getValuePropertieOS(env, "directorio.imagen.generica.categoria");//env.getProperty("directorio.imagen.generica.categoria");
        log.info("rutaRaiz de imágenes genéricas de categorias->{}",rutaRaiz);
        String server = env.getProperty("server");//PropertiesDirectorioImagen.SERVER;//env.getProperty("server");
        String locationServer = env.getProperty("directorio.imagen.generica.location.server");//PropertiesDirectorioImagen.DIRECTORIO_IMAGEN_GENERICA_LOCATION_SERVER;//env.getProperty("directorio.imagen.generica.location.server");
        log.info("server:{},locationServer:{}",server,locationServer);
        for(Categoria c : response.getCategorias()){
            File dirCategoria = new File(rutaRaiz + File.separator + c.getIdCategoria().toString());
            if(dirCategoria.exists()){
                log.info("Directorio categoria ->{}",dirCategoria.getAbsolutePath());
                if(c.getSubCategorias()!=null && c.getSubCategorias().size() > 0){
                    for(Categoria sc : c.getSubCategorias()){
                        File dirSubCategoria = new File(dirCategoria.getAbsolutePath() + File.separator + sc.getIdCategoria().toString());
                        if(dirSubCategoria.exists()){
                            //log.info("Listando y agregagando imagenes genericas ubicadas en directorio ->{}",dirSubCategoria.getAbsolutePath());
                            File[] listOfFiles = dirSubCategoria.listFiles();
                            if(listOfFiles!=null && listOfFiles.length > 0){
                                for(File f : listOfFiles){
                                    if (f.isFile()) {
                                        log.info("Imagen encontrada ->{} ya agregada para categoria({}) y subcategoria({}).",f.getName(),c.getIdCategoria(),sc.getIdCategoria());
                                        String urlImagen = server + locationServer + c.getIdCategoria() + "/" + sc.getIdCategoria() + "/" + f.getName();
                                        log.info("urlImagen ->{}",urlImagen);
                                        response.getImgenesGenericas().add(new ImagenGenerica(c.getIdCategoria(), sc.getIdCategoria(), f.getName(), null, null, urlImagen));

                                    }
                                }
                            }
                            else{
                                log.info("Sin imagenes encontradas para directorio ->{}",dirSubCategoria.getAbsolutePath());
                            }

                        }
                        else{
                            log.info("Directorio sub-categoria ->{} no existe",dirSubCategoria.getAbsolutePath());
                        }
                    }
                }
                else{
                    log.info("sin subcategorias para categoria({}) ->{}",c.getIdCategoria(),c.getNombre());
                }
            }
            else{
                log.info("No existe directorio para categoria({}) ->{}",c.getIdCategoria(),c.getNombre());
            }
        }
    }

    private void generarRutaImagenesGenericasS3(InfoInicioBeneficioRequest request, InfoInicioBeneficioResponse response){

        final AmazonS3 s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();

//        List<ObjectListing> objectListings = response.getCategorias()
//                .forEach(categoria -> categoria.getSubCategorias().stream()
//                    .filter(subCategoria -> s3.doesObjectExist(bucketImagenes, imagenGenericaLocation + "/" +
//                            categoria.getIdCategoria() + "/" + subCategoria.getIdCategoria()))
//                    .map(s -> s3.listObjects(bucketImagenes, imagenGenericaLocation + "/" +
//                            categoria.getIdCategoria() + "/" + s.getIdCategoria()))
//                        .collect(Collectors.toList())
//                    );

        for(Categoria c : response.getCategorias()){
            if(c.getSubCategorias()!=null && c.getSubCategorias().size() > 0) {
                for (Categoria sc : c.getSubCategorias()) {
                    String dirSubCategoria = environment + imagenGenericaLocation + c.getIdCategoria() + "/" + sc.getIdCategoria();
                    ObjectListing ol = s3.listObjects(bucketImagenes, dirSubCategoria);
                    List<S3ObjectSummary> s3ObjectSummaries = ol.getObjectSummaries();
//                        s3ObjectSummaries.stream().map(s3ObjectSummary -> s3ObjectSummary.getKey());
                    s3ObjectSummaries.forEach(s3ObjectSummary -> {
                        log.info(s3ObjectSummary.getKey());
                        String[] ruta = s3ObjectSummary.getKey().split("/");
                        String filename = ruta[ruta.length - 1];
                        String urlImagen = S3Server + bucketImagenes + "/" + s3ObjectSummary.getKey();
                        response.getImgenesGenericas().add(new ImagenGenerica(c.getIdCategoria(), sc.getIdCategoria(), filename, null, null, urlImagen));
                    });
                }
            }
            else{
                log.info("sin subcategorias para categoria({}) ->{}",c.getIdCategoria(),c.getNombre());
            }
        }
    }
}
