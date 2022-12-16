import com.google.gson.Gson;

public class BuyingItem {
    protected String title;
    protected String date;
    protected int sum;

    public BuyingItem() {
    }

    public BuyingItem(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    public static BuyingItem loadFromJson(String jsonIn) {
        Gson gson = new Gson();
        return gson.fromJson(jsonIn, BuyingItem.class);
    }
}
