package com.db.tradestore.validators;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class TradeValidator implements ConstraintValidator<TradeValidate, Trade> {
    @Autowired
    TradeRepository tradeRepository;

    @Override
    public void initialize(TradeValidate constraintAnnotation) {
    }

    @Override
    public boolean isValid(Trade trade, ConstraintValidatorContext constraintValidatorContext) {
        if (trade.getMaturityDate().isBefore(LocalDate.now())) {
            constraintValidatorContext.disableDefaultConstraintViolation();
            constraintValidatorContext.buildConstraintViolationWithTemplate("{maturity.date.message}")
                    .addPropertyNode("maturityDate")
                    .addConstraintViolation();
            return false;
        }
        Trade byTradeId = tradeRepository.findByTradeId(trade.getTradeId());
        if (!ObjectUtils.isEmpty(byTradeId)) {
            if (byTradeId.getVersion() > trade.getVersion()) {
                return false;
            }
        }
        return true;
    }
}
