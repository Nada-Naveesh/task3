import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {
    private String name;
    private List<Book> books;
    private List<User> users;
    private Map<String, String> issuedBooks; // bookId -> userId
    
    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>();
        this.users = new ArrayList<>();
        this.issuedBooks = new HashMap<>();
    }
    
    // Book Management Methods
    public void addBook(Book book) {
        books.add(book);
        System.out.println("Book added successfully: " + book.getTitle());
    }
    
    public void removeBook(String bookId) {
        Book bookToRemove = findBookById(bookId);
        if (bookToRemove != null) {
            books.remove(bookToRemove);
            System.out.println("Book removed successfully: " + bookToRemove.getTitle());
        } else {
            System.out.println("Book not found with ID: " + bookId);
        }
    }
    
    public Book findBookById(String bookId) {
        for (Book book : books) {
            if (book.getBookId().equals(bookId)) {
                return book;
            }
        }
        return null;
    }
    
    public List<Book> findBooksByTitle(String title) {
        List<Book> foundBooks = new ArrayList<>();
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                foundBooks.add(book);
            }
        }
        return foundBooks;
    }
    
    // User Management Methods
    public void addUser(User user) {
        users.add(user);
        System.out.println("User added successfully: " + user.getName());
    }
    
    public User findUserById(String userId) {
        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
    
    // Book Issue and Return Methods
    public boolean issueBook(String bookId, String userId) {
        Book book = findBookById(bookId);
        User user = findUserById(userId);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        
        if (user == null) {
            System.out.println("User not found!");
            return false;
        }
        
        if (!book.isAvailable()) {
            System.out.println("Book is already issued!");
            return false;
        }
        
        if (!user.borrowBook(book)) {
            System.out.println("User has reached the maximum limit of borrowed books (5)!");
            return false;
        }
        
        book.setAvailable(false);
        issuedBooks.put(bookId, userId);
        System.out.println("Book '" + book.getTitle() + "' issued to " + user.getName());
        return true;
    }
    
    public boolean returnBook(String bookId) {
        Book book = findBookById(bookId);
        
        if (book == null) {
            System.out.println("Book not found!");
            return false;
        }
        
        if (book.isAvailable()) {
            System.out.println("Book is not issued to anyone!");
            return false;
        }
        
        String userId = issuedBooks.get(bookId);
        User user = findUserById(userId);
        
        if (user != null) {
            user.returnBook(book);
        }
        
        book.setAvailable(true);
        issuedBooks.remove(bookId);
        System.out.println("Book '" + book.getTitle() + "' returned successfully");
        return true;
    }
    
    // Display Methods
    public void displayAllBooks() {
        System.out.println("\n=== All Books in " + name + " ===");
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
        } else {
            for (Book book : books) {
                System.out.println(book);
            }
        }
    }
    
    public void displayAvailableBooks() {
        System.out.println("\n=== Available Books ===");
        boolean found = false;
        for (Book book : books) {
            if (book.isAvailable()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No available books at the moment.");
        }
    }
    
    public void displayAllUsers() {
        System.out.println("\n=== All Users ===");
        if (users.isEmpty()) {
            System.out.println("No users registered.");
        } else {
            for (User user : users) {
                System.out.println(user);
            }
        }
    }
    
    public void displayIssuedBooks() {
        System.out.println("\n=== Currently Issued Books ===");
        if (issuedBooks.isEmpty()) {
            System.out.println("No books are currently issued.");
        } else {
            for (Map.Entry<String, String> entry : issuedBooks.entrySet()) {
                Book book = findBookById(entry.getKey());
                User user = findUserById(entry.getValue());
                System.out.println("Book: " + book.getTitle() + " | Issued to: " + user.getName());
            }
        }
    }
    
    // Getters
    public String getName() {
        return name;
    }
    
    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }
    
    public List<User> getUsers() {
        return new ArrayList<>(users);
    }
}