import com.google.gson.Gson;

public class HttpResponseConverter {

    public <T> T fromJson(String json, Class<T> cls) {
        Gson gson = new Gson();

        return gson.fromJson(json, cls);
    }
}
