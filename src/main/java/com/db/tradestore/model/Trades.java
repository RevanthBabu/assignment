package com.db.tradestore.model;

import java.util.List;

public class Trades {
    public List<Trade> getTrades() {
        return trades;
    }

    public void setTrades(List<Trade> trades) {
        this.trades = trades;
    }

    private List<Trade> trades;
}
