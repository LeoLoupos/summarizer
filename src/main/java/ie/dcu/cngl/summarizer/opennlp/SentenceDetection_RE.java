package ie.dcu.cngl.summarizer.opennlp;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSSample;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Leo on 06/02/17.
 */
public class SentenceDetection_RE {


    public int Splitter(String sentence) throws IOException {


        //Loading sentence detector model
        InputStream inputStream = new FileInputStream("en-sent.bin");
        SentenceModel model = new SentenceModel(inputStream);

        //Instantiating the SentenceDetectorME class
        SentenceDetectorME detector = new SentenceDetectorME(model);

        //Detecting the sentence
        String[] sentences  = detector.sentDetect(sentence);

///Counts the article's lines and returns the number of lines of the summarized article.
        int counter = 0;

        //Printing the sentences
        for(String sent : sentences) {
           // System.out.println(counter);
            counter++;
        }

        double[] probs = detector.getSentenceProbabilities();

        System.out.println("  ");

        //for(int i = 0; i<probs.length; i++)
              //  System.out.println(probs[i]);

        return (int)counter/5;
    }

    public void Tokenizer(String text) throws Exception{
        //Loading Parts of speech-maxent model
        InputStream inputStream = new FileInputStream("en-pos-maxent.bin");
        POSModel model = new POSModel(inputStream);

        //Instantiating POSTaggerME class
        POSTaggerME tagger = new POSTaggerME(model);


        //Tokenizing the sentence using WhitespaceTokenizer class
        WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(text);

        int counter = 0;
        for (String a :tokens){
            counter++;
        }
        System.out.println(counter);
        //Generating tags
        String[] tags = tagger.tag(tokens);

        //Instantiating the POSSample class
        POSSample sample = new POSSample(tokens, tags);
        System.out.println(sample.toString());

//        return tags;

    }

    public int wordCount(String text) throws Exception{
        InputStream inputStream = new FileInputStream("en-pos-maxent.bin");
        WhitespaceTokenizer whitespaceTokenizer= WhitespaceTokenizer.INSTANCE;
        String[] tokens = whitespaceTokenizer.tokenize(text);
        int x = 0;
        for (String a :tokens){
            x++;
        }
        return x;
    }





}
