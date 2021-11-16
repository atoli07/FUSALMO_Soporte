package org.fusalmo.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.AreaEntity;
import org.fusalmo.www.entities.JefeAsignadoEntity;
import org.fusalmo.www.utils.JPAUtil;

public class AreasModel {

    public List<AreaEntity> listarMantenimientos() {
        //Obtengo una instancia de EntityManager
        EntityManager em = JPAUtil.getEntityManager();
        try {
            Query consulta = em.createNamedQuery("AreaEntity.findAll");
            //El método getResultList() de la clase Query permite obtener
            // la lista de resultados de una consulta de selección
            List<AreaEntity> lista = consulta.getResultList();
            em.close();// Cerrando el EntityManager
            return lista;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//fin listar mantenimientos

    public AreaEntity obtenerMantenimientos(String id) {
        EntityManager em = JPAUtil.getEntityManager();
        try {
            //Recupero el objeto desde la BD a través del método find
            AreaEntity mante = em.find(AreaEntity.class, id);
            em.close();
            return mante;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }//obtener mantenimientos por ID

    public int insertarMantenimientos(AreaEntity mante) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.persist(mante); //Guardando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }//agregar mantenimientos
    
    public int eliminarMante(String recurso){
        EntityManager em= JPAUtil.getEntityManager();
        EntityTransaction tran =em.getTransaction();
        try{
            AreaEntity temp=em.find(AreaEntity.class, recurso);
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

    public int modificarMantenimientos(AreaEntity mante) {
        EntityManager em = JPAUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin();//Iniciando transacción
            em.merge(mante); //Actualizando el objeto en la BD
            tran.commit();//Confirmando la transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }//modificar mantenimientos

    public int eliminarMantenimientos(String id) {
        EntityManager em = JPAUtil.getEntityManager();
        int filasBorradas = 0;
        try {
            //Recuperando el objeto a eliminar
            AreaEntity est = em.find(AreaEntity.class, id);
            if (est != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();//Iniciando transacción
                em.remove(est);//Borrando la instancia
                tran.commit();//Confirmando la transacción
                filasBorradas = 1;
            }
            em.close();
            return filasBorradas;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    } //eliminar Mantenimientos 

    public List<JefeAsignadoEntity> listarJefeAsignado() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Query consulta = em.createNamedQuery("JefeAsignadoEntity.findAll");
            List<JefeAsignadoEntity> listarJefes = consulta.getResultList();
            return listarJefes;

        } catch (Exception e) {

            em.close();
            return null;

        }

    }

    public JefeAsignadoEntity buscarRecursoId(String idRecurso) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            //em.refresh(em);
            //em.clear();
            //em.flush();
            JefeAsignadoEntity recurso = em.find(JefeAsignadoEntity.class, idRecurso);

            em.close();

            return recurso;

        } catch (Exception e) {

            em.close();

            return null;

        }

    }
    
    public AreaEntity buscarAreaID(String idArea) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            //em.refresh(em);
            //em.clear();
            //em.flush();
            AreaEntity area = em.find(AreaEntity.class, idArea);

            em.close();

            return area;

        } catch (Exception e) {

            em.close();

            return null;

        }

    }
    
    public JefeAsignadoEntity buscarJefeID(String idJefe) {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            //em.refresh(em);
            //em.clear();
            //em.flush();
            JefeAsignadoEntity jefe = em.find(JefeAsignadoEntity.class, idJefe);

            em.close();

            return jefe;

        } catch (Exception e) {

            em.close();

            return null;

        }

    }
    
    public String contarAreas(){
        
        String count = "";
        
        //int returnCount;
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("AreaEntity.countAll");
            
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
        
        StringBuilder idPlantilla = new StringBuilder("AR0000");
        String finalID = "";
        int countResource = Integer.valueOf(contarAreas());
        int resultLength = String.valueOf(countResource).length();
        int numberReturn;
        System.out.println(countResource + " Conteo de areas");
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
                countResource = countResource + 1;
                finalID = idPlantilla + String.valueOf(countResource);
                
                //Comprueba si ya existe este recurso, si existe se guardará en una
                //posición anterior, sino continuará en aumento el numero en la BD
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
                resultLength = String.valueOf(numberReturn).length();
                
                if(resultLength == 1){
                    
                    finalID = idPlantilla + String.valueOf(numberReturn);
                    return finalID;
                    
                }
                if(resultLength == 2){
                    
                    //Borra el caracter 4 de la cadena
                    idPlantilla = idPlantilla.deleteCharAt(4);
                    
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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);
                
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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

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
                numberReturn = checkRecurso(finalID, countResource, idPlantilla);

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
    
    public int checkRecurso(String finalId, int resultCount, StringBuilder id){
        
        AreaEntity returnArea = buscarAreaID(finalId);
        System.out.println(returnArea + " PRIMER RECURSO RETORNADO * ** *");
        if(returnArea == null){
            
            return resultCount;
            
        }else {

            while (returnArea != null) {

                resultCount += 1;
                
                finalId = id + String.valueOf(resultCount);

                returnArea = buscarAreaID(finalId);
                System.out.println(returnArea + " recurso retornado");
            }
            
            return resultCount;
            
        }
        
    }
    
}
