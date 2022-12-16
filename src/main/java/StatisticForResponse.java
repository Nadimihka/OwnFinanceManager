import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class StatisticForResponse {
    protected MaxSumItem maxCategory;

    public StatisticForResponse(MaxSumItem maxSumItem) {
        this.maxCategory = maxSumItem;
    }

    public String saveJson() {
        Gson gson = new Gson();
       /* Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();*/
        String json = gson.toJson(this);
        return (json);
    }

    /*public void saveJson(File jsonFile) throws IOException {
        try (PrintWriter out = new PrintWriter(jsonFile)) {
            Gson gson = new Gson();
            String json = gson.toJson(this);
            out.println(json);*/
}
