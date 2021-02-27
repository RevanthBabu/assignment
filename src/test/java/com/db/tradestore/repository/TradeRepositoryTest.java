package com.db.tradestore.repository;

import com.db.tradestore.model.Trade;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;

import static com.db.tradestore.TradeUtil.aTrade;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class TradeRepositoryTest {
    @Autowired
    TestEntityManager testEntityManager;
    @Autowired
    TradeRepository tradeRepository;

    @Test
    public void testFindByTradeId(){
        Trade trade = aTrade();

        testEntityManager.persist(trade);

        Trade byTradeId = tradeRepository.findByTradeId("T4");
        assertNotNull(byTradeId);
        assertEquals(trade, byTradeId);
    }

    @Test
    public void testFindByTradeIdVersion(){
        Trade trade = aTrade();

        testEntityManager.persist(trade);

        Trade byTradeIdVersion = tradeRepository.findByTradeIdVersion("T4", 4);
        assertNotNull(byTradeIdVersion);
        assertEquals(trade, byTradeIdVersion);
    }
}