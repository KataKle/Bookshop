package bookshop.display_strategy;

import bookshop.data.Book;

public class DisplayYearFirst implements DisplayBook {
    @Override
    public String displayBook(Book book) {
        return book.getId() +
                ". " +
                book.getYear() +
                " " + book.getTitle() +
                ", " + book.getIsbn();
    }
}
