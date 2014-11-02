/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.SpTrang;
import java.util.List;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditSpTrang extends ABaseEdit {

    public List getSdeps() throws Exception {
        return DAOImpl.getRBSdeps();
    }

    private SpTrang spTrang;

    public SpTrang getSpTrang() {
        return spTrang;
    }

    public void setSpTrang(SpTrang spTrang) {
        this.spTrang = spTrang;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.spTrang;
    }

    @Override
    protected boolean validateForm() throws Exception {
        if (spTrang.getKeyName() == null || spTrang.getKeyName().length() == 0) {
            addFieldError("spTrang.keyName", "Незадано наименование");
            return false;
        }

        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        //DAOImpl.addSpTrang(spTrang);
    }

    @Override
    protected void editDAO() throws Exception {
       // DAOImpl.editSpTrang(spTrang);
    }

    @Override
    protected void delDAO() throws Exception {
       // DAOImpl.delSpTrang(spTrang);
    }

    @Override
    protected void createObject() throws Exception {
        this.spTrang = new SpTrang();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        //this.spTrang = DAOImpl.loadSpTrang(id);
    }

}
