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
import org.fusalmo.www.entities.RecursosDeEmpleadosEntity;

/**
 *
 * @author Soporte
 */
public class RecursosEmpleadosModel {
    public List<RecursosDeEmpleadosEntity> listarRecursosPorIdEmpleado(String idemp){
        EntityManager em= JPAUtil.getEntityManager();
        try{
            Query consulta= em.createQuery("SELECT r FROM RecursosDeEmpleadosEntity r WHERE r.idEmpleado.id = :idEmpleado");
            consulta.setParameter("idEmpleado",idemp);
            List<RecursosDeEmpleadosEntity> lista= consulta.getResultList();
            return lista;
        }catch(Exception e){
            em.close();
            return null;
        }
    }

}
