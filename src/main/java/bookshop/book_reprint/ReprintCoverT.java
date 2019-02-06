package bookshop.book_reprint;

public class ReprintCoverT extends BookReprint {
    @Override
    void bookReprint() {
        System.out.println("Dodruk: oprawa twarda");
    }

    @Override
    void mailTo() {
        System.out.println("Zlecenie dodruku w oprawie twardej\n");
    }
}
