/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.fusalmo.www.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Soporte
 */
public class JPAUtil {
    private static final EntityManagerFactory emFactory;
    static {
        emFactory = Persistence.createEntityManagerFactory("FUSALMO_Soporte_PU");
    }
    
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
}
