/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ar2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kopychenko
 */
public class Main {

    final static int BSIZE = 1024;

    /**
     * Класс пользователя
     */
    static class User {

        private String login;
        private float trLimit;
        private float trFact;

        public User(String login) {
            this.login = login;
        }

        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            this.login = login;
        }

        public float getTrFact() {
            return trFact;
        }

        public void setTrFact(float trFact) {
            this.trFact = trFact;
        }

        public float getTrLimit() {
            return trLimit;
        }

        public void setTrLimit(float trLimit) {
            this.trLimit = trLimit;
        }
    }

    static List<User> users;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException, IOException {
        File fileIN = new File("q.html");

        FileReader reader = new FileReader(fileIN);

        StringBuilder sb = new StringBuilder();

        char[] b = new char[BSIZE];
        int n;

        while ((n = reader.read(b)) > 0) {
            sb.append(b, 0, n);
        }


        users = new ArrayList<User>();

        //Разбираем и парсим



        {
            String patternLogin = "\\w*\\\\\\w*";
            Pattern regPatLogin = Pattern.compile(patternLogin);

            String patternTr = "\\d*,?\\d*.\\d\\d [KMG]B";
            Pattern regPatTr = Pattern.compile(patternTr);

            Matcher mLogin = regPatLogin.matcher(sb.toString());
            Matcher mTr = regPatTr.matcher(sb.toString());

            while (mLogin.find()) {
                String sUser = mLogin.group();
                //System.out.println(sUser);

                User user = new User(sUser);

                int i = 0;

                while (i < 2 && mTr.find()) {
                    String sTr = mTr.group();
                    float fTr = Float.parseFloat(sTr.replaceAll("[ [KMG]B|,]", ""));

                    char suffixB = sTr.charAt(sTr.length() - 2);
                    //System.out.println(sTr);


                    float k = 1;
                    if (suffixB == 'K') {
                        k = 1;
                    } else if (suffixB == 'M') {
                        k = 1000;
                    } else if (suffixB == 'G') {
                        k = 1000000;
                    }

                    if (i == 0) {
                        user.setTrFact(k * fTr);
                    } else if (i == 1) {
                        user.setTrLimit(k * fTr);
                    }

                    ++i;
                }

                users.add(user);
            }
        }

        reader.close();




        for(User user: users){
            System.out.println(user.getLogin() + ": " + user.getTrFact() + " - " + user.getTrLimit());
        }

    }
}
