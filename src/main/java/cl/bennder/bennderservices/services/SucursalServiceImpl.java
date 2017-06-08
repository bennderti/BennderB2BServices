/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.bennder.bennderservices.services;

import cl.bennder.bennderservices.mapper.ProveedorMapper;
import cl.bennder.bennderservices.mapper.SucursalMapper;
import cl.bennder.entitybennderwebrest.model.Validacion;
import cl.bennder.entitybennderwebrest.request.InfoInicioSucursalRequest;
import cl.bennder.entitybennderwebrest.request.InfoSucursalRequest;
import cl.bennder.entitybennderwebrest.request.SucursalesRequest;
import cl.bennder.entitybennderwebrest.response.InfoInicioSucursalResponse;
import cl.bennder.entitybennderwebrest.response.InfoSucursalResponse;
import cl.bennder.entitybennderwebrest.response.SucursalesResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author ext_dayanez
 */
@Service
@Transactional
public class SucursalServiceImpl implements SucursalService{
    
    @Autowired
    private SucursalMapper sucursalMapper;
    
    @Autowired
    private ProveedorMapper proveedorMapper;

    private static final Logger log = LoggerFactory.getLogger(SucursalServiceImpl.class);

    @Override
    public SucursalesResponse getSucursalesProveedor(SucursalesRequest request) {
        SucursalesResponse response = new SucursalesResponse();
        response.setValidacion(new Validacion("0","1","Problemas al obtener sucursales."));
        log.info("inicio");
        try {
            Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
            log.info("idProveedor->{}",idProveedor);
            response.setSucursales(sucursalMapper.getTodasSucursales(idProveedor));
            response.setValidacion(new Validacion("0","0","Sucursales OK."));
            log.info("iSucursales OK");
            
        } catch (Exception e) {
            response.setValidacion(new Validacion("1","1","Error al obtener sucursales"));
            log.error("Exception getSucursalesProveedor,",e);
        }
        
        log.info("fin");
        return response;
    }

    
    
    @Override
    public InfoSucursalResponse guardarSucursal(InfoSucursalRequest request) {
        InfoSucursalResponse response = new InfoSucursalResponse();
        response.setValidacion(new Validacion("0","1","Problemas al guardar sucursal"));
        log.info("inicio");
        try {
            
            if(request.getSucursal().getIdSucursal() == null){
                log.info("creando sucursal...");
                Integer idDireccion= sucursalMapper.getSeqIdDireccion();
                log.info("idDireccion ->{}",idDireccion);
                log.info("guardando direccion...");
                request.getSucursal().getDireccion().setIdDireccion(idDireccion);
                sucursalMapper.insertDireccion(request.getSucursal().getDireccion());                
                Integer idProveedor = proveedorMapper.getIdProveedorByUser(request.getIdUsuario());
                log.info("guardando datos sucursal para proveedor ->{}",idProveedor);
                sucursalMapper.insertSucursal(idProveedor, request.getSucursal());
                response.setValidacion(new Validacion("0","0","Datos sucursal guardados OK."));
            }
            else{
                log.info("editando sucursal...");
                log.info("actualizando direccion...");
                sucursalMapper.updateDireccion(request.getSucursal().getDireccion());
                log.info("actualizando datos sucursal...");
                sucursalMapper.updateSucursal(request.getSucursal());
                response.setValidacion(new Validacion("0","0","Datos sucursal actualizado OK."));
            }            
            
            
        } catch (Exception e) {
            response.setValidacion(new Validacion("1","1","Error al guardar sucursal"));
            log.error("Exception guardarSucursal,",e);
        }
        
        log.info("fin");
        return response;
    }
    
    
    
    
    @Override
    public InfoInicioSucursalResponse getInfoInicioSucursal(InfoInicioSucursalRequest request) {
        InfoInicioSucursalResponse response = new InfoInicioSucursalResponse();
        response.setValidacion(new Validacion("0","1","Problemas al obtener información de sucursal"));
        
        try {
            response.setRegiones(sucursalMapper.getRegiones());
            response.setComunas(sucursalMapper.getComunas());
            
            if(request.getIdSucursal() != null){
                response.setSucursal(sucursalMapper.getSucursal(request.getIdSucursal()));
            }
            response.setValidacion(new Validacion("0","0","Datos sucursal OK."));
        } catch (Exception e) {
            response.setValidacion(new Validacion("1","1","Error al obtener información de sucursal"));
            log.error("Exception getInfoInicioSucursal,",e);
        }
        
        
        return response;
    }
    
    
    
}
