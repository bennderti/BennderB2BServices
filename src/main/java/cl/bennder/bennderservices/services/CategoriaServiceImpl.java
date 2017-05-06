/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.mapper.CategoriaMapper;
import cl.bennder.entitybennderwebrest.model.Categoria;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dyanez
 */
@Service
@Transactional
public class CategoriaServiceImpl implements CategoriaService{

    @Autowired
    private CategoriaMapper categoriaMapper;
    
    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaMapper.getAllCategorias();
    }
    
    
    
}
