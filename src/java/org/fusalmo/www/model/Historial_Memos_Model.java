/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.fusalmo.www.entities.MemosEntity;
import org.fusalmo.www.utils.JPAUtil;

/**
 *
 * @author Nemy
 */
public class Historial_Memos_Model {

    public List<MemosEntity> listaMemos(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("MemosEntity.findAll");
            
            List<MemosEntity> listarMemos = consulta.getResultList();
            
            return listarMemos;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public List<MemosEntity> listaMemosByPrestamo(){
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createQuery("SELECT m FROM MemosEntity m WHERE m.idTipo.idTipo = 'EXT' OR m.idTipo.idTipo = 'INT'");
            List<MemosEntity> listarMemos = consulta.getResultList();
            return listarMemos;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
}
