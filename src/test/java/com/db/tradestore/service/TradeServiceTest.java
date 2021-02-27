package com.db.tradestore.service;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.db.tradestore.TradeUtil.aTrade;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TradeServiceTest {

    @InjectMocks
    TradeService tradeService;
    @Mock
    TradeRepository tradeRepository;
    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    void findAll() {
        List<Trade> trades = Lists.newArrayList(aTrade());
        when(tradeRepository.findAll()).thenReturn(trades);

        Iterable<Trade> all = tradeService.findAll();
        assertEquals(trades, all);
    }

    @Test
    void findByTradeIdVersion() {
        Trade trade = aTrade();
        when(tradeRepository.findByTradeIdVersion(trade.getTradeId(), trade.getVersion())).thenReturn(trade);

        assertEquals(trade, tradeService.findByTradeIdVersion(trade.getTradeId(), trade.getVersion()));
    }
}