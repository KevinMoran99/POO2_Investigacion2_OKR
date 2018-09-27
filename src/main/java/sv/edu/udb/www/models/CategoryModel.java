/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.models;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import sv.edu.udb.www.entitites.Category;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author kevin
 */
public class CategoryModel {
    
    public static final int ALL = 0;
    public static final int ACTIVE = 1;
    
    public List<Category> getCategories(int option) {
        //Obteniendo instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query query;
            
            if (option == ALL) {
                query = em.createNamedQuery("Category.findAll");
            }
            else {
                query = em.createNamedQuery("Category.findActive");
            }
            
            List<Category> categories = query.getResultList();
            em.close();
            return categories;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public Category getCategoryById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Category category = em.find(Category.class, id);
            em.close();
            return category;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public int addCategory(Category category) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin(); // Iniciando transacción
            em.persist(category); //Guardando objeto en DB
            tran.commit(); //Confirmando transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    public int updateCategory(Category category) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin(); // Iniciando transacción
            em.merge(category); //Actualizando objeto en DB
            tran.commit(); //Confirmando transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    public int deleteCategory(Category category) {
        EntityManager em = JpaUtil.getEntityManager();
        int deletedRows = 0;
        try {
            //Asegurándose de que la entidad existe
            category = em.find(Category.class, category.getId());
            if (category != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(category); //Eliminando objeto de la db
                tran.commit();
                deletedRows = 1;
            }
            em.close();
            return deletedRows;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
}
