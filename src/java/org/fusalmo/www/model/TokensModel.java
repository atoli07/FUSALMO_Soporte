/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import static java.lang.Integer.parseInt;
import org.fusalmo.www.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.EmpleadoEntity;
import org.fusalmo.www.entities.EstadoTokenEntity;
import org.fusalmo.www.entities.RecursosEntity;
import org.fusalmo.www.entities.TokensEntity;
/**
 *
 * @author Soporte
 */
public class TokensModel {
    
     public List<TokensEntity> listarTokens(){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            Query consulta= em.createNamedQuery("TokensEntity.findAll");
            List<TokensEntity> lista= consulta.getResultList();
            return lista;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
     
    public  List<TokensEntity> listarTokensActivos(){
         EntityManager em= JPAUtil.getEntityManager();
         try{
            Query consulta= em.createNamedQuery("TokensEntity.findByIsDeleted");
            consulta.setParameter("isDeleted", Boolean.FALSE);
            List<TokensEntity> lista= consulta.getResultList();
            return lista;
        }catch(Exception e){
            em.close();
            return null;
        }
     }
     
    public List<TokensEntity> listarTokensByIdEmpleado(String idemp){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            Query consulta =em.createQuery("SELECT t FROM TokensEntity t WHERE t.idEmpleado.id = :idEmpleado");
            consulta.setParameter("idEmpleado",idemp);
            List<TokensEntity> lista= consulta.getResultList();
            return lista;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
     
    public EmpleadoEntity obtenerEmpleado(String id){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            EmpleadoEntity consulta = em.find(EmpleadoEntity.class, id);
            return consulta;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
    
    public EstadoTokenEntity obtenerEstadoToken(Integer id){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            //Query consulta= em.createNamedQuery("EstadoTokenEntity.findById");
            //consulta.setParameter(":id", id);
            //EstadoTokenEntity estado=(EstadoTokenEntity)consulta.getSingleResult();
            EstadoTokenEntity consulta = em.find(EstadoTokenEntity.class, id);
            return consulta;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
    
    public RecursosEntity obtenerRecurso(String id){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            Query consulta= em.createNamedQuery("RecursosEntity.findById");
            consulta.setParameter(":id", id);
            RecursosEntity recurso=(RecursosEntity)consulta.getSingleResult();
            return recurso;
        }catch(Exception e){
            em.close();
            return null;
        }
    }
     
     public TokensEntity obtenerToken(String id){
         EntityManager em= JPAUtil.getEntityManager();
        try{
            TokensEntity token= em.find(TokensEntity.class, id);
            em.close();
            return token;
        }catch(Exception e){
            em.close();
            return null;
        }
     }
     
   public int eliminarToken(String id){
        EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction tran =em.getTransaction();
        try{
            TokensEntity temp=em.find(TokensEntity.class, id);
            temp.setIsDeleted(Boolean.TRUE);
            tran.begin();
            em.persist(temp);
            tran.commit();
            em.close();
            return 1;
        }catch(Exception e){
            em.close();
            System.out.println(e);
            return 0;
        }
    }
     
     
     public int crearToken(TokensEntity token){
        EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction tran =em.getTransaction();
        try{
            tran.begin();
            em.persist(token);
            tran.commit();
            em.close();
            return 1;
        }catch(Exception e){
            em.close();
            return 0;
        }
     }
     
     public int actualizarEstadoToken(TokensEntity token){
         EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction tran =em.getTransaction();
        try{
            tran.begin();
            em.merge(token);
            tran.commit();
            em.close();
            return 1;
        }catch(Exception e){
            em.close();
            return 0;
        }
     }
     
     public String contarTokens(){
        
        String count = "";
        
        //int returnCount;
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            Query consulta = em.createNamedQuery("RecursosEntity.countAll");
            List results = consulta.getResultList();
            for (Object result : results) {
                count = result.toString();
            }
            return count;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
     
    public String crearID(){
        
        StringBuilder idPlantilla = new StringBuilder("TK0000");
        String finalID = "";
        EntityManager em= JPAUtil.getEntityManager();
        int countResource = Integer.valueOf(contarTokens());
        int resultLength = String.valueOf(countResource).length();
        System.out.println("El resultLength es: "+resultLength);
        int numberReturn;
        System.out.println(countResource + " Conteo de tokens");
        if(resultLength == 1 && countResource < 10){
            if(countResource == 9){
                
                //Borra el caracter 5 y 4 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
                
                //Borra el caracter 5 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);
                resultLength = String.valueOf(numberReturn).length();
                System.out.println("~~~~resultLength "+resultLength);
                if(resultLength == 1){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    //Borra el caracter 4 de la cadena
                    System.out.println("~~~~idPlantilla "+idPlantilla);
                    //idPlantilla = idPlantilla.deleteCharAt(4);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    //Borra el caracter 4 y 3 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 4, 3 y 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 4, 3, 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 2 && countResource < 100){
            
            if(countResource == 99){
                
                //Borra el caracter 5, 4 y 3 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5 y 4 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    //Borra el caracter 3 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 3 y 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 3, 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(3);
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 3 && countResource < 1000){
            
            if(countResource == 999){
                
                //Borra el caracter 5, 4, 3 y 2 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5, 4 y 3 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    //Borra el caracter 2 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(2);
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 4 && countResource < 10000){
            
            if(countResource == 9999){
                
                //Borra el caracter 5, 4, 3, 2 y 1 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                idPlantilla = idPlantilla.deleteCharAt(1);
                
                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                finalID = idPlantilla + String.valueOf(numberReturn);
                return finalID;
                
            }else{
            
                //Borra el caracter 5, 4, 3 y 2 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    idPlantilla = idPlantilla.insert(2, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        if(resultLength == 4 && countResource < 100000){
            
            if(countResource == 99999){
                
                return null;
                
            }else{
            
                //Borra el caracter 5, 4, 3, 2 y 1 de la cadena
                idPlantilla = idPlantilla.deleteCharAt(5);
                idPlantilla = idPlantilla.deleteCharAt(4);
                idPlantilla = idPlantilla.deleteCharAt(3);
                idPlantilla = idPlantilla.deleteCharAt(2);
                idPlantilla = idPlantilla.deleteCharAt(1);

                //Es el id final que se retornará para guardar en la BD
                //concatena el Id y el resultado de la cuenta total de los registros
                //para guardar dinámicamente
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkToken(finalID, countResource, idPlantilla);

                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    //Agrega el caracter 0 al string
                    idPlantilla = idPlantilla.insert(4, "0");
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    idPlantilla = idPlantilla.insert(3, "0");
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 3){
                    
                    idPlantilla = idPlantilla.insert(2, "0");
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 4){
                    
                    idPlantilla = idPlantilla.insert(1, "0");
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 5){
                    
                    //Borra el caracter 2 y 1 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(1);
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                
            }
            
        }
        
        return finalID;
    
    }
    
    public int checkToken(String finalId, int resultCount, StringBuilder id){
        
        TokensEntity returnResource = obtenerToken(finalId);
        System.out.println(returnResource + " PRIMER TOKEN RETORNADO * ** *");
        if(returnResource == null){
            
            return resultCount;
            
        }else {

            while (returnResource != null) {

                resultCount += 1;
           
                if (resultCount>=10) {
                    id= id.deleteCharAt(4);
                    System.out.println(id);
                    
                    while(returnResource != null){
                        resultCount += 1;
                        finalId = id + String.valueOf(resultCount);
                        returnResource = obtenerToken(finalId);
                        System.out.println(returnResource + " token retornado");
                    }
                    return resultCount;
                }
                else{
                    finalId = id + String.valueOf(resultCount);
                }

                returnResource = obtenerToken(finalId);
                System.out.println(returnResource + " token retornado");
            }
            
            return resultCount;
            
        } 
    }
    
}
