/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.SClass;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditSclass extends ABaseEdit {

    private SClass sclass;

    public SClass getSclass() {
        return sclass;
    }

    public void setSclass(SClass sclass) {
        this.sclass = sclass;
    }



    @Override
    protected IIdObject getIDObj() {
        return this.sclass;
    }

    @Override
    protected boolean validateForm() throws Exception {
        if(sclass.getKeyName() == null || sclass.getKeyName().length() == 0) {
            addFieldError("sclass.keyName", "Незадано наименование");
            return false;
        }
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        DAOImpl.addSclass(sclass);
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.editSclass(sclass);
    }

    @Override
    protected void delDAO() throws Exception {
        DAOImpl.delSclass(sclass);
    }

    @Override
    protected void createObject() throws Exception {
        sclass = new SClass();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        sclass = DAOImpl.loadSclass(id);
    }

}
