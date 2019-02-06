package bookshop.importers;

import bookshop.data.Author;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AuthorImporter {
    public List<Author> importAuthors(String fileName) throws IOException {
        List<Author> importedAuthors = new ArrayList<>();
        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                importedAuthors.add(generateAuthorFromString(line));
            }

            return importedAuthors;
        }
    }

    private Author generateAuthorFromString(String authorAsString) {
        String[] split = authorAsString.trim().split(";");
        int id = Integer.parseInt(split[0]);
        String name = split[1];
        String lastName = split[2];
        int age = Integer.parseInt(split[3]);

        return new Author(id, name, lastName, age);
    }
}
