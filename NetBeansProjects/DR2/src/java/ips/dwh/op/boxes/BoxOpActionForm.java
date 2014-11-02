/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.op.boxes;

import ips.dwh.config.Config;
import ips.dwh.datawork.DAOImpl;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts.upload.FormFile;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author kopychenko
 */
public class BoxOpActionForm extends org.apache.struts.action.ActionForm {

    private static String DATE_FORMAT = "dd.MM.yyyy";
    private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
    private String op;
    private int id;
    private Integer sectionId;
    private String keyName;
    private String description;
    private String keyWords;
    private String keyFios;
    private String repFilePath;
    private String fileName;
    private int accessLevelId;
    private String kDate;
    private String cdate;
    private String udate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccessLevelId() {
        return accessLevelId;
    }

    public void setAccessLevelId(int accessLevelId) {
        this.accessLevelId = accessLevelId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getKeyFios() {
        return keyFios;
    }

    public void setKeyFios(String keyFios) {
        this.keyFios = keyFios;
    }

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public String getRepFilePath() {
        return repFilePath;
    }

    public void setRepFilePath(String repFilePath) {
        System.out.println(">>SET>>>" + repFilePath);
        this.repFilePath = repFilePath;
    }

    public String getkDate() {
        return kDate;
    }

    public void setkDate(String kDate) {
        this.kDate = kDate;
    }

    public Integer getSectionId() {
        return sectionId;
    }

    public void setSectionId(Integer sectionId) {
        if (sectionId.intValue() != 0) {
            this.sectionId = sectionId;
        } else {
            this.sectionId = null;
        }
    }
    protected FormFile inputFile;

    public FormFile getInputFile() {
        return inputFile;
    }

    public void setInputFile(FormFile inputFile) {
        this.inputFile = inputFile;
    }

    public String getCdate() {
        return cdate;
    }

    public void setCdate(String cdate) {
        this.cdate = cdate;
    }

    public String getUdate() {
        return udate;
    }

    public void setUdate(String udate) {
        this.udate = udate;
    }

    @Override
    public void reset(ActionMapping mapping, HttpServletRequest request) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        String ops = request.getParameter("op");
        System.out.println(">>>>>>>" + ops);

        if (ops != null &&
                (ops.equals("edit") || ops.equals("del"))) {

            Integer tId = Integer.parseInt(request.getParameter("id"));

            try {

                DAOImpl.Result result = DAOImpl.getBox(tId);
                ResultSet rs = result.getRs();

                if (rs.next()) {
                    this.id = rs.getInt("ID");
                    this.sectionId = (Integer) rs.getObject("section_id");
                    this.keyName = rs.getString("key_name");
                    this.description = rs.getString("description");
                    this.keyWords = rs.getString("key_words");
                    this.keyFios = rs.getString("key_fios");
                    this.fileName = rs.getString("file_name");
                    this.repFilePath = rs.getString("rep_file_path");
                    this.accessLevelId = rs.getInt("access_level_id");
                    this.kDate = sdf.format(rs.getDate("kdate"));
                    this.udate = sdf.format(rs.getDate("udate"));
                    this.cdate = sdf.format(rs.getDate("cdate"));

                }

                System.out.println(">>>>>>>>" + repFilePath);

                result.closeAll();
            } catch (Exception ex) {
                Logger.getLogger(BoxOpActionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        super.reset(mapping, request);
    }

    /**
     *
     */
    public BoxOpActionForm() {
        super();
    }

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param request The HTTP Request we are processing.
     * @return
     */
    @Override
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException ex) {
        }

        boolean go = true;

        if (getKeyName() == null || getKeyName().length() < 1) {
            errors.add("keyName", new ActionMessage("errors.keyname"));
            go = false;
        }

        if (op.equals("add") &&
                (inputFile.getFileName() == null ||
                inputFile.getFileName().length() == 0 ||
                inputFile.getFileSize() == 0)) {
            errors.add("inputFile", new ActionMessage("errors.inputFile"));
            go = false;
        }
        try {
            sdf.parse(kDate);
        } catch (ParseException ex) {
            errors.add("kDate", new ActionMessage("errors.keyDate"));
            go = false;
        }

        if (go) {

            try {

                if (op.equals("add")) {
                    addBox();
                } else if (op.equals("edit")) {
                    editBox();
                } else if (op.equals("del")) {
                    delBox();
                }

            } catch (Exception ex) {
                Logger.getLogger(BoxOpActionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }


        return errors;
    }

    /**
     * Добавить
     * @throws Exception
     */
    private void addBox() throws Exception {

        int newId = DAOImpl.addBoxBegin(
                sectionId,
                keyName,
                description,
                keyWords,
                keyFios,
                inputFile.getFileName(),
                accessLevelId, sdf.parse(kDate));


        writeInputFile(newId);

        DAOImpl.addBoxEnd(newId, repFilePath);

    }

    /**
     * редактировать
     * @throws Exception
     */
    private void editBox() throws Exception {

        if (inputFile.getFileName() != null &&
                inputFile.getFileName().length() > 0 &&
                inputFile.getFileSize() > 0) {
            writeInputFile(id);
            this.fileName = inputFile.getFileName();
        }

        DAOImpl.editBox(id,
                keyName,
                description,
                keyWords,
                keyFios,
                fileName,
                accessLevelId, sdf.parse(kDate));

    }

    /**
     * удалить
     * @throws Exception
     */
    private void delBox() throws Exception {

        DAOImpl.delBox(id);
        File dFile = new File(Config.getDocRoot() + "/" + repFilePath);
        dFile.delete();

    }

    /**
     * Получить новый каталог
     * @return
     */
    private final String getNewRepoFolderName() {
        SimpleDateFormat tdf = new SimpleDateFormat("yyyy/MM/dd/HH");
        Date nowDate = new Date();
        return tdf.format(nowDate);
    }

    /**
     * Записать фаил
     * @param id
     * @throws Exception
     */
    private final void writeInputFile(int id) throws Exception {
        InputStream fis = null;
        FileOutputStream fos = null;
        FormFile fFile = null;

        String docRoot = Config.getDocRoot();
        String NewRepoFolderName = "";
        if (op.equals("add")) {
            NewRepoFolderName = getNewRepoFolderName();

            boolean success = (new File(docRoot + "/" + NewRepoFolderName)).mkdirs();
            //if(!success){
            //    throw new Exception("Небыли созданы каталоги: " + NewRepoFolderName);
            //}
            repFilePath = NewRepoFolderName + "/" + id;
        } else if (op.equals("edit")) {

        }

        System.out.println(">>WF>>>" + repFilePath);

        try {
            fFile = getInputFile();
            System.out.println("Запись файла " + fFile.getFileName() + " - " + fFile.getFileSize());
            fis = fFile.getInputStream();
            // Now open the file
            File file = new File(Config.getDocRoot() + "/" + repFilePath);

            fos = new FileOutputStream(file);
            // Copy the data from the file to the large object
            byte[] buf = new byte[2048];
            int s;
            while ((s = fis.read(buf, 0, 2048)) > 0) {
                fos.write(buf, 0, s);
            }

        } catch (Exception ex) {
            Logger.getLogger(BoxOpActionForm.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (fFile != null) {
                    fFile.destroy();
                }
                if (fis != null) {
                    fis.close();
                }
                if (fos != null) {
                    fos.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(BoxOpActionForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
