package ie.dcu.cngl.summarizer.main;

import ie.dcu.cngl.summarizer.Aggregator;
import ie.dcu.cngl.summarizer.Mary.TextToSpeech;
import ie.dcu.cngl.summarizer.Summarizer;
import ie.dcu.cngl.summarizer.Weighter;
import ie.dcu.cngl.summarizer.opennlp.SentenceDetection_RE;
import ie.dcu.cngl.tokenizer.Structurer;

import java.io.*;

import org.apache.commons.io.FileUtils;


public class Test {
	public static void main(String [] args) throws Exception, FileNotFoundException, IOException {
		//String text = FileUtils.readFileToString(new File("C:\\Users\\Shane\\Desktop\\long.txt"), "UTF-8");
        String LinesFromNewsFile="";

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

/*
		String text = "Temperature data for 2016 shows it is likely to have edged ahead of 2015 as the world's warmest year.\n" +
				"Data from Nasa and the UK Met Office shows temperatures were about 0.07 degrees Celsius above the 2015 mark.\n" +
				"Although the Met Office increase was within the margin of error, Nasa says that 2016 was the third year in a row to break the record.\n" +
				"The El Niño weather phenomenon played a role, say scientists, but the main factor was human emissions of CO2.\n" +
				"The latest conclusions won't come as a much of a shock to observers, as the likely outcome was trailed heavily towards the end of last year.\n" +
				"So warm was the early part of 2016 - influenced by a powerful El Niño - that some leading climate scientists were predicting as early as May that a new record was likely.\n" +
				"During an El Niño, a band of unusually warm ocean water develops in parts of the Pacific. The phenomenon affects the climate globally, disrupting weather patterns.\n" +
				"What is climate change?\n" +
				"How global temperature has changed\n" +
				"According to Nasa figures, 2016 is now the warmest year in a record that dates back to 1880.\n" +
				"\"2015 has been the warmest year on record up until now, so 2016 has just beaten that and and it's beaten that by about 0.1- 0.12 of a degree Celsius, which doesn't seem like a lot, but in terms of the year to year variations it's actually huge,\" Dr Gavin Schmidt from Nasa told BBC News.\n" +
				"\"This is a very clear record that we're seeing. It is driven mainly by changes in the tropical Pacific where we had an El Niño event that produced a lot of warmth. But we've also seen long term trends in warming mostly due to the increasing greenhouse gases.\"\n" +
				"Many parts of the world had their warmest recorded year in 2016\n" +
				"Another factor that has affected temperatures in 2016 is the unusual warmth in the Arctic.\n" +
				"The sea-ice covering the Arctic reached its second lowest level (in terms of extent) in September 2016. The sea-ice grows in autumn and winter and shrinks each spring and summer.\n" +
				"While the sea-ice extent last year didn't break the record, the mercury stayed high and the smaller amount of ice now present in the region is at unprecedented levels for the time of year.\n" +
				"A number of meteorological agencies from around the world have released their figures today. They all suggest that warming in 2016 was a record that had an important contribution from El Niño.\n" +
				"The Met Office says it contributed about 0.2C to the annual average for 2016. However, researchers believe that while this is substantial, it is not the whole story.\n" +
				"\"We understand the contribution El Niño makes fairly well and we've seen it many times,\" said Prof Ellie Highwood from the University of Reading.\n" +
				"\"But even if you take that contribution away, we would expect 2015 and 2016 to still be the warmest years we've seen, so a majority of it is coming from global warming and the greenhouse effect.\"\n" +
				"The World Meteorological Organisation (WMO), which pulls together temperature data from a number of sources, agrees that 2016 broke the record by 0.07C.\n" +
				"Not all of the reports on temperature data in 2016 are clear that the warmest-year record has been broken.\n" +
				"Century dominated by records\n" +
				"The Met Office says that 2016 was 0.77 above the long term rate, but with a plus or minus error margin of 0.1C, meaning that last year was at the very least, one of the two warmest years on record.\n" +
				"\"The final figures confirm that 2016 was yet another extremely warm year,\" said Peter Stott from the Met Office.\n" +
				"\"The temperature for last year was very close to the year before, temperatures for 2016 exceeding those for 2015 by a small margin.\"\n" +
				"Regardless of the small margins, when the new data on 2016 is included, 15 of the warmest 16 years on record have now occurred since 2001.\n" +
				"According to Noaa (the US National Oceanic and Atmospheric Administration), the only year from the 20th Century to break into the top 16 is 1998, and which ranks seventh warmest.\n" +
				"This prolonged period of warming was having significant impacts around the world.\n" +
				"The Arctic region exceeded the long term average by up to 6C through most of 2016\n" +
				"\"We have also broken sea ice minimum records in the Arctic and Antarctic,\" said Petteri Taalas from the WMO.\n" +
				"\"The Arctic is warming twice as fast as the global average. The persistent loss of sea-ice is driving weather, climate and ocean circulation patterns in other parts of the world. We also have to pay attention to the potential release of methane from melting permafrost,\" he said.\n" +
				"Of great concern to scientists and politicians is the fact that the newly published temperature data shows the Earth is once again more than one degree warmer than pre-industrial times, and edging closer to the threshold of 1.5C set under the Paris climate pact.\n" +
				"With the Trump administration about to take office in the US, there are concerns that political support for climate action might fade. This would be a big mistake according to scientists.\n" +
				"\"Climate change is one of the great challenges of the 21st Century and shows no signs of slowing down,\" said Prof Mark Maslin, from University College London.\n" +
				"\"The decarbonisation of the global economy is the ultimate goal to prevent the worst effects of climate change. The hottest year on record is such a clear warning siren that even President-elect Trump cannot ignore it.\"\n" +
				"Researchers say that 2017 is unlikely to break the warming record but will be in the";
*/
        TextToSpeech tts = new TextToSpeech();
        tts.setVoice("cmu-slt-hsmm");

        for(int i=0;i<stories.length;i++) {
            String text = stories[i];

           // SentenceDetection_RE sen = new SentenceDetection_RE();
            //int x = sen.Splitter(text);


            Structurer structurer = new Structurer();
            Weighter weighter = new Weighter();
            Aggregator aggregator = new Aggregator();
            Summarizer summarizer = new Summarizer(structurer, weighter, aggregator);
            summarizer.setNumSentences(5);

            //tts.speak("Story summary", 2.0f, false, true);
            //String buffer="";
            String summary = "";

            summary = summarizer.summarize(text);
            tts.speak((String)summary, 2.0f, false, true);
        }
	}
}
