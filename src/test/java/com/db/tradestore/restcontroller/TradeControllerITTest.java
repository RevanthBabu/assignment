package com.db.tradestore.restcontroller;

import com.db.tradestore.model.Trade;
import com.db.tradestore.model.Trades;
import com.db.tradestore.validationconfig.ValidationErrorResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TradeControllerITTest {

    @Autowired
    TestRestTemplate restTemplate;

    @Test
    public void testGetTrade() {
        Trade trade = restTemplate.getForObject("/transform/T1/1", Trade.class);
        assertEquals("T1", trade.getTradeId());
        assertEquals(1, trade.getVersion());
    }

    @Test
    public void testValidationOfMaturityDateFailure() {
        Trade trade = new Trade();
        trade.setMaturityDate(LocalDate.parse("2020-02-26"));
        trade.setTradeId("T1");
        trade.setVersion(1);
        ResponseEntity<ValidationErrorResponse> tradeResponseEntity = restTemplate.postForEntity("/transform", trade, ValidationErrorResponse.class);
        assertEquals(400, tradeResponseEntity.getStatusCode().value());
        assertEquals("Please enter Maturity date on or after today's date", tradeResponseEntity.getBody().getViolations().get(0).getMessage());
    }

    @Test
    public void testInsertNewTrade() {
        Trades trades = restTemplate.getForObject("/transform/findall", Trades.class);
        int numberOfTradesBefore = trades.getTrades().size();

        Trade trade = new Trade();
        trade.setVersion(4);
        trade.setTradeId("T4");
        trade.setMaturityDate(LocalDate.parse("2022-02-27"));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpired("N");
        trade.setCounterPartyId("CP-4");
        trade.setBookId("B4");
        Trade responseTrade = restTemplate.postForObject("/transform", trade, Trade.class);
        assertNotNull(responseTrade);

        Trades tradesAfter = restTemplate.getForObject("/transform/findall", Trades.class);
        assertEquals(numberOfTradesBefore + 1, tradesAfter.getTrades().size());
    }

    @Test
    public void testTradeTransmissionSuccess() {
        Trade trade = new Trade();
        trade.setMaturityDate(LocalDate.parse("2025-02-27"));
        trade.setTradeId("T1");
        trade.setVersion(2);
        ResponseEntity<Trade> tradeResponseEntity = restTemplate.postForEntity("/transform", trade, Trade.class);
        assertEquals(200, tradeResponseEntity.getStatusCode().value());
    }

    @Test
    public void testTradeOverrideFailureDueToLowerVersion() {
        Trade trade = new Trade();
        trade.setMaturityDate(LocalDate.parse("2025-02-27"));
        trade.setTradeId("T2");
        trade.setVersion(1);
        ResponseEntity<ValidationErrorResponse> tradeResponseEntity = restTemplate.postForEntity("/transform", trade, ValidationErrorResponse.class);
        assertEquals(400, tradeResponseEntity.getStatusCode().value());
        assertEquals("Please enter the version greater than or equal to the current version against trade id", tradeResponseEntity.getBody().getViolations().get(0).getMessage());
    }
}