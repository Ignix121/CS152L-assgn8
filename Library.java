/*
 *@version date: 2018/04/22
 *@author: Prakat Bhatta
 *This is Library Objects to check in and check out books or 
 *delete the records
 */
public class Library { 

    /** Unique books in the library. */
    private Book[] books;
    
    /** Number of copies for each book. */
    private int[] copies;

    /** Number of copies currently checked out for each book. */
    private int[] checkedOut;

    /** Number of unique books in the library. */
    private int numBooks;

    /** Construct a new empty Library. */
    public Library() {
        // We'll assume we never have more than 400 books.
        int librarySize = 400;
        books = new Book[librarySize];
        copies = new int[librarySize];
        checkedOut = new int[librarySize];
        numBooks = 0;
    }

    /**
     * Get the number of total copies of all books that exist in the
     * library.
     * @return Total number of copies in the library.
     */
    public int totalCopies() {
        int res = 0;
        for(int i = 0; i < numBooks; i++) {
            res += copies[i];
        }
        return res;
    }

    /**
     * Get the number of copies currently checked out.
     * @return Total number of copies checked out.
     */
    public int totalCheckedOut() {
        int res = 0;
        for(int i = 0; i < numBooks; i++) {
            res += checkedOut[i];
        }
        return res;
    }

    /**
     * Get a String representing the status of the library.
     * @return Status string.
     */
    public String statusString() {
        return "Total unique books: " + numBooks +
                "\nTotal number of copies: " + totalCopies() +
                "\nTotal checked out: " + totalCheckedOut();
    }

    /**
     * Add all the books in the array to the library. Adds one copy of
     * each book.
     * @param newBooks Books to add.
     * 
     */
    public void addBooks( Book[] newBooks ) {
        for(Book book : newBooks) addBook(book);
    }

    /**
     * Add a single book to the library.
     *
     * If the book is already present, adds another copy.
     * If the book is new, add it after the existing books.
     * @param b Book to add.
     */
    public void addBook( Book b ) {
        boolean notAvailable = true;
        for(int i =0; i < numBooks; i++) {
            if(books[i].equals(b)) {
                notAvailable = false;
                copies[i]++;
            }
        }

        if(notAvailable) {
            books[numBooks] = b;
            copies[numBooks] = 1;
            checkedOut[numBooks] = 0;
            numBooks++;
        }
    }
  
    /**
     * Checks out a book from the library, if possible.
     * @param b Book to check out.
     * @return String denoting success or failure.
     */
    public String checkOutBook ( Book b ) {
        for(int i = 0; i < numBooks; i++) {
            if(b.equals(books[i])) {
                if(copies[i] > checkedOut[i]) {
                    checkedOut[i]++;
                    return "Checked out!";
                }else {
                    return "All out of copies.";
                }
            }
        }
        return "Book not found.";
    }

    /**
     * Checks in a book to the library, if possible.
     * @param b Book to check in.
     * @return String denoting success or failure.
     */
    public String checkInBook ( Book b ) {
        for(int k = 0; k < numBooks; k++) {
            if(b.equals(books[k])) {
                if(checkedOut[k] > 0) {
                    checkedOut[k]--;
                    return "Checked in!";
                }else {
                    return "All of our copies are already checked in.";
                }
            }
        }
        return "Book not found.";
    }
  
    /**
     * Get a string representation of the entire library collection 
     * and status.
     * @return String representation of library.
     */
    public String toString() {
        String res = "";
        for(int i = 0; i < numBooks; i++) {
            res += i + ". " + books[i] + " : " +
                    (copies[i] - checkedOut[i]) + "/" + copies[i] + "\n"; 
        }
        res += "\n" + statusString();
        return res;
    }
  
    /**
     * Get number of unique books that exist for a given author.
     * @param a The author to check.
     * @return Number of books by the author.
     */
    public int numBooksByAuthor( Author a ) {
        int res = 0;
        for(int i = 0; i < numBooks; i++) {
            if(books[i].getAuthor().isSame(a)) {
                res ++;
            }
        }
        return res;
    }

    /**
     * Returns a String that lists the unique books which exist for a
     * given author, in standard book format, with a newline after
     * each.  If no books are found for the author, returns string
     * that says so.
     * 
     * @param a The author in question.
     * @return String listing books by the author.
     */
    public String listBooksByAuthor( Author a ) { 
        String res = "";
        for(int i = 0; i < numBooks; i++) {
            if(books[i].getAuthor().isSame(a)) {
                res += books[i] + "\n";
            }
        }
        if(res.length() == 0) {
            return "No books by " + a + ".";
        }else {
        return res;
        }
    }  
  
    /**
     * Returns string that lists the unique books with contain the
     * given string within their titles, without regard for case, with
     * a newline after each.  If no books are found containing the
     * string, returns string that indicates that.
     * @param s The string to look for in the titles.
     * @return String listing books that contain given string in titles.
     */
    public String listBooksByTitle( String s ) {
        String res = "";
        for(int i = 0; i < numBooks; i++) {
            if(books[i].getTitle().toLowerCase().contains(s.toLowerCase())) {
                res += books[i] + "\n";
            }
        }
        if(res.length() == 0) {
            return "No books with \"" + s + "\" in the title.";
        }else {
        return res;
        }
    }

    /**
     * Deletes a book entirely from the library.
     * @param b Book to remove.
     * @return String denoting success or failure.
     */
    public String deleteBook( Book b ) {
        // This method is for optional bonus points.
        boolean thereIs = false;
        for(int i = 0; i < numBooks; i++) {
            if(thereIs) {
                books[i-1] = books[i];
                copies[i-1] = copies[i];
                checkedOut[i-1] = checkedOut[i];
            }else if (books[i].equals(b)){
                thereIs = true;
            }
        }if(thereIs) {
            numBooks--;
            return "Book removed.";
        }else {
        return "Book not found.";
        }
    }  
  
}