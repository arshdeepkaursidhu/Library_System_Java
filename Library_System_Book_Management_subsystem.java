// Book Management Subsystem
class BookManagementSubsystem {
    // List to store books
    private List<Book> books;

    // Constructor to initialize the book list
    public BookManagementSubsystem() {
        this.books = new ArrayList<>();
    }

    // Method to manage books
    public void manageBooks() {
        int choice;
        do {
            System.out.println("\n--- Book Management ---");
            System.out.println("1. Add Book");
            System.out.println("2. Update Book");
            System.out.println("3. Delete Book");
            System.out.println("4. View All Books");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = LibrarySystem.scanner.nextInt();
            switch (choice) {
                case 1:
                    addBook();
                    break;
                case 2:
                    updateBook();
                    break;
                case 3:
                    deleteBook();
                    break;
                case 4:
                    viewAllBooks();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a new book
    private void addBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = LibrarySystem.scanner.next();
        System.out.print("Enter Book Title: ");
        String title = LibrarySystem.scanner.next();
        System.out.print("Enter Book Author: ");
        String author = LibrarySystem.scanner.next();
        books.add(new Book(isbn, title, author));
        System.out.println("Book added successfully.");
    }

    // Method to update an existing book
    private void updateBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = LibrarySystem.scanner.next();
        Book book = findBookByISBN(isbn);
        if (book != null) {
            System.out.print("Enter new Book Title: ");
            String title = LibrarySystem.scanner.next();
            System.out.print("Enter new Book Author: ");
            String author = LibrarySystem.scanner.next();
            book.setTitle(title);
            book.setAuthor(author);
            System.out.println("Book updated successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to delete a book
    private void deleteBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = LibrarySystem.scanner.next();
        Book book = findBookByISBN(isbn);
        if (book != null) {
            books.remove(book);
            System.out.println("Book deleted successfully.");
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to view all available books
    private void viewAllBooks() {
        System.out.println("\n--- Book List ---");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Method to find a book by ISBN
    private Book findBookByISBN(String isbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    // Method to borrow a book
    public void borrowBook(String isbn, String memberId) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (book.isBorrowed()) {
                System.out.println("Book is already borrowed.");
            } else {
                book.setBorrowed(true);
                book.setBorrowedBy(memberId);
                System.out.println("Book borrowed successfully.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to return a book
    public void returnBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (book.isBorrowed()) {
                book.setBorrowed(false);
                book.setBorrowedBy(null);
                System.out.println("Book returned successfully.");
            } else {
                System.out.println("Book is not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to renew a book
    public void renewBook(String isbn) {
        Book book = findBookByISBN(isbn);
        if (book != null) {
            if (book.isBorrowed()) {
                // Renew book logic
                System.out.println("Book renewed successfully.");
            } else {
                System.out.println("Book is not borrowed.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    // Method to search for books
    public void searchBooks(String keyword) {
        System.out.println("\n--- Search Results ---");
        int count = 0;
        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                    book.getAuthor().toLowerCase().contains(keyword.toLowerCase())) {
                System.out.println(book);
                count++;
            }
        }
        if (count == 0) {
            System.out.println("No books found.");
        }
    }
}

// Book class
class Book {
    private String isbn;
    private String title;
    private String author;
    private boolean isBorrowed;
    private String borrowedBy;

    // Constructor to initialize a book
    public Book(String isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.isBorrowed = false;
        this.borrowedBy = null;
    }

    // Getters and setters for book properties
    public String getIsbn() {
        return isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isBorrowed() {
        return isBorrowed;
    }

    public void setBorrowed(boolean borrowed) {
        isBorrowed = borrowed;
    }

    public String getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(String borrowedBy) {
        this.borrowedBy = borrowedBy;
    }

    // toString method to represent a book as a string
    @Override
    public String toString() {
        return "ISBN: " + isbn + ", Title: " + title + ", Author: " + author + ", Borrowed: " + isBorrowed;
    }
}
