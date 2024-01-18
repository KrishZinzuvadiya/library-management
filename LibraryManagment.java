import java.util.*;
class LibraryManagment{
	public static void main(String args[]){
	Scanner sc = new Scanner(System.in);
	 Library library = new Library(10);
	 	System.out.println("-----------------------------------------------------------------");
		System.out.println("------------------LIBRARY MANAGMENT SYSTEM-----------------------");
		System.out.println("-----------------------------------------------------------------");

	 while(true){
		System.out.println("1. Add Book");
		System.out.println("2. Display Books");
		System.out.println("3. Find Book by ID");
		System.out.println("4. Find Book by Author");
		System.out.println("5. Find Book by Price");
		System.out.println("6. Checkout Book");
		System.out.println("7. Return Book");
		System.out.println("8. Exit");
		System.out.print("Enter your choice: ");
		int choice = sc.nextInt();
		sc.nextLine();
		
		switch(choice){
			 case 1:{
                    System.out.print("Enter book ID: ");
                    int bookId = sc.nextInt();
                    System.out.print("Enter book title: ");
                    String title = sc.next();
					sc.nextLine();
                    System.out.print("Enter author name: ");
                    String author = sc.nextLine();
                    System.out.print("Enter book price: $");
                    double price = sc.nextDouble();
                    library.addBook(bookId, title, author, price);
                    break;
					}
					case 2:{
                    library.displayBooks();
                    break;
					}
					
					case 3:{
                    System.out.print("Enter book ID to find: ");
                    int findBookId = sc.nextInt();
                    Book foundBookById = library.findBookById(findBookId);
                    if (foundBookById != null) {
                        System.out.println("Book found: " + foundBookById);
                    } else {
                        System.out.println("Book not found by ID.");
                    }
                    break;
					}
					
					case 4: {
                    System.out.print("Enter author name to find: ");
                    String findAuthor = sc.nextLine();
                    Book foundBookByAuthor = library.findBookByAuthor(findAuthor);
                    if (foundBookByAuthor != null) {
                        System.out.println("Book found: " + foundBookByAuthor);
                    } else {
                        System.out.println("Book not found by author name.");
                    }
                    break;
					}
					
					case 5:{
                    System.out.print("Enter book price to find: $");
                    double findPrice = sc.nextDouble();
                    Book foundBookByPrice = library.findBookByPrice(findPrice);
                    if (foundBookByPrice != null) {
                        System.out.println("Book found: " + foundBookByPrice);
                    } else {
                        System.out.println("Book not found by price.");
                    }
                    break;
					}
					
					case 6:{
                    System.out.print("Enter the book ID to checkout: ");
                    int checkoutBookId = sc.nextInt();
                    library.checkoutBook(checkoutBookId);
                    break;
					}
					
					case 7: {
                    System.out.print("Enter the book ID to return: ");
                    int returnBookId = sc.nextInt();
                    library.returnBook(returnBookId);
                    break;
					}
					
					case 8:{
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                    break;
					}
					default:{
                    System.out.println("Invalid choice. Please try again.");
				}
			}
		}
	}
}
class Book {	
	int bookId;
	String title;
	String author;
	double price;
	boolean available;
	
	public Book(int bookId, String title, String author, double price) {
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.price = price;
		this.available = true;
    }
	
	int getBookId(){
		return bookId;
	}
	
	String getTitle(){
		return title;
	}
	
	String getAuthor() {
		return author;
	}

	double getPrice() {
		return price;
	}
	
	boolean isAvailable() {
		return available;
	}

	void markAsUnavailable() {
		available = false;
	}

	void markAsAvailable() {
		available = true;
	}
	public String toString() {
		return "Book ID: " + bookId + "\nTitle: " + title + "\nAuthor: " + author + "\nPrice: $" + price + "\nAvailable: " + (available ? "Yes" : "No");
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
    }
	
	 public void addBook(int bookId, String title, String author, double price) {
        if (numBooks < capacity) {
            Book newBook = new Book(bookId, title, author, price);
            books[numBooks++] = newBook;
            System.out.println("Book added successfully!");
        } else {
            System.out.println("Library is full. Cannot add more books.");
        }
    }
	
	public void displayBooks() {
		System.out.println("hello");
		System.out.println("Book ID: 1");
		System.out.println("Title: Java-1");
		System.out.println("Author: MainJava");
		System.out.println("Price: 199");
		System.out.println("Please Add Other Book For Checkout...");
		{
            System.out.println("Library Books:");
            for (int i = 0; i < numBooks; i++) {
                System.out.println(books[i]);
            }
		}
    }
	
	public Book findBookById(int bookId) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getBookId() == bookId) {
                return books[i];
            }
        }
        return null;
    }

    public Book findBookByAuthor(String author) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getAuthor().equalsIgnoreCase(author)) {
                return books[i];
            }
        }
        return null;
    }

    public Book findBookByPrice(double price) {
        for (int i = 0; i < numBooks; i++) {
            if (books[i].getPrice() == price) {
                return books[i];
            }
        }
        return null;
    }

    public void checkoutBook(int bookId) {
        Book book = findBookById(bookId);
        if (book != null) {
            if (book.isAvailable()) {
                book.markAsUnavailable();
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
            System.out.println("Book returned successfully!");
        } else {
            System.out.println("Book not found in the library.");
        }
    }
}