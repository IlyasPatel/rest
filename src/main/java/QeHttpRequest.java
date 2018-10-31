import org.apache.http.HttpHeaders;
import org.apache.http.entity.ContentType;

import java.io.File;
import java.io.InputStream;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class QeHttpRequest {

    private URI uri;
    private Map<String, String> headers = new HashMap<>();
    private String certificate;
    private File certificateFile;
    private HttpVerb verb;
    private String body;

    private QeHttpRequest() {
        this.certificate = "";
        this.body = "";
        this.verb = HttpVerb.GET;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    private final QeHttpRequest setUri(URI uri) {
        this.uri = uri;

        return this;
    }

    public static class Builder {

        private QeHttpRequest request = new QeHttpRequest();

        public Builder withUri(URI uri) {
            request.setUri(uri);
            return this;
        }

        public Builder withDefaultHeaders() {
            withContentTypeHeader();
            return this;
        }

        public Builder withHeader(String name, String value) {
            request.headers.put(name, value);
            return this;
        }

        public Builder withContentTypeHeader() {
            request.headers.put(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString());
            return this;
        }

        public Builder withHeaders(Map<String, String> httpHeaders) {
            httpHeaders.forEach((k, v) -> {
                request.headers.put(k, v);
            });
            return this;
        }

        public Builder withNoHeaders() {
            request.headers.clear();
            return this;
        }

        public Builder withCertificate(File certificate) {
            request.certificateFile = certificate;
            return this;
        }

        public Builder get() {
            request.verb = HttpVerb.GET;
            return this;
        }

        public Builder post() {
            request.verb = HttpVerb.POST;
            return this;
        }

        public Builder post(String body) {
            request.verb = HttpVerb.POST;
            request.body = body;
            return this;
        }
        public Builder put() {
            request.verb = HttpVerb.PUT;
            return this;
        }

        public Builder delete() {
            request.verb = HttpVerb.DELETE;
            return this;
        }

        public Builder options() {
            request.verb = HttpVerb.OPTIONS;
            return this;
        }

        public Builder head() {
            request.verb = HttpVerb.HEAD;
            return this;
        }

        public QeHttpRequest build() {
            return request;
        }

        private InputStream getCertificateInputStream() {
            return QeHttpRequest.class.getResourceAsStream(request.certificate);
        }
    }

    public URI getUri() {
        return uri;
    }

    public Map<String, String> getHttpHeaders() {
        return headers;
    }

    public String getCertificate() {
        return certificate;
    }

    public File getCertificateFile() {
        return certificateFile;
    }

    public HttpVerb getVerb() {
        return verb;
    }

    public String getBody() {
        return body;
    }

}
