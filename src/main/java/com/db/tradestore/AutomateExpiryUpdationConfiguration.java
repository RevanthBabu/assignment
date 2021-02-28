package com.db.tradestore;

import com.db.tradestore.model.Trade;
import com.db.tradestore.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDate;
import java.util.List;

@Configuration
@EnableScheduling
public class AutomateExpiryUpdationConfiguration {

    @Autowired
    TradeService tradeService;

    /*its ok to run the scheduler once at the start of the day but as test cases cannot wait for that long,
    we had to schedule it to run every minute*/
    @Scheduled(cron = "1 * * * * *")
    public void scheduleAutomaticExpiryUpdation() {
        List<Trade> trades = tradeService.findAll();
        trades.forEach(t ->
        {
            if (t.getMaturityDate().isBefore(LocalDate.now()) && t.getExpired().equals("N")) {
                t.setExpired("Y");
                tradeService.save(t);
                System.out.println("trade being updated :: " + t);
            }
        }
        );
    }
}
