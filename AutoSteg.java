import java.util.Arrays;
import java.io.File;
import java.lang.String;
import java.io.FileWriter;
import java.io.BufferedWriter;
import main.Embed;
import main.Extract;

/*
 * Created on April 25th, 2013
 */

/**
 * Way to automate encryption
 * 
 * @author Hello, ladies. Look at your man, now back to me, now back at your man, now back to me. Sadly, he isn't me. 
 */

public class AutoSteg {

    native static double spotf5(String name);

    static {
        System.loadLibrary("spotf5");
    }


    public static void main(final String[] args) {

        if (args.length < 1 || args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("HIYA! Welcome to AutoSteg, which is basically a wrapper that allows you to pass directories as argument to the F5 algo.");
        System.out.println("Format is: java AutoSteg directory_name e [-e msg_name.txt] [-p password] [-q quality_factor]");
        } 

        else{
            String inputDirectory = args[0]; //get directory name
            String outputDirectory = inputDirectory+"/stego_output";  //it appears that the folder stego_ouput must exist OR THE PROGRAM GETS MAD
            File folder = new File(inputDirectory);
            File[] allFiles = folder.listFiles();  //get all files in directory. please don't break this by giving a directory with non-pic files. thank you.
            String[] newArgs = new String[args.length+1]; 

            //there's probably a better way to copy arguments, eh. Java.
            for (int i = 0; i<args.length-1; i++){
                newArgs[i] = args[i+1];
            }

            for (File file: allFiles) {
                String filename = String.format("%s", file); //get filename with path and everything
                String picNameWithJpg = filename.substring(filename.lastIndexOf("/")); //get only the pic name with jpg extensions
                String picName = picNameWithJpg.substring(0, picNameWithJpg.lastIndexOf(".")); //get only the pic name with jpg extensions
                String outputFilename = String.format("%s%sSTEGO.jpg", outputDirectory, picName);
                
                //System.out.printf("here are your strings FILENAME: %s\n PICNAME: %s\n Picname: %s\n OUTPUT: %s\n", filename, picNameWithJpg, picName, outputFilename); //just for debugging

                newArgs[args.length-1] = filename; //input file parameter
                newArgs[args.length] = outputFilename; //output file parameter

                //System.out.println(Arrays.toString(newArgs)); //just for debugging

                //again, probably a more efficient way to do this without constantly copying arrays
                if (newArgs[0].equals("e")) {
                    Embed.main(Arrays.copyOfRange(newArgs, 1, newArgs.length));
                    
                    double beta = spotf5(newArgs[args.length]);
                    if(beta > 0.25){
                        System.out.println("\t\tWE SEE THE STEGO.");
                        System.out.printf("\t\tWE DETECTED: %f\n", beta);
                    }
                    else{
                        System.out.println("\t\tNO STEGO HERE. MOVING ALONG.");
                    }

                    try{
                        String resultsFileOutputDirectory = outputDirectory + ".csv";

                        FileWriter fstream = new FileWriter(resultsFileOutputDirectory, true); 
                        BufferedWriter out = new BufferedWriter(fstream);

                        String output = String.format("%s, %s, %f\n", picName, beta>0.25?"detected":"not detected", beta);
                        out.write(output);
                        out.close();
                    }
                    catch (Exception e)
                    {
                        System.err.println("Error: " + e.getMessage());
                    }

                }

                //hmm, would anyone want the extraction process?
                if (newArgs[0].equals("x")) {
                    Extract.main(Arrays.copyOfRange(newArgs, 1, newArgs.length));
                }

            } //end of for loop for each file in directory

        } //end of reading arguments

    } //end of main
}
