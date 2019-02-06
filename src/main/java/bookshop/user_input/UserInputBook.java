package bookshop.user_input;

import bookshop.*;
import bookshop.book_reprint.BookReprint;
import bookshop.book_reprint.ReprintCoverM;
import bookshop.book_reprint.ReprintCoverT;
import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.data.CoverType;
import bookshop.display_strategy.DisplayBook;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInputBook {
    private BookFunction bookFunction = new BookFunction();
    private AuthorFunction authorFunction = new AuthorFunction();
    private CategoryFunction categoryFunction = new CategoryFunction();
    private BookData bookData = BookData.getInstance();
    private Validator validator = new Validator();
    private Print print = new Print();

    private void modifyBookTitle(Book book, Scanner scanner) {
        System.out.println("Tytuł: " + book.getTitle() + "\n");
        String newTitle;
        do {
            System.out.println("Wprowadź nowy tytuł: ");
            newTitle = scanner.nextLine();
        }
        while (!validator.validateTitle(newTitle));
        book.setTitle(newTitle);
        System.out.println("Tytuł zmieniony: \n" + book);
    }

    public void addNewBookFromScanner(Scanner scanner) {
        String title;
        String isbnString;
        String yearString;
        String coverTypeString;
        String authorIdAsString;
        int authorId;
        List<Author> authors = new ArrayList<>();
        Category category;

        int id = bookFunction.generateBookId();

        do {
            System.out.println("Podaj tytuł książki: ");
            title = scanner.nextLine();
        }
        while (!validator.validateTitle(title));

        do {
            System.out.println("Podaj isbn książki: ");
            isbnString = scanner.nextLine();
        }
        while (!validator.validateInputIsbn(isbnString));

        do {
            System.out.println("Podaj rok wydania książki: ");
            yearString = scanner.nextLine();
        }
        while (!validator.validateYear(yearString));

        do {
            System.out.println("Podaj rodzaj okładki:\n\"M\" - okładka miękka\n\"T\" - okładka twarda ");
            coverTypeString = scanner.nextLine();
        }
        while (!validator.validateCoverType(coverTypeString));

        print.printAuthorsList(bookData.getAuthorsList());
        do {
            System.out.println("Wprowadź id autora: ");
            authorIdAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(authorIdAsString));
        authorId = Integer.parseInt(authorIdAsString);
        Optional<Author> wantedAuthor = Optional.ofNullable(authorFunction.getAuthorById(authorId));
        if (wantedAuthor.isPresent()) {
            authors.add(wantedAuthor.get());
        } else {
            System.out.println("Nie znaleziono autora o podanym id!");
        }

        print.printCategoriesList(bookData.getCategoriesList());
        String idAsString;
        do {
            System.out.println("Podaj id kategorii: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        Optional<Category> editedCategory = Optional.ofNullable(categoryFunction.getCategoryById(Integer.parseInt(idAsString)));
        if (editedCategory.isPresent()) {
            category = editedCategory.get();
        } else {
            System.out.println("Nie znaleziono kategorii o podanym id!");
            category = null;
        }

        List<Book> temp = new ArrayList<>(bookData.getBooksList());
        temp.add(new Book(id, title, Long.parseLong(isbnString), Integer.parseInt(yearString), bookFunction
                .generateCoverType(coverTypeString), authors, category));
        bookData.setBooksList(temp);
    }

    public void modifyBookOfGivenId(Scanner scanner, DisplayBook display) {
        print.printBooksList(display, bookData.getBooksList());
        System.out.println("Wprowadź id książki: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Book> editedBook = Optional.ofNullable(bookFunction.getBookById(id, bookData.getBooksList()));
        if (editedBook.isPresent()) {
            modifyBookTitle(editedBook.get(), scanner);
        } else {
            System.out.println("Nie znaleziono książki o podanym id!");
        }
    }

    public void showBookOfGivenIsbn(Scanner scanner, DisplayBook display) {
        String wantedIsbn;
        do {
            System.out.println("Wprowadź ISBN: ");
            wantedIsbn = scanner.nextLine();
        }
        while (!validator.validateInputIsbn(wantedIsbn));
        Optional<Book> wantedBook = bookFunction.getBookByIsbnStream(Long.parseLong(wantedIsbn), bookData.getBooksList());
        if (wantedBook.isPresent()) {
            System.out.println(display.displayBook(wantedBook.get()) + "\n");
        } else {
            System.out.println("Nie znaleziono podanej książki!");
        }
    }

    public void removeBookOfGivenId(Scanner scanner, DisplayBook display) {
        String idAsString;
        print.printBooksList(display, bookData.getBooksList());
        do {
            System.out.println("Podaj id książki do usunięcia: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        bookFunction.removeBook(Integer.parseInt(idAsString));
        System.out.println("Usuwanie książki zakończone powodzeniem!");
    }

    public void orderChosenBookReprint(Scanner scanner, DisplayBook display) {
        BookReprint bookReprint;
        Book chosenBook;
        String idAsString;
        print.printBooksList(display, bookData.getBooksList());
        do {
            System.out.println("Podaj id książki: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        chosenBook = bookFunction.getBookById(Integer.parseInt(idAsString), bookData.getBooksList());
        if (chosenBook!=null&&chosenBook.getCoverType().equals(CoverType.M)) {
            bookReprint = new ReprintCoverM();
        } else if (chosenBook!=null&&chosenBook.getCoverType().equals(CoverType.T)) {
            bookReprint = new ReprintCoverT();
        } else {
            throw new IllegalArgumentException("Nie rozpoznano typu okładki!");
        }
        bookReprint.orderBookReprint();
    }
}
