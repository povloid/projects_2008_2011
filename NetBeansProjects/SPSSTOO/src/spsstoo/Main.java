/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package spsstoo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kopychenko
 */
public class Main {

    /**
     * Ответ
     */
    static class A {

        public A(int id) {
            this.id = id;
        }
        int id;
        boolean y;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public boolean isY() {
            return y;
        }

        public void setY(boolean y) {
            this.y = y;
        }
    }

    /**
     * Вопрос
     */
    static class Q {

        int id;

        public Q(int id) {
            this.id = id;
        }
        List<A> answers = new ArrayList<A>();

        public List<A> getAnswers() {
            return answers;
        }

        public void setAnswers(List<A> answers) {
            this.answers = answers;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

    static class Q2 {

        int qId;
        int aId;
        int aV;

        public Q2(int qId) {
            this.qId = qId;
        }

        public int getaId() {
            return aId;
        }

        public void setaId(int aId) {
            this.aId = aId;
        }

        public int getqId() {
            return qId;
        }

        public void setqId(int qId) {
            this.qId = qId;
        }

        public int getaV() {
            return aV;
        }

        public void setaV(int aV) {
            this.aV = aV;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {



        List<Q> qs = new ArrayList<Q>();
        Map<Integer,Q> qsMap = new HashMap<Integer,Q>();

        {
            File file = new File("scheme.txt");

            InputStream is = new FileInputStream(file);

            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int qIndex = 0;
            int aIndex = 0;
            Q cQ = null;
            while (br.ready()) {

                String s = br.readLine();

                if (s.charAt(0) == 'В') {
                    System.out.println();

                    String[] ss = s.replace(".", "").split(" ");

                    int qMapIndex = Integer.parseInt(ss[3]);

                    cQ = new Q(qMapIndex);
                    aIndex = 0;

                    System.out.print(qIndex + "[" + ss[3]  + "] -");

                    qsMap.put(qMapIndex, cQ);
                    qs.add(cQ);
                    

                } else {
                    cQ.getAnswers().add(new A(++aIndex));

                    System.out.print(aIndex + ",");
                }
            }
        }




        File file = new File("result.csv");

        InputStream is = new FileInputStream(file);

        BufferedReader br = new BufferedReader(new InputStreamReader(is));

        String header[] = br.readLine().replaceAll("\"", "").replaceAll("q", "").split(";");

        List<Q2> q2List = new ArrayList<Q2>();
        int q2Id = 0;
        for (int i = 1; i < header.length; i++) {
            String index[] = header[i].replace('.', ';').split(";");

            int id = Integer.parseInt(index[0]);
            //int id = ++q2Id;

            Q2 currQ2 = new Q2(id);

            if (index.length > 1) {
                currQ2.setaId(Integer.parseInt(index[1]));
            }
            q2List.add(currQ2);
        }


        File outFile = new File("out.txt");
        OutputStream os = new FileOutputStream(outFile);
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(os));



        System.out.println();

        while (br.ready()) {

            clearQ2List(q2List);
            clearQList(qsMap);

            String avrsRow[] = br.readLine().split(";");

            int avrsRowIndex = 0;

            //System.out.println("START");

            for (Q2 q2 : q2List) {

                String v = avrsRow[++avrsRowIndex];

                //System.out.println("qID = " + q2.getqId() + " - index = " + avrsRowIndex + " - " + q2List.indexOf(q2));

                if (v.trim().length() > 0) {
                    q2.setaV(Integer.parseInt(v));
                }


                //System.out.println(">>>" + q2.getqId());
                setY(qsMap.get(q2.getqId()), q2.getaV());
            }
            printQList(avrsRow[0], qs);
            printQList(pw, avrsRow[0], qs);
        }


        pw.close();
        os.close();




    }

    static void clearQ2List(List<Q2> q2List) {
        for (Q2 q2 : q2List) {
            q2.setaV(0);
        }
    }

    static void printQList(String prefix, List<Q> list) {
        System.out.print(prefix + " ");
        for (Q q : list) {
            for (A a : q.getAnswers()) {
                if (a.isY()) {
                    System.out.print("1");
                } else {
                    System.out.print("0");
                }
            }
        }
        System.out.println();
    }

    static void printQList(PrintWriter pw, String prefix, List<Q> list) {
        pw.print(prefix + " ");
        for (Q q : list) {
            for (A a : q.getAnswers()) {
                if (a.isY()) {
                    pw.print("1");
                } else {
                    pw.print("0");
                }
            }
        }
        pw.println();
    }

    /**
     * Отчистка ответов
     * @param map
     */
    static void clearQList(Map<Integer,Q> map) {
        for (Q q : map.values()) {
            for (A a : q.getAnswers()) {
                a.setY(false);
            }
        }
    }

    /**
     * Установка ответа
     * @param q
     * @param yId
     */
    static void setY(Q q, int yId) {
        for (A a : q.getAnswers()) {
            if (a.getId() == yId) {
                a.setY(true);
            }
        }
    }
}
