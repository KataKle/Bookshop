package bookshop;

import bookshop.data.Book;
import bookshop.data.Category;
import bookshop.data.CoverType;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

public class BookFunction {
    Book getBookByIsbn(long isbn, List<Book> list) {
        for (Book book : list) {
            if (book.getIsbn() == isbn) {
                return book;
            }
        }
        return null;
    }

    public Optional<Book> getBookByIsbnStream(long isbn, List<Book> list) {
        return list.stream().filter(c -> c.getIsbn() == isbn).findFirst();
    }

    long sum(List<Book> list) {
        long sum = 0;
        for (Book book : list) {
            sum = sum + book.getYear();
        }
        return sum;
    }

    long sumStream(List<Book> list) {
        return list.stream().mapToInt(Book::getYear).sum();
    }

    int numberOfBooksPublishedAfter2007(List<Book> list) {
        int numberOfBooks = 0;
        for (Book book : list) {
            if (book.getYear() > 2007) {
                numberOfBooks = numberOfBooks + 1;
            }
        }
        return numberOfBooks;
    }

    int numberOfBooksPublishedAfter2007Stream(List<Book> list) {
        return (int) list.stream().filter(c -> c.getYear() > 2007).count();
    }

    boolean areAllPublishedAfter2000(List<Book> list) {
        for (Book book : list) {
            if (book.getYear() < 2000) {
                return false;
            }
        }
        return true;
    }

    boolean areAllPublishedAfter2000Stream(List<Book> list) {
        return list.stream().allMatch(c -> c.getYear() > 2000);
    }

    List<Book> getLastTwoBooks(List<Book> list) {
        return list.subList((list.size() - 2), list.size());
    }

    List<Book> getLastTwoBooksStream(List<Book> list) {
        return list.stream().skip(list.size() - 2).collect(Collectors.toList());
    }

    Book getOldestBook(List<Book> list) {
        list.sort(Comparator.comparingInt(Book::getYear));
        return list.get(0);
    }

    Optional<Book> getOldestBookStream(List<Book> list) {
        return list.stream().min(Comparator.comparingInt(Book::getYear));
    }

    Book getYoungestBook(List<Book> list) {
        list.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return list.get(0);
    }

    Optional<Book> getYoungestBookStream(List<Book> list) {
        return list.stream().min((o1, o2) -> o2.getYear() - o1.getYear());
    }

    int getAverageYear(List<Book> list) {
        int sum = 0;
        int counter = 0;
        for (Book book : list) {
            sum = sum + book.getYear();
            counter++;
        }
        if (counter != 0) {
            return sum / counter;
        } else {
            return -1;
        }
    }

    OptionalDouble getAverageYearStream(List<Book> list) {
        return list.stream().mapToInt(Book::getYear).average();
    }

    boolean isAnyBefore2003(List<Book> list) {
        for (Book book : list) {
            if (book.getYear() < 2003) {
                return true;
            }
        }
        return false;
    }

    boolean isAnyBefore2003Stream(List<Book> list) {
        return list.stream().map(Book::getYear).anyMatch(c -> c < 2003);
    }

    List<Book> startsWithCAndAfter2010(List<Book> list) {
        List<Book> wantedBooks = new ArrayList<>();
        for (Book book : list) {
            if (book.getYear() > 2010 && book.getTitle().startsWith("C")) {
                wantedBooks.add(book);
            }
        }
        return wantedBooks;
    }

    List<Book> startsWithCAndAfter2010Stream(List<Book> list) {
        return list.stream().filter(c -> c.getYear() > 2010).filter(c -> c.getTitle().startsWith("C")).collect(Collectors.toList());
    }

    List<Book> sortByYearAscending(List<Book> list) {
        list.sort(Comparator.comparingInt(Book::getYear));
        return list;
    }

    List<Book> sortByYearAscendingStream(List<Book> list) {
        return list.stream().sorted((o1, o2) -> (o1.getYear() - o2.getYear())).collect(Collectors.toList());
    }

    List<Book> sortByYearDescending(List<Book> list) {
        list.sort((o1, o2) -> o2.getYear() - o1.getYear());
        return list;
    }

    List<Book> sortByYearDescendingStream(List<Book> list) {
        return list.stream().sorted((o1, o2) -> (o2.getYear() - o1.getYear())).collect(Collectors.toList());
    }

    List<Book> add100toPublicationYear(List<Book> list) {
        List<Book> yearPlus100List = new ArrayList<>(list);
        for (Book book : yearPlus100List) {
            int year = book.getYear();
            book.setYear(year + 100);
        }
        return yearPlus100List;
    }

    List<Book> add100toPublicationYearStream(List<Book> list) {
        List<Book> yearPlus100List = new ArrayList<>(list);
        yearPlus100List.stream().forEach(c -> c.setYear(c.getYear() + 100));
        return yearPlus100List;
    }

    List<String> titleIfYearIsEvenNumber(List<Book> list) {
        List<String> titlesList = new ArrayList<>();
        for (Book book : list) {
            if (book.getYear() % 2 == 0) {
                titlesList.add(book.getTitle());
            }
        }
        return titlesList;
    }

    List<String> titleIfYearIsEvenNumberStream(List<Book> list) {
        return list.stream().filter(c -> c.getYear() % 2 == 0).map(Book::getTitle).collect(Collectors.toList());
    }

    List<Book> sortAlphabetically(List<Book> list) {
        list.sort(Comparator.comparing(Book::getTitle));
        return list;
    }

    List<Book> sortAlphabeticallyStream(List<Book> list) {
        return list.stream().sorted(Comparator.comparing(Book::getTitle)).collect(Collectors.toList());
    }

    List<Book> getOnlyGivenCategoryStream(Category category, List<Book> list) {
        return list.stream().filter(c -> c.getCategory().equals(category)).collect(Collectors.toList());
    }

    public Book getBookById(int id, List<Book> booksList) {//TODO zrobić coś z nullem
        for (Book book : booksList) {
            if (book.getId() == id) {
                return book;
            }
        }
    return null;}

    public void removeBook(int id) {
        List<Book> temp = Lists.newArrayList(BookData.getInstance().getBooksList());
        Optional<Book> book = Optional.ofNullable(getBookById(id, BookData.getInstance().getBooksList()));
        if (book.isPresent()) {
            temp.remove(book.get());
            BookData.getInstance().setBooksList(temp);
        } else {
            System.out.println("Nie znaleziono książki o podanym id");
        }
    }

    public int generateBookId() {
        List<Integer> existingIds = BookData.getInstance()
                .getBooksList()
                .stream()
                .map(Book::getId).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        return existingIds.get(0) + 1;
    }

    public CoverType generateCoverType(String coverTypeAsString) {
        return CoverType.valueOf(coverTypeAsString.toUpperCase());
    }
}