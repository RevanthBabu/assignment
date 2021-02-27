package com.db.tradestore.restcontroller;

import com.db.tradestore.model.Trade;
import com.db.tradestore.model.Trades;
import com.db.tradestore.service.TradeService;
import com.db.tradestore.validators.TradeValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/transform")
@Validated
public class TradeController {

    @Autowired
    TradeService tradeService;

    @RequestMapping(value = "/findall", method = RequestMethod.GET)
    public Trades findAllTrades() {
        Trades trades = new Trades();
        trades.setTrades(tradeService.findAll());
        return trades;
    }

    @RequestMapping(value = "/{tradeId}/{version}", method = RequestMethod.GET)
    public Trade getTrade(@PathVariable("tradeId") String tradeId, @PathVariable("version") int version) {
        return tradeService.findByTradeIdVersion(tradeId, version);
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public Trade postTrade(@RequestBody @Valid @TradeValidate Trade trade) {
        return tradeService.save(trade);
    }
}
