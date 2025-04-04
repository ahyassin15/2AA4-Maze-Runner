package ca.mcmaster.se2aa4.mazerunner;

import static org.junit.jupiter.api.Assertions.*;
import org.apache.commons.cli.CommandLine;
import org.junit.jupiter.api.Test;

public class CLIParserTest {
    
    @Test
    public void testCommandLineArgs() throws Exception {
        CLIArgumentHandler processor = new CLIArgumentHandler();
        String file = "examples/maze1.txt";
        String[] args = { "-i", file, "-p", "path" };
        CommandLine commandLine = processor.process(args);
 
        assertTrue(commandLine.hasOption("i"));
        assertEquals(commandLine.getOptionValue("i"), file);
        assertTrue(commandLine.hasOption("p"));
        assertEquals(commandLine.getOptionValue("p"), "path");
    }

    @Test
    public void testCommandLineArgsNoPath() throws Exception {
        CLIArgumentHandler processor = new CLIArgumentHandler();
        String file = "examples/maze1.txt";
        String[] args = { "-i", file };
        CommandLine commandLine = processor.process(args);
 
        assertTrue(commandLine.hasOption("i"));
        assertEquals(commandLine.getOptionValue("i"), file);
        assertFalse(commandLine.hasOption("p"));
    }
}
