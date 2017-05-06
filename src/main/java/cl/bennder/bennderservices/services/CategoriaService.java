/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.model.Categoria;
import java.util.List;

/**
 *
 * @author dyanez
 */
public interface CategoriaService {
    
    /**
     * Obtiene categorias y sub categorias asociadas
     * @return Lista de categorías/subtcategorias
     */
    public List<Categoria> getAllCategorias();
}
