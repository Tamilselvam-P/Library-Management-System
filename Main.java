import java.util.ArrayList;
import java.util.*;
class Book {
    String title;
    String author;
	int Year;
    boolean available;
    public Book(String title, String author, int Year) {
        this.title = title;
        this.author = author;
		this.Year = Year;
        this.available = true;
    }
}
class User {
    String username;
    ArrayList<Book> borrowedBooks;
    public User(String username) {
        this.username = username;
        this.borrowedBooks = new ArrayList<>();
    }
}
class Library {
    ArrayList<Book> books;
    ArrayList<User> users;
    public Library() {
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
    }
    public void addBook(String title, String author, int Year) {
        books.add(new Book(title, author, Year));
    }
    public void displayBooks() {
        System.out.println("Books in the Library:");
        for (Book book : books) {
            System.out.println("Title: " + book.title + ", Author: " + book.author + ", Publish: " + book.Year + ", Available: " + book.available);
        }
    }
    public void registerUser(String username) {
        users.add(new User(username));
    }
    public void displayUsers() {
        System.out.println("Users in the Library:");
        for (User user : users) {
            System.out.println("Username: " + user.username);
        }
    }
    public void borrowBook(String username, String title) {
        for (User user : users) {
            if (user.username.equals(username)) {
                for (Book book : books) {
                    if (book.title.equals(title) && book.available) {
                        book.available = false;
                        user.borrowedBooks.add(book);
                        System.out.println(username + " has successfully borrowed: " + book.title);
                        return;
                    }
                }
                System.out.println("Book not available for borrowing.");
                return;
            }
        }
        System.out.println("User not found.");
    }
    public void returnBook(String username, String title) {
        for (User user : users) {
            if (user.username.equals(username)) {
                for (Book book : user.borrowedBooks) {
                    if (book.title.equals(title)) {
                        book.available = true;
                        user.borrowedBooks.remove(book);
                        System.out.println(username + " has successfully returned: " + book.title);
                        return;
                    }
                }
                System.out.println("User did not borrow the specified book.");
                return;
            }
        }
        System.out.println("User not found.");
    }
}
public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("The Great Gatsby", "F. Scott Fitzgerald",1995);
        library.addBook("To Kill a Mockingbird", "Harper Lee",1997);
        library.addBook("1984", "George Orwell",2000);
        library.addBook("The Untold Story Of Dhoni","Neeraj Pandey",2016);
        library.registerUser("john_doe");
        library.registerUser("jane_smith");
        library.registerUser("john_paul");
        library.registerUser("mohan_raj");
        library.registerUser("moideen_khan");
        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\nLibrary Management System Menu:");
            System.out.println("1. Display Books");
            System.out.println("2. Display Users");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;
                case 2:
                    library.displayUsers();
                    break;
                case 3:
                    System.out.print("Enter your username: ");
                    scanner.nextLine(); // Consume the newline character
                    String borrowUsername = scanner.nextLine();
                    System.out.print("Enter the title of the book to borrow: ");
                    String borrowTitle = scanner.nextLine();
                    library.borrowBook(borrowUsername, borrowTitle);
                    break;
                case 4:
                    System.out.print("Enter your username: ");
                    scanner.nextLine(); // Consume the newline character
                    String returnUsername = scanner.nextLine();
                    System.out.print("Enter the title of the book to return: ");
                    String returnTitle = scanner.nextLine();
                    library.returnBook(returnUsername, returnTitle);
                    break;
                case 0:
                    System.out.println("Exiting Library Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        } while (choice != 0);
    }
}