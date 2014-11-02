/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ips.prbase.model.attributes.interfaces;

import java.io.File;

/**
 *
 * @author kopychenko
 */
public interface IOidAttribute extends IBasicAttribute{

    void setFile(File file);
    File getFile();

    String getFileName();
    void setFileName(String fileName);

    String getFileFileName();
    String getFileContentType();
    void setFileFileName(String fileFileName);
    void setFileContentType(String fileContentType);

    int getOid();
    void setOid(int id);

    
}
