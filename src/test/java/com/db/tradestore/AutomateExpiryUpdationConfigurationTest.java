package com.db.tradestore;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AutomateExpiryUpdationConfigurationTest {

    @Autowired
    TradeRepository tradeRepository;


    @Test
    void testScheduleAutomaticExpiryUpdation() throws InterruptedException {
        Trade byTradeId = tradeRepository.findByTradeId("T3");
        assertNotNull(byTradeId);
        assertEquals("N", byTradeId.getExpired());

        Thread.sleep(60000);

        Trade byTradeIdAfterUpdation = tradeRepository.findByTradeId("T3");
        assertNotNull(byTradeIdAfterUpdation);
        assertEquals("Y", byTradeIdAfterUpdation.getExpired());
    }
}