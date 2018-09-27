/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sv.edu.udb.www.beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.bootsfaces.utils.FacesMessages;
import sv.edu.udb.www.entitites.Category;
import sv.edu.udb.www.models.CategoryModel;

/**
 *
 * @author kevin
 */
@ManagedBean
@SessionScoped
public class CategoryBean {

    private Category category;
    
    /**
     * Creates a new instance of CategoryBean
     */
    public CategoryBean() {
        category = new Category();
        category.setState(true);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    public void resetCategory() {
        category = new Category();
        category.setState(true);
    }
    
    public List<Category> getCategories() {
        CategoryModel model = new CategoryModel();
        return model.getCategories(CategoryModel.ALL);
    }
    
    public String addCategory() {
        CategoryModel model = new CategoryModel();
        if (model.addCategory(category) != 1) {
            FacesMessages.error("categoryForm:addCategory", "", "Ocurrió un error al añadir la categoría");
            return null;
        }
        else {
            FacesMessages.info("categoryForm:addCategory", "", "Categoría añadida exitosamente");
            resetCategory();
            return "categories?faces-redirect-true";
        }
        
    }
    
    public String updateCategory() {
        CategoryModel model = new CategoryModel();
        
        if (model.getCategoryById(category.getId()) == null) {
            FacesMessages.error("categoryForm:updateCategory", "", "El id especificado no existe");
            return null;
        }
        if (model.updateCategory(category) != 1) {
            FacesMessages.error("categoryForm:updateCategory", "", "Ocurrió un error al modificar la categoría");
            return null;
        }
        else {
            FacesMessages.info("categoryForm:updateCategory", "", "Categoría modificada exitosamente");
            resetCategory();
            return "categories?faces-redirect-true";
        }
    }
    
    public String deleteCategory(Category categoryToDelete) {
        CategoryModel model = new CategoryModel();
        if(model.deleteCategory(categoryToDelete) > 0) {
            FacesMessages.info("", "Categoría eliminada exitosamente");
        }
        else {
            FacesMessages.error("", "Ocurrió un error al eliminar la categoría");
        }
        return "categories?faces-redirect-true";
    }
}
