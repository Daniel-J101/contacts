import models.Contact;
import models.ContactManager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.ParseException;
import java.util.Scanner;

public class Main {
    private static ContactManager manager = new ContactManager();

    public static void main(String[] args) {
        try {
            loadContacts("contacts.txt");
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
//            e.printStackTrace();
        } finally {
            System.out.println("CONTACTS LOADED\n\n");
            System.out.println(manager);
            manageContacts();
        }




    }

    /**
     * Name: manageContacts
     *
     * Inside the function:
     *   • 1. Starts a new instance of Scanner;
     *   • 2. In an infinite loop, the user can choose to a) add b) remove a contact c) exit.
     *   •        case a: ask for the name, phone number and birthDate.
     *   •        case b: ask who they'd like to remove.
     *   •        case c: break the loop.
     *   • 3. close Scanner.
     */
    public static void manageContacts() {
        Scanner scanner = new Scanner(System.in);

        while(true) {
            System.out.println("Would you like to \n\ta) add another contact\n\tb) remove a contact\n\tc) exit");
            String answer = scanner.nextLine();

            if(answer.equals("a")) {
                System.out.println("\tName: ");
                String name = scanner.nextLine();
                System.out.println("\tPhone Number: ");
                String number = scanner.nextLine();
                System.out.println("\tBirth Date: ");
                String bDay = scanner.nextLine();

                if(name.isEmpty() || number.isEmpty() || number.length() < 5) {
                    System.out.println("\nThe input you provided is not valid. Registration failed.");
                } else {
                    try {
                        manager.addContact(new Contact(name, number, bDay));
                    } catch (ParseException e) {
                        System.out.println(e.getMessage());
                    } finally {
                        System.out.println("\n\nUPDATED CONTACTS\n\n" + manager);

                    }
                }
            } else if(answer.equals("b")) {
                System.out.println("\nWho would you like to remove?");
                manager.removeContact(scanner.nextLine());

                System.out.println("\n\nUPDATED CONTACTS\n\n" + manager);
            } else
                break;
        }
        scanner.close();


    }



    /**
     * Name: loadContacts
     * @param fileName (String)
     * @throws FileNotFoundException
     *
     * Inside the function:
     *   • 1. loads contacts from <fileName>;
     *   • 2. From the manager object, it adds all contacts to the contacts list.
     *        Hint: use scan.next to grab the next String separated by white space.
     */
    public static void loadContacts(String fileName) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(fileName);
        Scanner scanner = new Scanner(fis);
        while(scanner.hasNextLine()) {
            try {
                Contact contact = new Contact(scanner.next(), scanner.next(), scanner.next());
                manager.addContact(contact);
            } catch (ParseException e) {
                System.out.println(e.getMessage());
//                e.printStackTrace();
            }
        }
        scanner.close();

    }
}