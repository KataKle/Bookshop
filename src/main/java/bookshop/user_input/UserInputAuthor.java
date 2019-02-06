package bookshop.user_input;

import bookshop.*;
import bookshop.data.Author;
import bookshop.data.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class UserInputAuthor {
    private AuthorFunction authorFunction = new AuthorFunction();
    private BookData bookData = BookData.getInstance();
    private Validator validator = new Validator();
    private Print print = new Print();

    public void addNewAuthorFromScanner(Scanner scanner) {
        String name;
        String lastName;
        String age;
        do {
            System.out.println("Podaj imię autora: ");
            name = scanner.nextLine();
        }
        while (!validator.validateInputStrings(name));
        do {
            System.out.println("Imię: " + name + "\n" + "Podaj nazwisko autora: ");
            lastName = scanner.nextLine();
        }
        while (!validator.validateInputStrings(lastName));
        do {
            System.out.println("Imię: " + name + ", nazwisko: " + lastName + "\n" + "Podaj wiek autora: ");
            age = scanner.nextLine();
        }
        while (!validator.validateInputAge(age));
        int id = authorFunction.generateAuthorId();
        Author newAuthor = new Author(id, name, lastName, Integer.parseInt(age));
        BookData.getInstance().getAuthorsList().add(newAuthor);
        System.out.println("Autor dodany: " + newAuthor);
    }

    private void modifyAuthorsAge(Author author, Scanner scanner) {
        System.out.println("Wiek: " + author.getAge());
        String age;
        do {
            System.out.print("\nWprowadź nowy wiek: ");
            age = scanner.nextLine();
        }
        while (!validator.validateInputAge(age));
        author.setAge(Integer.parseInt(age));
        System.out.println("Wiek zmieniony: \n" + author);
    }

    public void modifyAuthorsAgeById(Scanner scanner) {
        print.printAuthorsList(bookData.getAuthorsList());
        String idAsString;
        do {
            System.out.println("Wprowadź id autora: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        int id = Integer.parseInt(idAsString);
        Optional<Author> editedAuthor = Optional.ofNullable(authorFunction.getAuthorById(id));
        if (editedAuthor.isPresent()) {
            modifyAuthorsAge(editedAuthor.get(), scanner);
        } else {
            System.out.println("Nie znaleziono autora o podanym id!");
        }
    }

    public List<Book> findBooksOfGivenAuthor(Scanner scanner) {
        List<Book> authoredBooks = new ArrayList<>();
        print.printAuthorsList(bookData.getAuthorsList());
        System.out.println("Wprowadź id autora: ");
        int id = Integer.parseInt(scanner.nextLine());
        Optional<Author> wantedAuthor = Optional.ofNullable(authorFunction.getAuthorById(id));
        if (wantedAuthor.isPresent()) {
            for (Book book : bookData.getBooksList()) {
                if (book.getAuthors().contains(wantedAuthor.get())) {
                    authoredBooks.add(book);
                }
            }
        } else {
            System.out.println("Nie znaleziono autora o podanym id!");
        }
        return authoredBooks;
    }

    public void removeAuthorOfGivenId(Scanner scanner) {
        String idAsString;
        print.printAuthorsList(bookData.getAuthorsList());
        do {
            System.out.println("Podaj id autora do usunięcia: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        authorFunction.removeAuthor(Integer.parseInt(idAsString));
        System.out.println("Usuwanie autora zakończone powodzeniem!");
    }
}

