/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

/**
 *
 * @author dyanez
 */
public interface EncriptacionSpringService {
    
    /***
     * Encriptación bcryp de password
     * @param password password a hashear by bcryp
     * @return 
     */
    public String passEncoderGenerator(String password);
    /***
     * Genera password temporatal de forma aleatoria
     * @param nBits Cantidad de bit aleatorio criptograficamente seguro y codificando en base-32. ejemplo: 60
     * @return 
     */
    public String generarPasswordTemporal(int nBits);
}
