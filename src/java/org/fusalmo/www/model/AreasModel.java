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

    public List<AreaEntity> listarJefeAsignado() {

        EntityManager em = JPAUtil.getEntityManager();

        try {

            Query consulta = em.createNamedQuery("JefeAsignadoEntity.findAll");
            List<AreaEntity> listarJefe = consulta.getResultList();
            return listarJefe;

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
}
