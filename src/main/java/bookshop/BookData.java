package bookshop;

import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;

import java.util.List;

public class BookData {
    private static BookData INSTANCE;
    private List<Book> booksList;
    private List<Category> categoriesList;
    private List<Author> authorsList;

    public static BookData getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BookData();
        }
        return INSTANCE;
    }

    private BookData() {
    }

    public void setBooksList(List<Book> booksList) {
        this.booksList = booksList;
    }

    public void setCategoriesList(List<Category> categoriesList) {
        this.categoriesList = categoriesList;
    }

    public void setAuthorsList(List<Author> authorsList) {
        this.authorsList = authorsList;
    }

    public List<Book> getBooksList() {
        return booksList;
    }

    public List<Category> getCategoriesList() {
        return categoriesList;
    }

    public List<Author> getAuthorsList() {
        return authorsList;
    }
}
