/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.mapper.ParametroSistemaMapper;
import cl.bennder.bennderservices.model.ParametroSistema;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dyanez
 */
@Service
@Transactional
public class ParametroSistemaServicesImpl implements ParametroSistemaServices{

    @Autowired
    private ParametroSistemaMapper parametroSistemaMapper;
    
    @Override
    public ParametroSistema getDatosParametroSistema(String tipoParametro, String clave) {
        return parametroSistemaMapper.getDatosParametroSistema(tipoParametro, clave);
    }
    
}
