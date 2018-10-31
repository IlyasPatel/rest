public enum HttpVerb {

    GET("GET"),
    PUT("PUT"),
    POST("POST"),
    DELETE("DELETE"),
    OPTIONS("OPTIONS"),
    HEAD("HEAD");

    private final String verb;

    HttpVerb(String verb) {
        this.verb = verb;
    }

    public String verb() {
        return verb;
    }
}
