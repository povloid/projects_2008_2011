/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ips.dwh.config;

/**
 *
 * @author kopychenko
 */
public class Config {

    // Константы
    private static final String DOC_REPOSITORY = "/opt/ips_docs_rep";
    private static final String DOC_TMP = DOC_REPOSITORY + "/tmp";
    private static final String DOC_ROOT = DOC_REPOSITORY + "/root";

    public static final String getDocRepository() {
        return DOC_REPOSITORY;
    }

    public static final String getDocTmp() {
        return DOC_TMP;
    }

    public static final String getDocRoot() {
        return DOC_ROOT;
    }
}
