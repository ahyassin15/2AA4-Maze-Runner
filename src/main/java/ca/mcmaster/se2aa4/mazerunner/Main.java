package ca.mcmaster.se2aa4.mazerunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.commons.cli.*;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        logger.info("** Starting Maze Runner");
        CommandLineParser parser = new DefaultParser();
        CommandLine cmd = null;

        try {
            cmd = parser.parse(getParserOptions(), args);
            String filePath = cmd.getOptionValue('i');
            // Maze maze = new Maze(filePath);

            // if (cmd.getOptionValue("p") != null) {
            //     logger.info("Validating path");
            //     Path path = new Path(cmd.getOptionValue("p"));
            //     if (maze.validatePath(path)) {
            //         System.out.println("correct path");
            //     } else {
            //         System.out.println("incorrect path");
            //     }
            // } else {
            //     String method = cmd.getOptionValue("method", "righthand");
            //     Path path = solveMaze(method, maze);
            //     System.out.println(path.getFactorizedForm());
            // }
        } catch (Exception e) {
            System.err.println("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("MazeSolver failed.  Reason: " + e.getMessage());
            logger.error("PATH NOT COMPUTED");
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
