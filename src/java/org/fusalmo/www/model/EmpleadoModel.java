package org.fusalmo.www.model;
import org.fusalmo.www.utils.JPAUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.EmpleadoEntity;


/**
 *
 * @author brand
 */
public class EmpleadoModel {
    public List<EmpleadoEntity> listarEmpleado() {
 //Obtengo una instancia de EntityManager
 EntityManager em = JPAUtil.getEntityManager();
 try {
 Query consulta = em.createNamedQuery("EmpleadoEntity.findAll");
 //El método getResultList() de la clase Query permite obtener
 // la lista de resultados de una consulta de selección
 List<EmpleadoEntity> lista = consulta.getResultList();
 em.close();// Cerrando el EntityManager
 return lista;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }
 public EmpleadoEntity obtenerEmpleado(String id) {
 EntityManager em = JPAUtil.getEntityManager();
 try {
 //Recupero el objeto desde la BD a través del método find
 EmpleadoEntity empleado = em.find(EmpleadoEntity.class,
id);
 em.close();
 return empleado;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }
 public int insertarEmpleado(EmpleadoEntity empleado) {
 EntityManager em = JPAUtil.getEntityManager();
 EntityTransaction tran = em.getTransaction();
 try {
 tran.begin();//Iniciando transacción
 em.persist(empleado); //Guardando el objeto en la BD
 tran.commit();//Confirmando la transacción
 em.close();
 return 1;
 } catch (Exception e) {
 em.close();
 return 0;
 }
 }
 public int modificarEmpleado(EmpleadoEntity empleado) {
 EntityManager em = JPAUtil.getEntityManager();
 EntityTransaction tran = em.getTransaction();
 try {
 tran.begin();//Iniciando transacción
 em.merge(empleado); //Actualizando el objeto en la BD
 tran.commit();//Confirmando la transacción
 em.close();
 return 1;
 } catch (Exception e) {
 em.close();
 return 0;
 }
 }
 public int eliminarEmpleado(String id) {
 EntityManager em = JPAUtil.getEntityManager();
 int filasBorradas = 0;
 try {
 //Recuperando el objeto a eliminar
 EmpleadoEntity est = em.find(EmpleadoEntity.class, id);
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
 }
 
 public List<EmpleadoEntity> listaAreas(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("AreaEntity.findAll");
            List<EmpleadoEntity> listarAreas = consulta.getResultList();
            return listarAreas;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
 
 public List<EmpleadoEntity> listaRecursos(){
        
        EntityManager em = JPAUtil.getEntityManager();
        
        try {
            
            Query consulta = em.createNamedQuery("RecursosEntity.findAll");
            List<EmpleadoEntity> listarAreas = consulta.getResultList();
            return listarAreas;
            
        } catch (Exception e) {
            
            em.close();
            return null;
            
        }
        
    }
    
}
