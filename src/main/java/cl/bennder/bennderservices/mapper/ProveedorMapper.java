/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;

import cl.bennder.entitybennderwebrest.model.BeneficioCargador;
import cl.bennder.entitybennderwebrest.model.BeneficioImagen;
import cl.bennder.entitybennderwebrest.model.Categoria;
import cl.bennder.entitybennderwebrest.model.Proveedor;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 *
 * @author dyanez
 */
public interface ProveedorMapper {
    
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
}
