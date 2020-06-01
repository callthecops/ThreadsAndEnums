import java.io.IOException;
import java.util.List;

/**
 * WomanBirthdayRoutine is the class wich implements Runnable.This class is used so that we can use two threads to do the same
 * work but parsing total diferent files and writing total different files.
 * <p>
 * The class has a constructor wich has 2 arguments namely the input File and the output file both as String types, and also
 * the implemented run method wich represents the work that the threads will be doing but with different .txt files.
 *
 * @author R.Tudor Andrei
 */

public class WomanBirthdayRoutine implements Runnable {
    private String mSrcFile;
    private String mOutFile;


    public WomanBirthdayRoutine(String mSrcFile, String mOutFile) {
        this.mSrcFile = mSrcFile;
        this.mOutFile = mOutFile;
    }


    public void run() {
        try {
            List<Person> persons = Person.readFile(mSrcFile);
            List<Person> women = Person.selectWomen(persons);
            Person.sendHappyBirthday(women, mOutFile);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

