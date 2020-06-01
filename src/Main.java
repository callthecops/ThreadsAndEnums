/**
 * The Main class is where the Runnables(jobs) for the threads are created along with the needed file Input location and fil output
 * location arguments.Next the Threads are instantiated and finally they are started.
 *
 * @author R.Tudor Andrei
 */

public class Main {
    public static void main(String[] args) {
        Runnable jobOne = new WomanBirthdayRoutine("src/file1.txt", "src/HappyBirthday1.txt");
        Runnable jobTwo = new WomanBirthdayRoutine("src/file2.txt", "src/HappyBirthday2.txt");

        Thread first = new Thread(jobOne);
        Thread second = new Thread(jobTwo);

        first.start();
        second.start();

    }
}
