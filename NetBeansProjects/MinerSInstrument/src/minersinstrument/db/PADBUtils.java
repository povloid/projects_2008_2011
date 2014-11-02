/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package minersinstrument.db;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import minersinstrument.ui.AErrorDialog;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author pacman
 */
public final class PADBUtils {

    private final static String buildSQLProcString(ADBProc aProc) {
        String procString = "{? = call " + aProc.getProcName() + "(";

        List<ADBProcParametr> parList = aProc.getParList();
        int parCount = parList.size();

        for (int i = 0; i < parCount; i++) {
            if (i < parCount - 1) {
                procString += "?,";
            } else {
                procString += "?";
            }
        }

        procString += ")}";
        System.out.println(procString);

        return procString;
    }

    private final static String buildSQLProcStringForVoid(ADBProc aProc) {
        String procString = "{ call " + aProc.getProcName() + "(";

        List<ADBProcParametr> parList = aProc.getParList();
        int parCount = parList.size();

        for (int i = 0; i < parCount; i++) {
            if (i < parCount - 1) {
                procString += "?,";
            } else {
                procString += "?";
            }
        }

        procString += ")}";
        System.out.println(procString);

        return procString;
    }

    public final static void executeVoidProcedure(PGPoolingDataSource source, ADBProc aProc) {
        Connection conn = null;

        try {

            conn = source.getConnection();

            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall(buildSQLProcStringForVoid(aProc));

            int index = 0;
            for (ADBProcParametr par : aProc.getParList()) {
                proc.setObject(++index, par.getValue(), par.getDbType());
            }

            proc.execute();

            proc.close();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } catch (Exception ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }
        }

    }

    public final static int getIntScalar(PGPoolingDataSource source, ADBProc aProc) {
        int result = 0;

        Connection conn = null;

        try {

            conn = source.getConnection();

            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall(buildSQLProcString(aProc));

            proc.registerOutParameter(1, Types.INTEGER);

            int index = 1;
            for (ADBProcParametr par : aProc.getParList()) {
                proc.setObject(++index, par.getValue(), par.getDbType());
            }

            proc.execute();

            result = proc.getInt(1);

            proc.close();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } catch (Exception ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }
            return result;
        }
    }

    public final static String getStringScalar(PGPoolingDataSource source, ADBProc aProc) {
        String result = null;

        Connection conn = null;

        try {

            conn = source.getConnection();

            conn.setAutoCommit(false);

            CallableStatement proc = conn.prepareCall(buildSQLProcString(aProc));

            proc.registerOutParameter(1, Types.VARCHAR);

            int index = 1;
            for (ADBProcParametr par : aProc.getParList()) {
                proc.setObject(++index, par.getValue(), par.getDbType());
            }

            proc.execute();

            result = proc.getString(1);

            proc.close();

            conn.commit();

        } catch (SQLException ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } catch (Exception ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }
            return result;
        }
    }



    /**
     * Результирующий класс
     */
    public final static class PADBResult {

        private ResultSet rs;
        private Connection conn;
        private CallableStatement proc;

        public PADBResult(ResultSet rs, Connection conn, CallableStatement proc) {
            this.rs = rs;
            this.conn = conn;
            this.proc = proc;
        }

        public Connection getConn() {
            return conn;
        }

        public CallableStatement getProc() {
            return proc;
        }

        public ResultSet getRs() {
            return rs;
        }

        public void close() {
            try {
                if (rs != null) {
                    rs.close();
                }

                if (proc != null) {
                    proc.close();
                }

                if (conn != null) {
                    conn.close();
                }

            } catch (SQLException ex) {
                Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public final static PADBResult getResultSet(PGPoolingDataSource source, ADBProc aProc) {

        ResultSet rs = null;
        CallableStatement proc = null;
        Connection conn = null;

        try {

            conn = source.getConnection();
            conn.setAutoCommit(false);

            proc = conn.prepareCall(buildSQLProcString(aProc));

            proc.registerOutParameter(1, Types.OTHER);

            int index = 1;
            for (ADBProcParametr par : aProc.getParList()) {
                proc.setObject(++index, par.getValue(), par.getDbType());
            }

            proc.execute();
            rs = (ResultSet) proc.getObject(1);

        } catch (SQLException ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } catch (Exception ex) {
            Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex);

            AErrorDialog d = new AErrorDialog("Ошибка...", ex.getMessage());
            d.setVisible(true);
            d.dispose();

        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException ex2) {
                    Logger.getLogger(PADBUtils.class.getName()).log(Level.SEVERE, null, ex2);

                    AErrorDialog d = new AErrorDialog("Ошибка SQL уровня...", ex2.getMessage());
                    d.setVisible(true);
                    d.dispose();

                }
            }
            return new PADBResult(rs, conn, proc);
        }
    }
}
