package bookshop.data;

public enum CoverType {
    M("miękka"),
    T("twarda");

    String cover;

    CoverType(String cover) {
        this.cover = cover;
    }
}
