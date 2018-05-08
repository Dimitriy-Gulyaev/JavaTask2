package org.spbstu.gulyaev;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.io.IOException;
import java.util.IllegalFormatFlagsException;

/**
 * Command-line launcher.
 * If -n and -c options both aren't specified, last 10 lines will be extracted.
 * <p>
 * Options:
 * -o             sets the name of the output file. If not specified, the result will be put to the console.
 * -n             sets number of lines which will be extracted from text.
 * -c             sets number of chars which will be extracted from text.
 * inputName      sets the name of the input file. If not specified, program will read text from the console.
 * <p>
 * Example:
 * tail [-c num|-n num] [-o ofile] file0 file1 file2 …
 */

public class TextTailLauncher {

    @Option(name = "-o", metaVar = "outputName", usage = "Specifies the name of the output file")
    private String outputName;

    @Option(name = "-n", metaVar = "numberOfStrings", usage = "Number of strings in tail")
    private Integer numberOfStrings;

    @Option(name = "-c", metaVar = "numberOfChars", usage = "Number of chars in tail")
    private Integer numberOfChars;

    @Argument(metaVar = "inputName", usage = "Input file name")
    private String inputName;

    public static void main(String[] args) {
        new TextTailLauncher().launch(args);
    }

    private void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println(e.getMessage());
            System.err.println("Expected format: tail [-c num|-n num] [-o ofile] file0 file1 file2 …");
            parser.printUsage(System.err);
            return;
        }
        try {
            TextTail sample = new TextTail(numberOfChars, numberOfStrings, inputName, outputName);

            if (sample.numberOfChars != null && sample.numberOfStrings != null) throw new
                    IllegalFormatFlagsException("Only usage of -c or -n singly allowed");
            sample.tail(inputName, outputName);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
