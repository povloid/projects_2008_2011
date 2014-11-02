/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Category;
import java.util.List;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditCategory extends ABaseEdit{

    public List getSdeps() throws Exception {
        return DAOImpl.getRBSdeps();
    }

    public List getMeasures() throws Exception {
        return DAOImpl.getRBMeasures();
    }

    private Category category;

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.category;
    }

    @Override
    protected boolean validateForm() throws Exception {
        if (category.getKeyName() == null || category.getKeyName().length() == 0) {
            addFieldError("category.keyName", "Незадано наименование");
            return false;
        }

        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        DAOImpl.addСategory(category);
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.editСategory(category);
    }

    @Override
    protected void delDAO() throws Exception {
        DAOImpl.delСategory(category);
    }

    @Override
    protected void createObject() throws Exception {
        this.category = new Category();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.category = DAOImpl.loadСategory(id);
    }

}
