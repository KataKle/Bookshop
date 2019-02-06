package bookshop.importers;

import bookshop.*;
import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.data.CoverType;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class BookImporterTest {

    @Test
    public void importBooks() throws IOException {
        BookData bookData = BookData.getInstance();
        CategoryImporter categoryImporter = new CategoryImporter();
        bookData.setCategoriesList(categoryImporter.importCategories("categories.csv"));
        AuthorImporter authorImporter = new AuthorImporter();
        bookData.setAuthorsList(authorImporter.importAuthors("authors.csv"));

        List<Book> testList = Lists.newArrayList(new Book(1, "Clean Code", 132350882, 2008, CoverType.T, Lists.newArrayList(new Author(1, "Robert C.", "Martin", 32)), new Category(3, "Techniki programowania", 2)), new Book(2, "Effective Java (3rd Edition)", 134685997, 2018, CoverType.M, Lists.newArrayList(new Author(2, "Martin", "Fowler", 50)), new Category(1, "Java", 3)));
        BookImporter bookImporter = new BookImporter();
        List<Book> importedList = bookImporter.importBooks("books_test.csv");

        Assert.assertEquals(testList, importedList);
    }

    @Test
    public void badFileNameThrowsException() {
        BookImporter bookImporter = new BookImporter();
        org.junit.jupiter.api.Assertions.assertThrows(IOException.class,
                () -> bookImporter.importBooks("blah.csv"));
    }
}