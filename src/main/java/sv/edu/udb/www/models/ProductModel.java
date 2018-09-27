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
import sv.edu.udb.www.entitites.Product;
import sv.edu.udb.www.utils.JpaUtil;

/**
 *
 * @author kevin
 */
public class ProductModel {
    
    public List<Product> getProducts() {
        //Obteniendo instancia de EntityManager
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Query query = em.createNamedQuery("Product.findAll");
            
            List<Product> products = query.getResultList();
            em.close();
            return products;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public Product getProductById(int id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Product product = em.find(Product.class, id);
            em.close();
            return product;
        } catch (Exception e) {
            em.close();
            return null;
        }
    }
    
    public int addProduct(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin(); // Iniciando transacción
            em.persist(product); //Guardando objeto en DB
            tran.commit(); //Confirmando transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    public int updateProduct(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tran = em.getTransaction();
        try {
            tran.begin(); // Iniciando transacción
            em.merge(product); //Actualizando objeto en DB
            tran.commit(); //Confirmando transacción
            em.close();
            return 1;
        } catch (Exception e) {
            em.close();
            return 0;
        }
    }
    
    public int deleteProduct(Product product) {
        EntityManager em = JpaUtil.getEntityManager();
        int deletedRows = 0;
        try {
            //Asegurándose de que la entidad existe
            product = em.find(Product.class, product.getId());
            if (product != null) {
                EntityTransaction tran = em.getTransaction();
                tran.begin();
                em.remove(product); //Eliminando objeto de la db
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
