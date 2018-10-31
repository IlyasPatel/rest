import org.junit.Before;
import org.junit.Test;

import java.net.URI;
import java.net.URISyntaxException;


public class QeHttpRequestITest {

    private QeRestClient sut;

    @Before
    public void setUp() throws Exception {
        sut = new QeRestAssuredClient();
    }

    @Test
    public void should_make_https_delete_request() throws URISyntaxException {

        QeHttpRequest delete = QeHttpRequest.newBuilder()
                .withUri(new URI(""))
                .withContentTypeHeader()
                .delete()
                .build();

        QeHttpResponse actual = sut.makeRequest(delete);

        actual.getResponse();
        /*
            @Roberto
            This is my current implementation, I use a builder to create my http request and then pass to a method to
            make the actual request.
         */
    }

    @Test
    public void should_make_https_delete_request_and_convert() throws URISyntaxException {

        QeHttpRequest delete = QeHttpRequest.newBuilder()
                .withUri(new URI(""))
                .withContentTypeHeader()
                .delete()
                .build();

        QeHttpResponse actual = sut.makeRequest(delete);

        actual.as(TestResponseObject.class);
        //     __
        // @Roberto
        /*
            I added in the as() method to ask my question.

            actual is a POJO, is it ok to add the as() method to convert the JSON to a require object?

            The as() method looks like this:

            private HttpResponseConverter httpResponseConverter = new HttpResponseConverter();

            public <T> T as(Class<T> cls) {
                return httpResponseConverter.fromJson(this.response, cls);
            }

            I'm thinking it is not good practice as this is adding behaviour to a POJO. It is also possible that
            in the future, people may need to extend this method further to add complex conversions.
         */
    }
}
