package com.db.tradestore.repository;

import com.db.tradestore.model.Trade;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TradeRepository extends CrudRepository<Trade, Long> {
    @Query("select t from Trade t where t.tradeId=?1 and t.version=?2")
    Trade findByTradeIdVersion(String tradeId, Integer version);

    @Query("select t from Trade t where t.tradeId=?1")
    Trade findByTradeId(String tradeId);
}
