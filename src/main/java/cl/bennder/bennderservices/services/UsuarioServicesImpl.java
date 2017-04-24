/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.constantes.CodigoValidacion;
import cl.bennder.bennderservices.mapper.UsuarioMapper;
import cl.bennder.entitybennderwebrest.model.Usuario;
import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.GuardarPreferenciasRequest;
import cl.bennder.entitybennderwebrest.request.LoginRequest;
import cl.bennder.entitybennderwebrest.response.LoginResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author dyanez
 */
@Service
@Transactional
public class UsuarioServicesImpl implements UsuarioServices{
    
    private static final Logger log = LoggerFactory.getLogger(UsuarioServicesImpl.class);
    
    @Autowired
    private UsuarioMapper usuarioMapper;

    @Override
    public void registraAccesoUsuario(Integer idUsuario) {
        usuarioMapper.registraAccesoUsuario(idUsuario);
    }
    
    

//    @Override
//    public void listarUsuarios() {
//        log.info("INICIO");
//        List<Integer> users = usuarioMapper.getUsers();
//        if(users!= null){
//            for(Integer user:users){
//                log.info("usuario ->{}",user);
//            }
//        }
//        log.info("FIN");
//    }

    @Override
    public LoginResponse validacionUsuario(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        response.setValidacion(new Validacion(CodigoValidacion.ERROR_SERVICIO,"0","Problema en validación de usuario"));
        log.info("INICIO");
        Integer validacion = 0;
        Usuario usuario;
        
        try {
            if(request.getUser()!=null && request.getPassword()!=null)
            {
                log.info("Validando usuario ->{}",request.getUser());
                //usuario existe?
//                validacion = usuarioMapper.validaUsuario(request.getUser(), request.getPassword());
                usuario = usuarioMapper.getUsuarioValidacion(request.getUser(), request.getPassword());
                
                if(usuario != null){
                    //.- obtener idUSuario(rut usuario sin dv) por usuario
                    response.setIdUsuario(usuario.getIdUsuario());    
                    response.setIdEstadoUsuario(usuario.getIdEstado());
                    response.getValidacion().setCodigo(CodigoValidacion.OK);
                    response.getValidacion().setMensaje("Validación OK");
                    log.info("registra acceso usuario ->{}",usuario.getIdUsuario());
                    this.registraAccesoUsuario(usuario.getIdUsuario());
                    
                    
                    log.info("Validación OK");
                }
                else{
                    response.getValidacion().setMensaje("Usuario y contraseña incorrectos");
                    log.info("Usuario y contraseña incorrectos");
                }
            }
            else{
                log.info("Favor completar datos para validación de usuario");
                response.getValidacion().setMensaje("Favor completar datos para validación de usuario");
            }

        } catch (Exception e) {
            log.error("Error en Exception:",e);
            response.setValidacion(new Validacion(CodigoValidacion.ERROR_SERVICIO, "1","Error en validación de usuario"));
            
        }
        log.info("FIN");
        return response;
    }
    
    @Override
    public ValidacionResponse guardarDatosBienvenidaUsuario(GuardarPreferenciasRequest request) {
        ValidacionResponse response = new ValidacionResponse();
        response.setValidacion(new Validacion(CodigoValidacion.ERROR_SERVICIO,"0","Problema al guardar datos de la bienvenida del usuario"));
        log.info("INICIO");
        
        //GuardarDatosAdicionalesUsuario
        
        //GuardarPreferenciasCategorias
        return response;
    }
    
}
