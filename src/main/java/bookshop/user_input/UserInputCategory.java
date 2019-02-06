package bookshop.user_input;

import bookshop.BookData;
import bookshop.CategoryFunction;
import bookshop.Print;
import bookshop.Validator;
import bookshop.data.Category;

import java.util.Optional;
import java.util.Scanner;

public class UserInputCategory {
    private BookData bookData = BookData.getInstance();
    private CategoryFunction categoryFunction = new CategoryFunction();
    private Print print = new Print();
    private Validator validator = new Validator();

    public void addNewCategoryFromScanner(Scanner scanner) {
        String name;
        do {
            System.out.println("Podaj nazwę kategorii: ");
            name = scanner.nextLine();
        } while (!validator.validateTitle(name));

        System.out.println("Podaj ważność kategorii: ");
        int priority = Integer.parseInt(scanner.nextLine());
        int id = categoryFunction.generateCategoryId();
        Category category = new Category(id, name, priority);
        BookData.getInstance().getCategoriesList().add(category);
        System.out.println("Kategoria dodana: " + category);
    }

    private void modifyCategoryName(Category category, Scanner scanner) {
        String name;
        System.out.println("Nazwa: " + category.getName() + "\n");
        do {
            System.out.println("Wprowadź nową nazwę: ");
            name = scanner.nextLine();
        }
        while (!validator.validateTitle(name));
        category.setName(name);
        System.out.println("Nazwa zmieniona: \n" + category);
    }

    public void editCategoryById(Scanner scanner) {
        print.printCategoriesList(bookData.getCategoriesList());
        String idAsString;
        do {
            System.out.println("Podaj id kategorii: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        Optional<Category> editedCategory = Optional.ofNullable(categoryFunction.getCategoryById(Integer.parseInt(idAsString)));
        if (editedCategory.isPresent()) {
            modifyCategoryName(editedCategory.get(), scanner);
        } else {
            System.out.println("Nie znaleziono kategorii o podanym id!");
        }
    }

    public void removeCategoryOfGivenId(Scanner scanner) {
        String idAsString;
        print.printCategoriesList(bookData.getCategoriesList());
        do {
            System.out.println("Podaj id kategorii do usunięcia: ");
            idAsString = scanner.nextLine();
        }
        while (!validator.validateInputId(idAsString));
        categoryFunction.removeCategory(Integer.parseInt(idAsString));
        System.out.println("Usuwanie kategorii zakończone powodzeniem!");
    }
}
