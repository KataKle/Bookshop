package bookshop.display_strategy;

import bookshop.data.Book;

public class DisplayTitleFirst implements DisplayBook {

    @Override
    public String displayBook(Book book) {
        return book.getId() +
                ". " +
                book.getTitle() +
                ", " + book.getYear() +
                ", " + book.getIsbn();
    }
}
