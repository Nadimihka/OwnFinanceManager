import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

@DisplayName("Тестирование: MaxCategory")
public class MaxCategoryTest {
    MaxCategory maxCategory = new MaxCategory(new File("categories.tsv"));
    BuyingItem buyingItem = new BuyingItem();

    public MaxCategoryTest() throws IOException {
    }

    @DisplayName("Тестируем определение категории")
    @Test
    public void defineCategoryTest() {
        String item = "{\"title\": \"булка\", \"date\": \"2022.3.12.\", \"sum\": 200}";
        buyingItem = BuyingItem.loadFromJson(item);
        Assertions.assertEquals("еда",
                maxCategory.defineCategory(buyingItem.getTitle()));
    }

    @DisplayName("Тестируем вычисление максимальной суммы")
    @Test
    public void maxSumForPeriodTest() {
        String item = "{\"title\": \"булка\", \"date\": \"2022.3.12.\", \"sum\": 200}";
        maxCategory.setListInCategory(item);
        Assertions.assertEquals("{\"maxCategory\":{\"category\":\"еда\",\"sum\":200}}",
                maxCategory.maxSumForPeriod());
    }
}
