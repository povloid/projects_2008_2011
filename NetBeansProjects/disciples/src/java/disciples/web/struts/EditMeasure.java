/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Measure;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 * Редактирование меры
 * @author dev_sport
 */
public class EditMeasure extends ABaseEdit{

    private Measure measure;

    public Measure getMeasure() {
        return measure;
    }

    public void setMeasure(Measure measure) {
        this.measure = measure;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.measure;
    }

    @Override
    protected boolean validateForm() throws Exception {
        if (measure.getKeyName() == null || measure.getKeyName().length() == 0) {
            addFieldError("measure.keyName", "Незадано наименование");
            return false;
        }
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        DAOImpl.addMeasure(measure);
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.editMeasure(measure);
    }

    @Override
    protected void delDAO() throws Exception {
        DAOImpl.delMeasure(measure);
    }

    @Override
    protected void createObject() throws Exception {
        this.measure = new Measure();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.measure = DAOImpl.loadMeasure(id);
    }

}
