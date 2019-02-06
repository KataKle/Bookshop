package bookshop.exporters;

import bookshop.data.Author;
import bookshop.data.Book;
import org.apache.log4j.Logger;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

class BooksExporter {
    private static Logger log = Logger.getLogger(BooksExporter.class.getName());

    void exportBooksList(List<Book> list, String fileName) {
        try (
                BufferedWriter writer = Files.newBufferedWriter(Paths.get(fileName));
                PrintWriter csvPrinter = new PrintWriter(writer)
        ) {
            for (Book book : list) {
                String bookString = String.valueOf(book.getId()) +
                        ";" +
                        book.getTitle() +
                        ";" +
                        book.getIsbn() +
                        ";" +
                        book.getYear() +
                        ";" +
                        book.getCoverType().name() +
                        ";" +
                        getAuthorsAsStringOfIds(book.getAuthors()) +
                        ";" +
                        book.getCategory().getId();
                csvPrinter.println(bookString);
            }
            csvPrinter.flush();
        } catch (IOException e) {
            System.out.println("Eksport do pliku csv nie powiódł się");
            log.error(e.getMessage());
        }
    }

    private String getAuthorsAsStringOfIds(List<Author> list) {
        List<Integer> listOfIds = list.stream().map(Author::getId).collect(Collectors.toList());
        StringBuilder stringBuilder = new StringBuilder();
        for (Integer id : listOfIds) {
            stringBuilder.append(id).append(",");
        }
        return (stringBuilder.substring(0, stringBuilder.length() - 1));
    }
}
