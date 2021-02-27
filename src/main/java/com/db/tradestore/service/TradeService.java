package com.db.tradestore.service;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TradeService {
    @Autowired
    TradeRepository tradeRepository;

    public List<Trade> findAll() {
        List<Trade> trades = StreamSupport
                .stream(tradeRepository
                        .findAll()
                        .spliterator(), false)
                .collect(Collectors.toList());
        return trades;
    }

    public Trade findByTradeIdVersion(String tradeId, int version) {
        return tradeRepository.findByTradeIdVersion(tradeId, version);
    }


    public Trade save(Trade trade) {
        return tradeRepository.save(trade);
    }
}
