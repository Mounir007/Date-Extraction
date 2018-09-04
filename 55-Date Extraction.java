import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FiFty {
    public static void main(String[] pArgs) throws IOException {
        // Text File path
        String fileName = "c:\\Users\\Mounir\\IdeaProjects\\TestTech55\\src\\test.txt";
        File file = new File(fileName);
        ArrayList<String> arraydate = new ArrayList<String>();
        // iterating through the file and reading line by line
        try (Stream linesStream = Files.lines(file.toPath())) {
            linesStream.forEach(line -> {
                // Replacing Month name with it's number (ex: August will be changed to 08)
                String stlin = line.toString()
                        .replaceAll("(?:)January(?:)", "01")
                        .replaceAll("(?:)February(?:)", "02")
                        .replaceAll("(?:)March(?:)", "03")
                        .replaceAll("(?:)April(?:)", "04")
                        .replaceAll("(?:)May(?:)", "05")
                        .replaceAll("(?:)June(?:)", "06")
                        .replaceAll("(?:)July(?:)", "07")
                        .replaceAll("(?:)August(?:)", "08")
                        .replaceAll("(?:)September(?:)", "09")
                        .replaceAll("(?:)October(?:)", "10")
                        .replaceAll("(?:)November(?:)", "11")
                        .replaceAll("(?:)December(?:)", "12");

                // Creating Date patterns

                // Pattern rx1 ex: 2000 (Year) - 02 (Month) - 01 (Day) YYYY(-,/,etc..) MM (-,/,etc..) DD
                Pattern  rx1 = Pattern.compile("([0-9]{4}).([0-9]{1,2}).([0-9]{1,2})");
                // Pattern rx2 ex: 01 (Day) 02 (Month) 2000 (Year) DD MM YYYY
                Pattern  rx2 = Pattern.compile("([0-9]{1,2}).([0-9]{1,2}).([0-9]{4})");
                // Pattern rx3 ex: 02 (Month) 01 (Day), 2000 (Year) MM DD, YYYY
                Pattern  rx3 = Pattern.compile("([0-9]{1,2})\\s([0-9]{1,2}),\\s([0-9]{4})");

                // Finding matches for each pattern

                Matcher matcher1 = rx1.matcher(stlin);

                while (matcher1.find()) {
                    String exp1;
                    if(Integer.parseInt(matcher1.group(2))>12){
                        exp1 = matcher1.group(1)+"-"+ matcher1.group(3)+"-"+ matcher1.group(2);

                    }else if (Integer.parseInt(matcher1.group(3))>12) {
                        exp1 = matcher1.group(1)+"-"+ matcher1.group(2)+"-"+ matcher1.group(3);

                    }else{
                        // I use the standard date format YYYY MM DD when we can't know which one is MM
                        exp1 = matcher1.group(1)+"-"+ matcher1.group(2)+"-"+ matcher1.group(3);
                    }
                    //Adding Date to the ArrayList
                    arraydate.add(exp1);
                }

                Matcher matcher2 = rx2.matcher(stlin);
                while (matcher2.find()) {
                    String exp2;
                    if(Integer.parseInt(matcher2.group(2))>12){
                        exp2 = matcher2.group(3)+"-"+ matcher2.group(1)+"-"+ matcher2.group(2);

                    }else if (Integer.parseInt(matcher2.group(1))>12) {
                        exp2 = matcher2.group(3)+"-"+ matcher2.group(2)+"-"+ matcher2.group(1);

                    }else{
                        // I use the standard date format DD MM YYYY when we can't know which one is MM
                        exp2 = matcher2.group(3)+"-"+ matcher2.group(1)+"-"+ matcher2.group(2);

                    }
                    //Adding Date to the ArrayList
                    arraydate.add(exp2);
                }

                Matcher matcher3 = rx3.matcher(stlin);
                while (matcher3.find()) {
                    String exp3;
                    if(Integer.parseInt(matcher3.group(1))>12){
                        exp3 = matcher3.group(3)+"-"+ matcher3.group(2)+","+ matcher3.group(1);

                    }else if (Integer.parseInt(matcher3.group(3))>12) {
                        exp3 = matcher3.group(3)+"-"+ matcher3.group(1)+"-"+ matcher3.group(2);

                    }else{
                        // I use the standard date format MM DD YYYY when we can't know which one is MM
                        exp3 = matcher3.group(3)+"-"+ matcher3.group(1)+"-"+ matcher3.group(2);
                    }
                    //Adding Date to the ArrayList
                    arraydate.add(exp3);
                }
            });countFrequencies(arraydate);
        }
    }
    // Count Frequencies of each element in the ArrayList
    public static void countFrequencies(ArrayList<String> list)
    {

        TreeMap<String, Integer> tmap = new TreeMap<String, Integer>();
        for (String t : list) {
            Integer c = tmap.get(t);
            tmap.put(t, (c == null) ? 1 : c + 1);
        }

        for (Map.Entry m : tmap.entrySet())
            System.out.println("Frequency of " + m.getKey() + " is " + m.getValue());
    }

}

    Text in Test.txt : 

Marvin Lee Minsky at the 05/13/2017 Mathematics Genealogy Project; 20 May 2014
Marvin Lee Minsky 07/09/2017 AI Genealogy Project. {reprint 18 September 2011)
"Personal page for Marvin Minsky". web.media.mit.edu. Retrieved 23 June 2016.
Admin (January 27, 2016). "Official AlcorMay 13, 2017 Statement Concerning Marvin Minsky". 
Alcor Life Extension Foundation. Retrieved 2016-04-07.
"IEEE Computer Society Magazine Honors 2015-02-13 Artificial Intelligence Leaders". 
DigitalJournal.com. August 24, 2011. Retrieved September 18, 2011. 
Press release source: PRWeb (Vocus).
"Dan David prize 2014 winners". May 15, 2014. Retrieved May 20, 2014.

    Output :
                                                    
Frequency of 2011-08-24 is 1
Frequency of 2011-09-18 is 2
Frequency of 2014-05-15 is 1
Frequency of 2014-05-20 is 2
Frequency of 2015-02-13 is 1
Frequency of 2016-01-27 is 1
Frequency of 2016-04-07 is 1
Frequency of 2016-06-23 is 1
Frequency of 2017-05-13 is 2
Frequency of 2017-07-09 is 1
