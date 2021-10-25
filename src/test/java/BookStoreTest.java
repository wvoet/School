

import bookstore.Book;
import bookstore.BookStore;
import org.testng.annotations.Test;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class BookStoreTest {

	@Test
	public void testBookStoreEmpty() {
		BookStore bs = new BookStore("");

		assertEquals(0, bs.getAmtBooks());
	}


	@Test
	public void testBookStoreStandard() {
		BookStore bs = new BookStore("My book store");

		var b1 = new Book("Title1", "Author1", 0);
		var b2 = new Book("Title2", "Author1", 0);
		var b3 = new Book("Title3", "Author2", 0);
		var b4 = new Book("Title4", "Author3", 0);

		bs.addBook(b1);
		bs.addBook(b2);
		bs.addBook(b3);
		bs.addBook(b4);


		assertEquals("My book store", bs.getName());

		assertEquals(4, bs.getAmtBooks());
		assertEquals(2, bs.getBooksByAuthor("Author1").size());
		assertEquals(1, bs.getBooksByAuthor("Author2").size());
		assertEquals(1, bs.getBooksByAuthor("Author3").size());

		assertTrue(bs.getBooksByAuthor("Author1").contains(b1));
		assertTrue(bs.getBooksByAuthor("Author1").contains(b2));
	}



	@Test
	public void testFictitiousAuthor() {
		BookStore bs = new BookStore("");

		// Make sure the application does not crash when we look up authors
		// that do not have any books registered in the book store
		assertEquals(0, bs.getBooksByAuthor("IDoNotExist").size());
	}


	@Test
	public void testFindByPublicationYear() {
		BookStore bs = new BookStore("My book store");

		var b1 = new Book("Title1", "Author1", 2000);
		var b2 = new Book("Title2", "Author1", 1500);
		var b3 = new Book("Title3", "Author2", 2000);
		var b4 = new Book("Title4", "Author3", -300);

		bs.addBook(b1);
		bs.addBook(b2);
		bs.addBook(b3);
		bs.addBook(b4);


		var booksIn2000 = bs.getBooksByPublicationYear(2000);
		assertEquals(2, booksIn2000.size());
		assertTrue(booksIn2000.contains(b1));
		assertTrue(booksIn2000.contains(b3));

		assertEquals(1, bs.getBooksByPublicationYear(1500).size());
		assertEquals(1, bs.getBooksByPublicationYear(-300).size());

		// Should return an empty list
		assertEquals(0, bs.getBooksByPublicationYear(1000).size());
	}
}

