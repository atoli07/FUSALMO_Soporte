/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import org.fusalmo.www.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.RecursosEntity;
/**
 *
 * @author Soporte
 */
public class RecursosModel {
    
    public List<RecursosEntity> listarRecursos(){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            Query consulta= em.createNamedQuery("RecursosEntity.findAll");
            List<RecursosEntity> lista= consulta.getResultList();
            return lista;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
    
}
