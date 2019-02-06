package bookshop.book_reprint;

public class ReprintCoverM extends BookReprint {
    @Override
    void bookReprint() {
        System.out.println("Dodruk: oprawa miękka");
    }

    @Override
    void mailTo() {
        System.out.println("Zlecenie dodruku w oprawie miękkiej\n");
    }

}
