package bookshop;

import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.display_strategy.DisplayBook;

import java.util.List;
import java.util.Map;

public class Print {
    private BookFunction bookFunction = new BookFunction();
    private CategoryFunction categoryFunction = new CategoryFunction();

    public void printBooksList(DisplayBook display, List<Book> booksList) {
        for (Book book : booksList) {
            System.out.println(display.displayBook(book));
        }
        System.out.print("\n");
    }

    public void printCategoriesList(List<Category> categoriesList) {
        for (Category category : categoriesList) {
            System.out.println(category);
        }
        System.out.println("\n");
    }

    public void printAuthorsList(List<Author> authorsList) {
        for (Author author : authorsList) {
            System.out.println(author);
        }
        System.out.println("\n");
    }

    void printAuthorsMap(Map<Author, Integer> map) {
        for (Author author : map.keySet()) {
            System.out.println(author + " : " + map.get(author).toString());
        }
    }

    void printBooksFromCategoryId(int id, List<Book> list, DisplayBook display) {
        List<Book> temp = bookFunction.getOnlyGivenCategoryStream(categoryFunction.getCategoryById(id), list);
        printBooksList(display, temp);
    }
}
