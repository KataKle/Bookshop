package bookshop.importers;

import bookshop.data.Category;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class CategoryImporterTest {

    @Test
    public void importCategories() throws IOException {
        CategoryImporter categoryImporter = new CategoryImporter();
        List<Category> importedList = categoryImporter.importCategories("categories_test.csv");
        int sizeOfImportedList = importedList.size();

        Assert.assertEquals(3, sizeOfImportedList);

        Category testCategory = new Category(3, "Techniki programowania", 2);
        Category importedCategory = importedList.get(2);

        Assert.assertEquals(testCategory, importedCategory);
    }
}