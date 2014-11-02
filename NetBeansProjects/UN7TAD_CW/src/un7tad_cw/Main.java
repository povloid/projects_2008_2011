/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package un7tad_cw;


import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;

/**
 *
 * @author kopychenko
 */
public class Main {

    static float frequency = 48000;
    static byte[] buf;
    static AudioFormat af;
    static SourceDataLine sdl;

    final static int toneF = 800;

    final static int e = 100;
    final static int t = 300;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws LineUnavailableException, InterruptedException {

        buf = new byte[1];
        af = new AudioFormat(frequency, 8, 1, true, false);

        sdl = AudioSystem.getSourceDataLine(af);
        sdl = AudioSystem.getSourceDataLine(af);
        sdl.open(af);
        sdl.start();

        for (int i = 0; i < 1000; i++) {
            generateTone(toneF, e, 10, e);
            generateTone(toneF, t, 10, e);
            generateTone(toneF, e, 10, e);
            generateTone(toneF, e, 10, e);

            generateTone(toneF, 500, 0, e);
        }

        sdl.drain();
        sdl.stop();
        sdl.close();




    }

//    /** Generates a tone.
//    @param hz Base frequency (neglecting harmonic) of the tone in cycles per second
//    @param msecs The number of milliseconds to play the tone.
//    @param volume Volume, form 0 (mute) to 100 (max).
//    @param addHarmonic Whether to add an harmonic, one octave up. */
//    public static void generateTone(int hz, int msecs, int volume, boolean addHarmonic)
//            throws LineUnavailableException {
//
//
//        byte[] buf;
//        AudioFormat af;
//        if (addHarmonic) {
//            buf = new byte[2];
//            af = new AudioFormat(frequency, 8, 2, true, false);
//        } else {
//            buf = new byte[1];
//            af = new AudioFormat(frequency, 8, 1, true, false);
//        }
//        SourceDataLine sdl = AudioSystem.getSourceDataLine(af);
//        sdl = AudioSystem.getSourceDataLine(af);
//        sdl.open(af);
//        sdl.start();
//        for (int i = 0; i < msecs * frequency / 1000; i++) {
//            double angle = i / (frequency / hz) * 2.0 * Math.PI;
//            buf[0] = (byte) (Math.sin(angle) * volume);
//
//            if (addHarmonic) {
//                double angle2 = (i) / (frequency / hz) * 2.0 * Math.PI;
//                buf[1] = (byte) (Math.sin(2 * angle2) * volume * 0.6);
//                sdl.write(buf, 0, 2);
//            } else {
//                sdl.write(buf, 0, 1);
//            }
//        }
//        sdl.drain();
//        sdl.stop();
//        sdl.close();
//    }
    /** Generates a tone.
    @param hz Base frequency (neglecting harmonic) of the tone in cycles per second
    @param msecs The number of milliseconds to play the tone.
    @param volume Volume, form 0 (mute) to 100 (max).
    @param addHarmonic Whether to add an harmonic, one octave up. */
    public static void generateTone(int hz, int msecs, int volume, int afterPause)
            throws LineUnavailableException {




        float v = 0;
        float slonge = msecs * frequency / 1000;

        for (int i = 0; i < slonge; i++) {
            double angle = i / (frequency / hz) * 2.0 * Math.PI;

            if (v < volume && (slonge - volume * 10) > i) {
                v += 0.1;
            } else if ((slonge - volume * 10) < i) {
                v -= 0.1;
            }

            buf[0] = (byte) (Math.sin(angle) * (v));

            sdl.write(buf, 0, 1);
        }

        buf[0] = 0;
        slonge = (afterPause * frequency) / 1000;
        for (int i = 0; i < slonge; i++) {
            sdl.write(buf, 0, 1);
        }


    }
}
