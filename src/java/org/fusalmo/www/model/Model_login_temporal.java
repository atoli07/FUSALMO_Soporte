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
import org.fusalmo.www.utils.JPAUtil;

/**
 *
 * @author Nemy
 */
public class Model_login_temporal {
    
    public List<EmpleadoEntity> buscarEmpleado(String mail, String password){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consultaMail = em.createNamedQuery("EmpleadoEntity.findByCorreo").setParameter("correo", mail);
            Query consultaPassword = em.createNamedQuery("EmpleadoEntity.findByContra").setParameter("contra", password);
            
            List<EmpleadoEntity> responseMail = consultaMail.getResultList();
            List<EmpleadoEntity> responsePassword = consultaPassword.getResultList();
            
            if(responseMail != null && responsePassword != null){
                
                return responseMail;
                
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
