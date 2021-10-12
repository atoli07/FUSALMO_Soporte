/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.model;

import org.fusalmo.www.entities.UsuariosITEntity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Brymolina
 */
public class UserItModel {
    private EntityManagerFactory emf;

    public UserItModel() {
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
    
}
