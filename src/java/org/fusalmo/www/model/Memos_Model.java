/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.utils.JPAUtil;

/**
 *
 * @author Nemy
 */
public class Memos_Model {
    
    public List<AreaEntity> listaAreas(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("AreaEntity.findAll");
            List<AreaEntity> listarAreas = consulta.getResultList();
            return listarAreas;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<EmpleadoEntity> listarEMPArea(String idArea){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("EmpleadoEntity.findByAreaAsignada");
            consulta.setParameter("idAreaAsignada", idArea);
            List<EmpleadoEntity> listarEmpleados = consulta.getResultList();
            System.out.println("#############################");
            System.out.println(listarEmpleados.get(0).getApellidos());
            return listarEmpleados;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public AreaEntity obtenerArea(String idArea){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            AreaEntity area = em.find(AreaEntity.class, idArea);
            
            em.close();
            
            return area;
            
        } catch (Exception e) {
            
            em.close();
            
            return null;
            
        }
        
    }
    
}
