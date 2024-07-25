package DigitalLibraryManagementSystem;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library(); 
        Scanner scanner = new Scanner(System.in);

       
        
        library.addBook(new Book("Java Programming", "John Doe", 2021));
        library.addBook(new Book("Data Structures", "Jane Smith", 2023));
        library.addBook(new Book("Algorithms", "Alice Johnson", 2022));
        library.addBook(new Book("python Programming", "girish Doe", 2021));
        library.addBook(new Book("os Structures", "Jane Smith", 2023));
        library.addBook(new Book("OOP", "Alice Johnson", 2022));

     
        User user1 = new User("user1", "password1");
        User user2 = new User("user2", "password2");
 
      
        user1.borrowBook(library.findBook("Java Programming"));
        user2.borrowBook(library.findBook("Data Structures"));

       
        System.out.println("Welcome to Digital Library Management System");

        boolean loggedIn = false;
        User currentUser = null;

        while (true) {
            System.out.println("1. Login");
            System.out.println("2. Search for a Book");
            System.out.println("3. Borrow a Book");
            System.out.println("4. Return a Book");
            System.out.println("5. View Library Inventory");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String password = scanner.nextLine();

                    if (username.equals(user1.getUsername()) && password.equals(user1.getPassword())) {
                        loggedIn = true;
                        currentUser = user1;
                    } else if (username.equals(user2.getUsername()) && password.equals(user2.getPassword())) {
                        loggedIn = true;
                        currentUser = user2;
                    } else {
                        System.out.println("Invalid username or password.");
                    }
                    break;
                case 2:
                    System.out.print("Enter book title to search: ");
                    String searchTitle = scanner.nextLine();
                    Book foundBook = library.findBook(searchTitle);
                    if (foundBook != null) {
                        System.out.println("Book found:");
                        System.out.println(foundBook);
                    } else {
                        System.out.println("Book not found.");
                    }
                    break;
                case 3:
                    if (loggedIn) {
                        System.out.print("Enter book title to borrow: ");
                        String borrowTitle = scanner.nextLine();
                        Book borrowBook = library.findBook(borrowTitle);
                        if (borrowBook != null && borrowBook.isAvailable()) {
                            currentUser.borrowBook(borrowBook);
                            System.out.println("Book borrowed successfully.");
                        } else {
                            System.out.println("Book not available or does not exist.");
                        }
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 4:
                    if (loggedIn) {
                        System.out.print("Enter book title to return: ");
                        String returnTitle = scanner.nextLine();
                        Book returnBook = library.findBook(returnTitle);
                        if (returnBook != null && currentUser.getBorrowedBooks().contains(returnBook)) {
                            currentUser.returnBook(returnBook);
                            System.out.println("Book returned successfully.");
                        } else {
                            System.out.println("Book not borrowed by current user.");
                        }
                    } else {
                        System.out.println("Please login first.");
                    }
                    break;
                case 5:
                    System.out.println(library);
                    break;
                case 6:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
