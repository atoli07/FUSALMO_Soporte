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
import org.fusalmo.www.entities.JefeAsignadoEntity;


/**
 *
 * @author brand
 */
public class JefeAsignadoModel {
    public List<JefeAsignadoEntity> listarJefe() {
 //Obtengo una instancia de EntityManager
 EntityManager em = JPAUtil.getEntityManager();
 try {
 Query consulta = em.createNamedQuery("JefeAsignadoEntity.findAll");
 //El método getResultList() de la clase Query permite obtener
 // la lista de resultados de una consulta de selección
 List<JefeAsignadoEntity> lista = consulta.getResultList();
 em.close();// Cerrando el EntityManager
 return lista;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }
 public JefeAsignadoEntity obtenerJefe(String id) {
 EntityManager em = JPAUtil.getEntityManager();
 try {
 //Recupero el objeto desde la BD a través del método find
 JefeAsignadoEntity jefe = em.find(JefeAsignadoEntity.class,
id);
 em.close();
 return jefe;
 } catch (Exception e) {
 em.close();
 return null;
 }
 }
 public int insertarJefe(JefeAsignadoEntity jefe) {
 EntityManager em = JPAUtil.getEntityManager();
 EntityTransaction tran = em.getTransaction();
 try {
 tran.begin();//Iniciando transacción
 em.persist(jefe); //Guardando el objeto en la BD
 tran.commit();//Confirmando la transacción
 em.close();
 return 1;
 } catch (Exception e) {
 em.close();
 return 0;
 }
 }
 public int modificarJefe(JefeAsignadoEntity jefe) {
 EntityManager em = JPAUtil.getEntityManager();
 EntityTransaction tran = em.getTransaction();
 try {
 tran.begin();//Iniciando transacción
 em.merge(jefe); //Actualizando el objeto en la BD
 tran.commit();//Confirmando la transacción
 em.close();
 return 1;
 } catch (Exception e) {
 em.close();
 return 0;
 }
 }
 public int eliminarJefe(String id) {
 EntityManager em = JPAUtil.getEntityManager();
 int filasBorradas = 0;
 try {
 //Recuperando el objeto a eliminar
 JefeAsignadoEntity est = em.find(JefeAsignadoEntity.class, id);
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
}
    
