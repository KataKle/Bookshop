package bookshop;

import java.util.HashMap;
import java.util.Map;


public final class FilesProperties {
    private Map<Key, String> fileNamesMap = new HashMap<>();

    private void setFileNamesMap() {
        fileNamesMap.put(Key.CATEGORIES_FILENAME, "categories.csv");
        fileNamesMap.put(Key.AUTHORS_FILENAME, "authors.csv");
        fileNamesMap.put(Key.BOOKS_FILENAME, "books.csv");
    }

    public final String getFileName(Key key) {
        setFileNamesMap();

        if (fileNamesMap.containsKey(key)) {
            return fileNamesMap.get(key);
        } else {
            throw new IllegalArgumentException("Podaj poprawną nazwę pliku");
        }
    }
}
