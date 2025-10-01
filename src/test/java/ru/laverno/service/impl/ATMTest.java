package ru.laverno.service.impl;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.laverno.ATM;
import ru.laverno.enums.enums.ATMDenomination;
import ru.laverno.exception.ATMNotEnoughMoneyException;
import ru.laverno.exception.AmountRemainderException;

import java.util.HashMap;

public class ATMTest {

    ATM atm = new ATM();

    @BeforeEach
    public void setUp() {
        var balance = new HashMap<ATMDenomination, Integer>();
        balance.put(ATMDenomination.FIVE_THOUSAND, 1);
        balance.put(ATMDenomination.ONE_THOUSAND, 5);
        balance.put(ATMDenomination.ONE_HUNDRED, 0);
        balance.put(ATMDenomination.FIVE_HUNDRED, 0);
        balance.put(ATMDenomination.FIFTY, 0);
        atm.setBalance(balance);
    }

    @Test
    void withdraw() {
        var result = atm.withdraw(8000);

        Assertions.assertThat(result)
                .hasSize(2);
        Assertions.assertThat(result.get(ATMDenomination.FIVE_THOUSAND))
                .isNotNull()
                .isEqualTo(1);
        Assertions.assertThat(result.get(ATMDenomination.ONE_THOUSAND))
                .isNotNull()
                .isEqualTo(3);
    }

    @Test
    void notEnoughMoney() {
        Assertions.assertThatExceptionOfType(ATMNotEnoughMoneyException.class)
                .isThrownBy(() -> atm.withdraw(11000))
                .withMessage("Can not give money");
    }

    @Test
    void amount() {
        Assertions.assertThatExceptionOfType(AmountRemainderException.class)
                .isThrownBy(() -> atm.withdraw(8010));
    }
}
