import java.util.List;
import java.util.Scanner;

public class LibraryManagementSystem {
    private static Library library = new Library("City Central Library");
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        initializeSampleData();
        
        System.out.println("=== Welcome to " + library.getName() + " ===");
        
        while (true) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    removeBook();
                    break;
                case 3:
                    addUser();
                    break;
                case 4:
                    issueBook();
                    break;
                case 5:
                    returnBook();
                    break;
                case 6:
                    library.displayAllBooks();
                    break;
                case 7:
                    library.displayAvailableBooks();
                    break;
                case 8:
                    library.displayAllUsers();
                    break;
                case 9:
                    library.displayIssuedBooks();
                    break;
                case 10:
                    searchBooks();
                    break;
                case 11:
                    System.out.println("Thank you for using the Library Management System. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
            
            System.out.println("\nPress Enter to continue...");
            scanner.nextLine();
        }
    }
    
    private static void displayMenu() {
        System.out.println("\n=== Library Management System ===");
        System.out.println("1. Add Book");
        System.out.println("2. Remove Book");
        System.out.println("3. Add User");
        System.out.println("4. Issue Book");
        System.out.println("5. Return Book");
        System.out.println("6. Display All Books");
        System.out.println("7. Display Available Books");
        System.out.println("8. Display All Users");
        System.out.println("9. Display Issued Books");
        System.out.println("10. Search Books");
        System.out.println("11. Exit");
        System.out.println("=================================");
    }
    
    private static void addBook() {
        System.out.println("\n=== Add New Book ===");
        String bookId = getStringInput("Enter Book ID: ");
        String title = getStringInput("Enter Title: ");
        String author = getStringInput("Enter Author: ");
        String isbn = getStringInput("Enter ISBN: ");
        
        Book book = new Book(bookId, title, author, isbn);
        library.addBook(book);
    }
    
    private static void removeBook() {
        System.out.println("\n=== Remove Book ===");
        String bookId = getStringInput("Enter Book ID to remove: ");
        library.removeBook(bookId);
    }
    
    private static void addUser() {
        System.out.println("\n=== Add New User ===");
        String userId = getStringInput("Enter User ID: ");
        String name = getStringInput("Enter Name: ");
        String email = getStringInput("Enter Email: ");
        
        User user = new User(userId, name, email);
        library.addUser(user);
    }
    
    private static void issueBook() {
        System.out.println("\n=== Issue Book ===");
        String bookId = getStringInput("Enter Book ID: ");
        String userId = getStringInput("Enter User ID: ");
        
        library.issueBook(bookId, userId);
    }
    
    private static void returnBook() {
        System.out.println("\n=== Return Book ===");
        String bookId = getStringInput("Enter Book ID to return: ");
        
        library.returnBook(bookId);
    }
    
    private static void searchBooks() {
        System.out.println("\n=== Search Books ===");
        String title = getStringInput("Enter book title to search: ");
        
        List<Book> foundBooks = library.findBooksByTitle(title);
        if (foundBooks.isEmpty()) {
            System.out.println("No books found with title containing: " + title);
        } else {
            System.out.println("Found " + foundBooks.size() + " book(s):");
            for (Book book : foundBooks) {
                System.out.println(book);
            }
        }
    }
    
    private static void initializeSampleData() {
        // Add sample books
        library.addBook(new Book("B001", "The Great Gatsby", "F. Scott Fitzgerald", "978-0743273565"));
        library.addBook(new Book("B002", "To Kill a Mockingbird", "Harper Lee", "978-0061120084"));
        library.addBook(new Book("B003", "1984", "George Orwell", "978-0451524935"));
        library.addBook(new Book("B004", "Pride and Prejudice", "Jane Austen", "978-0141439518"));
        
        // Add sample users
        library.addUser(new User("U001", "John Doe", "john.doe@email.com"));
        library.addUser(new User("U002", "Jane Smith", "jane.smith@email.com"));
        library.addUser(new User("U003", "Bob Johnson", "bob.johnson@email.com"));
        
        // Issue some books
        library.issueBook("B001", "U001");
        library.issueBook("B002", "U002");
    }
    
    // Utility methods for input
    private static String getStringInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int value = Integer.parseInt(scanner.nextLine());
                return value;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        }
    }
}