package bookshop;

import bookshop.exporters.CsvExporter;
import bookshop.importers.AuthorImporter;
import bookshop.importers.BookImporter;
import bookshop.importers.CategoryImporter;
import bookshop.display_strategy.DisplayBook;
import bookshop.display_strategy.DisplayIsbnFirst;
import bookshop.display_strategy.DisplayTitleFirst;
import bookshop.display_strategy.DisplayYearFirst;
import bookshop.user_input.UserInputAuthor;
import bookshop.user_input.UserInputBook;
import bookshop.user_input.UserInputCategory;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.util.Scanner;

public class BookshopMain {
    private static final FilesProperties filesProperties = new FilesProperties();
    private static Logger log = Logger.getLogger(BookshopMain.class.getName());

    public static void main(String[] args) {
        boolean run = true;
        BookData bookData = BookData.getInstance();
        Print print = new Print();
        UserInputAuthor userInputAuthor = new UserInputAuthor();
        UserInputBook userInputBook = new UserInputBook();
        UserInputCategory userInputCategory = new UserInputCategory();
        AuthorFunction authorFunction = new AuthorFunction();
        DisplayBook chosenDisplay = new DisplayTitleFirst();
        CsvExporter exporter = new CsvExporter();


        try {
            CategoryImporter categoryImporter = new CategoryImporter();
            bookData.setCategoriesList(categoryImporter.importCategories(filesProperties.getFileName(Key.CATEGORIES_FILENAME)));
            AuthorImporter authorImporter = new AuthorImporter();
            bookData.setAuthorsList(authorImporter.importAuthors(filesProperties.getFileName(Key.AUTHORS_FILENAME)));
            BookImporter bookImporter = new BookImporter();
            bookData.setBooksList(bookImporter.importBooks(filesProperties.getFileName(Key.BOOKS_FILENAME)));
        } catch (IOException e) {
            System.out.println("Nie udało się zaimportować pliku");
            log.error("Nie udało się zaimportować pliku " + e.getMessage());
            run = false;
        }
        while (run) {
            Menu.printMenu();
            BookFunction bookFunction = new BookFunction();
            Scanner scanner = new Scanner(System.in);
            try {
                int optionSelected = Integer.parseInt(scanner.nextLine());
                switch (optionSelected) {
                    case 1:
                        run = false;
                        break;
                    case 2:
                        System.out.println("bookshop@bookshop.com\n");
                        break;
                    case 3:
                        print.printBooksList(chosenDisplay, bookData.getBooksList());
                        break;
                    case 4:
                        print.printAuthorsList(bookData.getAuthorsList());
                        break;
                    case 5:
                        print.printCategoriesList(bookData.getCategoriesList());
                        break;
                    case 6:
                        userInputBook.showBookOfGivenIsbn(scanner, chosenDisplay);
                        break;
                    case 7:
                        System.out.println(chosenDisplay.displayBook(bookFunction.getOldestBook(bookData.getBooksList())));
                        break;
                    case 8:
                        print.printBooksFromCategoryId(1, bookData.getBooksList(), chosenDisplay);
                        break;
                    case 9:
                        print.printBooksFromCategoryId(2, bookData.getBooksList(), chosenDisplay);
                        break;
                    case 10:
                        print.printBooksFromCategoryId(3, bookData.getBooksList(), chosenDisplay);
                        break;
                    case 11:
                        userInputAuthor.addNewAuthorFromScanner(scanner);
                        break;
                    case 12:
                        userInputCategory.addNewCategoryFromScanner(scanner);
                        break;
                    case 13:
                        userInputBook.modifyBookOfGivenId(scanner, chosenDisplay);
                        break;
                    case 14:
                        userInputAuthor.modifyAuthorsAgeById(scanner);
                        break;
                    case 15:
                        userInputCategory.editCategoryById(scanner);
                        break;
                    case 16:
                        exporter.exportAuthorsToCsv(bookData.getAuthorsList());
                        break;
                    case 17:
                        exporter.exportCategoriesToCsv(bookData.getCategoriesList());
                        break;
                    case 18:
                        exporter.exportBooksToCsv(bookData.getBooksList());
                        break;
                    case 19:
                        print.printBooksList(chosenDisplay, userInputAuthor.findBooksOfGivenAuthor(scanner));
                        break;
                    case 20:
                        print.printAuthorsMap(authorFunction.groupAuthorsByNumberOfBooks());
                        break;
                    case 21:
                       exporter.exportAllToCsv(bookData.getBooksList(), bookData.getAuthorsList(), bookData.getCategoriesList());
                        break;
                    case 22:
                        userInputBook.removeBookOfGivenId(scanner, chosenDisplay);
                        break;
                    case 23:
                        userInputAuthor.removeAuthorOfGivenId(scanner);
                        break;
                    case 24:
                        userInputCategory.removeCategoryOfGivenId(scanner);
                        break;
                    case 25:
                        userInputBook.orderChosenBookReprint(scanner,chosenDisplay);
                        break;
                    case 97:
                        chosenDisplay = new DisplayYearFirst();
                        System.out.println("Zmieniono sposób wyświetlania na: rok, tytuł, ISBN");
                        break;
                    case 98:
                        chosenDisplay = new DisplayTitleFirst();
                        System.out.println("Zmieniono sposób wyświetlania na: tytuł, rok, ISBN");
                        break;
                    case 99:
                        chosenDisplay = new DisplayIsbnFirst();
                        System.out.println("Zmieniono sposób wyświetlania na: ISBN, tytuł, rok");
                        break;
                    default:
                        System.out.println("Niepoprawna komenda. Spróbuj ponownie");
                        break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Wprowadź liczbę!\n");
                log.info("Nie podano prawidłowej liczby " + e.getMessage());
            }
        }
    }
}

