import java.util.Arrays;
import java.io.File;
import java.lang.String;

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


    public static void main(final String[] args) {

        if (args.length < 1 || args[0].equals("-h") || args[0].equals("--help")) {
            System.out.println("HIYA! Welcome to AutoSteg, which is basically a wrapper that allows you to pass directories as argument to the F5 algo.");
        System.out.println("Format is: java AutoSteg directory_name e [-e msg_name.txt] [-p password] [-q quality_factor]");
        } 

        else{
            String directory = args[0];
            File folder = new File(directory);
            File[] allFiles = folder.listFiles();
            String[] newArgs = new String[args.length];

            //there's probably a better way to copy arguments, but I don't do Java that well
            for (int i = 0; i<args.length-1; i++){
                newArgs[i] = args[i+1];
            }

            for (File file: allFiles) {
                newArgs[args.length-1] = String.format("%s", file);  //yeah, this doesn't allow users to pass output file name
                //System.out.println(Arrays.toString(newArgs));

                //again, probably a more efficient way to do thi without constantly copying arrays
                 if (newArgs[0].equals("e")) {
                    Embed.main(Arrays.copyOfRange(newArgs, 1, newArgs.length));
                } if (newArgs[0].equals("x")) {
                    Extract.main(Arrays.copyOfRange(newArgs, 1, newArgs.length));
                }
            }

        }

    }
}
