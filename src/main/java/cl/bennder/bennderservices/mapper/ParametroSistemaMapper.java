/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;

import cl.bennder.bennderservices.model.ParametroSistema;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 
 * @author dyanez
 */
public interface ParametroSistemaMapper {
    
    /***
     * Obtiene los datos de parametros de sistema
     * @param tipoParametro Tipo de parametro
     * @param clave Clave de parametro
     * @return Datos de parametros de sistemas 
     */
    @Select(" SELECT TIPO_PARAMETRO AS tipoParametro,CLAVE as clave,VALOR_A as valorA,VALOR_B as valorB, VALOR_C as valorC"
          + " FROM PARAMETRO_SISTEMA "
          + " WHERE TIPO_PARAMETRO =#{tp} AND CLAVE = #{c} ")
    public ParametroSistema getDatosParametroSistema(@Param("tp") String tipoParametro,@Param("c") String clave);
}
