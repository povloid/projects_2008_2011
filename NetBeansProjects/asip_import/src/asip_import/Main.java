/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package asip_import;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kpg.db.sql.rb.BasicDB.Field;
import kpg.db.sql.rb.BasicDBForID;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class Main {

    private static PGPoolingDataSource source = new PGPoolingDataSource();

    static {
        source.setDataSourceName("asip source");
        source.setServerName("localhost");
        source.setDatabaseName("asip");
        source.setUser("asip_master");
        source.setPassword("paradox");
        source.setMaxConnections(10);
        source.setPortNumber(5432);
    }

    public static Connection getConnection() throws SQLException {
        return source.getConnection();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, Exception {

        String descriptorKeyName = "Анкета TV №1";
        int descriptorId = 0;

        {

            // Для вопросов
            List<Field> fieldsList0 = new ArrayList<Field>();
            fieldsList0.add(new Field("key_name", Types.VARCHAR));
            fieldsList0.add(new Field("description", Types.VARCHAR));
            BasicDBForID dBasic = new BasicDBForID(source, "descriptors", fieldsList0);

            Map<String, Object> parsMap = new HashMap<String, Object>();
            parsMap.put("key_name", descriptorKeyName);
            parsMap.put("description", "Описание ");

            descriptorId = dBasic.insertWithReturningId(parsMap);

        }

        Connection conn = getConnection();

        FileInputStream fis = new FileInputStream("Ankatv.str");
        InputStreamReader isr = new InputStreamReader(fis, "Cp866");
        BufferedReader br = new BufferedReader(isr);


        conn.setAutoCommit(false);



        // Для вопросов
        List<Field> fieldsList1 = new ArrayList<Field>();
        fieldsList1.add(new Field("nomer", Types.INTEGER));
        fieldsList1.add(new Field("qtext", Types.VARCHAR));
        fieldsList1.add(new Field("descriptor_id", Types.INTEGER));
        BasicDBForID qBasic = new BasicDBForID(source, "questions", fieldsList1);

        //Для ответов
        List<Field> fieldsList2 = new ArrayList<Field>();
        fieldsList2.add(new Field("questions_id", Types.INTEGER));
        fieldsList2.add(new Field("atext", Types.VARCHAR));
        BasicDBForID aBasic = new BasicDBForID(source, "answers_variants", fieldsList2);

        int qId = 0;

        Map<String, Object> parsMap = new HashMap<String, Object>();

        while (br.ready()) {
            String line = br.readLine();

            if (line.charAt(0) == 'В') {
                System.out.println("Вопрос");

                int pointPos = line.indexOf(".");

                int qn = Integer.parseInt(line.substring(1, pointPos).replace('=', ' ').trim());
                System.out.println(qn);

                String qt = line.substring(pointPos + 1);

                System.out.println(qt);

                parsMap.clear();
                parsMap.put("nomer", qn);
                parsMap.put("qtext", qt);
                parsMap.put("descriptor_id", descriptorId);

                qId = qBasic.insertWithReturningId(parsMap);

            } else if (line.charAt(0) == 'О') {
                System.out.println("Ответ");

                int pointPos = line.indexOf(".");

                String at = line.substring(pointPos + 1);

                System.out.println(at);

                parsMap.clear();
                parsMap.put("questions_id", qId);
                parsMap.put("atext", at);

                aBasic.insert(parsMap);

            }
        }

        br.close();
        isr.close();
        fis.close();
    }
}
