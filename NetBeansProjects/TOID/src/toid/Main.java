/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package toid;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;
import org.postgresql.largeobject.LargeObject;
import org.postgresql.largeobject.LargeObjectManager;

/**
 *
 * @author kopychenko
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {

        // Устанавливаем соединение
        String url = "jdbc:postgresql:dbtus";
        Properties props = new Properties();
        props.setProperty("user", "tus");
        props.setProperty("password", "paradox");
        Connection con = DriverManager.getConnection(url, props);


        {
            // All LargeObject API calls must be within a transaction block
            con.setAutoCommit(false);

            // Get the Large Object Manager to perform operations with
            LargeObjectManager lobj = ((org.postgresql.PGConnection) con).getLargeObjectAPI();

            // Create a new large object
            long oid = lobj.createLO(LargeObjectManager.READ | LargeObjectManager.WRITE);

            // Open the large object for writing
            LargeObject obj = lobj.open(oid, LargeObjectManager.WRITE);

            // Now open the file
            File file = new File("1.avi");
            FileInputStream fis = new FileInputStream(file);

            // Copy the data from the file to the large object
            byte buf[] = new byte[2048];
            int s, tl = 0;
            while ((s = fis.read(buf, 0, 2048)) > 0) {
                obj.write(buf, 0, s);
                tl += s;
            }

            // Close the large object
            obj.close();

            // Now insert the row into imageslo
            PreparedStatement ps = con.prepareStatement("INSERT INTO xfiles (xfile) VALUES (?)");
            //ps.setString(1, file.getName());
            ps.setLong(1, oid);
            ps.executeUpdate();
            ps.close();
            fis.close();

            // Finally, commit the transaction.
            con.commit();
        }



//        {
//            // All LargeObject API calls must be within a transaction block
//            con.setAutoCommit(false);
//
//            // Get the Large Object Manager to perform operations with
//            LargeObjectManager lobj = ((org.postgresql.PGConnection) con).getLargeObjectAPI();
//
//            PreparedStatement ps = con.prepareStatement("SELECT xfile FROM xfiles WHERE id = 7");
//            //ps.setString(1, "tomcat2.gif");
//            ResultSet rs = ps.executeQuery();
//            while (rs.next()) {
//                // Open the large object for reading
//                long oid = rs.getLong(1);
//                LargeObject obj = lobj.open(oid, LargeObjectManager.READ);
//
//                // Read the data
//                ///byte buf[] = new byte[obj.size()];
//                ///obj.read(buf, 0, obj.size());
//                // Do something with the data read here
//
//                File file = new File("2.flv");
//                FileOutputStream fis = new FileOutputStream(file);
//
//
//                byte buf[] = new byte[2048];
//                int s, tl = 0;
//                while ((s = obj.read(buf, 0, 2048)) > 0) {
//                    fis.write(buf, 0, s);
//                    tl += s;
//                }
//
//                fis.close();
//
//                // Close the object
//                obj.close();
//            }
//            rs.close();
//            ps.close();
//
//            // Finally, commit the transaction.
//            con.commit();
//        }
    }
}
