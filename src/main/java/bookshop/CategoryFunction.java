package bookshop;

import bookshop.data.Category;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CategoryFunction {
    private BookData bookData = BookData.getInstance();

    public int generateCategoryId() {
        List<Integer> existingIds = BookData.getInstance()
                .getCategoriesList()
                .stream()
                .map(Category::getId).sorted((o1, o2) -> o2 - o1).collect(Collectors.toList());
        return existingIds.get(0) + 1;
    }

    public Category getCategoryById(int id) {
        for (Category category : bookData.getCategoriesList()) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }

    public void removeCategory(int id) {
        List<Category> temp = Lists.newArrayList(BookData.getInstance().getCategoriesList());
        Optional<Category> category = Optional.ofNullable(getCategoryById(id));
        if (category.isPresent()) {
            temp.remove(category.get());
            BookData.getInstance().setCategoriesList(temp);
        } else {
            System.out.println("Nie znaleziono kategorii o podanym id");
        }
    }
}
