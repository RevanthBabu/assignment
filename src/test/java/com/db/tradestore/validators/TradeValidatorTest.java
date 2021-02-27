package com.db.tradestore.validators;

import com.db.tradestore.model.Trade;
import com.db.tradestore.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.validation.ConstraintValidatorContext;

import static com.db.tradestore.TradeUtil.aTrade;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

class TradeValidatorTest {

    @InjectMocks
    TradeValidator versionValidator;
    @Mock ConstraintValidatorContext constraintValidatorContext;
    @Mock TradeRepository tradeRepository;

    @BeforeEach
    void setUp() {
        openMocks(this);
    }

    @Test
    public void testVersionEqualsToWhatItIsInDb(){
        Trade aTrade = aTrade();
        when(tradeRepository.findByTradeId("T4")).thenReturn(aTrade);

        assertTrue(versionValidator.isValid(aTrade, constraintValidatorContext));
    }

    @Test
    public void testVersionNotEqualsToWhatItIsInDb(){
        Trade aTrade = aTrade();
        when(tradeRepository.findByTradeId("T4")).thenReturn(aTrade);
        Trade anotherTrade = aTrade();
        anotherTrade.setVersion(3);
        assertFalse(versionValidator.isValid(anotherTrade, constraintValidatorContext));
    }
}