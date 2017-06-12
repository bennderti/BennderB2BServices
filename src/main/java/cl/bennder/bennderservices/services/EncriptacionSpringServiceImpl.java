/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author Usuario
 */
@Service
public class EncriptacionSpringServiceImpl implements EncriptacionSpringService{

    private static final Logger log = LoggerFactory.getLogger(EmailServicesImpl.class);

    @Override
    public String generarPasswordTemporal(int nBits) {
        SecureRandom random = new SecureRandom();
        log.info("generando clave temporal");
        String r =  new BigInteger(nBits, random).toString(32);
        return r;
    }
    
    
    
    @Override
    public String passEncoderGenerator(String password) {
        String hashedPassword  = null;
        log.info("inicio");
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        hashedPassword = passwordEncoder.encode(password);
        if(hashedPassword!=null){
            log.info("password encode by bcrypt ->{}",hashedPassword);
        }
        log.info("fin");
        return hashedPassword;
    }
    
    
}
