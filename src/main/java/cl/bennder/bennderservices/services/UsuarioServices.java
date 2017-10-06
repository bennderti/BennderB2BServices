/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.entitybennderwebrest.request.CambioPasswordRequest;
import cl.bennder.entitybennderwebrest.request.LoginRequest;
import cl.bennder.entitybennderwebrest.response.CambioPasswordResponse;
import cl.bennder.entitybennderwebrest.response.LoginResponse;
import cl.bennder.entitybennderwebrest.response.ValidacionResponse;


/**
 *
 * @author dyanez
 */
public interface UsuarioServices {
    //public void listarUsuarios();
    public LoginResponse validacionUsuario(LoginRequest request);
    
    
    /***
     * Método que registra acceso de usuario
     * @param idUsuario identificador de usuario
     */
    public void registraAccesoUsuario(Integer idUsuario);
    public CambioPasswordResponse cambioPassword(CambioPasswordRequest request);
    
}
