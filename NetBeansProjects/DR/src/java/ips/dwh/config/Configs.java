package ips.dwh.config;

public final class Configs {
	
	// Константы
    private static final String DOC_REPOSITORY = "/opt/ips_docs_rep";
//    private static final String DOC_REPOSITORY = "/home/kopychenko/opt/ips_doc_rep";
//    private static final String DOC_REPOSITORY = "/home/pk/ips/opt/ips_doc_rep";
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
