package bookshop.importers;

import bookshop.*;
import bookshop.data.Author;
import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.data.CoverType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class BookImporter {
    private BookFunction bookFunction = new BookFunction();

    public List<Book> importBooks(String fileName) throws IOException {
        List<Book> importedBooks = new ArrayList<>();

        try (FileReader fileReader = new FileReader(fileName);
             BufferedReader reader = new BufferedReader(fileReader)) {
            String line;
            while ((line = reader.readLine()) != null) {
                importedBooks.add(generateBookFromString(line));
            }
        }
        return importedBooks;
    }

    private Book generateBookFromString(String bookAsString) {
        String[] split = bookAsString.trim().split(";");
        int id = Integer.parseInt(split[0]);
        String title = split[1];
        long isbn = Long.parseLong(split[2]);
        int year = Integer.parseInt(split[3]);
        CoverType coverType = bookFunction.generateCoverType(split[4]);
        List<Author> authors = generateAuthorsList(split[5]);
        Category category = generateCategory(split[6]);

        return new Book(id, title, isbn, year, coverType, authors, category);
    }

    private List<Author> generateAuthorsList(String authorsListAsString) {
        List<Author> authors = new ArrayList<>();
        String[] authorsArray = authorsListAsString.split(",");
        for (Author author : BookData.getInstance().getAuthorsList()) {
            for (String anAuthorsArray : authorsArray) {
                if (Integer.parseInt(anAuthorsArray) == author.getId()) {
                    authors.add(author);
                }
            }
        }
        return authors;
    }

    private Category generateCategory(String categoryAsString) {
        Category category = null;
        for (Category cat : BookData.getInstance().getCategoriesList()) {
            if (Integer.parseInt(categoryAsString) == cat.getId()) {
                category = cat;
            }
        }
        return category;
    }
}

