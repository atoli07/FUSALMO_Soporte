/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.UsuariosITEntity;
import org.fusalmo.www.utils.JPAUtil;

/**
 *
 * @author Nemy
 */
public class Login_model {
    
    public List<EmpleadoEntity> buscarEmpleado(String mail, String password){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query finUser = em.createNamedQuery("EmpleadoEntity.findByMailPass").setParameter("correo", mail).setParameter("contra", password);
            
            List<EmpleadoEntity> responseQuery = finUser.getResultList();
            
            if(responseQuery != null){
                
                return responseQuery;
                
            }else{
                
                return null;
                
            }
            
                
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<UsuariosITEntity> buscarAdmin(String mail, String password){
    
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query findUser = em.createNamedQuery("UsuariosITEntity.findByMailPass").setParameter("correo", mail).setParameter("contra", password);
            
            List<UsuariosITEntity> responseQuery = findUser.getResultList();
            
            if(responseQuery != null){
                
                return responseQuery;
                
            }else{
                
                return null;
                
            }
                
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public EmpleadoEntity buscarIdEmpleado(String emp){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            EmpleadoEntity empleado = em.find(EmpleadoEntity.class, emp);
            
            em.close();
            
            return empleado;
                
        } catch (Exception e) {
            
            em.close();
            
            return null;
            
        }
        
    }
    
}
