/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Sdep;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditSdep extends ABaseEdit {

    private Sdep sdep;

    public Sdep getSdep() {
        return sdep;
    }

    public void setSdep(Sdep sdep) {
        this.sdep = sdep;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.sdep;
    }


    @Override
    protected boolean validateForm() throws Exception {
        if (sdep.getKeyName() == null || sdep.getKeyName().length() == 0) {
            addFieldError("sdep.keyName", "Незадано наименование");
            return false;
        }
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        DAOImpl.addSdep(sdep);
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.editSdep(sdep);
    }

    @Override
    protected void delDAO() throws Exception {
        DAOImpl.delSdep(sdep);
    }

    @Override
    protected void createObject() throws Exception {
        sdep = new Sdep();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        sdep = DAOImpl.loadSdep(id);
    }


}
