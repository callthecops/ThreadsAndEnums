import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

enum Gender {
    FEMALE, MALE;
}

/**
 * The Person class is the Class where most of the logic happens.Here we have an enum type "Gender" wich is used to store
 * the gender of the Person objects.The Person class also contains 3 static methods, one Person constructor with 3 parameters(name,
 * birthdate,gender),a getter for birthDate and the overriden toString method.
 * <p>
 * The readFile() method reads the .txt files line by line, splits at comma, saves each string into an array spot and finally
 * builds a Person Object, adding it to an array.
 * <p>
 * The selectWomen() method takes that respective Array as argument,creates an array where only Person object of gender female are added,
 * searches for Objects of Female gender, adds them to the array and finally it returns the array.
 * <p>
 * The sendHappyBirthday() method takes that returned array as argument, creates a date of "today", creates a FileWriter,
 * searches for Female Object birthdate(month and day) - compares it to the current date wich it created and finally prints a
 * happy birthday message in the console for that Person object while at the same time it writes it in a .txt file.
 *
 * @author R.Tudor Andrei
 */

public class Person {
    private String name;
    private LocalDate birthDate;
    private Gender gender;


    public Person(String name, LocalDate date, Gender gender) {
        this.name = name;
        this.birthDate = date;
        this.gender = gender;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public static List<Person> readFile(String fileName) throws IOException {
        List<Person> pers = new ArrayList<>();
        System.out.println("Reading from :" + fileName + "\n");

        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String line = br.readLine();
        String[] fields;

        while (line != null) {
            // spliting at , and saving in fields
            fields = line.split(",");

            //adding the name
            String name = fields[0];

            //Using substring to select specific characters in the string and saving them into varialbes, then forming a complete date.

            int year = Integer.parseInt(fields[1].substring(0, 4));
            int month = Integer.parseInt(fields[1].substring(5, 7));
            int days = Integer.parseInt(fields[1].substring(8, 10));
            LocalDate date = LocalDate.of(year, month, days);

            //Creating an Enum reference, then comparing with data from .txt files

            String value = fields[2];
            Gender gender;
            if (value.equals("male") || value.equals("female")) {
                gender = Gender.valueOf(value.toUpperCase());
            } else if (value.equals("0")) {
                gender = Gender.MALE;
            } else {
                gender = Gender.FEMALE;
            }
            Person addMe = new Person(name, date, gender);
            pers.add(addMe);
            line = br.readLine();
        }

        //Printing out the object that where created with the parsed data from the .txt files.Uncomment to print all the names in the list.

        for (Person p : pers) {
            //System.out.println(p);
        }
        return pers;
    }

    //This method selects all the women in the array

    public static List<Person> selectWomen(List<Person> li) {
        List<Person> women = new ArrayList<>();
        for (int i = 0; i < li.size(); i++) {
            if (li.get(i).gender == Gender.FEMALE) {
                women.add(li.get(i));
            }
        }
        return women;
    }

    //This method generates the present day, and then uses a List of Person object(only women) to send all the Objects a
    //Happy Birthday message.It also writes each message for each object to a .txt file.

    public static void sendHappyBirthday(List<Person> w, String fileName) throws IOException {
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat form = new SimpleDateFormat("yyyy-MM-dd");
        String today = form.format(date);
        String monthAndDay = today.substring(5, 10);
        FileWriter writer = new FileWriter(fileName);


        for (int i = 0; i < w.size(); i++) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formatedBirthDate = dtf.format(w.get(i).birthDate);
            String formatedBirthDateMonthAndDay = formatedBirthDate.substring(5, 10);
            if (formatedBirthDateMonthAndDay.equals(monthAndDay)) {
                System.out.println("Happy Birthday! " + w.get(i).name + "(" + w.get(i).birthDate.getYear() + ")");
                writer.write("Happy Birthday! " + w.get(i).name + "(" + w.get(i).birthDate.getYear() + ")\n");
            }
        }
        writer.close();
    }

    public String toString() {
        String person = String.format(" Name: " + name + " Birthdate: " + birthDate + " Gender: " + gender);
        return person;
    }
}
