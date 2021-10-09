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
import javax.persistence.FlushModeType;
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
    
    public RecursosEntity buscarRecursoId(String idRecurso){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            //em.refresh(em);
            //em.clear();
            //em.flush();
            
            RecursosEntity recurso = em.find(RecursosEntity.class, idRecurso);
            
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
        
        StringBuilder idPlantilla = new StringBuilder("R00000");
        String finalID = "";
        int countResource = Integer.valueOf(contarRecursos());
        int resultLength = String.valueOf(countResource).length();
        int numberReturn;
        
        if(resultLength == 1 && countResource < 10){
            
            if(countResource == 9){
                
                //Borra el caracter 5 y 4 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                finalID = idPlantilla + String.valueOf(countResource + 1);
                System.out.println(finalID + " ID ANTES");
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
                
                //Borra el caracter 5 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                finalID = idPlantilla + String.valueOf(countResource + 1);
                System.out.println(finalID + " ID ANTES");
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
            
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }
            
        }
        
        return finalID;
    
    }
    
    public int checkRecurso(String finalId, int resultCount, StringBuilder id){
        
        RecursosEntity returnResource = buscarRecursoId(finalId);
        System.out.println(returnResource + " PRIMER RECURSO RETORNADO * ** *");
        if(returnResource == null){
            
            return resultCount;
            
        }else {

            while (returnResource != null) {

                resultCount += 1;
                
                finalId = id + String.valueOf(resultCount);

                returnResource = buscarRecursoId(finalId);
                System.out.println(returnResource + " recurso retornado");
            }
            
            return resultCount;
            
        }
        
        
        
    }
    
}

/*COMENTADO

    public String crearID(){
        
        StringBuilder id = new StringBuilder("R00000");
        String finalId = "";
        String totalRecursos = contarRecursos();
        int resultCount = Integer.parseInt(totalRecursos) + 1;
        int resultLength = totalRecursos.length();
        int numberReturn;
        
        if(resultLength == 1){
            System.out.println("ENTROOOO************************************* 1");
            //Borra el caracter 5 de la cadena
            id = id.deleteCharAt(5);
            
            //Es el id final que se retornará para guardar en la BD
            //concatena el Id y el resultado de la cuenta total de los registros
            //para guardar dinámicamente
            finalId = id + String.valueOf(resultCount);
            
            //Comprueba si ya existe este recurso, si existe se guardará en una
            //posición anterior, sino continuará en aumento el numero en la BD
            numberReturn = checkRecurso(finalId, resultCount, id);
            
            resultLength = String.valueOf(numberReturn).length();
            
            System.out.println(numberReturn + " numero retornado");
            System.out.println(resultLength + " longitud de caracteres");
            
            if(resultLength == 1){
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 2){
                System.out.println("ENTROOOO************************************* 2");
                //Borra el caracter 4 de la caden
                id = id.deleteCharAt(4);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 3){
                
                //Borra el caracter 4 y 3 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 4){
                
                //Borra el caracter 4, 3 y 2 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 5){
                
                //Borra el caracter 4, 3, 2 y 1 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                id = id.deleteCharAt(1);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            
        }
        if(resultLength == 2){
            System.out.println("ENTROOOO*************************************");
            //Borra el caracter 4 de la cadena
            //id = id.deleteCharAt(5);
            id = id.deleteCharAt(4);
            System.out.println(id);
            //Es el id final que se retornará para guardar en la BD
            //concatena el Id y el resultado de la cuenta total de los registros
            //para guardar dinámicamente
            finalId = id + String.valueOf(resultCount);
            
            //Comprueba si ya existe este recurso, si existe se guardará en una
            //posición anterior, sino continuará en aumento el numero en la BD
            numberReturn = checkRecurso(finalId, resultCount, id);
            
            resultLength = String.valueOf(numberReturn).length();
            
            if(resultLength == 1){
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 2){
                
                //Borra el caracter 4 de la caden
                id = id.deleteCharAt(4);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 3){
                
                //Borra el caracter 4 y 3 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 4){
                
                //Borra el caracter 4, 3 y 2 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 5){
                
                //Borra el caracter 4, 3, 2 y 1 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                id = id.deleteCharAt(1);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            
        }
        if(resultLength == 3){
            
            //Borra el caracter 3 de la cadena
            id = id.deleteCharAt(5);
            id = id.deleteCharAt(4);
            id = id.deleteCharAt(3);
            
            //Es el id final que se retornará para guardar en la BD
            //concatena el Id y el resultado de la cuenta total de los registros
            //para guardar dinámicamente
            finalId = id + String.valueOf(resultCount);
            
            //Comprueba si ya existe este recurso, si existe se guardará en una
            //posición anterior, sino continuará en aumento el numero en la BD
            numberReturn = checkRecurso(finalId, resultCount, id);
            
            resultLength = String.valueOf(numberReturn).length();
            
            if(resultLength == 1){
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 2){
                
                //Borra el caracter 4 de la caden
                id = id.deleteCharAt(4);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 3){
                
                //Borra el caracter 4 y 3 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 4){
                
                //Borra el caracter 4, 3 y 2 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 5){
                
                //Borra el caracter 4, 3, 2 y 1 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                id = id.deleteCharAt(1);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            
        }
        if(resultLength == 4){
            
            //Borra el caracter 2 de la cadena
            id = id.deleteCharAt(5);
            id = id.deleteCharAt(4);
            id = id.deleteCharAt(3);
            id = id.deleteCharAt(2);
            
            //Es el id final que se retornará para guardar en la BD
            //concatena el Id y el resultado de la cuenta total de los registros
            //para guardar dinámicamente
            finalId = id + String.valueOf(resultCount);
            
            //Comprueba si ya existe este recurso, si existe se guardará en una
            //posición anterior, sino continuará en aumento el numero en la BD
            numberReturn = checkRecurso(finalId, resultCount, id);
            
            resultLength = String.valueOf(numberReturn).length();
            
            if(resultLength == 1){
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 2){
                
                //Borra el caracter 4 de la caden
                id = id.deleteCharAt(4);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 3){
                
                //Borra el caracter 4 y 3 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 4){
                
                //Borra el caracter 4, 3 y 2 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 5){
                
                //Borra el caracter 4, 3, 2 y 1 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                id = id.deleteCharAt(1);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            
        }
        if(resultLength == 5){
            
            //Borra el caracter 1 de la cadena
            id = id.deleteCharAt(5);
            id = id.deleteCharAt(4);
            id = id.deleteCharAt(3);
            id = id.deleteCharAt(1);
            
            //Es el id final que se retornará para guardar en la BD
            //concatena el Id y el resultado de la cuenta total de los registros
            //para guardar dinámicamente
            finalId = id + String.valueOf(resultCount);
            
            //Comprueba si ya existe este recurso, si existe se guardará en una
            //posición anterior, sino continuará en aumento el numero en la BD
            numberReturn = checkRecurso(finalId, resultCount, id);
            
            resultLength = String.valueOf(numberReturn).length();
            
            if(resultLength == 1){
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 2){
                
                //Borra el caracter 4 de la caden
                id = id.deleteCharAt(4);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 3){
                
                //Borra el caracter 4 y 3 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 4){
                
                //Borra el caracter 4, 3 y 2 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            if(resultLength == 5){
                
                //Borra el caracter 4, 3, 2 y 1 de la cadena
                id = id.deleteCharAt(4);
                id = id.deleteCharAt(3);
                id = id.deleteCharAt(2);
                id = id.deleteCharAt(1);
                
                finalId = id + String.valueOf(numberReturn);
                
                return finalId;
                
            }
            
        }
        
        return finalId;
    
    }
    
    public int checkRecurso(String finalId, int resultCount, StringBuilder id){
        
        RecursosEntity returnResource = buscarRecursoId(finalId);
        System.out.println(returnResource + " PRIMER RECURSO RETORNADO * ** *");
        if(returnResource == null){
            
            return resultCount;
            
        }else {

            while (returnResource != null) {

                resultCount += 1;
                
                finalId = id + String.valueOf(resultCount);

                returnResource = buscarRecursoId(finalId);
                System.out.println(returnResource + " recurso retornado");
            }
            
            return resultCount;
            
        }
        
        
        
    }

*/