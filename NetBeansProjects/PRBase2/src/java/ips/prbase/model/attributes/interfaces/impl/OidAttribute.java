/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces.impl;

import ips.prbase.model.attributes.interfaces.IOidAttribute;
import java.io.File;

/**
 *
 * @author kopychenko
 */
public class OidAttribute extends BasicAttribute implements IOidAttribute{

    private File file;

    public void setFile(File file) {
        this.file = file;
    }

    public File getFile() {
        return this.file;
    }

    private String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    String fileContentType;

    String fileFileName;

    public String getFileContentType() {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType) {
        this.fileContentType = fileContentType;
    }

    public String getFileFileName() {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName) {
        this.fileFileName = fileFileName;
    }

    int oid;

    public int getOid() {
        return this.oid;
    }

    public void setOid(int id) {
        this.oid = id;
    }

}
