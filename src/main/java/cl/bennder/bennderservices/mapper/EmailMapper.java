/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;

import cl.bennder.bennderservices.model.PlantillaCorreo;
import org.apache.ibatis.annotations.Select;

/**
 *
 * @author dyanez
 */
public interface EmailMapper {
    
    /***
    * Método encargado de los datos básicos de una plantilla de correo
    *@param idPlantilla 
    *@return Datos de la plantilla de correo 
    */
    @Select("SELECT nombre,asunto FROM PLANTILLA_CORREO WHERE ID_PLANTILLA = #{idPlantilla}")
    public PlantillaCorreo getDatosPlantillaCorreo(Integer idPlantilla);
    
   
}
