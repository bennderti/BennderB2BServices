package cl.bennder.bennderservices.util;

import cl.bennder.entitybennderwebrest.model.Beneficio;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Diego on 30-03-2017.
 */
public class ImagenUtil {
    private static final Logger log = LoggerFactory.getLogger(ImagenUtil.class);
    
    /***
     * Actualiza url http de imagen en servidor
     * @param server Servidor
     * @param beneficio 
     */
    public static void setUrlImagenesBenecio(String server,Beneficio beneficio){
        if(beneficio!=null && beneficio.getImagenesBeneficio()!=null && beneficio.getImagenesBeneficio().size() > 0){
            for(BeneficioImagen imagen : beneficio.getImagenesBeneficio()){
                imagen.setUrlImagen(server + imagen.getPath());
                log.info("imagen.getUrlImagen() ->{}",imagen.getUrlImagen());
            }
        }
    }
}
