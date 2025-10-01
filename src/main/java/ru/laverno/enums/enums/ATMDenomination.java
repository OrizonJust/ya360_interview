package ru.laverno.enums.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import ru.laverno.exception.ATMNotEnoughMoneyException;

import java.util.Arrays;

@AllArgsConstructor
@Getter
public enum ATMDenomination {

    FIVE_THOUSAND(5000, "RUB"),
    ONE_THOUSAND(1000, "RUB"),
    FIVE_HUNDRED(500, "RUB"),
    ONE_HUNDRED(100, "RUB"),
    FIFTY(50, "RUB");

    private final int amount;
    private final String currency;

    public static ATMDenomination nextDenomination(ATMDenomination currentDenomination) {
        return Arrays.stream(ATMDenomination.values())
                .filter(denomination -> currentDenomination.ordinal() < denomination.ordinal())
                .findFirst()
                .orElseThrow(() -> new ATMNotEnoughMoneyException("Can not give money"));
    }
}
