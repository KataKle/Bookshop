package bookshop;

import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.data.CoverType;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.*;

import static org.junit.Assert.*;

public class BookFunctionTest {
    private List<Author> emptyAuthorsList = new ArrayList<>();
    private Category testCategory1 = new Category(0, "Test category1", 3);
    private Category testCategory2 = new Category(0, "Test category2", 2);
    private BookFunction bookFunction = new BookFunction();

    private Book book1 = new Book(1, "BookTest", 4736583, 2006, CoverType.M, emptyAuthorsList, testCategory1);
    private Book book2 = new Book(2, "C Book Test", 38588, 1999, CoverType.T, emptyAuthorsList, testCategory1);
    private Book book3 = new Book(3, "Das Buch", 345, 2009, CoverType.M, emptyAuthorsList, testCategory1);
    private Book book4 = new Book(4, "E Test Book", 34536, 2013, CoverType.M, emptyAuthorsList, testCategory2);
    private Book book5 = new Book(5, "C Test Book", 74224, 2015, CoverType.M, emptyAuthorsList, testCategory2);
    private Book book6 = new Book(6, "F Test Book", 35463, 2008, CoverType.M, emptyAuthorsList, testCategory2);

    private List<Book> testBooks = Lists.newArrayList(book1, book2, book3, book4, book5, book6);

    @Test
    public void getBookByIsbn() {
        Book testBook = bookFunction.getBookByIsbn(4736583, testBooks);
        Assertions.assertEquals(book1, testBook);
    }

    @Test
    public void getBookByIsbnStream() {
        Optional<Book> testBookStream = bookFunction.getBookByIsbnStream(4736583, testBooks);
        Assertions.assertEquals(book1, testBookStream.get());
    }

    @Test
    public void wrongIsbn() {
        Assertions.assertNull(bookFunction.getBookByIsbn(476376, testBooks));
    }

    @Test
    public void wrongIsbnStream() {
        Assertions.assertFalse(bookFunction.getBookByIsbnStream(437346, testBooks).isPresent());
    }

    @Test
    public void sum() {
        Assertions.assertEquals(12050, bookFunction.sum(testBooks));
    }

    @Test
    public void sumStream() {
        Assertions.assertEquals(12050, bookFunction.sumStream(testBooks));
    }

    @Test
    public void numberOfBooksPublishedAfter2007() {
        Assertions.assertEquals(4, bookFunction.numberOfBooksPublishedAfter2007(testBooks));
    }

    @Test
    public void numberOfBooksPublishedAfter2007Stream() {
        Assertions.assertEquals(4, bookFunction.numberOfBooksPublishedAfter2007Stream(testBooks));
    }

    @Test
    public void areAllPublishedAfter2000() {
        assertFalse(bookFunction.areAllPublishedAfter2000(testBooks));
    }

    @Test
    public void areAllPublishedAfter2000Stream() {
        assertFalse(bookFunction.areAllPublishedAfter2000Stream(testBooks));
    }

    @Test
    public void getLastTwoBooks() {
        List<Book> expectedList = Lists.newArrayList(book5, book6);

        Assertions.assertEquals(expectedList, bookFunction.getLastTwoBooks(testBooks));
    }

    @Test
    public void getLastTwoBooksStream() {
        List<Book> expectedList = Lists.newArrayList(book5, book6);

        Assertions.assertEquals(expectedList, bookFunction.getLastTwoBooksStream(testBooks));
    }

    @Test
    public void getOldestBook() {
        Book testBook = bookFunction.getOldestBook(testBooks);
        Assertions.assertEquals(book2, testBook);
    }

    @Test
    public void getOldestBookStream() {
        Optional<Book> testBook = bookFunction.getOldestBookStream(testBooks);
        Assertions.assertEquals(book2, testBook.get());
    }

    @Test
    public void getYoungestBook() {
        Book testBook = bookFunction.getYoungestBook(testBooks);
        Assertions.assertEquals(book5, testBook);
    }

    @Test
    public void getYoungestBookStream() {
        Optional<Book> testBook = bookFunction.getYoungestBookStream(testBooks);
        Assertions.assertEquals(book5, testBook.get());
    }

    @Test
    public void getAverageYear() {
        int testAverage = bookFunction.getAverageYear(testBooks);
        int average = 2008;

        Assertions.assertEquals(average, testAverage);
    }

    @Test
    public void getAverageYearStream() {
        int testAverage = (int) Math.round(bookFunction.getAverageYearStream(testBooks).getAsDouble());
        int average = 2008;

        Assertions.assertEquals(average, testAverage);
    }

    @Test
    public void isAnyBefore2003() {
        boolean testAnyBefore2003 = bookFunction.isAnyBefore2003(testBooks);

        Assertions.assertTrue(testAnyBefore2003);
    }

    @Test
    public void isAnyBefore2003Stream() {
        boolean testAnyBefore2003 = bookFunction.isAnyBefore2003Stream(testBooks);

        Assertions.assertTrue(testAnyBefore2003);
    }

    @Test
    public void startsWithCAndAfter2010() {
        List<Book> testList = bookFunction.startsWithCAndAfter2010(testBooks);
        List<Book> expectedList = Lists.newArrayList(book5);

        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    public void startsWithCAndAfter2010Stream() {
        List<Book> testList = bookFunction.startsWithCAndAfter2010Stream(testBooks);
        List<Book> expectedList = Lists.newArrayList(book5);

        Assertions.assertEquals(expectedList, testList);
    }

    @Test
    public void sortByYearAscending() {
        List<Book> sortedList = Lists.newArrayList(book2, book1, book6, book3, book4, book5);
        List<Book> testSortedList = bookFunction.sortByYearAscending(testBooks);

        Assertions.assertEquals(sortedList, testSortedList);
    }

    @Test
    public void sortByYearAscendingStream() {
        List<Book> sortedList = Lists.newArrayList(book2, book1, book6, book3, book4, book5);
        List<Book> testSortedList = bookFunction.sortByYearAscendingStream(testBooks);

        Assertions.assertEquals(sortedList, testSortedList);
    }

    @Test
    public void sortByYearDescending() {
        List<Book> sortedList = Lists.newArrayList(book5, book4, book3, book6, book1, book2);
        List<Book> testSortedList = bookFunction.sortByYearDescending(testBooks);

        Assertions.assertEquals(sortedList, testSortedList);
    }

    @Test
    public void sortByYearDescendingStream() {
        List<Book> sortedList = Lists.newArrayList(book5, book4, book3, book6, book1, book2);
        List<Book> testSortedList = bookFunction.sortByYearDescendingStream(testBooks);

        Assertions.assertEquals(sortedList, testSortedList);
    }

    @Test
    public void add100toPublicationYear() {
        Book book1Plus100 = new Book(1, "BookTest", 4736583, 2106, CoverType.M, emptyAuthorsList, testCategory1);
        Book book2Plus100 = new Book(2, "C Book Test", 38588, 2099, CoverType.T, emptyAuthorsList, testCategory1);
        Book book3Plus100 = new Book(3, "Das Buch", 345, 2109, CoverType.M, emptyAuthorsList, testCategory1);
        Book book4Plus100 = new Book(4, "E Test Book", 34536, 2113, CoverType.M, emptyAuthorsList, testCategory2);
        Book book5Plus100 = new Book(5, "C Test Book", 74224, 2115, CoverType.M, emptyAuthorsList, testCategory2);
        Book book6Plus100 = new Book(6, "F Test Book", 35463, 2108, CoverType.M, emptyAuthorsList, testCategory2);

        List<Book> expectedList = Lists.newArrayList(book1Plus100, book2Plus100, book3Plus100, book4Plus100, book5Plus100, book6Plus100);

        Assertions.assertEquals(expectedList, bookFunction.add100toPublicationYear(testBooks));
    }

    @Test
    public void add100toPublicationYearStream() {
        Book book1Plus100 = new Book(1, "BookTest", 4736583, 2106, CoverType.M, emptyAuthorsList, testCategory1);
        Book book2Plus100 = new Book(2, "C Book Test", 38588, 2099, CoverType.T, emptyAuthorsList, testCategory1);
        Book book3Plus100 = new Book(3, "Das Buch", 345, 2109, CoverType.M, emptyAuthorsList, testCategory1);
        Book book4Plus100 = new Book(4, "E Test Book", 34536, 2113, CoverType.M, emptyAuthorsList, testCategory2);
        Book book5Plus100 = new Book(5, "C Test Book", 74224, 2115, CoverType.M, emptyAuthorsList, testCategory2);
        Book book6Plus100 = new Book(6, "F Test Book", 35463, 2108, CoverType.M, emptyAuthorsList, testCategory2);

        List<Book> expectedList = Lists.newArrayList(book1Plus100, book2Plus100, book3Plus100, book4Plus100, book5Plus100, book6Plus100);

        Assertions.assertEquals(expectedList, bookFunction.add100toPublicationYearStream(testBooks));
    }

    @Test
    public void titleIfYearIsEvenNumber() {
        List<String> expectedList = Lists.newArrayList("BookTest", "F Test Book");
        Assertions.assertEquals(expectedList, bookFunction.titleIfYearIsEvenNumber(testBooks));
    }

    @Test
    public void titleIfYearIsEvenNumberStream() {
        List<String> expectedList = Lists.newArrayList("BookTest", "F Test Book");
        Assertions.assertEquals(expectedList, bookFunction.titleIfYearIsEvenNumberStream(testBooks));
    }

    @Test
    public void sortAlphabetically() {
        List<Book> expectedList = Lists.newArrayList(book1, book2, book5, book3, book4, book6);

        Assertions.assertEquals(expectedList, bookFunction.sortAlphabetically(testBooks));
    }

    @Test
    public void sortAlphabeticallyStream() {
        List<Book> expectedList = Lists.newArrayList(book1, book2, book5, book3, book4, book6);

        Assertions.assertEquals(expectedList, bookFunction.sortAlphabeticallyStream(testBooks));
    }

    @Test
    public void getOnlyGivenCategoryStream() {
        List<Book> expectedList = Lists.newArrayList(book4,book5,book6);

        Assertions.assertEquals(expectedList,bookFunction.getOnlyGivenCategoryStream(testCategory2,testBooks));
    }
    @Test
    public void getBookById() {
        Assertions.assertEquals(book3,bookFunction.getBookById(3,testBooks));
    }

    @Test
    public void generateCoverType() {
        CoverType expectedCoverType = CoverType.M;

        Assertions.assertEquals(expectedCoverType, bookFunction.generateCoverType("m"));
    }
}