package ca.mcmaster.se2aa4.mazerunner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Objects;

public class Path {
    
    private String path;
    private String[][] maze;

    public Path(String path, String[][] theMaze) {
        this.path = path;
        this.maze = theMaze;
    }

    private static final Logger logger = LogManager.getLogger();

    public boolean verifyPath() {
        logger.info("**** Verifying path");
        logger.error("PATH NOT VALID");
        return false;
    }

    public String convertPath() {
        return "";
    }

}
