/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import java.util.Iterator;
import org.fusalmo.www.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TipoRecursoEntity;
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
    
    public TipoRecursoEntity obtenerRecurso(Integer idRecurso){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            TipoRecursoEntity recurso = em.find(TipoRecursoEntity.class, idRecurso);
            
            em.close();
            
            return recurso;
            
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
    
    public int nuevoRecurso(RecursosEntity recurso){
        
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        
        try {
            
            tran.begin();
            em.persist(recurso);
            tran.commit();
            em.close();
            return 1;
            
        } catch (Exception e) {
            em.close();
            return 0;
        }
        
    }
    
    public String contarRecursos(){
        
        String count = "";
        
        //int returnCount;
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("RecursosEntity.countAll");
            
            //int resultsNumber = consulta.getFirstResult();
            List results = consulta.getResultList();
            
            //System.out.println(resultsNumber);
            //System.out.println(results);
            //System.out.println(results.size());
            //System.out.println(results.toString() + " Pasado a String");
            
            for (Object result : results) {
                //System.out.println(result.toString());
                count = result.toString();
            }
            
            //System.out.println(count + " Cuenta del resultado");
            
            //returnCount = Integer.parseInt(count);
            
            return count;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
    public String crearID(){
        
        StringBuilder id = new StringBuilder("R00000");
        String finalId = "";
        String totalRecursos = contarRecursos();
        int resultCount = Integer.parseInt(totalRecursos) + 1;
        int resultLength = totalRecursos.length();
        
        if(resultLength == 1){
            
            id = id.deleteCharAt(5);
            
            finalId = id + String.valueOf(resultCount);
            
        }
        if(resultLength == 2){
            
            id = id.deleteCharAt(4);
            
            finalId = id + String.valueOf(resultCount);
            
        }
        if(resultLength == 3){
            
            id = id.deleteCharAt(3);
            
            finalId = id + String.valueOf(resultCount);
            
        }
        if(resultLength == 4){
            
            id = id.deleteCharAt(2);
            
            finalId = id + String.valueOf(resultCount);
            
        }
        if(resultLength == 5){
            
            id = id.deleteCharAt(1);
            
            finalId = id + String.valueOf(resultCount);
            
        }
        
        //System.out.println(finalId);
        
        return finalId;
    
    }
    
}
