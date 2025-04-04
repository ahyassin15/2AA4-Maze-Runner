package ca.mcmaster.se2aa4.mazerunner.maze;
 
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class MazePath implements Iterable<MazeStep> {
    private List<MazeStep> path;

    public MazePath(String path) {
        this.path = new LinkedList<>();
        populatePath(path);
    }

    private void populatePath(String path) {
        
        int repeatCount = -1; //Stores number of times a movement should be repeated

        for (int i = 0; i < path.length(); i++) {

            char current = path.charAt(i);
            
            //If the character is a digit, store it in repeatCount
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
 
    public String toCanonicalForm() {
        return toCanonicalForm(false);
    }
 
    public String toCanonicalForm(boolean withSpaces) {
        
        if (path.isEmpty()) {
            return "";
        }
            
        StringBuilder builder = new StringBuilder(); //Create Stringbuilder
        MazeStep current = path.get(0);
        builder.append(current.toString());
        
        for (int i = 1; i < path.size(); i++) {
            
            if (!path.get(i).equals(current)) {
                if (withSpaces) {
                    builder.append(" ");
                }
            }
 
            //Append current character
            builder.append(path.get(i).toString());
            current = path.get(i);
        }

        return builder.toString();
    }

    public String toFactoredForm() {
        return toFactoredForm(false);
    }
 
    public String toFactoredForm(boolean withSpaces) {
        
        if (path.isEmpty()) {
            return "";
        }
 
        StringBuilder builder = new StringBuilder(); //Create StringBuilder
        MazeStep current = path.get(0);
        int count = 1;
 
        //Iterate through path string
        for (int i = 1; i < path.size(); i++) {
            if (path.get(i).equals(current)) {
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
 
    @Override
    public boolean equals(Object object) {

        //If we're comparing the same object in memory, they're equal
        if (this == object) {
            return true; 
        }

        //If the other object is null or not a MazePath, they can't be equal
        if (object == null || getClass() != object.getClass()) {
            return false;
        }
            
        MazePath other = (MazePath) object;
 
        //If sizes of the two paths aren't the same, return false
        if (this.path.size() != other.path.size()) {
            return false;
        } 
 
        //Create iterators to go through both paths step by step
        Iterator<MazeStep> thisSteps = this.path.iterator();
        Iterator<MazeStep> otherSteps = other.path.iterator();
 
        // Compare each step in both path
        while (thisSteps.hasNext() && otherSteps.hasNext()) {
            MazeStep step1 = thisSteps.next();
            MazeStep step2 = otherSteps.next();

            if (!step1.equals(step2))
                return false;
        }
 
        return !(thisSteps.hasNext() || otherSteps.hasNext());
    }
 
    public boolean checkEqualPath(String path) {
        return equals(new MazePath(path));
    }
 
    @Override
    public String toString() {
        return toFactoredForm();
    }
}