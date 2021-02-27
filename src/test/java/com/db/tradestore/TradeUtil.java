package com.db.tradestore;

import com.db.tradestore.model.Trade;

import java.time.LocalDate;

public class TradeUtil {
    public static Trade aTrade() {
        Trade trade = new Trade();
        trade.setVersion(4);
        trade.setTradeId("T4");
        trade.setMaturityDate(LocalDate.parse("2022-02-27"));
        trade.setCreatedDate(LocalDate.now());
        trade.setExpired("N");
        trade.setCounterPartyId("CP-4");
        trade.setBookId("B4");
        return trade;
    }
}
