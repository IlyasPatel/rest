import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.response.Response;
import org.apache.http.message.BasicHeader;

import java.util.ArrayList;
import java.util.List;

public class QeRestAssuredClient implements QeRestClient {

    @Override
    public QeHttpResponse makeRequest(QeHttpRequest qeHttpRequest) {
        // TODO Should assert that certain fields are not empty using java assert
        Response response = makeRestAssuredRequest(qeHttpRequest);

        List<BasicHeader> qeHttpHeaderList = new ArrayList<>();

        for (Header header : response.getHeaders().asList()) {
            qeHttpHeaderList.add(new BasicHeader(header.getName(), header.getValue()));
        }

        return new QeHttpResponse(response.getBody().asString(), response.getStatusCode(), qeHttpHeaderList);
    }

    private Response makeRestAssuredRequest(QeHttpRequest qeHttpRequest) {
        Response response;

        switch (qeHttpRequest.getVerb()) {
            case GET:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .when()
                        .get(qeHttpRequest.getUri());
                break;
            case POST:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .body(qeHttpRequest.getBody())
                        .when()
                        .post(qeHttpRequest.getUri());
                break;
            case PUT:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .body(qeHttpRequest.getBody())
                        .when()
                        .put(qeHttpRequest.getUri());
                break;
            case DELETE:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .body(qeHttpRequest.getBody())
                        .when()
                        .delete(qeHttpRequest.getUri());
                break;
            case OPTIONS:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .when()
                        .options(qeHttpRequest.getUri());
                break;
            case HEAD:
                response = RestAssured
                        .given()
                        .headers(qeHttpRequest.getHttpHeaders())
                        .when()
                        .head(qeHttpRequest.getUri());
            default:
                response = null;
                /*throw new QeCustomException(
                        qeHttpRequest.getVerb() + " is not supported. If you require support for this http method, "
                                + "please update the qetesttoolkit library.  You can speak to a member of the QE team "
                                + "if you require assistance."
                );*/
        }

        return response;
    }
}
