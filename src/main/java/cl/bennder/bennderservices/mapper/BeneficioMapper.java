package cl.bennder.bennderservices.mapper;

import cl.bennder.entitybennderwebrest.model.*;
import java.util.Date;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.IntegerTypeHandler;
import org.apache.ibatis.type.StringTypeHandler;

import java.util.List;

/**
 * Created by Diego on 10-03-2017.
 */
public interface BeneficioMapper {
    
    /***
     * Método encargado de obtener las sucursales de un proveedor según beneficio
     * @param idBeneficio Identificador de beneficio
     * @return Lista de sucursales de proveedor
     */
    @Select("select sp.id_direccion as idDireccion, c.nombre||' - '||d.calle ||' ('||coalesce('Nro. ' ||d.numero,'S/N')||')' as nombreSucursal  " +
            "from sucursal_proveedor sp inner join direccion d on sp.id_direccion=d.id_direccion  " +
            "inner join comuna c on c.id_comuna=d.id_comuna  " +
            "where sp.id_proveedor =(select id_proveedor from beneficio where id_beneficio = #{idBeneficio}  and habilitado=true) and habilitado=true ")
    public List<SucursalProveedor> getSucursalesProveedorByBeneficio(Integer idBeneficio);
    
    @Select(  " select b.id_beneficio as idBeneficio,b.titulo,p.nombre as nombreProveedor,b.id_proveedor as idProveedor "
            + " from beneficio b inner join proveedor p on b.id_proveedor=p.id_proveedor " +
              " where b.id_beneficio = #{idBeneficio}")
    public Beneficio getInfoGeneralBeneficio(Integer idBeneficio);
    
    
    @Select("select count(1) from sucursal_proveedor where id_direccion= #{idDireccion} and password = #{password} and id_proveedor =#{idProveedor}")
    public Integer esPasswordSucursalValida(@Param("password") String password,@Param("idDireccion") Integer idDireccion, @Param("idProveedor") Integer idProveedor);
    
    /***
     * Obtiene el título del beneficio para completar asunto cuando se envia correo de beneficio seleccionado
     * @param idBeneficio iddentificador de beneficio
     * @author dyanez
     * @return 
     */
    @Select("select titulo from beneficio where id_beneficio=#{idBeneficio}")
    public String getTituloBeneficioAsuntoEnvioCorreo(Integer idBeneficio);
    
    /***
     * Obtiene el stock actual del beneficio seleccionado
     * @author dyanez
     * @param idBeneficio identificador de beneficio
     * @return Stock beneficio
     */
    @Select("select stock from beneficio where id_beneficio= #{idBeneficio}")
    public Integer getStockBeneficio(Integer idBeneficio);
    
   
  
    
    
    
    //https://www.postgresql.org/docs/8.1/static/functions-datetime.html 
    /***
     * Valida si cliente ya habia obtenido determinado beneficio dentro de fecha de vigencia
     * @param idUsuario Identificador de usuario
     * @param idBeneficio Identificador de beneficio
     * @return 
     */
    @Select("SELECT COUNT(1) FROM BENEFICIO B INNER JOIN USUARIO_BENEFICIO UB ON UB.ID_BENEFICIO = B.ID_BENEFICIO " +
            "WHERE current_date <=  B.fecha_expiracion AND UB.ID_BENEFICIO = #{b} AND UB.ID_USUARIO = #{u} AND UB.ID_ACCION_BENEFICIO <> 0")
    public Integer usuarioHaObtenidoCuponbeneficio(@Param("u") Integer idUsuario,@Param("b") Integer idBeneficio);
    
    
    /***
     * Obtiene los beneficios de una categoria seleccionda para el cargador (datos simples)
     * @param idCategoria Identificador de la categoria
     * @return 
     */
    @Select("SELECT ID_BENEFICIO AS idBeneficio,TITULO as titulo FROM BENEFICIO WHERE ID_CATEGORIA = #{idCategoria}")
    List<BeneficioCargador> getBeneficiosCargadorByIdCat(Integer idCategoria);

    @Select(" SELECT b.id_beneficio AS idBeneficio," +
            " b.id_beneficio," +
            " b.titulo, " +
            " b.descripcion," +
            " tb.id_tipo_beneficio," +
            " tb.nombre," +
            " bd.porcentaje_descuento as porcentajeDescuento," +
            " bp.precio_normal as precioNormal," +
            " bp.precio_oferta as precioOferta," +
            " p.nombre as nombreProveedor" +
            " FROM beneficio b" +
            " INNER JOIN tipo_beneficio tb ON tb.id_tipo_beneficio = b.id_tipo_beneficio " +
            " INNER JOIN proveedor p ON p.id_proveedor = b.id_proveedor" +
            " LEFT JOIN beneficio_descuento bd ON b.id_beneficio = bd.id_beneficio" +
            " LEFT JOIN beneficio_producto bp ON b.id_beneficio = bp.id_beneficio" +
            " WHERE b.id_categoria = #{idCategoria}" +
            " AND NOW() BETWEEN b.fecha_inicial AND b.fecha_expiracion " +
            " AND b.habilitado = TRUE " +
            " AND b.stock > 0")
    @TypeDiscriminator(column = "id_tipo_beneficio",
            cases = {
            @Case(value = "1", type = Descuento.class),
            @Case(value = "2", type = Producto.class)

    })
    @Results({
            @Result(property = "tipoBeneficio.idTipoBeneficio", column = "id_tipo_beneficio", javaType = TipoBeneficio.class, typeHandler = IntegerTypeHandler.class),
            @Result(property = "tipoBeneficio.nombre", column = "nombre", javaType = TipoBeneficio.class, typeHandler = StringTypeHandler.class),
            @Result(property = "imagenesBeneficio", column = "id_beneficio", javaType=List.class, many = @Many(select = "obtenerImagenesBeneficioPreview")),
    })
    List<Beneficio> obtenerBeneficiosPorCategoria(Integer idCategoria);


    @Select(" SELECT b.id_beneficio AS idBeneficio," +
            " b.id_beneficio," +
            " b.titulo, " +
            " b.descripcion," +
            " tb.id_tipo_beneficio," +
            " tb.nombre," +
            " bd.porcentaje_descuento as porcentajeDescuento," +
            " bp.precio_normal as precioNormal," +
            " bp.precio_oferta as precioOferta," +
            " p.nombre as nombreProveedor" +
            " FROM beneficio b" +
            " INNER JOIN tipo_beneficio tb ON tb.id_tipo_beneficio = b.id_tipo_beneficio " +
            " INNER JOIN proveedor p ON p.id_proveedor = b.id_proveedor" +
            " LEFT JOIN beneficio_descuento bd ON b.id_beneficio = bd.id_beneficio" +
            " LEFT JOIN beneficio_producto bp ON b.id_beneficio = bp.id_beneficio" +
            " WHERE b.id_categoria in (SELECT id_categoria FROM categoria WHERE id_categoria_padre = #{idCategoriaPadre})" +
            " AND NOW() BETWEEN b.fecha_inicial AND b.fecha_expiracion " +
            " AND b.habilitado = TRUE " +
            " AND b.stock > 0")
    @TypeDiscriminator(column = "id_tipo_beneficio",
            cases = {
                    @Case(value = "1", type = Descuento.class),
                    @Case(value = "2", type = Producto.class)

            })
    @Results({
            @Result(property = "tipoBeneficio.idTipoBeneficio", column = "id_tipo_beneficio", javaType = TipoBeneficio.class, typeHandler = IntegerTypeHandler.class),
            @Result(property = "tipoBeneficio.nombre", column = "nombre", javaType = TipoBeneficio.class, typeHandler = StringTypeHandler.class),
            @Result(property = "imagenesBeneficio", column = "id_beneficio", javaType=List.class, many = @Many(select = "obtenerImagenesBeneficioPreview")),
    })
    List<Beneficio> obtenerBeneficiosPorCategoriaPadre(Integer idCategoriaPadre);

    @Select(" SELECT b.id_beneficio AS idBeneficio," +
            " b.id_beneficio as idBeneficioParaCondiciones," +
            " b.id_beneficio as idBeneficioParaImagenes," +
            " b.titulo, " +
            " b.descripcion," +
            " b.fecha_inicial as fechaInicial," +
            " b.fecha_expiracion as fechaExpiracion," +
            " b.fecha_creacion as fechaCreacion," +
            " b.stock," +
            " b.limite_stock as limiteStock," +
            " b.calificacion," +
            " b.visitas_general as visitasGeneral," +
            " tb.id_tipo_beneficio," +
            " tb.nombre," +
            " bd.porcentaje_descuento as porcentajeDescuento," +
            " bp.precio_normal as precioNormal," +
            " bp.precio_oferta as precioOferta," +
            " bg.gancho," +
            " p.nombre as nombreProveedor," +
            " c.nombre as nombreCategoria" +
            " FROM beneficio b" +
            " INNER JOIN categoria c ON c.id_categoria = b.id_categoria" +
            " INNER JOIN tipo_beneficio tb ON tb.id_tipo_beneficio = b.id_tipo_beneficio " +
            " INNER JOIN proveedor p ON p.id_proveedor = b.id_proveedor" +
            " LEFT JOIN beneficio_descuento bd ON b.id_beneficio = bd.id_beneficio" +
            " LEFT JOIN beneficio_producto bp ON b.id_beneficio = bp.id_beneficio" +
            " LEFT JOIN beneficio_gancho bg ON b.id_beneficio = bg.id_beneficio " +
            " WHERE b.id_beneficio = #{idBeneficio}")
    @TypeDiscriminator(column = "id_tipo_beneficio",
            cases = {
                    @Case(value = "1", type = Descuento.class),
                    @Case(value = "2", type = Producto.class)

            })
    @Results({
            @Result(property = "tipoBeneficio.idTipoBeneficio", column = "id_tipo_beneficio", javaType = TipoBeneficio.class, typeHandler = IntegerTypeHandler.class),
            @Result(property = "tipoBeneficio.nombre", column = "nombre", javaType = TipoBeneficio.class, typeHandler = StringTypeHandler.class),
            @Result(property = "imagenesBeneficio", column = "idBeneficioParaImagenes", javaType=List.class, many = @Many(select = "obtenerImagenesBeneficio")),
            @Result(property = "condiciones", column = "idBeneficioParaCondiciones", javaType=List.class, many = @Many(select = "obtenerCondicionesBeneficio"))

    })
    Beneficio obtenerDetalleBeneficio(Integer idBeneficio);

    //@Select(" SELECT imagen " +
    @Select(" SELECT path " +
            " FROM beneficio_imagen" +
            " WHERE id_beneficio = #{idBeneficio}")
    List<BeneficioImagen> obtenerImagenesBeneficio(Integer idBeneficio);

    @Select(" SELECT condicion " +
            " FROM condicion_beneficio" +
            " WHERE id_beneficio = #{idBeneficio}")
    List<String> obtenerCondicionesBeneficio(Integer idBeneficio);


    //@Select(" SELECT imagen " +
    @Select(" SELECT path " +
            " FROM beneficio_imagen" +
            " WHERE id_beneficio = #{idBeneficio}" +
            " AND orden in (1,2)")
    List<BeneficioImagen> obtenerImagenesBeneficioPreview(Integer idBeneficio);
    
    /**
     * Obtiene lista de los beneficios con mejor calificaciòn, basado en las preferencias de categoría del usuario, con un limite de 9 items.
     * Finalmente si la cantidad de beneficios es menor a 9, lo completa con los beneficios generales con mejor calificación
     * @param idUsuario
     * @return 
     */
    @Select("(SELECT B.ID_BENEFICIO, B.TITULO, B.CALIFICACION, B.ID_TIPO_BENEFICIO, BP.PRECIO_NORMAL, BP.PRECIO_OFERTA, BD.PORCENTAJE_DESCUENTO, BG.GANCHO, P.NOMBRE " +
            "FROM BENEFICIO B " +
            "INNER JOIN INTERES_USUARIO IU ON B.ID_CATEGORIA = IU.ID_CATEGORIA AND IU.ID_USUARIO = #{idUsuario} " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY B.CALIFICACION DESC, B.FECHA_EXPIRACION)" +
            "UNION" +
            "(SELECT B.ID_BENEFICIO " +
            "FROM BENEFICIO B " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY B.CALIFICACION DESC, B.FECHA_EXPIRACION)" +
            "LIMIT 9")
    List<Beneficio> obtenerBeneficiosDestacadosInteresUsuario (Integer idUsuario);
    
    /**
     * Obtiene lista de los últimos 9 visitados beneficios visitados por el usuario.
     * Si faltan items, la lista se completa con los beneficios más visitados de bennder.
     * @param idUsuario
     * @return 
     */
    @Select("(SELECT B.ID_BENEFICIO, B.TITULO, B.CALIFICACION, B.ID_TIPO_BENEFICIO, BP.PRECIO_NORMAL, BP.PRECIO_OFERTA, BD.PORCENTAJE_DESCUENTO, BG.GANCHO, P.NOMBRE " +
            "FROM BENEFICIO B " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "INNER JOIN USUARIO_BENEFICIO UB ON B.ID_BENEFICIO = UB.ID_BENEFICIO AND UB.ID_USUARIO = #{idUsuario} AND UB.ID_ACCION_BENEFICIO = 0 " +
            "INNER JOIN FECHA_ACCION_BENEFICIO FAB ON UB.ID_BENEFICIO = FAB.ID_BENEFICIO AND UB.ID_USUARIO = FAB.ID_USUARIO " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY FAB.FECHA DESC)" +
            "UNION" +
            "(SELECT B.ID_BENEFICIO " +
            "FROM BENEFICIO B " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY VISITAS_GENERAL DESC)" +
            "LIMIT 9")
    List<Beneficio> obtenerUltimosBeneficiosVistosUsuario (Integer idUsuario);
    
    /**
     * Obtiene la lista de los nuevos beneficios agregados a bennder, con un máximo de 9 items.
     * Se toma como prioridad los beneficios con las categorìas elegidas por el usuario.
     * @param idUsuario
     * @return 
     */
    @Select("(SELECT B.ID_BENEFICIO, B.TITULO, B.CALIFICACION, B.ID_TIPO_BENEFICIO, BP.PRECIO_NORMAL, BP.PRECIO_OFERTA, BD.PORCENTAJE_DESCUENTO, BG.GANCHO, P.NOMBRE " +
            "FROM BENEFICIO B " +
            "INNER JOIN INTERES_USUARIO IU ON B.ID_CATEGORIA = IU.ID_CATEGORIA AND IU.ID_USUARIO = #{idUsuario} " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY B.FECHA_CREACION DESC)" +
            "UNION" +
            "(SELECT B.ID_BENEFICIO " +
            "FROM BENEFICIO B " +
            "INNER JOIN PROVEEDOR P ON B.ID_PROVEEDOR = P.ID_PROVEEDOR AND P.HABILITADO = TRUE " +
            "LEFT JOIN BENEFICIO_PRODUCTO BP ON B.ID_BENEFICIO = BP.ID_BENEFICIO " +
            "LEFT JOIN BENEFICIO_DESCUENTO BD ON B.ID_BENEFICIO = BD.ID_BENEFICIO " + 
            "LEFT JOIN BENEFICIO_GANCHO BG ON B.ID_BENEFICIO = BG.ID_BENEFICIO " +
            "WHERE NOW() BETWEEN B.FECHA_INICIAL AND B.FECHA_EXPIRACION AND B.HABILITADO = TRUE AND B.STOCK > 0 " +
            "ORDER BY B.FECHA_CREACION DESC)" +
            "LIMIT 9")
    List<Beneficio> obtenerNuevosBeneficiosInteresUsuario (Integer idUsuario);
    
    /***
     * Obtiene path location a nivel de servidor del logo del proveedor
     * @param idBeneficio identificador de beneficio
     * @return 
     */
    @Select("SELECT PATH_LOGO FROM PROVEEDOR WHERE ID_PROVEEDOR =( " +
            "SELECT ID_PROVEEDOR FROM BENEFICIO WHERE ID_BENEFICIO = #{idBeneficio} )")
    public String getPathLogoProveedorByBeneficio(Integer idBeneficio);
    
    
    
    @Update("UPDATE BENEFICIO SET VISITAS_GENERAL = (SELECT COALESCE(VISITAS_GENERAL,0) + 1 FROM BENEFICIO WHERE ID_BENEFICIO = #{idBeneficio}) " +
            "WHERE ID_BENEFICIO = #{idBeneficio} ")
    public void actualizarVisitasBeneficio(Integer idBeneficio);
    
    /***
     * Obtiene la secuencia de beneficio
     * @return 
     */
    @Select("SELECT nextval('beneficio_id_beneficio_seq')")
    public Integer getSeqIdBeneficio();
    
    
    @Update("UPDATE beneficio " +
"   SET id_beneficio=?, id_tipo_beneficio=#{tipoBeneficio.idTipoBeneficio}, id_proveedor=#{idProveedor}, id_categoria=#{idCategoria}, " +
"       titulo=#{titulo}, descripcion=#{descripcion}, fecha_creacion=#{fechaCreacion}, fecha_expiracion=#{fechaExpiracion}, " +
"       stock= #{stock}, limite_stock =#{limiteStock}" +
" WHERE id_beneficio = #{idBeneficio}")
    public void updateDatosGeneralesBeneficio(Beneficio beneficio);
    
    
    @Insert("INSERT INTO beneficio(" +
"            id_beneficio, id_tipo_beneficio, id_proveedor, id_categoria, " +
"            titulo, descripcion, fecha_creacion, fecha_expiracion,  " +
"            stock,calificacion,visitas_general,limite_stock,habilitado)" +
"    VALUES (#{idBeneficio}, #{tipoBeneficio.idTipoBeneficio}, #{idProveedor}, #{idCategoria}," +
"            #{titulo}, #{descripcion}, #{fechaCreacion}, #{fechaExpiracion}, " +
"             #{stock},0,0,#{limiteStock},false)")
    public void insertDatosGeneralesBeneficio(Beneficio beneficio);
    
    @Update("update beneficio_descuento set porcentaje_descuento = #{porcentaje} where id_beneficio = #{idBeneficio}")
    public void updateBeneficioDescuento(@Param("porcentaje") Integer porcentaje,@Param("idBeneficio") Integer idBeneficio);
    
    @Insert("insert into beneficio_descuento(id_beneficio,porcentaje_descuento) values(#{idBeneficio},#{porcentaje})")
    public void insertBeneficioDescuento(@Param("porcentaje") Integer porcentaje,@Param("idBeneficio") Integer idBeneficio);
    
    @Select("select count(1) from beneficio_descuento where id_beneficio = #{idBeneficio}")
    public Integer existeBeneficioDescuento(Integer idBeneficio);
    
    @Update("update beneficio_producto set precio_normal = #{precioNormal},precio_oferta =#{precioOferta} "
            + "where id_beneficio = #{idBeneficio}")
    public void updateBeneficioProducto(@Param("precioOferta") Integer precioOferta,@Param("precioNormal") Integer precioNormal,@Param("idBeneficio") Integer idBeneficio);
    
    @Insert("insert into beneficio_producto(id_beneficio,precio_normal,precio_oferta) values(#{idBeneficio},#{precioNormal},#{precioOferta})")
    public void insertBeneficioProducto(@Param("precioOferta") Integer precioOferta,@Param("precioNormal") Integer precioNormal,@Param("idBeneficio") Integer idBeneficio);
    
    @Select("select count(1) from beneficio_producto where id_beneficio = #{idBeneficio}")
    public Integer existeBeneficioProducto(Integer idBeneficio);
    
    @Delete("delete from beneficio_gancho where id_beneficio = #{idBeneficio}")
    public void deleteInfoAdicionales(Integer idBeneficio);

    @Insert("insert into beneficio_gancho(id_beneficio,gancho) values(#{idBeneficio},#{adicional})")
    public void insertInfoAdicionales(@Param("adicional") String adicional,@Param("idBeneficio") Integer idBeneficio);
    
    @Delete("delete from condicion_beneficio where id_beneficio = #{idBeneficio}")
    public void deleteCondiciones(Integer idBeneficio);

    @Insert("insert into condicion_beneficio(id_beneficio,condicion) values(#{idBeneficio},#{condicion})")
    public void insertCondiciones(@Param("condicion") String condicion,@Param("idBeneficio") Integer idBeneficio);
    
    @Delete("delete from sucursal_beneficio where id_beneficio = #{idBeneficio}")
    public void deleteSucursales(Integer idBeneficio);

    @Insert("insert into sucursal_beneficio(id_beneficio,id_sucursal) values(#{idBeneficio},#{idSucursal})")
    public void insertSucursal(@Param("idSucursal") Integer idSucursal,@Param("idBeneficio") Integer idBeneficio);
    
    @Delete("DELETE FROM BENEFICIO_IMAGEN WHERE ID_BENEFICIO = #{idBeneficio}")
    public void eliminarImagenesBeneficio(Integer idBeneficio);
    
     
    @Select("SELECT nextval('beneficio_imagen_id_imagen_seq')")
    public Integer getSeqIdImagen();
    
      /***
     * Encargado de guardar una imagen asociada a beneficio
     * @param beneficioImagen Datos de imagen asociada a beneficio seleccionado
     */
    @Insert("INSERT INTO BENEFICIO_IMAGEN(ID_BENEFICIO,PATH,ORDEN,NOMBRE,ID_IMAGEN) "
            + "VALUES(#{idBeneficio},#{path},#{orden},#{nombre},#{idImagen})")
    public void guardaImagenBeneficio(BeneficioImagen beneficioImagen);
    
    
    
}
