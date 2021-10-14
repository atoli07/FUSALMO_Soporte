/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;


import javax.persistence.EntityManager;
import org.fusalmo.www.entities.EmpleadoEntity;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Brymolina
 */
public class UserAdminModel {
    private EntityManagerFactory amf;

    public UserAdminModel() {
        amf= Persistence.createEntityManagerFactory("FUSALMO_Soporte_PU");
    }
    
    public EmpleadoEntity validarUsuario(String correo,String contra){
    EmpleadoEntity usera;
    EntityManager am=amf.createEntityManager();
    
    String sql ="SELECT e FROM EmpleadoEntity e WHERE e.correo = :correo AND e.contra = :contra";
    Query query = am.createQuery(sql);
    query.setParameter("correo",correo);
    query.setParameter("contra",contra);
    
    usera= (EmpleadoEntity) query.getSingleResult();
    
    return usera;
    }
    
    
}
