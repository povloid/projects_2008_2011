/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package disciples.web.struts;

import disciples.dao.DAOImpl;
import disciples.model.Disciple;
import java.io.File;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletContext;
import kpg.web.struts.base.edit.ABaseEdit;
import kpg.web.struts.base.edit.IIdObject;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author dev_sport
 */
public class EditDisciple extends ABaseEdit {
    
    
    
    
    
    
    // Фотография
    private File file;
    private String contentType;
    private String filename;
    
    
    public void setUpload(File file) {
        this.file = file;
    }

    public void setUploadContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setUploadFileName(String filename) {
        this.filename = filename;
    }
    
    
    public String fotoUpload() throws Exception{
        System.out.println("Фото");
        return INPUT;
    }
    

    

    public List getSdeps() throws Exception {
        return DAOImpl.getRBSdeps();
    }

    public List getSclasses() throws Exception {
        return DAOImpl.getRBSclasses();
    }
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

        if (disciple.getFname() == null || disciple.getFname().length() == 0) {
            addFieldError("disciple.fname", "Незадана фамилия");
            return false;
        }

        if (disciple.getIname() == null || disciple.getIname().length() == 0) {
            addFieldError("disciple.iname", "Незадано имя");
            return false;
        }

        if (disciple.getBdate() != null && disciple.getEdate() != null
                && disciple.getBdate().getTime() > disciple.getEdate().getTime()) {
            addFieldError("disciple.bdate", "Дата начала неможет быть позже даты конца");
            return false;
        }


        return true;
    }

    @Override
    protected void addDAO() throws Exception {
        saveFoto();
        DAOImpl.addDisciple(disciple);
    }

    @Override
    protected void editDAO() throws Exception {
        saveFoto();
        DAOImpl.editDisciple(disciple);
    }

    @Override
    protected void delDAO() throws Exception {
        DAOImpl.delDisciple(disciple);
    }

    @Override
    protected void createObject() throws Exception {
        disciple = new Disciple();
    }

    @Override
    protected void loadOdject(int id) throws Exception {
        disciple = DAOImpl.loadDisciple(id);
    }
    
    
    /**
     * Сохранить фотографию
     * @throws Exception 
     */
    private void saveFoto() throws Exception{
        if(file != null){
            String p = servletContext.getInitParameter("FOTO_BASE_DIR");
            
            String newFileName = (new Date()).getTime() + ".foto";
            
            FileUtils.copyFile(file, new File(p + "/" + newFileName));
            
            disciple.setFoto(newFileName);
            disciple.setFotoContentType(contentType);
        } else {
            Disciple d = DAOImpl.loadDisciple(disciple.getId());
            disciple.setFoto(d.getFoto());
            disciple.setFotoContentType(d.getFotoContentType());
            
        }
    }
    
}
