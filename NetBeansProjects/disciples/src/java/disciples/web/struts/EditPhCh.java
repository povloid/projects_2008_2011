/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import disciples.model.PhCh;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditPhCh extends ABaseEdit {

    private PhCh phCh;
    private Disciple disciple;

    public Disciple getDisciple() {
        return disciple;
    }

    public void setDisciple(Disciple disciple) {
        this.disciple = disciple;
    }


    
    @Override
    protected IIdObject getIDObj() {
        return this.phCh;
    }

    public PhCh getPhCh() {
        return phCh;
    }

    public void setPhCh(PhCh phCh) {
        this.phCh = phCh;
    }
    

    @Override
    protected boolean validateForm() throws Exception {
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        DAOImpl.addPhCh(phCh);

        this.result = "add";
        int dId = phCh.getDiscipleId();
        this.phCh = new PhCh();
        this.phCh.setDiscipleId(dId);
        this.disciple = DAOImpl.loadDisciple(this.phCh.getDiscipleId());
    }

    @Override
    protected void editDAO() throws Exception {
        DAOImpl.editPhCh(phCh);

        this.result = "edit";
        int dId = phCh.getDiscipleId();
        this.phCh = new PhCh();
        this.phCh.setDiscipleId(dId);
        this.disciple = DAOImpl.loadDisciple(this.phCh.getDiscipleId());
    }

    @Override
    protected void delDAO() throws Exception {

        DAOImpl.delPhCh(phCh);
        deleteF = false;

        this.result = "del";
        int dId = phCh.getDiscipleId();
        this.phCh = new PhCh();
        this.phCh.setDiscipleId(dId);
        this.disciple = DAOImpl.loadDisciple(this.phCh.getDiscipleId());
    }

    @Override
    protected void createObject() throws Exception {
        int discipleId = Integer.parseInt(request.getParameter("disciple_id"));
        this.phCh = new PhCh();
        this.phCh.setDiscipleId(discipleId);
        this.disciple = DAOImpl.loadDisciple(this.phCh.getDiscipleId());
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.phCh = DAOImpl.loadPhCh(id);
        this.disciple = DAOImpl.loadDisciple(this.phCh.getDiscipleId());
    }

}
