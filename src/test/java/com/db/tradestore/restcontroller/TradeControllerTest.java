package com.db.tradestore.restcontroller;

import com.db.tradestore.TradeUtil;
import com.db.tradestore.model.Trade;
import com.db.tradestore.model.Trades;
import com.db.tradestore.service.TradeService;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

public class TradeControllerTest {
    @InjectMocks
    TradeController tradeController;

    @Mock
    TradeService tradeService;

    private List<Trade> trades;
    private Trade aTrade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        aTrade = TradeUtil.aTrade();
        trades = Lists.newArrayList(aTrade);
    }

    @Test
    public void findAllTrades_returnsAllTrades() {
        when(tradeService.findAll()).thenReturn(trades);

        Trades allTrades = tradeController.findAllTrades();

        assertEquals(trades, allTrades.getTrades());
    }

    @Test
    public void getTrade_returnsQueriedTrade() {
        String tradeId = "T4";
        int version = 4;
        when(tradeService.findByTradeIdVersion(tradeId, version)).thenReturn(aTrade);

        Trade trade = tradeController.getTrade(tradeId, version);

        assertEquals(aTrade, trade);
    }

    @Test
    public void getTrade_returnsNothingWhenQueriedResourceNotFound() {
        String tradeId = "T4";
        int version = 4;
        when(tradeService.findByTradeIdVersion(tradeId, version)).thenReturn(null);

        Trade trade = tradeController.getTrade(tradeId, version);

        assertNull(trade);
    }

    @Test
    public void postTrade_returnsTheSameObject() {
        when(tradeService.save(aTrade)).thenReturn(aTrade);

        Trade trade = tradeController.postTrade(aTrade);

        assertEquals(aTrade, trade);
    }
}
