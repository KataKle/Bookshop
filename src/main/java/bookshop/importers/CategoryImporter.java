package bookshop.importers;

import bookshop.data.Category;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoryImporter {
    public List<Category> importCategories(String fileName) throws IOException {
        List<Category> importedCategories = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                importedCategories.add(generateCategoryFromString(line));
            }
            return importedCategories;
        }
    }

    private Category generateCategoryFromString(String categoryAsString) {
        String[] split = categoryAsString.trim().split(";");
        int id = Integer.parseInt(split[0]);
        String name = split[1];
        int priority = Integer.parseInt(split[2]);

        return new Category(id, name, priority);
    }
}
