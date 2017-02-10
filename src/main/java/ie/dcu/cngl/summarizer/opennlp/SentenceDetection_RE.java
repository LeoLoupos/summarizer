package ie.dcu.cngl.summarizer.opennlp;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Leo on 06/02/17.
 */
public class SentenceDetection_RE {


    public static int Splitter(String sentence) throws IOException {


        //Loading sentence detector model
        InputStream inputStream = new FileInputStream("en-sent.bin");
        SentenceModel model = new SentenceModel(inputStream);

        //Instantiating the SentenceDetectorME class
        SentenceDetectorME detector = new SentenceDetectorME(model);

        //Detecting the sentence
        String[] sentences  = detector.sentDetect(sentence);
///Unique
        int counter = 0;

        //Printing the sentences
        for(String sent : sentences)
            System.out.println(sent);
            counter++;

        double[] probs = detector.getSentenceProbabilities();

        System.out.println("  ");

        for(int i = 0; i<probs.length; i++)
                System.out.println(probs[i]);
        return counter/2;
}
}
