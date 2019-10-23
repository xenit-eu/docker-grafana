package eu.xenit.docker.grafana.test;

import static io.restassured.RestAssured.preemptive;

import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class RestAssuredTest {

    private static final Logger logger = LoggerFactory.getLogger(RestAssuredTest.class);

    private static final String GRAFANA_USERNAME = "admin";
    private static final String GRAFANA_PASSWORD = "admin";

    @BeforeAll
    public static void initializeRestAssured() {
        logger.info("Initializing REST-Assured for smoke tests");

        final String baseURI = "http://" + System.getProperty("grafana.host", "localhost");
        RestAssured.baseURI = baseURI;
        int port = Integer.parseInt(System.getProperty("grafana.tcp.3000", "3000"));
        RestAssured.port = port;

        logger.info("REST-Assured initialized with following URI: {}:{}", baseURI, port);

        RestAssured.authentication = preemptive().basic(GRAFANA_USERNAME, GRAFANA_PASSWORD);
    }

}
