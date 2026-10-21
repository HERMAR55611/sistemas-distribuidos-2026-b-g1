package co.futbolix.reservation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;
import java.util.concurrent.atomic.AtomicReference;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class TracePropagationTest {

    static final String TRACE_ID = "4f8a2c1e9b7d40a6b3e5c7d9f1a2b4c6";
    static final AtomicReference<String> received = new AtomicReference<>();
    static HttpServer fakeCourtService;

    @DynamicPropertySource
    static void courtService(DynamicPropertyRegistry props) throws Exception {
        fakeCourtService = HttpServer.create(new InetSocketAddress(0), 0);
        fakeCourtService.createContext("/courts", ex -> {
            received.set(ex.getRequestHeaders().getFirst("traceparent"));
            byte[] body = "{\"slots\":[]}".getBytes();
            ex.getResponseHeaders().add("Content-Type", "application/json");
            ex.sendResponseHeaders(200, body.length);
            ex.getResponseBody().write(body);
            ex.close();
        });
        fakeCourtService.start();
        props.add("court.base-url", () -> "http://localhost:" + fakeCourtService.getAddress().getPort());
    }

    @AfterAll
    static void stop() {
        fakeCourtService.stop(0);
    }

    @Autowired
    MockMvc mvc;

    @Test
    void traceIdReceivedIsTheSameTraceIdSentToCourtService() throws Exception {
        mvc.perform(post("/reservations")
                        .header("traceparent", "00-" + TRACE_ID + "-1a2b3c4d5e6f7081-01")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"courtId\":\"c0000000-0000-4000-8000-000000000001\","
                                + "\"reservationDate\":\"2026-10-25\",\"startTime\":\"18:00\",\"endTime\":\"20:00\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("traceparent", org.hamcrest.Matchers.containsString(TRACE_ID)));

        assertThat(received.get()).startsWith("00-" + TRACE_ID + "-");
        assertThat(received.get()).doesNotContain("1a2b3c4d5e6f7081");
    }

    @Test
    void metricsEndpointExposesCounterAndHistogram() throws Exception {
        traceIdReceivedIsTheSameTraceIdSentToCourtService();
        mvc.perform(get("/metrics"))
                .andExpect(status().isOk())
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "reservation_requests_total{outcome=\"success\"")))
                .andExpect(content().string(org.hamcrest.Matchers.containsString(
                        "reservation_request_duration_seconds_bucket")));
    }
}
