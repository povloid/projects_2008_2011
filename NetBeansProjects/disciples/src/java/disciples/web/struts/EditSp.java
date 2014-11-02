/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import disciples.model.Sp;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditSp extends ABaseEdit {

    private Sp sp;
    private Disciple disciple;

    public Sp getSp() {
        return sp;
    }

    public void setSp(Sp sp) {
        this.sp = sp;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.sp;
    }

    public Disciple getDisciple() {
        return disciple;
    }

    public void setDisciple(Disciple disciple) {
        this.disciple = disciple;
    }


    @Override
    protected boolean validateForm() throws Exception {
        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        this.result = "add";
        DAOImpl.addSp(sp);
        createObject();
    }

    @Override
    protected void editDAO() throws Exception {
        this.result = "edit";
        DAOImpl.editSp(sp);
        createObject();
    }

    @Override
    protected void delDAO() throws Exception {
        this.result = "del";
        DAOImpl.delSp(sp);
        createObject();
    }

    @Override
    protected void createObject() throws Exception {
        this.sp = new Sp();
        parseAndCreateDiscipleAndSdep();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.sp = DAOImpl.loadSp(id);
        parseAndCreateDiscipleAndSdep();
    }


    private void parseAndCreateDiscipleAndSdep() throws Exception {

        int discipleId = 0;
        if(request.getParameterMap().containsKey("disciple_id")){
             discipleId = Integer.parseInt(request.getParameter("disciple_id"));
        } else if(request.getParameterMap().containsKey("sp.discipleId")){
             discipleId = Integer.parseInt(request.getParameter("sp.discipleId"));
        }
        
        this.disciple = DAOImpl.loadDisciple(discipleId);
        this.sp.setDiscipleId(discipleId);
    }

}
