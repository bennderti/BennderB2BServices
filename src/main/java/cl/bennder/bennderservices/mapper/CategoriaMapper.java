/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.mapper;


import cl.bennder.entitybennderwebrest.model.Categoria;
import java.util.List;

import org.apache.ibatis.annotations.*;

/**
 *
 * @author dyanez
 */
public interface CategoriaMapper {

    @Select( "select id_categoria,nombre " +
            "from categoria " +
            "where id_categoria_padre = -1 order by id_categoria")
    @Results(value = {
           @Result (property = "idCategoria", column = "id_categoria"),
           @Result (property = "nombre", column = "nombre"),
           @Result (property = "subCategorias", column = "id_categoria", javaType=List.class, many = @Many(select = "obtenerSubCategorias"))
    })
    public List<Categoria> getAllCategorias();

    @Select("select id_categoria,nombre " +
            "from categoria " +
            "where id_categoria_padre = #{idCategoriaPadre} order by id_categoria")
    @Results(value = {
           @Result (property = "idCategoria", column = "id_categoria"),
           @Result (property = "nombre", column = "nombre")
    })
    public List<Categoria> obtenerSubCategorias(Integer idCategoriaPadre);



}
