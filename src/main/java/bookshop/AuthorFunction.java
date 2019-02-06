package bookshop;

import bookshop.data.Author;
import bookshop.data.Book;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class AuthorFunction {
    private BookData bookData = BookData.getInstance();

    public int generateAuthorId() {
        List<Integer> existingIds = BookData.getInstance()
                .getAuthorsList()
                .stream()
                .map(Author::getId).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        return existingIds.get(0) + 1;
    }

    Map<Author, Integer> groupAuthorsByNumberOfBooks() {
        HashMap<Author, Integer> authorsMap = new HashMap<>();
        for (Book book : BookData.getInstance().getBooksList()) {
            for (Author author : book.getAuthors()) {
                if (authorsMap.containsKey(author)) {
                    Integer newQuantity = authorsMap.get(author) + 1;
                    authorsMap.put(author, newQuantity);
                } else {
                    authorsMap.put(author, 1);
                }
            }
        }
        return authorsMap;
    }

    public Author getAuthorById(int id) {
        for (Author author : bookData.getAuthorsList()) {
            if (author.getId() == id) {
                return author;
            }
        }
        return null;
    }

    public void removeAuthor(int id) {
        List<Author> temp = Lists.newArrayList(BookData.getInstance().getAuthorsList());
        Optional<Author> author = Optional.ofNullable(getAuthorById(id));
        if (author.isPresent()) {
            temp.remove(author.get());
            BookData.getInstance().setAuthorsList(temp);
        } else {
            System.out.println("Nie znaleziono autora o podanym id");
        }
    }
}

