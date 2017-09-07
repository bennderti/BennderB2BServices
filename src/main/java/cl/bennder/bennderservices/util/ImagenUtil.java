package cl.bennder.bennderservices.util;

import cl.bennder.entitybennderwebrest.model.Beneficio;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.PutObjectRequest;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.io.*;

/**
 * Created by Diego on 30-03-2017.
 */
@Component
public class ImagenUtil {
    private static final Logger log = LoggerFactory.getLogger(ImagenUtil.class);

    @Value("${s3.bucketName}")
    public String bucketName;

    @Value("${directorio.imagen.location.server}")
    private String dirProveedor;
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
    
    /***
     * Permite obtener el valor correspondiente del properties según sistema operativo
     * @param env
     * @param keyPropertie
     * @return 
     */
    public static String getValuePropertieOS(Environment env,String keyPropertie){
        String os = System.getProperty("os.name");
        log.info("os->{}",os);
        os = os.toLowerCase();
        if(os.contains("windows")){
            return env.getProperty(keyPropertie);
        }
        else{
            return env.getProperty(keyPropertie).replaceAll("C:", "");
        }
    }

    public String guardarImagenEnAmazonS3(byte[] imagen,Integer idProveedor, Integer idMagen, String extension, Integer idBeneficio){


        AmazonS3 amazonS3 = new AmazonS3Client();
        String path = "/" + idProveedor + "/" + idBeneficio + "/" + idMagen.toString() + "." + extension;
        String key = "dev" + "/" + dirProveedor + path;

        try {
            File file = File.createTempFile(idMagen.toString(), extension);
            file.deleteOnExit();

            FileUtils.writeByteArrayToFile(file, imagen);
            amazonS3.putObject(new PutObjectRequest( bucketName, key, file));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }
}
