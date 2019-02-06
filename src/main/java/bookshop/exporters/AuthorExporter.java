package bookshop.exporters;

import bookshop.data.Author;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

class AuthorExporter {
    private static Logger log = Logger.getLogger(AuthorExporter.class.getName());

    void exportAuthorsList(List<Author> list, String fileName) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));

                CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)

        ) {
            for (Author author : list) {
                String authorString = String.valueOf(author.getId()) +
                        ";" +
                        author.getName() +
                        ";" +
                        author.getLastName() +
                        ";" +
                        author.getAge();
                csvPrinter.printRecord(authorString);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.out.println("Eksport do pliku csv nie powiódł się");
            log.error(e.getMessage());
        }
    }

}
