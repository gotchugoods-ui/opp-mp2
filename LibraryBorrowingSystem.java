import java.util.Scanner;
 
 
class Book {

   private String bookID;

   private String title;

   private boolean available;

   public Book(String bookID, String title) {

       this.bookID = bookID;

       this.title = title;

       this.available = true;

   }

   public String getBookID() {

       return bookID;

   }

   public String getTitle() {

       return title;

   }

   public boolean isAvailable() {

       return available;

   }

   public void setAvailable(boolean available) {

       this.available = available;

   }

}
 
class Member {

   private String memberID;

   private String name;

   private Book[] borrowedBooks;

   private int borrowedCount;

   public Member(String memberID, String name) {

       this.memberID = memberID;

       this.name = name;

       borrowedBooks = new Book[3];

       borrowedCount = 0;

   }

   public String getMemberID() {

       return memberID;

   }

   public String getName() {

       return name;

   }

   public boolean borrowBook(Book book) {

       if (!book.isAvailable()) {

           System.out.println("Book is unavailable.");

           return false;

       }

       if (borrowedCount >= 3) {

           System.out.println("Borrowing limit reached.");

           return false;

       }

       borrowedBooks[borrowedCount++] = book;

       book.setAvailable(false);

       System.out.println(name + " borrowed " + book.getTitle());

       return true;

   }

   public boolean returnBook(String bookID) {

       for (int i = 0; i < borrowedCount; i++) {

           if (borrowedBooks[i].getBookID().equals(bookID)) {

               borrowedBooks[i].setAvailable(true);

               for (int j = i; j < borrowedCount - 1; j++) {

                   borrowedBooks[j] = borrowedBooks[j + 1];

               }

               borrowedBooks[borrowedCount - 1] = null;

               borrowedCount--;

               System.out.println("Book returned successfully.");

               return true;

           }

       }

       System.out.println("Book not found.");

       return false;

   }

   public void displayBorrowedBooks() {

       System.out.println("\nMember: " + name);

       if (borrowedCount == 0) {

           System.out.println("No borrowed books.");

           return;

       }

       for (int i = 0; i < borrowedCount; i++) {

           System.out.println("- " + borrowedBooks[i].getTitle());

       }

   }

}
 
public class LibraryBorrowingSystem {

   public static void main(String[] args) {

       Scanner sc = new Scanner(System.in);

       System.out.print("Enter number of books: ");

       int bookCount = sc.nextInt();

       sc.nextLine();

       Book[] books = new Book[bookCount];

       for (int i = 0; i < bookCount; i++) {

           System.out.println("\nBook " + (i + 1));

           System.out.print("Book ID: ");

           String id = sc.nextLine();

           System.out.print("Title: ");

           String title = sc.nextLine();

           books[i] = new Book(id, title);

       }

       System.out.print("\nEnter number of members: ");

       int memberCount = sc.nextInt();

       sc.nextLine();

       Member[] members = new Member[memberCount];

       for (int i = 0; i < memberCount; i++) {

           System.out.println("\nMember " + (i + 1));

           System.out.print("Member ID: ");

           String id = sc.nextLine();

           System.out.print("Name: ");

           String name = sc.nextLine();

           members[i] = new Member(id, name);

       }

       System.out.print("\nEnter number of actions: ");

       int actions = sc.nextInt();

       sc.nextLine();

       for (int i = 0; i < actions; i++) {

           System.out.print("\nMember ID: ");

           String memberID = sc.nextLine();

           System.out.print("Action (B/R): ");

           String action = sc.nextLine();

           Member member = null;

           for (Member m : members) {

               if (m.getMemberID().equals(memberID)) {

                   member = m;

                   break;

               }

           }

           if (member == null) {

               System.out.println("Member not found.");

               continue;

           }

           System.out.print("Book ID: ");

           String bookID = sc.nextLine();

           if (action.equalsIgnoreCase("B")) {

               Book book = null;

               for (Book b : books) {

                   if (b.getBookID().equals(bookID)) {

                       book = b;

                       break;

                   }

               }

               if (book != null) {

                   member.borrowBook(book);

               } else {

                   System.out.println("Book not found.");

               }

           } else if (action.equalsIgnoreCase("R")) {

               member.returnBook(bookID);

           }

       }

       System.out.println("\n=== BORROWED BOOK SUMMARY ===");

       for (Member m : members) {

           m.displayBorrowedBooks();

       }

       System.out.println("\n=== AVAILABLE BOOKS ===");

       for (Book b : books) {

           if (b.isAvailable()) {

               System.out.println(b.getBookID() + " - " + b.getTitle());

           }

       }

       sc.close();

   }

}
 
