/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.mapper.CategoriaMapper;
import cl.bennder.entitybennderwebrest.model.Categoria;
import cl.bennder.entitybennderwebrest.model.ImagenGenerica;
import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.GetTodasCategoriaRequest;
import cl.bennder.entitybennderwebrest.request.InfoBeneficioRequest;
import cl.bennder.entitybennderwebrest.request.UploadImagenesGenericaRequest;
import cl.bennder.entitybennderwebrest.response.GetTodasCategoriaResponse;
import cl.bennder.entitybennderwebrest.response.InfoBeneficioResponse;
import cl.bennder.entitybennderwebrest.response.UploadImagenesGenericaResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
    
    @Autowired
    private Environment env;
    
    @Autowired
    private CategoriaMapper categoriaMapper;

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
                    String rutaRaiz = env.getProperty("directorio.imagen.generica.categoria");
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
                String rutaRaiz = env.getProperty("directorio.imagen.generica.categoria");
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
