package bookshop.importers;

import bookshop.data.Author;

import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

public class AuthorImporterTest {

    @Test
    public void importAuthors() throws IOException {
        AuthorImporter authorImporter = new AuthorImporter();
        List<Author> importedList = authorImporter.importAuthors("authors_test.csv");
        List<Author> testList = Lists.newArrayList(new Author(1,"Robert C.","Martin",32),new Author(2, "Martin", "Fowler", 50), new Author(3,"Kent", "Beck", 54));

        Assert.assertEquals(testList,importedList);
    }
}