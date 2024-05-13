import java.util.*;

// Main class that integrates all subsystems and starts the application
public class LibrarySystem {
    // Scanner object for user input
    // private static final Scanner scanner = new Scanner(System.in);
    public static final Scanner scanner = new Scanner(System.in);

    // Instances of the subsystems
    public static final BookManagementSubsystem bookSubsystem = new BookManagementSubsystem();
    public static final MemberManagementSubsystem memberSubsystem = new MemberManagementSubsystem();
    private static final LibraryInterfaceSubsystem interfaceSubsystem = new LibraryInterfaceSubsystem();

    public static void main(String[] args) {
        // Display the main menu
        interfaceSubsystem.displayMainMenu();
    }

    // Method to borrow a book
    public static void borrowBook() {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.next();
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.next();
        bookSubsystem.borrowBook(isbn, memberId);
    }

    // Method to return a book
    public static void returnBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.next();
        bookSubsystem.returnBook(isbn);
    }

    // Method to renew a book
    public static void renewBook() {
        System.out.print("Enter Book ISBN: ");
        String isbn = scanner.next();
        bookSubsystem.renewBook(isbn);
    }

    // Method to search for books
    public static void searchBooks() {
        System.out.print("Enter search keyword: ");
        String keyword = scanner.next();
        bookSubsystem.searchBooks(keyword);
    }
}

// Interface subsystem for user interaction
class LibraryInterfaceSubsystem {
    // Current state of the interface
    private State currentState;

    // Constructor to initialize the current state
    public LibraryInterfaceSubsystem() {
        this.currentState = new MainMenuState(this);
    }

    // Method to display the main menu
    public void displayMainMenu() {
        while (true) {
            currentState.display();
        }
    }

    // Method to change the current state
    public void setState(State state) {
        this.currentState = state;
    }

    // Method to manage books
    public void manageBooks() {
        LibrarySystem.bookSubsystem.manageBooks();
    }

    // Method to manage members
    public void manageMembers() {
        LibrarySystem.memberSubsystem.manageMembers();
    }

    // Method to borrow a book
    public void borrowBook() {
        LibrarySystem.borrowBook();
    }

    // Method to return a book
    public void returnBook() {
        LibrarySystem.returnBook();
    }

    // Method to renew a book
    public void renewBook() {
        LibrarySystem.renewBook();
    }

    // Method to search for books
    public void searchBooks() {
        LibrarySystem.searchBooks();
    }
}

// Interface for the state pattern
interface State {
    void display();
}

// Concrete state class for the main menu
class MainMenuState implements State {
    private LibraryInterfaceSubsystem libraryInterface;

    public MainMenuState(LibraryInterfaceSubsystem libraryInterface) {
        this.libraryInterface = libraryInterface;
    }

    public void display() {
        System.out.println("\n--- Library System Main Menu ---");
        System.out.println("1. Manage Books");
        System.out.println("2. Manage Members");
        System.out.println("3. Borrow Book");
        System.out.println("4. Return Book");
        System.out.println("5. Renew Book");
        System.out.println("6. Search Books");
        System.out.println("7. Exit");
        System.out.print("Enter choice: ");
        int choice = LibrarySystem.scanner.nextInt();
        switch (choice) {
            case 1:
                libraryInterface.setState(new ManageBooksState(libraryInterface));
                break;
            case 2:
                libraryInterface.setState(new ManageMembersState(libraryInterface));
                break;
            case 3:
                libraryInterface.borrowBook();
                break;
            case 4:
                libraryInterface.returnBook();
                break;
            case 5:
                libraryInterface.renewBook();
                break;
            case 6:
                libraryInterface.searchBooks();
                break;
            case 7:
                System.exit(0);
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

// Concrete state class for managing books
class ManageBooksState implements State {
    private LibraryInterfaceSubsystem libraryInterface;

    public ManageBooksState(LibraryInterfaceSubsystem libraryInterface) {
        this.libraryInterface = libraryInterface;
    }

    public void display() {
        libraryInterface.manageBooks();
        libraryInterface.setState(new MainMenuState(libraryInterface));
    }
}

// Concrete state class for managing members
class ManageMembersState implements State {
    private LibraryInterfaceSubsystem libraryInterface;

    public ManageMembersState(LibraryInterfaceSubsystem libraryInterface) {
        this.libraryInterface = libraryInterface;
    }

    public void display() {
        libraryInterface.manageMembers();
        libraryInterface.setState(new MainMenuState(libraryInterface));
    }
}

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

// Member Management Subsystem
class MemberManagementSubsystem {
    // List to store members
    private List<Member> members;

    // Constructor to initialize the member list
    public MemberManagementSubsystem() {
        this.members = new ArrayList<>();
    }

    // Method to manage members
    public void manageMembers() {
        int choice;
        do {
            System.out.println("\n--- Member Management ---");
            System.out.println("1. Add Member");
            System.out.println("2. Update Member");
            System.out.println("3. Delete Member");
            System.out.println("4. View All Members");
            System.out.println("5. Back to Main Menu");
            System.out.print("Enter choice: ");
            choice = LibrarySystem.scanner.nextInt();
            switch (choice) {
                case 1:
                    addMember();
                    break;
                case 2:
                    updateMember();
                    break;
                case 3:
                    deleteMember();
                    break;
                case 4:
                    viewAllMembers();
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

    // Method to add a new member
    private void addMember() {
        System.out.print("Enter Member ID: ");
        String memberId = LibrarySystem.scanner.next();
        System.out.print("Enter Member Name: ");
        String name = LibrarySystem.scanner.next();
        System.out.print("Enter Member Email: ");
        String email = LibrarySystem.scanner.next();
        members.add(new Member(memberId, name, email));
        System.out.println("Member added successfully.");
    }

    // Method to update an existing member
    private void updateMember() {
        System.out.print("Enter Member ID: ");
        String memberId = LibrarySystem.scanner.next();
        Member member = findMemberById(memberId);
        if (member != null) {
            System.out.print("Enter new Member Name: ");
            String name = LibrarySystem.scanner.next();
            System.out.print("Enter new Member Email: ");
            String email = LibrarySystem.scanner.next();
            member.setName(name);
            member.setEmail(email);
            System.out.println("Member updated successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // Method to delete a member
    private void deleteMember() {
        System.out.print("Enter Member ID: ");
        String memberId = LibrarySystem.scanner.next();
        Member member = findMemberById(memberId);
        if (member != null) {
            members.remove(member);
            System.out.println("Member deleted successfully.");
        } else {
            System.out.println("Member not found.");
        }
    }

    // Method to view all registered members
    private void viewAllMembers() {
        System.out.println("\n--- Member List ---");
        for (Member member : members) {
            System.out.println(member);
        }
    }

    // Method to find a member by ID
    private Member findMemberById(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }
}

// Member class
class Member {
    private String memberId;
    private String name;
    private String email;

// Constructor to initialize a member
public Member(String memberId, String name, String email) {
    this.memberId = memberId;
    this.name = name;
    this.email = email;
}

// Getters and setters for member properties
public String getMemberId() {
    return memberId;
}

public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getEmail() {
    return email;
}

public void setEmail(String email) {
    this.email = email;
}

// toString method to represent a member as a string
@Override
public String toString() {
    return "Member ID: " + memberId + ", Name: " + name + ", Email: " + email;
}
}
