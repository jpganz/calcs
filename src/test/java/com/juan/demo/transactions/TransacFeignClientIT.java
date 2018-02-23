package com.juan.demo.transactions;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.juan.demo.config.ControllersConfig;
import com.juan.demo.config.FeignConfig;

import com.juan.demo.config.ServiceConfig;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.io.IOException;
import java.util.List;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.containing;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.junit.Assert.assertThat;
import static org.springframework.http.HttpStatus.OK;

@RunWith(SpringRunner.class)
@EnableAutoConfiguration
@SpringBootTest(classes = { ControllersConfig.class, FeignConfig.class, ServiceConfig.class})
@PropertySource("classpath:application-test.properties")
@WebAppConfiguration
public class TransacFeignClientIT {

    private static final String GET_TRANSACTIONS_URL = "/transaction";
    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9595);

    @Autowired
    private TransacFeignClient transacFeignClient;

    @Test
    public void getTransferUpdatesSuccess() throws IOException {
        stubResponse(GET_TRANSACTIONS_URL, OK.value(), "transferUpdates.json");
        final List<Double> transactions = transacFeignClient.getTransactions();
        Assert.assertThat(transactions.size(), is(3));
        assertThat(transactions, containsInAnyOrder(10.50, 32.57, 50.75));
    }

    private void stubResponse(final String url, final int status, final String fileName) throws IOException {
        stubFor(get(urlEqualTo(url))
                        .withHeader("Accept", containing("application/json"))
                        .willReturn(aResponse()
                                            .withStatus(status)
                                            .withHeader("Content-Type", "application/json")
                                            .withBody("[10.50,32.57,50.75]")
                        ));
    }

}
