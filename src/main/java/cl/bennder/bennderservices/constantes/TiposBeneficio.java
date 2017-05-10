/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.constantes;

/**
 * Accion del usuario sobre beneficio seleccionado
 * @author dyanez
 */
public abstract class TiposBeneficio {
    public static Integer DESCUENTO = 1;//Tipo de producto con info %
    public static Integer PRODUCTO_OFERTA = 2;//Tipo de producto oferta precio normal, precio oferta
    public static Integer PRODUCTO_ADICIONAL = 3;//Producto con listado de descripciones de producto/servicio adicional
}
