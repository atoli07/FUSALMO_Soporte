
package org.fusalmo.www.model;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.fusalmo.www.entities.MantenimientosEntity;
import org.fusalmo.www.utils.JPAUtil;


public class MantenimientosModel 
{
  
    
 public List<MantenimientosEntity> listarMantenimientos() {
 //Obtengo una instancia de EntityManager
 EntityManager em = JPAUtil.getEntityManager();
 try {
 Query consulta = em.createNamedQuery("MantenimientosEntity.findAll");
 //El método getResultList() de la clase Query permite obtener
 // la lista de resultados de una consulta de selección
 List<MantenimientosEntity> lista = consulta.getResultList();
 em.close();// Cerrando el EntityManager
 return lista;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }//fin listar mantenimientos
     
     
 public MantenimientosEntity obtenerMantenimientos(String id) {
 EntityManager em = JPAUtil.getEntityManager();
 try {
 //Recupero el objeto desde la BD a través del método find
 MantenimientosEntity mante = em.find(MantenimientosEntity.class,id);
 em.close();
 return mante;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }//obtener mantenimientos por ID
 
 public int insertarMantenimientos(MantenimientosEntity mante) {
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
 
 public int modificarMantenimientos(MantenimientosEntity mante) {
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
 MantenimientosEntity est = em.find(MantenimientosEntity.class, id);
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
    
    
    
}
