import java.util.*;
class TestLibrary {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library(10);
        Admin admin = new Admin("admin", "admin123");

        System.out.println("-----------------------------------------------------------------");
        System.out.println("------------------LIBRARY MANAGEMENT SYSTEM-----------------------");
        System.out.println("-----------------------------------------------------------------");

        while (true) {
            System.out.println("-----CHOICE-----");
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
            System.out.println("-----ADMIN MENU-----");
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
    public static void displayCustomerMenu(Scanner sc, Library library) {
        while (true) {
            System.out.println("-----CUSTOMER MENU-----");
            System.out.println("1. Display Books");
            System.out.println("2. Find Book by ID");
            System.out.println("3. Find Book by Author");
            System.out.println("4. Find Book by Price");
            System.out.println("5. Checkout Book");
            System.out.println("6. Return Book");
            System.out.println("7. Logout");

            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume the newline character

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
                    System.out.println("Logging out from customer account.");
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
	
	public Library(int capacity) {
        this.capacity = capacity;
        this.books = new Book[capacity];
        this.numBooks = 0;
		addBook(1, "BasicJava", "Java-1", 12.3,5);
		addBook(2, "AdvanceJava", "MainJava", 24.9,3);
    }
	
	 public void addBook(int bookId, String title, String author, double price, int availableCopies) {
        if (numBooks < capacity) {
            Book newBook = new Book(bookId, title, author, price, availableCopies);
            books[numBooks++] = newBook;
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library is full. Cannot add more books.");
        }
    }
	//----->
	 public void removeBook(int bookId) {
        int indexToRemove = -1;

        for (int i = 0; i < numBooks; i++) {
            if (books[i].getBookId() == bookId) {
                indexToRemove = i;
                break;
            }
        }

        if (indexToRemove != -1) {
            // Shift books to fill the gap
            for (int i = indexToRemove; i < numBooks - 1; i++) {
                books[i] = books[i + 1];
            }

            // Set the last element to null and decrement numBooks
            books[numBooks - 1] = null;
            numBooks--;

            System.out.println("Book removed successfully!");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
	
	public void displayBooks() {
		{
			System.out.println("---------------------");
            System.out.println("Library Books:");
			//Already Books Added...--->
			
            for (int i = 0; i < numBooks; i++) {
				System.out.println("--------------------");
                System.out.println(books[i]);
            }
		}
    }
	// Find By Id....
	public Book findBookById(int bookId) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
            if (books[i].getBookId() == bookId) {
                return books[i];
            }
        }
        return null;
    }
	// Find By Author....
    public Book findBookByAuthor(String author) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                return books[i];
            }
        }
        return null;
    }
	// Find By Price....
    public Book findBookByPrice(double price) {
        for (int i = 0; i < numBooks; i++) {
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
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
