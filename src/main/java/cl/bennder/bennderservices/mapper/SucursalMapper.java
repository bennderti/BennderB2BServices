/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;

import cl.bennder.entitybennderwebrest.model.Comuna;
import cl.bennder.entitybennderwebrest.model.Direccion;
import cl.bennder.entitybennderwebrest.model.Region;
import cl.bennder.entitybennderwebrest.model.Sucursal;
import java.util.List;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;

/**
 *
 * @author ext_dayanez
 */
public interface SucursalMapper {
    
    
    @Select("select id_region as idRegion,nombre from region order by 1")
    public List<Region> getRegiones();
    
    
    @Select("select id_comuna as idComuna,nombre,id_region from comuna order by 1")
     @Results({
            @Result(property="region.idRegion", column="id_region", javaType = Region.class,typeHandler = IntegerTypeHandler.class)})
    public List<Comuna> getComunas();
    
    
    @Select("select sp.id_sucursal as idSucursal,sp.nombre ,sp.horario_atencion as horarioAtencion,sp.oficina,"
            + "d.id_direccion,d.calle,d.numero,c.id_comuna,r.id_region "
            + "from sucursal_proveedor sp inner join direccion d on sp.id_direccion=d.id_direccion " +
            "inner join comuna c on c.id_comuna=d.id_comuna inner join region r on r.id_region=c.id_region " +
            "where sp.id_sucursal= #{idSucursal}")
      @Results({
            @Result(property="direccion.idDireccion", column="id_direccion", javaType = Direccion.class,typeHandler = IntegerTypeHandler.class),
            @Result(property="direccion.calle", column="calle", javaType = Direccion.class,typeHandler = StringTypeHandler.class),
            @Result(property="direccion.numero", column="numero", javaType = Direccion.class,typeHandler = StringTypeHandler.class),
            @Result(property="direccion.comuna.idComuna", column="id_comuna", javaType = Comuna.class,typeHandler = IntegerTypeHandler.class),
            @Result(property="direccion.comuna.region.idRegion", column="id_region", javaType = Region.class,typeHandler = IntegerTypeHandler.class)
        }) 
    public Sucursal getSucursal(Integer idSucursal);
    
}
