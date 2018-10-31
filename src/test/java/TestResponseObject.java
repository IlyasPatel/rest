import com.google.gson.annotations.SerializedName;

import java.util.UUID;

public class TestResponseObject {

    public String message;

    @SerializedName("trace-id")
    public UUID traceId;

    public String getMessage() {
        return message;
    }

    public UUID getTraceId() {
        return traceId;
    }
}
