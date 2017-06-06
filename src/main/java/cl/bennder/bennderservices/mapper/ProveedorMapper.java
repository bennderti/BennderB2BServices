/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;

import cl.bennder.entitybennderwebrest.model.BeneficioCargador;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import cl.bennder.entitybennderwebrest.model.Categoria;
import cl.bennder.entitybennderwebrest.model.Comuna;
import cl.bennder.entitybennderwebrest.model.Descuento;
import cl.bennder.entitybennderwebrest.model.Proveedor;
import cl.bennder.entitybennderwebrest.model.Region;
import cl.bennder.entitybennderwebrest.model.SucursalProveedor;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.type.IntegerTypeHandler;

/**
 *
 * @author dyanez
 */
public interface ProveedorMapper {
    
    
    @Select("select maX_imagenes from plan_proveedor where id_plan=(select id_plan from proveedor where id_proveedor= #{idProveedor})")
    public Integer getMaxImagenPlanProveedor(Integer idProveedor);
    
    /*@Select("select sp.id_sucursal as idSucursal,sp.nombre as nombreSucursal,c.id_comuna as idComuna from sucursal_proveedor sp inner join direccion d " +
            "on sp.id_direccion = d.id_direccion inner join comuna c on c.id_comuna = d.id_comuna " +
            "where sp.id_proveedor = #{idProveedor} and sp.habilitado = true")*/
    @Select(" select sp.id_sucursal as idSucursal,sp.nombre ||' ('||d.calle||' '||coalesce('Nro. '||d.numero,'s/n')||', '||c.nombre||', '||r.nombre||')' as nombreSucursal,c.id_comuna as idComuna from sucursal_proveedor sp inner join direccion d " +
            " on sp.id_direccion = d.id_direccion inner join comuna c on c.id_comuna = d.id_comuna " +
            " inner join region r on r.id_region=c.id_region " +
            " where sp.id_proveedor = #{idProveedor} and sp.habilitado = true")
    public List<SucursalProveedor> getSucursalProveedor(Integer idProveedor);
    
    @Select("select id_region as idRegion,nombre from region where id_region in( " +
            "select distinct c.id_region from sucursal_proveedor sp inner join direccion d " +
            "on sp.id_direccion = d.id_direccion inner join comuna c on c.id_comuna = d.id_comuna " +
            "where sp.id_proveedor = #{idProveedor})")
    public List<Region> getRegionesSucursales(Integer idProveedor);
    
    
    @Select("select c.id_comuna as idComuna, c.nombre as nombre,r.id_region from comuna c inner join( " +
            "select distinct c.id_comuna from sucursal_proveedor sp inner join direccion d " +
            "on sp.id_direccion = d.id_direccion inner join comuna c on c.id_comuna = d.id_comuna " +
            "where sp.id_proveedor = #{idProveedor})c2 on c.id_comuna=c2.id_comuna " +
            "inner join region r on r.id_region=c.id_region")
    @Results({
            @Result(property = "region.idRegion", column = "id_region", javaType = Region.class, typeHandler = IntegerTypeHandler.class)
    })
    public List<Comuna> getComunasSucursales(Integer idProveedor);
    
    /***
     * Encargado de guardar una imagen asociada a beneficio
     * @param beneficioImagen Datos de imagen asociada a beneficio seleccionado
     */
    @Insert("INSERT INTO BENEFICIO_IMAGEN(ID_BENEFICIO,PATH,ORDEN,NOMBRE,ID_IMAGEN) "
            + "VALUES(#{idBeneficio},#{path},#{orden},#{nombre},#{idImagen})")
    public void guardaImagenBeneficio(BeneficioImagen beneficioImagen);
    
    
    @Delete("DELETE FROM BENEFICIO_IMAGEN WHERE ID_BENEFICIO = #{idBeneficio}")
    public void eliminarImagenesBeneficio(Integer idBeneficio);
    
    @Select("SELECT nextval('beneficio_imagen_id_imagen_seq')")
    public Integer getSeqIdImagen();
    
        /***
     * Pérmite obtener las subcategorias de las categorias de beneficios habilitados para el proveedor
     * @param idCategoria identificador de categoria padre
     * @param idProveedor identificadr de proveedor
     * @return 
     */
    @Select("SELECT c.id_categoria as idCategoria, c.nombre FROM categoria c " +
            "WHERE id_categoria_padre = #{idCat} and id_categoria in(select distinct id_categoria from beneficio where id_proveedor = #{idProv})")
    List<Categoria> obtenerSubCategoriasByIdCatProveedor(@Param("idCat") Integer idCategoria,@Param("idProv") Integer idProveedor);
    
     /***
     * Obtiene los beneficios de una categoria seleccionda para el cargador (datos simples)
     * @param idCategoria Identificador de la categoria
     * @return 
     */
    @Select("SELECT ID_BENEFICIO AS idBeneficio,TITULO as titulo FROM BENEFICIO WHERE ID_CATEGORIA = #{idCategoria}")
    List<BeneficioCargador> getBeneficiosCargadorByIdCat(Integer idCategoria);
    
    /***
     * Obtiene los proveedor habilitados
     * @return lista de proveedores
     * @author dyanez
     */
    @Select("SELECT ID_PROVEEDOR AS idProveedor,nombre,rut,path_logo as pathLogo FROM PROVEEDOR WHERE HABILITADO = TRUE")
    public List<Proveedor> obtenerProveedorHabilitados();
    
    
   /***
    * Obtiene las categorias del proveedor (se trabaja a dos niveles, categoria-subcategoria)
    * @param idProveedor identificador de proveedor
    * @return Categorias de proveedor
    */
   @Select("SELECT CAT.ID_CATEGORIA AS idCategoria,CAT.NOMBRE FROM CATEGORIA CAT INNER JOIN( " +
            "SELECT  DISTINCT C1.ID_CATEGORIA_PADRE FROM CATEGORIA C1 INNER JOIN(  " +
            "SELECT DISTINCT ID_CATEGORIA FROM BENEFICIO WHERE ID_PROVEEDOR = #{idProveedor}) B1 " +
            "ON B1.ID_CATEGORIA = C1.ID_CATEGORIA)  SUB ON SUB.ID_CATEGORIA_PADRE=CAT.ID_CATEGORIA ")
    public List<Categoria> obtenerCategoriaProveedor(Integer idProveedor);
    
    
    /***
     * Método que permite actualizar datos de proveedor
     * @param proveedor Datos de proveedor
     */
    @Update("UPDATE proveedor " +
            "   SET nombre= #{nombre}, rut=#{rut}," +
            "   path_logo= #{pathLogo} " +
            " WHERE id_proveedor = #{idProveedor}")
    public void actualizaDatosGeneralesProveedor(Proveedor proveedor);
    
    
    /***
     * Obtiene el id del proveedor del usuario conectado
     * @param idUsuario rut de usuario sin dv
     * @return 
     */
    @Select("select id_proveedor from usuario_proveedor where id_usuario = #{idUsuario}")
    public Integer getIdProveedorByUser(Integer idUsuario);
    
    /**
     * Obtiene la cantidad máxima de publicaciones activas que puede tener el proveedor
     * MG - 05/06/2017
     * @param idProveedor
     * @return 
     */
    @Select("SELECT PP.MAX_PUBLICACIONES " +
            "FROM PROVEEDOR P " +
            "INNER JOIN PLAN_PROVEEDOR PP ON P.ID_PLAN = PP.ID_PLAN " +
            "WHERE P.ID_PROVEEDOR = #{idProveedor}")
    public Integer getCantBeneficiosPublicados(Integer idProveedor);
}
