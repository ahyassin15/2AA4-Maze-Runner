package ca.mcmaster.se2aa4.mazerunner.maze;
 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MazePath implements Iterable<MazeStep> {
    private List<MazeStep> path;

    public MazePath(String path) {
        this.path = new LinkedList<>();
        parsePath(path);
    }

    private void parsePath(String path) {
        
        int repeatCount = -1; // Stores number of times a movement should be repeated

        for (int i = 0; i < path.length(); i++) {

            char current = path.charAt(i);
            
            // If the character is a digit, store it in the number variable
            if (Character.isDigit(current)) {
                if (repeatCount == -1) {
                    repeatCount = Character.getNumericValue(path.charAt(i));
                } else {
                    repeatCount *= 10;
                    repeatCount += Character.getNumericValue(path.charAt(i));
                }
            } else if (current != ' ') {
                if (repeatCount > 0) {
                    for (int j = 0; j < repeatCount; j++) {
                        this.path.add(MazeStep.fromCharacter(path.charAt(i)));
                    }
                    repeatCount = -1;
                } else {
                    this.path.add(MazeStep.fromCharacter(path.charAt(i)));
                }
            }
        }
    }

    @Override
    public Iterator<MazeStep> iterator() {
        return path.iterator();
    }
 
    public String toFactoredForm() {
        return toFactoredForm(false);
    }
 
    public String toFactoredForm(boolean withSpaces) {
        
        if (path.isEmpty()) {
            return "";
        }
 
        //Create StringBuilder
        StringBuilder builder = new StringBuilder();
        
        MazeStep current = path.get(0);
        int count = 1;
 
        //Iterate through path string
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i) == current) {
                count++;
                
            } else {
                if (count > 1) {
                    builder.append(count);
                }
                
                builder.append(current.toString());
            
                if (withSpaces) {
                    builder.append(" ");
                }

                //Reset count and set the current character
                current = path.get(i);
                count = 1;
            }
        }
 
        //Add last character
        if (count > 1) {
            builder.append(count);
        }
            
        builder.append(current.toString());
 
        return builder.toString();
    }
 
    public String toCanonicalForm() {
        return toCanonicalForm(false);
    }
 
    public String toCanonicalForm(boolean withSpaces) {
        if (path.isEmpty()) {
            return "";
        }
            
        StringBuilder string_builder = new StringBuilder();
        
        MazeStep current = path.get(0);
        string_builder.append(current.toString());
        
        for (int i = 1; i < path.size(); i++) {
            
            if (path.get(i) != current) {
                if (withSpaces) {
                    string_builder.append(" ");
                }
            }
 
            //Append current character
            string_builder.append(path.get(i).toString());
            current = path.get(i);
        }

        return string_builder.toString();
    }
 
    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
 
        MazePath other = (MazePath) object;
 
        //If sizes of the two paths aren't the same, return false
        if (path.size() != other.path.size())
            return false;
 
        //Compare elements of the two paths
        Iterator<MazeStep> thisIterator = this.path.iterator();
        Iterator<MazeStep> otherIterator = other.path.iterator();
 
        //If all elements equal, return true
        while (thisIterator.hasNext() && otherIterator.hasNext()) {
            MazeStep thisInstruction = thisIterator.next();
            MazeStep otherInstruction = otherIterator.next();

            if (!thisInstruction.equals(otherInstruction))
                return false;
        }
 
        if (thisIterator.hasNext() || otherIterator.hasNext())
            return false;
 
        return true;
    }
 
    public boolean checkEqualPath(String path) {
        return equals(new MazePath(path));
    }
 
    @Override
    public String toString() {
        return toFactoredForm();
    }
}