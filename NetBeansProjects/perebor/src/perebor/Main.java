/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package perebor;

/**
 *
 * @author kopychenko
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String text = "i love nsvhjoeijv  oiejfo woi pascal  pascalina jcwoihj oew love jqweiofewo pascal ";
        String word1 = "love";
        String word2 = "pascal";

        String s = replace(text, word2);
        s = replace(s, word1);
        System.out.println(s);

        System.out.println(text.replaceAll(" love[\\W?|\\$?]", " \"love\" ").replaceAll(" pascal[\\W?|\\$?]", " \"pascal\" "));

    }

    public static String replace(String s, String so) {
        String outStr = "";
        for (int i = 0; i < s.length(); ++i) {
            if (i < s.length() - so.length() && s.substring(i, so.length() + i).equals(so)) {
                //System.out.println(s.substring(i, so.length() + i));
                outStr = outStr + "\"" + s.substring(i, so.length() + i) + "\" ";
                i += so.length();
            } else {
                outStr = outStr + s.charAt(i);
                System.out.println("[" + i + "] " + outStr);
            }
        }
        return outStr;
    }
}
