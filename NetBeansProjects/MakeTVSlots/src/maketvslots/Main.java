/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package maketvslots;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author kopychenko
 */
public class Main {

    static class Row {

        // "Первый канал "Евразия" ;15.12.2009;6:00;"ОБРУЧАЛЬНОЕ КОЛЬЦО". Многосерийный фильм (каз.);7;2;0
        private String kanal;
        private String date;
        private int hh;
        private int mm;
        private String peredacha;
        private int vsego;
        private int r15_19;
        private int r20_29;

        public Row(String[] strT) {

            this.kanal = strT[0];
            this.date = strT[1];

            String[] time = strT[2].split(":");
            this.hh = Integer.parseInt(time[0]);
            this.mm = Integer.parseInt(time[1]);

            this.peredacha = strT[3];
            this.vsego = Integer.parseInt(strT[4]);
            this.r15_19 = Integer.parseInt(strT[5]);
            this.r20_29 = Integer.parseInt(strT[6]);

        }

        public String getDate() {
            return date;
        }

        public int getHh() {
            return hh;
        }

        public String getKanal() {
            return kanal;
        }

        public int getMm() {
            return mm;
        }

        public String getPeredacha() {
            return peredacha;
        }

        public int getR15_19() {
            return r15_19;
        }

        public int getR20_29() {
            return r20_29;
        }

        public int getVsego() {
            return vsego;
        }

        @Override
        public String toString() {


            return hh + ":" + mm + ";" + kanal + ";" + date + ";" + hh + ":" + mm + ";" + peredacha + ";" + vsego + ";" + r15_19 + ";" + r20_29;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException, IOException {

        System.out.println();

        if(args.length == 0) {
            System.out.println("Не задан фаил...");
            return;
        }

        System.out.println("Задан фаил: " + args[0]);

        List<Row> rows = new ArrayList<Row>();

        FileInputStream fis = new FileInputStream(args[0]);
        InputStreamReader isr = new InputStreamReader(fis);
        BufferedReader br = new BufferedReader(isr);

        BufferedWriter bw = new BufferedWriter(new FileWriter(args[0] + ".out"));



        while (br.ready()) {
            rows.add(new Row(br.readLine().split(";")));
        }


        int hh = -1;

        int vsego = -1;
        int r15_19 = -1;
        int r20_29 = -1;

        Row lastRow = null;

        boolean print = false;
        //boolean firstRow = true;
        for (Row row : rows) {


            if (row.getHh() != hh) {

                if (print) {
                    System.out.println(hh + ":00;;;;;" + vsego + ";" + r15_19 + ";" + r20_29);
                    bw.write(hh + ":00;;;;;" + vsego + ";" + r15_19 + ";" + r20_29+ "\n");
                }

                print = true;

                hh = row.getHh();


                if (row.getMm() >= 15 && lastRow!=null) {

                    vsego = lastRow.getVsego();
                    r15_19 = lastRow.getR15_19();
                    r20_29 = lastRow.getR20_29();

                    //firstRow = false;
                } else {

                    vsego = 0;
                    r15_19 = 0;
                    r20_29 = 0;
                }
            }



            System.out.println(row);
            bw.write(row + "\n");

            lastRow = row;

            vsego += row.getVsego();
            r15_19 += row.getR15_19();
            r20_29 += row.getR20_29();
        }

        System.out.println((hh) + ":40;;;;;" + vsego + ";" + r15_19 + ";" + r20_29);
        bw.write((hh) + ":40;;;;;" + vsego + ";" + r15_19 + ";" + r20_29 + "\n");

        br.close();
        isr.close();
        fis.close();

        bw.close();
    }
}
