package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;


public class CLIArgumentHandler {

    //Logger instance
    private static final Logger logger = LogManager.getLogger();

    public CommandLine process(String[] args) throws Exception {

        //Log command-line parsing is starting
        logger.trace("Starting command-line argument processing...");
        
        //Create command line parser instance
        CommandLineParser cliParser = new DefaultParser();
        Options cliOptions = new Options();

        //Add required input file option (-i)
        cliOptions.addOption("i", true, "Path to the maze input file");
        //Add optional path verification option (-p)
        cliOptions.addOption("p", true, "Validate a given path");

        //Get parsed command line options
        try {
            return cliParser.parse(cliOptions, args);
        } 
        finally {
            //Log command-line parsing is completed
            logger.trace("Command-line argument processing completed");
        }
    }

}
