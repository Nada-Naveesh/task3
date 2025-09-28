public class Book {
    private String bookId;
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable;
    
    public Book(String bookId, String title, String author, String isbn) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.isAvailable = true;
    }
    
    // Getters and Setters
    public String getBookId() {
        return bookId;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public boolean isAvailable() {
        return isAvailable;
    }
    
    public void setAvailable(boolean available) {
        isAvailable = available;
    }
    
    @Override
    public String toString() {
        String status = isAvailable ? "Available" : "Issued";
        return String.format("ID: %s | Title: %s | Author: %s | ISBN: %s | Status: %s", 
                           bookId, title, author, isbn, status);
    }
}