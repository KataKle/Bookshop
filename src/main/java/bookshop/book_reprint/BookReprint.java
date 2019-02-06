package bookshop.book_reprint;

public abstract class BookReprint {

    public void orderBookReprint(){
        beginOrder();
        bookReprint();
        endOrderOfReprint();
        mailTo();
    }

    void beginOrder() {
        System.out.println("Rozpoczęto zlecenie dodruku");
    }

    abstract void bookReprint();

    void endOrderOfReprint() {
        System.out.println("Zakończono zlecenie dodruku");
    }

    abstract void mailTo();
}

