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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import kpg.db.sql.rb.BasicDB;
import kpg.db.sql.rb.BasicDB.Field;
import kpg.db.sql.rb.BasicDBForID;
import org.postgresql.ds.PGPoolingDataSource;

/**
 *
 * @author kopychenko
 */
public class ImportAnswersData {

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
     * Получить id дескриптора
     * @param keyName
     * @return
     * @throws Exception
     */
    private static int getDescriptorIdForKeyName(String keyName) throws Exception {
        int descriptorId = 0;

        // Для вопросов
        List<Field> fieldsList0 = new ArrayList<Field>();
        fieldsList0.add(new Field("key_name", Types.VARCHAR));
        fieldsList0.add(new Field("description", Types.VARCHAR));
        BasicDBForID dBasic = new BasicDBForID(source, "descriptors", fieldsList0);

        BasicDB.Result result = dBasic.getRows("key_name='" + keyName + "'");

        ResultSet rs = result.getRs();
        if (rs.next()) {
            descriptorId = rs.getInt("id");
        }

        result.closeAll();

        return descriptorId;
    }

    /**
     * Создать или получить блок ответов
     * @param descriptorId
     * @param keyName
     * @return
     * @throws Exception
     */
    private static int createOrGetBlock(int descriptorId, String keyName) throws Exception {
        int blockId = 0;

        // Для вопросов
        List<Field> fieldsList0 = new ArrayList<Field>();
        fieldsList0.add(new Field("key_name", Types.VARCHAR));
        fieldsList0.add(new Field("description", Types.VARCHAR));
        fieldsList0.add(new Field("descriptor_id", Types.INTEGER));
        BasicDBForID dBasic = new BasicDBForID(source, "answers_blocks", fieldsList0);

        BasicDB.Result result = dBasic.getRows("key_name='" + keyName +
                "' AND descriptor_id=" + descriptorId);

        ResultSet rs = result.getRs();
        if (rs.next()) {
            blockId = rs.getInt("id");
        } else {

            Map<String, Object> parsMap = new HashMap<String, Object>();
            parsMap.put("key_name", keyName);
            parsMap.put("description", "Описание ");
            parsMap.put("descriptor_id", descriptorId);

            blockId = dBasic.insertWithReturningId(parsMap);

        }

        result.closeAll();

        return blockId;
    }
    private static BasicDBForID qBasic = null;
    private static BasicDBForID avBasic = null;
    private static BasicDBForID aBasic = null;

    static {
        // Для вопросов
        List<Field> fieldsList0 = new ArrayList<Field>();
        fieldsList0.add(new Field("nomer", Types.INTEGER));
        fieldsList0.add(new Field("qtext", Types.VARCHAR));
        fieldsList0.add(new Field("descriptor_id", Types.INTEGER));
        qBasic = new BasicDBForID(source, "questions", fieldsList0);

        // Для вариантов ответов
        List<Field> fieldsList1 = new ArrayList<Field>();
        fieldsList1.add(new Field("atext", Types.VARCHAR));
        fieldsList1.add(new Field("questions_id", Types.INTEGER));
        avBasic = new BasicDBForID(source, "answers_variants", fieldsList1);

        // Для ответов
        List<Field> fieldsList2 = new ArrayList<Field>();
        fieldsList2.add(new Field("question_id", Types.INTEGER));
        fieldsList2.add(new Field("answer_wariant_id", Types.INTEGER));
        fieldsList2.add(new Field("nomer", Types.INTEGER));
        fieldsList2.add(new Field("answer_block_id", Types.INTEGER));
        fieldsList2.add(new Field("checked", Types.BOOLEAN));
        fieldsList2.add(new Field("nomer_uk", Types.INTEGER));
        aBasic = new BasicDBForID(source, "answers", fieldsList2);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException, SQLException, Exception {



        String descriptorName = "Анкета TV 1";
        String blockName = "Все анкеты";

        int descriptorId = getDescriptorIdForKeyName(descriptorName);
        System.out.println("descriptorId=" + descriptorId);
        int blockId = createOrGetBlock(descriptorId, blockName);
        System.out.println("blockId=" + blockId);


        FileInputStream fis = new FileInputStream("ankatv.dan");
        InputStreamReader isr = new InputStreamReader(fis, "Cp866");
        BufferedReader br = new BufferedReader(isr);

        Map<String, Object> parsMap = new HashMap<String, Object>();

        int nomerUk = 0;
        while (br.ready()) {

            String tLine = br.readLine().trim();
            if (tLine.length() == 0) {
                continue;
            }
            String[] line = tLine.split(" ");
            int aNomer = Integer.parseInt(line[0]);
            char[] bytes = line[1].toCharArray();

            BasicDB.Result qresult = qBasic.getRows("descriptor_id=" + descriptorId);
            ResultSet rs = qresult.getRs();

            int offset = 0;
            ++nomerUk;
            while (rs.next()) {

                int qId = rs.getInt("id");
                int qNomer = rs.getInt("nomer");


                BasicDB.Result avresult = avBasic.getRows("questions_id=" + qId + " ORDER BY id");
                ResultSet avrs = avresult.getRs();


                while (avrs.next()) {
                    int avId = avrs.getInt("id");


                    parsMap.clear();
                    parsMap.put("question_id", qId);
                    parsMap.put("answer_wariant_id", avId);
                    parsMap.put("nomer", line[0]);
                    parsMap.put("answer_block_id", blockId);
                    parsMap.put("nomer_uk", nomerUk);

                    char c = bytes[offset];
                    boolean checked = false;

                    if (c == '1') {
                        checked = true;
                    }
                    parsMap.put("checked", checked);

                    aBasic.insert(parsMap);

                    System.out.println(offset);
                    ++offset;

                }

                avresult.closeAll();
            }

            qresult.closeAll();

        }

        br.close();
        isr.close();
        fis.close();

    }
}
