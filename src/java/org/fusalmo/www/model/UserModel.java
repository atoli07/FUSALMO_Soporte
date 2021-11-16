/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import org.fusalmo.www.entities.EmpleadoEntity;

import org.fusalmo.www.entities.UsuariosITEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Brymolina
 */
public class UserModel {
    private EntityManagerFactory emf;

    public UserModel() {
        emf = Persistence.createEntityManagerFactory("FUSALMO_Soporte_PU");
    }
    
    public UsuariosITEntity validarUsuario(String correo, String contra){
    UsuariosITEntity user;
    EntityManager em=emf.createEntityManager();
    
    String sql="SELECT u FROM UsuariosITEntity u WHERE u.correo = :correo AND u.contra = :contra";
    Query query =em.createQuery(sql);
    query.setParameter("correo", correo);
    query.setParameter("contra", contra);
    
    user= (UsuariosITEntity) query.getSingleResult();
        return user;
}
   public EmpleadoEntity validarEmpleado(String correo, String contra){
       EmpleadoEntity empleado;
       EntityManager em = emf.createEntityManager();
       
    String sql ="SELECT e FROM EmpleadoEntity e WHERE e.correo = :correo AND e.contra = :contra";
    Query query =em.createQuery(sql);
    query.setParameter("correo", correo);
    query.setParameter("contra", contra);
    
    empleado = (EmpleadoEntity) query.getSingleResult();
    
    return empleado;
   }
   
   public EmpleadoEntity recuperarEmpleado(String correo){
       EmpleadoEntity empleado;
       EntityManager em = emf.createEntityManager();
       
    String sql ="SELECT e FROM EmpleadoEntity e WHERE e.correo = :correo ";
    Query query =em.createQuery(sql);
    query.setParameter("correo", correo);
 
    
    empleado = (EmpleadoEntity) query.getSingleResult();
    
    return empleado;
   }
   
    public UsuariosITEntity recuperarUsuario(String correo){
    UsuariosITEntity user;
    EntityManager em=emf.createEntityManager();
    
    String sql="SELECT u FROM UsuariosITEntity u WHERE u.correo = :correo ";
    Query query =em.createQuery(sql);
    query.setParameter("correo", correo);
   
    
    user= (UsuariosITEntity) query.getSingleResult();
        return user;
}
    
}
