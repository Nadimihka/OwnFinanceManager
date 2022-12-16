import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaxCategory {

    protected Map<String, List<BuyingItem>> fullList = new HashMap<>();
    protected Map<String, String> categories = new HashMap<>();

    public MaxCategory(File tsvFile) throws IOException {

        try (BufferedReader reader = new BufferedReader
                (new FileReader(tsvFile))) {
            while (reader.ready()) {
                String[] ar = reader.readLine().split("\t");
                categories.put(ar[0], ar[1]);
            }
            //    System.out.println(categories);
        }
    }

    public String defineCategory(String title) {
        if (categories.containsKey(title)) {
            return categories.get(title);
        } else {
            return "другое";
        }
    }

    public void setListInCategory(String item) {
        BuyingItem buyingItem = BuyingItem.loadFromJson(item);
        String category = defineCategory(buyingItem.getTitle());

        List<BuyingItem> list = new ArrayList<>();
        if (fullList.containsKey(category)) {
            list = fullList.get(category);
        }
        list.add(new BuyingItem(buyingItem.getTitle(), buyingItem.getDate(), buyingItem.getSum()));
        fullList.put(category, list);
    }

    public String maxSumForPeriod() {
        MaxSumItem maxSumItem = new MaxSumItem(null, 0);
        for (Map.Entry<String, List<BuyingItem>> entry :
                fullList.entrySet()) {
            List<BuyingItem> list = entry.getValue(); //список покупок в одной категории
            int sum = 0;
            for (int i = 0; i < list.size(); i++) {
                BuyingItem buyingItem = list.get(i);
                sum += buyingItem.getSum();
            }
            if (sum > maxSumItem.sum) {
                maxSumItem.sum = sum;
                maxSumItem.category = entry.getKey();
            }
        }
        return new StatisticForResponse(maxSumItem).saveJson();
    }
}
