package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.FileReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");

        Options options = new Options();
        options.addOption("i", true, "input file");

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine cmd = parser.parse(options, args);
            if (cmd.hasOption("i")) {
                String inputFile = cmd.getOptionValue("i");
                logger.info("**** Reading the maze from file " + inputFile);
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line;
                while ((line = reader.readLine()) != null) {
                    for (int idx = 0; idx < line.length(); idx++) {
                        if (line.charAt(idx) == '#') {
                            logger.info("WALL ");
                        } else if (line.charAt(idx) == ' ') {
                            logger.info("PASS ");
                        }
                    }
                    logger.info(System.lineSeparator());
                }
                System.out.print(System.lineSeparator());
                reader.close();
            } else {
                logger.error("Input file not provided. Use -i flag to specify the input file.");
            }
        } catch (ParseException e) {
            logger.error("Failed to parse command line arguments", e);
        } catch (Exception e) {
            logger.error("An error occurred while reading the maze file", e);
        }

        logger.info("End of MazeRunner");

        System.out.println("**** Computing path");
        System.out.println("PATH NOT COMPUTED");
        System.out.println("** End of MazeRunner");
    }

    /**
     * Get options for CLI parser.
     *
     * @return CLI parser options
     */
    private static Options getParserOptions() {
        Options options = new Options();

        Option fileOption = new Option("i", true, "File that contains maze");
        fileOption.setRequired(true);
        options.addOption(fileOption);

        options.addOption(new Option("p", true, "Path to be verified in maze"));
        options.addOption(new Option("method", true, "Specify which path computation algorithm will be used"));

        return options;
    }
}
