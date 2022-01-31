package models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Contact {

    private SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
    private String name, birthDate, number;
    private int age;

    public Contact(String name, String number, String birthDate) throws ParseException {
        if(name == null || name.isEmpty())
            throw new IllegalArgumentException("Name cannot be null/blank");
        this.name = name;
        this.number = number;
        this.birthDate = birthDate;
        this.age = toAge(birthDate);
    }

    public Contact(Contact source) {
        this.name = source.name;
        this.number = source.number;
        this.birthDate = source.birthDate;
        this.age = source.age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) throws ParseException {
        this.birthDate = birthDate;
        setAge(toAge(birthDate));
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Name: toAge
     * @param birthDate
     * @return age (int)
     * @throws ParseException
     *
     * Inside the function:
     *   1. Parses a date from the birthDate String
     *   2. Gets the current date
     *   3. Subtracts the difference and returns the age.
     *
     */
    public int toAge(String birthDate) throws ParseException {
        format.setLenient(false);
        Date date = format.parse(birthDate);
        long bDayInMilliseconds = new Date().getTime() - date.getTime();

        return (int)(TimeUnit.MILLISECONDS.toDays(bDayInMilliseconds) / 365);

    }

    public String toString() {
        return "Name: " + name + "\nPhone number: " + number + "\nBirth Date: " + birthDate + "\nAge: " + age + " year old\n";
    }
}