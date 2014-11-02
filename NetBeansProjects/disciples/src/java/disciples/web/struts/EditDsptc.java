/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import disciples.model.Dsptc;
import disciples.model.Sdep;
import java.util.List;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;

/**
 *
 * @author dev_sport
 */
public class EditDsptc extends ABaseEdit{

    public List getCategories() throws Exception {
        return DAOImpl.getRBCategories(this.dsptc.getSdepId());
    }

    private Dsptc dsptc;

    

    public Dsptc getDsptc() {
        return dsptc;
    }

    public void setDsptc(Dsptc dsptc) {
        this.dsptc = dsptc;
    }

    @Override
    protected IIdObject getIDObj() {
        return this.dsptc;
    }

    private Sdep sdep;
    private Disciple disciple;

    public Disciple getDisciple() {
        return disciple;
    }

    public void setDisciple(Disciple disciple) {
        this.disciple = disciple;
    }

    public Sdep getSdep() {
        return sdep;
    }

    public void setSdep(Sdep sdep) {
        this.sdep = sdep;
    }

    @Override
    protected boolean validateForm() throws Exception {
        return true;
    }

    @Override
    protected void addDAO() throws Exception {

        this.result = "add";

        DAOImpl.addDsptc(dsptc);

        createNewDsptc();
    }

    @Override
    protected void editDAO() throws Exception {
        this.result = "edit";

        DAOImpl.editDsptc(dsptc);

        createNewDsptc();
    }

    @Override
    protected void delDAO() throws Exception {
        this.result = "del";

        DAOImpl.delDsptc(dsptc);

        createNewDsptc();
    }

    @Override
    protected void createObject() throws Exception {
        createNewDsptc();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        this.dsptc = DAOImpl.loadDsptc(id);
        parseAndCreateDiscipleAndSdep();
    }


    private void createNewDsptc() throws Exception {
        this.dsptc = new Dsptc();
        parseAndCreateDiscipleAndSdep();
    }

    private void parseAndCreateDiscipleAndSdep() throws Exception {
        int discipleId = Integer.parseInt(request.getParameter("dsptc.discipleId"));
        int sdepId = Integer.parseInt(request.getParameter("dsptc.sdepId"));

        this.disciple = DAOImpl.loadDisciple(discipleId);

        if(sdepId > 0){
            this.sdep = DAOImpl.loadSdep(sdepId);
        } else {
            this.sdep = new Sdep(0, "Общая спортивная подготовка", "");
        }

        this.dsptc.setDiscipleId(discipleId);
        this.dsptc.setSdepId(sdepId);
    }

}
