/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package tt.db.settings.nodes;



import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.tree.DefaultMutableTreeNode;
import minersinstrument.cryptographic.DESedeEncrypter;
import minersinstrument.cryptographic.DESedeEncrypter.ALGORITM;
import tt.db.settings.nodes.db.DB;

/**
 * Узел, описывающий базу
 * @author pkopychenko
 */
public class BaseNodeRow extends DefaultMutableTreeNode {


    private String caption;
    private String dbName;
    private String dbUserName;
    //private char[] dbUserPassw;
    private String crDbUserPassw;


    public BaseNodeRow() {
    }

    public BaseNodeRow(ServerNodeRow serverNodeRow, String caption, String dbName,
            String dbUserName, char[] dbUserPassw, boolean createRealDB) throws Exception {

        this.caption = caption;
        this.dbName = dbName;
        this.dbUserName = dbUserName;
        setDbUserPassw(dbUserPassw);


        // Создание базы данных
        if (createRealDB) {
            DB.createDB(serverNodeRow.getSuperuser(),
                    new String(serverNodeRow.getPassword()),
                    serverNodeRow.getHost(), serverNodeRow.getPort(),
                    dbName, dbUserName, new String(dbUserPassw));
        }

    }

    @Override
    public String toString() {
        return caption;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public char[] getDbUserPassw() {

        if(ServersList.getKeyWord()==null) return null;


        char[] out = null;

        System.out.println(crDbUserPassw.toCharArray());

        try {

            DESedeEncrypter encrypter = new DESedeEncrypter(ServersList.getKeyWord(), ALGORITM.SHA1);
            
            out = encrypter.decrypt_c(crDbUserPassw.toCharArray());


        } catch (Exception ex) {
            Logger.getLogger(BaseNodeRow.class.getName()).log(Level.SEVERE, null, ex);
        }

        return out;
    }

    public void setDbUserPassw(char[] dbUserPassw) {

        if(ServersList.getKeyWord()==null) return;

        try {
             DESedeEncrypter encrypter = new DESedeEncrypter(ServersList.getKeyWord(), ALGORITM.SHA1);

            this.crDbUserPassw = encrypter.encrypt(dbUserPassw);

            System.out.println(crDbUserPassw.toCharArray());

        } catch (Exception ex) {
            Logger.getLogger(BaseNodeRow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCrDbUserPassw() {
        return crDbUserPassw;
    }

    public void setCrDbUserPassw(String crDbUserPassw) {
        this.crDbUserPassw = crDbUserPassw;
    }

    public String getDbUserName() {
        return dbUserName;
    }

    public void setDbUserName(String dbUserName) {
        this.dbUserName = dbUserName;
    }


    /**
     * Экспортировать данный в фаил
     * @param file
     * @throws Exception
     */
    public void backupData(File file) throws Exception{
        ServerNodeRow serverNodeRow = (ServerNodeRow) getParent();

        DB.backupData(dbUserName,
                new String(getDbUserPassw())+"",
                serverNodeRow.getHost(),
                serverNodeRow.getPort() ,
                dbName,
                file);
    }

//md5ecdd58b59ec7ccfa119fd6c00a5f5dc5 
//md55c90aea55653dcae179212d201c6ed42

    /**
     * Импортировать данные из файла
     * @param file
     * @throws Exception
     */
    public void restoreData(File file) throws Exception{

        ServerNodeRow serverNodeRow = (ServerNodeRow) getParent();

        DB.restoreData(dbUserName,
                new String(getDbUserPassw()),
                serverNodeRow.getHost(),
                serverNodeRow.getPort() ,
                dbName,
                file);
    }


    public static void main(String[] s) throws Exception {

        String s1 = "wjhfoiwjefoijwe";

        BaseNodeRow b = new BaseNodeRow();

        b.setDbUserPassw(s1.toCharArray());

        System.out.println(b.getDbUserPassw());

    }


}