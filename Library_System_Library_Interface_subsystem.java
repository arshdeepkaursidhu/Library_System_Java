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
