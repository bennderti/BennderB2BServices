package cl.bennder.bennderservices.util;

import cl.bennder.entitybennderwebrest.model.Beneficio;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import cl.bennder.entitybennderwebrest.model.ImagenEscalable;
import cl.bennder.entitybennderwebrest.utils.UtilsBennder;
import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.ObjectListing;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3ObjectSummary;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import javax.rmi.CORBA.Util;
import java.io.*;
import java.util.Iterator;
import java.util.Optional;

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

    @Value("${environment}")
    private String environment;
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

    /**
     * Setea la url de imagen para mostrar en pantalla
     * @param server
     * @param beneficio
     */
    public void setUrlImagenesBenecioS3(String server,Beneficio beneficio){
        if(beneficio!=null && beneficio.getImagenesBeneficio()!=null && beneficio.getImagenesBeneficio().size() > 0){
            for(BeneficioImagen imagen : beneficio.getImagenesBeneficio()){
                imagen.setUrlImagen(server + bucketName + "/" + environment + imagen.getPath());
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

    public String guardarImagenEnAmazonS3(byte[] imagen, Integer idProveedor, Integer idMagen, String extension, Integer idBeneficio, Optional<ImagenEscalable> escalable){


        AmazonS3 amazonS3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        String path = dirProveedor + idProveedor + "/" + idBeneficio + "/" + idMagen.toString() + "." + extension;
        String key = environment + path;

        try {
            File file = File.createTempFile(idMagen.toString(), extension);
            file.deleteOnExit();
            FileUtils.writeByteArrayToFile(file, imagen);

            escalable.ifPresent(imagenEscalable -> {
                log.info("Escalando imagen, pathFileSystem->{}",file.getAbsolutePath());
                UtilsBennder.resizeImage(file, imagenEscalable.getAnchoEscalable(), imagenEscalable.getAltoEscalable(), extension);
            });

            amazonS3.putObject(new PutObjectRequest(bucketName, key, file));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public void eliminarImagenAmazonS3(String dirSubCategoria){
        AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(Regions.US_WEST_2).build();
        try {
            ObjectListing object_listing = s3Client.listObjects(bucketName, dirSubCategoria);
            for (Iterator<?> iterator = object_listing.getObjectSummaries().iterator(); iterator.hasNext();) {
                S3ObjectSummary summary = (S3ObjectSummary)iterator.next();
                s3Client.deleteObject(new DeleteObjectRequest(bucketName, summary.getKey()));
            }
        } catch (AmazonServiceException ase) {
            log.error("Caught an AmazonServiceException.");
            log.error("Error Message:    " + ase.getMessage());
            log.error("HTTP Status Code: " + ase.getStatusCode());
            log.error("AWS Error Code:   " + ase.getErrorCode());
            log.error("Error Type:       " + ase.getErrorType());
            log.error("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            log.error("Caught an AmazonClientException.");
            log.error("Error Message: " + ace.getMessage());
        }
    }
}
