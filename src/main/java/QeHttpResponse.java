import com.google.gson.Gson;
import org.apache.http.message.BasicHeader;

import java.util.List;

public class QeHttpResponse {

    private final String response;
    private final int statusCode;
    private final List<BasicHeader> responseHeaders;

    public QeHttpResponse(String json, int httpStatusCode, List<BasicHeader> httpHeaderList) {
        this.response = json;
        this.statusCode = httpStatusCode;
        this.responseHeaders = httpHeaderList;
    }

    public String getResponse() {
        return response;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public List<BasicHeader> getResponseHeaders() {
        return responseHeaders;
    }

    @Override
    public String toString() {
        return "QeHttpResponse{" +
                "response='" + response + '\'' +
                ", statusCode=" + statusCode +
                ", responseHeaders=" + responseHeaders +
                '}';
    }

    private HttpResponseConverter httpResponseConverter = new HttpResponseConverter();

    public <T> T as(Class<T> cls) {
        return httpResponseConverter.fromJson(this.response, cls);
    }
}
