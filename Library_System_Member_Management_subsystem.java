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
