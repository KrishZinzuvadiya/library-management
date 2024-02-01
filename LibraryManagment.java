import java.util.*;

class ja {
	
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
	public static final String CYAN_COLOR = "\u001B[36m";
	public static final String MAGENTA_COLOR = "\u001B[35m";
	
	private int loginAttempts = 3; // (This is Set the maximum number of login attempts...)
	private boolean locked = false;	
	private String username;
	private String password;

	public Admin(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	//-->Login method...
	public void adminLogin(Scanner sc, Library library) {
		if (locked) {
			System.out.println("Account locked. Please contact the administrator.");
			return;
		}
		
		System.out.print("Enter admin username: ");
		String enteredUsername = sc.next();
		System.out.print("Enter admin password: ");
		String enteredPassword = sc.next();

		if (enteredUsername.equals(username) && enteredPassword.equals(password)) {
			loginAttempts = 3;
			System.out.println(MAGENTA_COLOR+"Welcome To Admin Account."+RESET_COLOR);

			adminMenu(sc, library);
		} else {
			loginAttempts--;
			System.out.println("Invalid username or password. Login failed.");
			
			if (loginAttempts > 0) {
				System.out.println(CYAN_COLOR+"Remaining login attempts: " + loginAttempts+RESET_COLOR);
			} else {
				locked = true;
				System.out.println("Account locked. Please contact the administrator.");
			}
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
	public static final String MAGENTA_COLOR = "\u001B[35m";

	public static void displayCustomerMenu(Scanner sc, Library library) {
		while (true) {
			System.out.println(MAGENTA_COLOR+"Welcome To Customer Account."+RESET_COLOR);
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
					library.displayBookById(findBookId);
                    break;

                case 3:
                    System.out.print("Enter author name to find: ");
                    String findAuthor = sc.nextLine();
                     library.displayBookByAuthor(findAuthor);
                    break;

                case 4:
                     System.out.print("Enter the maximum price: $");
                    double maxPrice = sc.nextDouble();
                    library.displayBooksUnderPrice(maxPrice);
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

/*-->Only Represents a book with attributes like ID, title, author, price, and available copies...
	Includes methods to decrease and increase available copies.
*/
class Book {
    private int bookId;
    private String title;
    private String author;
    private double price;
    private int availableCopies;

    public Book(int bookId, String title, String author, double price, int availableCopies) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
        this.availableCopies = availableCopies;
		
    }

    //-->Getters and Setters...
    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public double getPrice() {
        return price;
    }

    public int getAvailableCopies() {
        return availableCopies;
    }

    public void decreaseAvailableCopies() {
        if (availableCopies > 0) {
            availableCopies--;
        }
    }
	
    public void increaseAvailableCopies() {
        availableCopies++;
    }
	
}


/*...Library Class...
 *  add book
 * remove book
 * display book
 * all find by id, author and price
 */

class Library {
	Book[] books;
	int capacity;
	int numBooks;
	private static boolean booksInitialized = false;
    private boolean[] checkedOutBooks;
	
	public Library(int capacity) {
        this.capacity = capacity;
        this.books = new Book[capacity];
		this.checkedOutBooks = new boolean[capacity];
        this.numBooks = 0;
		
		books[numBooks++] = new Book(1, "BasicJava", "Java-1", 12.3, 5);
		books[numBooks++] = new Book(2, "AdvanceJava", "MainJava", 24.9, 3);
    }
	
	//--> add book method...
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

	//--> remove book method...
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
	
	/* Initialized...
 * Add book
 * Remove book
*/

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
	
	 //--> Display book method...
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

	//--> Find Book by Id... & ... Display Format
	public Book findBookById(int bookId) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getBookId() == bookId) {
                return books[i];
            }
        }
        return null;
    }
	
	public void displayBookById(int bookId) {
        Book foundBook = findBookById(bookId);
        if (foundBook != null) {
            System.out.println("Book found:");
            System.out.println("---------------------------------------------------------------------------------------------------");
            System.out.printf("| %-4s | %-20s | %-15s | %-8s | %-17s | %-16s |\n",
                    "ID", "Title", "Author", "Price", "Available Copies", "Available Status");
            System.out.println("---------------------------------------------------------------------------------------------------");

            String availabilityStatus = (foundBook.getAvailableCopies() > 0) ? "Yes" : "No";
            System.out.printf("| %-4d | %-20s | %-15s | $%-7.2f | %-17d | %-16s |\n",
                    foundBook.getBookId(), foundBook.getTitle(), foundBook.getAuthor(),
                    foundBook.getPrice(), foundBook.getAvailableCopies(), availabilityStatus);

            System.out.println("---------------------------------------------------------------------------------------------------");
        } else {
            System.out.println("Book not found by ID.");
        }
    }
	
	//--> Find Book by Author... & ... Display Format
    public Book findBookByAuthor(String author) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                return books[i];
            }
        }
        return null;
    }
	
	public void displayBookByAuthor(String author) {
    Book foundBook = findBookByAuthor(author);
    if (foundBook != null) {
        System.out.println("Book found:");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-15s | %-8s | %-17s | %-16s |\n",
                "ID", "Title", "Author", "Price", "Available Copies", "Available Status");
        System.out.println("---------------------------------------------------------------------------------------------------");

        String availabilityStatus = (foundBook.getAvailableCopies() > 0) ? "Yes" : "No";
        System.out.printf("| %-4d | %-20s | %-15s | $%-7.2f | %-17d | %-16s |\n",
                foundBook.getBookId(), foundBook.getTitle(), foundBook.getAuthor(),
                foundBook.getPrice(), foundBook.getAvailableCopies(), availabilityStatus);

        System.out.println("---------------------------------------------------------------------------------------------------");
    } else {
        System.out.println("Book not found by Author.");
    }
}

	//--> Find Book by Price... & ... Display Format
   public Book[] findBooksUnderPrice(double price) {
    Book[] matchingBooks = new Book[numBooks];
    int count = 0;

    for (int i = 0; i < numBooks; i++) {
        if (books[i].getPrice() <= price) {
            matchingBooks[count++] = books[i];
        }
    }

    return null;
}

	public void displayBooksUnderPrice(double maxPrice) {
    int count = 0;

    for (int i = 0; i < numBooks; i++) {
        if (books[i].getPrice() <= maxPrice) {
            count++;
        }
    }

    if (count == 0) {
        System.out.println("No books found under the specified price.");
    } else {
        System.out.println("Books under the specified price:");
        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.printf("| %-4s | %-20s | %-15s | %-8s | %-17s | %-16s |\n",
                "ID", "Title", "Author", "Price", "Available Copies", "Available Status");
        System.out.println("---------------------------------------------------------------------------------------------------");

        for (int i = 0; i < numBooks; i++) {
            if (books[i].getPrice() <= maxPrice) {
                String availabilityStatus = (books[i].getAvailableCopies() > 0) ? "Yes" : "No";
                System.out.printf("| %-4d | %-20s | %-15s | $%-7.2f | %-17d | %-16s |\n",
                        books[i].getBookId(), books[i].getTitle(), books[i].getAuthor(),
                        books[i].getPrice(), books[i].getAvailableCopies(), availabilityStatus);
            }
        }

        System.out.println("---------------------------------------------------------------------------------------------------");
    }
}

    //--> Checkout book method...
    public void checkoutBook(int bookId) {
        int index = findBookIndexById(bookId);

        if (index != -1 && books[index].getAvailableCopies() > 0) {
            books[index].decreaseAvailableCopies();
            checkedOutBooks[index] = true;
            System.out.println("Book checked out successfully!");
        } else if (index != -1) {
            System.out.println("Book is not available for checkout.");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
	
	 //--> Return book method...
    public void returnBook(int bookId) {
        int index = findBookIndexById(bookId);

        if (index != -1 && checkedOutBooks[index]) {
            books[index].increaseAvailableCopies();
            checkedOutBooks[index] = false;
            System.out.println("Book returned successfully!");
        } else if (index != -1) {
            System.out.println("Book is not checked out.");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
	
	//--> Book Index by Id...
	private int findBookIndexById(int bookId) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i] != null && books[i].getBookId() == bookId) {
                return i;
            }
        }
        return -1;
    }
}

