package ie.dcu.cngl.summarizer.Mary;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import net.sf.classifier4J.summariser.SimpleSummariser;
import net.sf.classifier4J.Utilities;
import static net.sf.classifier4J.Utilities.getMostFrequentWords;

/**
 *
 * @author admin
 */

public class AiTextToSpeech {

    /**
     * @param args the command line arguments
     */

    public static void  ListToFile(ArrayList<String> input,String filename) throws FileNotFoundException, IOException {

        File fout = new File(filename);
        FileOutputStream fos = new FileOutputStream(fout,true);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));



        for (int i = 0; i < input.size(); i++) {
            String temp=(String)input.get(i);
            bw.write(temp);
            bw.newLine();
        }

        bw.close();
    }

    public static void main(String[] args) {
        // TODO code application logic here
        SimpleSummariser katerina=new SimpleSummariser();
        String LinesFromNewsFile="";

        TextToSpeech tts = new TextToSpeech();

        try {
            File file=new File("news.data");//4 ar8ra pou xwrizontai meta3y tous me to &
            final BufferedReader in = new BufferedReader(new FileReader(file));
            String line="";
            while ((line = in.readLine()) != null) {
                LinesFromNewsFile=LinesFromNewsFile+line;
                line="";
            }
        } catch (IOException ex) {
            System.out.println("Error:"+ex.toString());
        }

        String[] stories=LinesFromNewsFile.split("&");

        for(int i=0;i<stories.length;i++) {
            Map wordFrequencies = Utilities.getWordFrequency(stories[i]);
            Set mostFrequentWords = getMostFrequentWords(12 , wordFrequencies);//poses polles keywords 8a vgazei
            Object[] arrayView = mostFrequentWords.toArray();
            System.out.println("Story:"+Integer.toString(i+1)+" key words are:");
            System.out.println("*************************************************");
            for(int j=0;j<arrayView.length;j++) {
                String filter="";
                filter=(String)arrayView[j];
                switch(filter.trim()) {

                    //Filter unnecessary words  x
                    case "from":
                        break;
                    case "by":
                        break;
                    case "to":
                        break;
                    case "in":
                        break;
                    case "of":
                        break;
                    case "for":
                        break;
                    case "at":
                        break;
                    case "the":
                        break;
                    case "and":
                        break;
                    case "a":
                        break;
                    case "that":
                        break;
                    case "not":
                        break;
                    case "on":
                        break;
                    case "with":
                        break;
                    case "as":
                        break;
                    case "an":
                        break;
                    //characters

                    case "o":
                        break;
                    case "s":
                        break;
                    case "us":
                        break;

                    case "has":
                        break;
                    case "mr":
                        break;
                    case "have":
                        break;
                    case "been":
                        break;
                    case "be":
                        break;
                    case "do":
                        break;
                    case "had":
                        break;
                    case "did":
                        break;
                    case "does":
                        break;
                    //
                    case "you":
                        break;
                    case "we":
                        break;
                    case "they":
                        break;
                    case "he":
                        break;
                    case "I":
                        break;
                    case "it":
                        break;
                    case "she":
                        break;


                    //
                    case "our":
                        break;
                    case "ours":
                        break;
                    case "his":
                        break;
                    case "her":
                        break;
                    case "yours":
                        break;
                    case "your":
                        break;
                    default:
                        System.out.println(filter);
                        break;
                }

            }
            System.out.println("*************************************************");
        }

        ArrayList<String> summaries=new ArrayList<>();

        for(int i=0;i<stories.length;i++) {
            // System.out.println("Summary for story:"+i+"is:");
            // System.out.println(katerina.summarise(stories[i], 5));
            summaries.add(katerina.summarise(stories[i], 3));//how big will be the summarise
        }

        //  tts.setVoice("cmu-slt-hsmm");
        //  tts.setVoice("dfki-poppy-hsmm");
        tts.setVoice("cmu-rms-hsmm");


        for(int i=0;i<summaries.size();i++) {

            String position="";

            switch(i+1) {
                case 1:
                    position="First";
                    break;
                case 2:
                    position="Second";
                    break;
                case 3:
                    position="Third";
                    break;
                case 4:
                    position="Fourth";
                    break;
                default:
                    break;
            }
            tts.speak(position+" Story summary", 2.0f, false, true);
            String buffer="";
            buffer=(String)summaries.get(i);
            tts.speak(buffer, 2.0f, false, true);
        }

        tts.speak("Text to speech finally ", 2.0f, false, true);

        try {
            ListToFile(summaries,"newssummaries2.data");
        } catch (Exception ex) {
        }
    }
}
