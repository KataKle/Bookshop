package bookshop.exporters;

import bookshop.FilesProperties;
import bookshop.Key;
import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;

import java.util.List;

public class CsvExporter {
    private AuthorExporter authorExporter = new AuthorExporter();
    private BooksExporter booksExporter = new BooksExporter();
    private CategoriesExporter categoriesExporter = new CategoriesExporter();
    private FilesProperties filesProperties = new FilesProperties();

    public void exportAuthorsToCsv (List<Author> authors){
        authorExporter.exportAuthorsList(authors,filesProperties.getFileName(Key.AUTHORS_FILENAME));
    }

    public void exportCategoriesToCsv (List<Category> categories){
        categoriesExporter.exportCategoriesList(categories,filesProperties.getFileName(Key.CATEGORIES_FILENAME));
    }

    public void exportBooksToCsv (List<Book> books){
        booksExporter.exportBooksList(books,filesProperties.getFileName(Key.BOOKS_FILENAME));
    }

    public void exportAllToCsv (List<Book> books, List <Author> authors, List <Category> categories){
        authorExporter.exportAuthorsList(authors,filesProperties.getFileName(Key.AUTHORS_FILENAME));
        categoriesExporter.exportCategoriesList(categories,filesProperties.getFileName(Key.CATEGORIES_FILENAME));
        booksExporter.exportBooksList(books,filesProperties.getFileName(Key.BOOKS_FILENAME));
    }

}
