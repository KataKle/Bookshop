package bookshop.display_strategy;

import bookshop.data.Book;

public class DisplayIsbnFirst implements DisplayBook {
    @Override
    public String displayBook(Book book) {
        return book.getId() +
                ". " +
                book.getIsbn() +
                ", " +
                book.getTitle() +
                " " +
                book.getYear();
    }
}
