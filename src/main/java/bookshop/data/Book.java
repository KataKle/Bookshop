package bookshop.data;

import java.util.List;

public class Book {
    private int id;
    private String title;
    private long isbn;
    private int year;
    private CoverType coverType;
    private List<Author> authors;
    private Category category;

    public Book(int id, String title, long isbn, int year, CoverType coverType, List<Author> authors, Category category) {
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.year = year;
        this.coverType = coverType;
        this.authors = authors;
        this.category = category;
    }

    private String authorsString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Author author : authors) {
            stringBuilder.append(author.getName()).append(" ").append(author.getLastName()).append(", ");
        }
        return stringBuilder.toString();
    }

    @Override
    public String toString() {
        return id + ": " +
                title +
                ", ISBN: " + isbn +
                ", rok wydania: " + year
                + ", oprawa: " + coverType.cover
                + ", "
                + authorsString()
                + category.toString();
    }

    public long getIsbn() {
        return isbn;
    }

    public int getYear() {
        return year;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public CoverType getCoverType() {
        return coverType;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (id != book.id) return false;
        if (isbn != book.isbn) return false;
        if (year != book.year) return false;
        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        if (coverType != book.coverType) return false;
        if (authors != null ? !authors.equals(book.authors) : book.authors != null) return false;
        return category != null ? category.equals(book.category) : book.category == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (int) (isbn ^ (isbn >>> 32));
        result = 31 * result + year;
        result = 31 * result + (coverType != null ? coverType.hashCode() : 0);
        result = 31 * result + (authors != null ? authors.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
