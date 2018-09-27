/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author kevin
 */
public class JpaUtil {
    
    private static final EntityManagerFactory emFactory;
    
    static {
        emFactory = Persistence.createEntityManagerFactory("POO2_Investigacion2_OKR_PU");
    }
    
    public static EntityManager getEntityManager() {
        return emFactory.createEntityManager();
    }
}
