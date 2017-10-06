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
import cl.bennder.entitybennderwebrest.request.CambioPasswordRequest;
import cl.bennder.entitybennderwebrest.request.LoginRequest;
import cl.bennder.entitybennderwebrest.response.CambioPasswordResponse;
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
    
    @Autowired
    private EncriptacionSpringService encriptacionSpringService;

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
                    
                    if(Boolean.TRUE.equals(usuario.getHabilitado())){
                        //.- validando si esta asociado a un proveedors
                        Integer idProveedor = usuarioMapper.getIdProvedorByUsuario(usuario.getIdUsuario());
                        if(idProveedor!=null){
                            //.- obtener idUSuario(rut usuario sin dv) por usuario
                            response.setIdUsuario(usuario.getIdUsuario());    
                            //response.setIdEstadoUsuario(usuario.getIdEstado());
                            response.setEsPasswordTemporal(usuario.isEsPasswordTemporal());                                                        
                            response.getValidacion().setCodigo(CodigoValidacion.OK);
                            response.getValidacion().setCodigoNegocio(CodigoValidacion.OK);
                            response.getValidacion().setMensaje("Validación OK");
                            log.info("registra acceso usuario ->{}",usuario.getIdUsuario());
                            log.info("Validación OK");
                            this.registraAccesoUsuario(usuario.getIdUsuario());
                        }
                        else{
                            response.getValidacion().setCodigoNegocio("1");
                            response.getValidacion().setMensaje("Usuario no registrado en comercio");
                            log.info("Usuario no registrado en comercio ->{}",usuario.getIdUsuario());
                        }
                    }
                    else{
                        response.getValidacion().setCodigoNegocio("2");
                        response.getValidacion().setMensaje("Usuario no habilitado");
                        log.info("Usuario no habilitado ->{}",usuario.getIdUsuario());
                    }

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
    public CambioPasswordResponse cambioPassword(CambioPasswordRequest request) {
        CambioPasswordResponse response = new CambioPasswordResponse();
        response.setValidacion(new Validacion(CodigoValidacion.ERROR_SERVICIO,"0","Problema al cambiar contraseña"));
        log.info("inicio");
        try {
            if(request!=null && request.getNewPassword()!=null && request.getUsuarioCorreo() != null){
                log.info("generando passencoder y actualizando password para usuario ->{}",request.getUsuarioCorreo());
                Integer idUsuario = usuarioMapper.getIdUsuarioByUsuarioCorreo(request.getUsuarioCorreo());
                String passEncode = encriptacionSpringService.passEncoderGenerator(request.getNewPassword());
                log.info("passEncode->{}",request.getNewPassword(),passEncode);
                log.info("actualizando password encoder...");
                usuarioMapper.updatePassword(passEncode, idUsuario, false);
                response.getValidacion().setCodigo("0");
                response.getValidacion().setCodigoNegocio("0");
                response.getValidacion().setMensaje("Cambio de clave OK");
                log.info("Cambio de clave OK");
            }
            else{
                response.getValidacion().setCodigoNegocio("1");
                response.getValidacion().setMensaje("Favor completar los datos");
                log.info("Favor completar los datos");
            }
            
            
        } catch (Exception e) {
            log.error("Error en Exception:",e);
            response.setValidacion(new Validacion(CodigoValidacion.ERROR_SERVICIO, "1","Error al cambiar contraseña"));
        }
        
        log.info("fin");
        return response;
    }
    
    
}
