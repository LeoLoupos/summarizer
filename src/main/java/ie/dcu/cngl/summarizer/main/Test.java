package ie.dcu.cngl.summarizer.main;

import ie.dcu.cngl.summarizer.Aggregator;
import ie.dcu.cngl.summarizer.Mary.TextToSpeech;
import ie.dcu.cngl.summarizer.Summarizer;
import ie.dcu.cngl.summarizer.Weighter;
import ie.dcu.cngl.summarizer.opennlp.SentenceDetection_RE;
import ie.dcu.cngl.tokenizer.Structurer;

import java.io.*;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;


public class Test {
	public static void main(String [] args) throws Exception, FileNotFoundException, IOException {
		//String text = FileUtils.readFileToString(new File("C:\\Users\\Shane\\Desktop\\long.txt"), "UTF-8");
        String LinesFromNewsFile="";

        try {
            File file=new File("dailymail.data");//3 articles separated with &
            final BufferedReader in = new BufferedReader(new FileReader(file));
            String line = "";
            while ((line = in.readLine()) != null) {
                LinesFromNewsFile=LinesFromNewsFile+line;
            }
        } catch (IOException ex) {
            System.out.println("Error:"+ex.toString());
        }

        String[] stories=LinesFromNewsFile.split("&");
        int  x = 0;
        //ArrayList<Double[]> weights = new ArrayList<Double[]>();


        // TextToSpeech tts = new TextToSpeech();
      //  tts.setVoice("cmu-slt-hsmm");
        SentenceDetection_RE sen = new SentenceDetection_RE();


        for(int i=0;i<stories.length;i++) {
            String text = stories[i];


            x = sen.Splitter(text);
            sen.Tokenizer(text);
            Structurer structurer = new Structurer();
            Weighter weighter = new Weighter();
            Aggregator aggregator = new Aggregator();
            Summarizer summarizer = new Summarizer(structurer, weighter, aggregator);
            summarizer.setNumSentences(x);
           // summarizer.setWeights(weights);
            //tts.speak("Story summary", 2.0f, false, true);
            //String buffer="";
            String summary = "";

            summary = summarizer.summarize(text);
            int y=sen.wordCount(summary);
            System.out.println(summary + "Words:" + y);

         //   tts.speak((String)summary, 2.0f, false, true);
        }
	}
}
