import java.util.*;
class LibraryManagment {
	
	public static final String BLUE_COLOR = "\u001B[34m";
	public static final String GREEN_COLOR = "\u001B[32m";
	public static final String RESET_COLOR = "\u001B[0m";
	public static final String YELLOW_COLOR = "\u001B[33m";

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library(10);
        Admin admin = new Admin("krish", "krish@123");
        System.out.println(GREEN_COLOR+"-----------------------------------------------------------------");
        System.out.println("------------------LIBRARY MANAGEMENT SYSTEM-----------------------");
        System.out.println("-----------------------------------------------------------------"+RESET_COLOR);

        while (true) {
            System.out.println(YELLOW_COLOR+"-----CHOICE-----"+RESET_COLOR);
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Menu");
            System.out.println("3. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    admin.adminLogin(sc, library);
                    break;

                case 2:
                    Customer.displayCustomerMenu(sc, library);
                    break;

                case 3:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Admin {
	public static final String RED_COLOR = "\u001B[31m";
	public static final String RESET_COLOR = "\u001B[0m";

    private String username;
    private String password;

    public Admin(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void adminLogin(Scanner sc, Library library) {
        System.out.print("Enter admin username: ");
        String enteredUsername = sc.next();
        System.out.print("Enter admin password: ");
        String enteredPassword = sc.next();

        if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
            adminMenu(sc, library);
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }

    private void adminMenu(Scanner sc, Library library) {
        while (true) {
            System.out.println(RED_COLOR+"-----ADMIN MENU-----"+RESET_COLOR);
            System.out.println("1. Add Book");
            System.out.println("2. Remove Book");
            System.out.println("3. Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    library.addBook(sc);
                    break;

                case 2:
                    library.removeBook(sc);
                    break;

                case 3:
                    System.out.println("Logging out from admin account.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}


class Customer {
	public static final String ORANGE_COLOR = "\u001B[38;5;208m";
	public static final String RESET_COLOR = "\u001B[0m";

    public static void displayCustomerMenu(Scanner sc, Library library) {
        while (true) {
            System.out.println(ORANGE_COLOR+"-----CUSTOMER MENU-----"+RESET_COLOR);
            System.out.println("1. Display Books");
            System.out.println("2. Find Book by ID");
            System.out.println("3. Find Book by Author");
            System.out.println("4. Find Book by Price");
            System.out.println("5. Checkout Book");
            System.out.println("6. Return Book");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); 

            switch (choice) {
                case 1:
                    library.displayBooks();
                    break;

                case 2:
                    System.out.print("Enter book ID to find: ");
                    int findBookId = sc.nextInt();
                    Book foundBookById = library.findBookById(findBookId);
                    if (foundBookById != null) {
                        System.out.println("Book found: " + foundBookById);
                    } else {
                        System.out.println("Book not found by ID.");
                    }
                    break;

                case 3:
                    System.out.print("Enter author name to find: ");
                    String findAuthor = sc.nextLine();
                    Book foundBookByAuthor = library.findBookByAuthor(findAuthor);
                    if (foundBookByAuthor != null) {
                        System.out.println("Book found: " + foundBookByAuthor);
                    } else {
                        System.out.println("Book not found by author name.");
                    }
                    break;

                case 4:
                    System.out.print("Enter book price to find: $");
                    double findPrice = sc.nextDouble();
                    Book foundBookByPrice = library.findBookByPrice(findPrice);
                    if (foundBookByPrice != null) {
                        System.out.println("Book found: " + foundBookByPrice);
                    } else {
                        System.out.println("Book not found by price.");
                    }
                    break;

                case 5:
                    System.out.print("Enter the book ID to checkout: ");
                    int checkoutBookId = sc.nextInt();
                    library.checkoutBook(checkoutBookId);
                    break;

                case 6:
                    System.out.print("Enter the book ID to return: ");
                    int returnBookId = sc.nextInt();
                    library.returnBook(returnBookId);
                    break;

                case 7:
                    System.out.println("Exiting From Customer Account.");
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

class Library {
	Book[] books;
	int capacity;
	int numBooks;
	private static boolean booksInitialized = false;
	public Library(int capacity) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.numBooks = 0;
            books[numBooks++] = new Book(1, "BasicJava", "Java-1", 12.3, 5);
    books[numBooks++] = new Book(2, "AdvanceJava", "MainJava", 24.9, 3);
    }
	
public void addBook(int bookId, String title, String author, double price, int availableCopies) {
    for (int i = 0; i < numBooks; i++) {
        if (books[i] != null && books[i].getBookId() == bookId) {
            System.out.println("Error: Book with the same Book ID already exists.");
            return;
        }
    }

    if (numBooks < capacity) {
        Book newBook = new Book(bookId, title, author, price, availableCopies);
        books[numBooks++] = newBook;
        System.out.println("Book added successfully!");
    } else {
        System.out.println("Library is full. Cannot add more books.");
    }
}



	 public void removeBook(int bookId) {
        int indexToRemove = -1;

        for (int i = 0; i < numBooks; i++) {
            if (books[i].getBookId() == bookId) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
			
            for (int i = indexToRemove; i < numBooks - 1; i++) {
                books[i] = books[i + 1];
            }
			
            books[numBooks - 1] = null;
            numBooks--;

            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
	
	
    public void displayBooks() {
    if (numBooks == 0) {
        System.out.println("No books available in the library.");
    } else {
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-15s | %-8s | %-17s | %-16s |\n",
                "ID", "Title", "Author", "Price", "Available Copies", "Available Status");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (int i = 0; i < numBooks; i++) {
            String availabilityStatus = (books[i].getAvailableCopies() > 0) ? "Yes" : "No";
            System.out.printf("| %-4d | %-20s | %-15s | $%-7.2f | %-17d | %-16s |\n",
                    books[i].getBookId(), books[i].getTitle(), books[i].getAuthor(),
                    books[i].getPrice(), books[i].getAvailableCopies(), availabilityStatus);
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
    }
}

	// Find By Id....
	public Book findBookById(int bookId) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
            if (books[i].getBookId() == bookId) {
                return books[i];
            }
        }
        return null;
    }
	// Find By Author....
    public Book findBookByAuthor(String author) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                return books[i];
            }
        }
        return null;
    }
	// Find By Price....
    public Book findBookByPrice(double price) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~");
            if (books[i].getPrice() == price) {
                return books[i];
            }
        }
        return null;
    }

    public void checkoutBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            if (book.getAvailableCopies()>0 ) {
				book.decreaseAvailableCopies();
                System.out.println("Book checked out successfully!");
            } else {
                System.out.println("Book is not available for checkout.");
            }
        } else {
            System.out.println("Book not found in the library.");
        }
    }

    public void returnBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            book.markAsAvailable();
			book.increaseAvailableCopies();
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found in the library.");
        }
    } 
	
	 public void addBook(Scanner sc) {
        System.out.print("Enter book ID: ");
        int bookId = sc.nextInt();
        System.out.print("Enter book title: ");
        String title = sc.next();
        sc.nextLine();
        System.out.print("Enter author name: ");
        String author = sc.nextLine();
        System.out.print("Enter book price: $");
        double price = sc.nextDouble();
        System.out.print("Enter available copies: ");
        int availableCopies = sc.nextInt();

        addBook(bookId, title, author, price, availableCopies);
    }

    public void removeBook(Scanner sc) {
        System.out.print("Enter the book ID to remove: ");
        int removeBookId = sc.nextInt();
        removeBook(removeBookId);
    }
}
