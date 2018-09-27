/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.bootsfaces.utils.FacesMessages;
import sv.edu.udb.www.entitites.Category;
import sv.edu.udb.www.entitites.Product;
import sv.edu.udb.www.models.CategoryModel;
import sv.edu.udb.www.models.ProductModel;

/**
 *
 * @author kevin
 */
@ManagedBean
@SessionScoped
public class ProductBean implements Serializable {

    private Product product;
    
    /**
     * Creates a new instance of ProductBean
     */
    public ProductBean() {
        product = new Product();
        product.setState(true);
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
    
    public void resetProduct() {
        product = new Product();
        product.setState(true);
    }
    
    public List<Product> getProducts() {
        ProductModel model = new ProductModel();
        return model.getProducts();
    }
    
    public List<Category> getActiveCategories() {
        CategoryModel model = new CategoryModel();
        return model.getCategories(CategoryModel.ACTIVE);
    }
    
    public String addProduct() {
        ProductModel model = new ProductModel();
        if (model.addProduct(product) != 1) {
            FacesMessages.error("productForm:addProduct", "", "Ocurrió un error al añadir el producto");
            return null;
        }
        else {
            FacesMessages.info("productForm:addProduct", "", "Producto añadido exitosamente");
            resetProduct();
            return "products?faces-redirect-true";
        }
        
    }
    
    public String updateProduct() {
        ProductModel model = new ProductModel();
        
        if (model.getProductById(product.getId()) == null) {
            FacesMessages.error("productForm:updateProduct", "", "El id especificado no existe");
            return null;
        }
        if (model.updateProduct(product) != 1) {
            FacesMessages.error("productForm:updateProduct", "", "Ocurrió un error al modificar el producto");
            return null;
        }
        else {
            FacesMessages.info("productForm:updateProduct", "", "Producto modificado exitosamente");
            resetProduct();
            return "products?faces-redirect-true";
        }
    }
    
    public String deleteProduct(Product productToDelete) {
        ProductModel model = new ProductModel();
        if(model.deleteProduct(productToDelete) > 0) {
            FacesMessages.info("", "Producto eliminado exitosamente");
        }
        else {
            FacesMessages.error("", "Ocurrió un error al eliminar el producto");
        }
        return "products?faces-redirect-true";
    }
}
