package bookshop.exporters;

import bookshop.data.Category;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class CategoriesExporter {
    private static Logger log = Logger.getLogger(CategoriesExporter.class.getName());

    void exportCategoriesList(List<Category> list, String fileName) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)
        ) {
            for (Category category : list) {
                String categoryString = String.valueOf(category.getId()) +
                        ";" +
                        category.getName() +
                        ";" +
                        category.getPriority();
                csvPrinter.printRecord(categoryString);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.out.println("Eksport do pliku csv nie powiódł się");
            log.error(e.getMessage());
        }
    }
}
