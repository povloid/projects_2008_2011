/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditAvscore extends ABaseEdit {

    private Disciple disciple;



    public Disciple getDisciple() {
        return disciple;
    }

    public void setDisciple(Disciple disciple) {
        this.disciple = disciple;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.disciple;
    }

    @Override
    protected boolean validateForm() throws Exception {
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.setAvscore(disciple);
    }

    @Override
    protected void delDAO() throws Exception {
    }

    @Override
    protected void createObject() throws Exception {
        this.disciple = new Disciple();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.disciple = DAOImpl.loadDisciple(id);
    }

}
