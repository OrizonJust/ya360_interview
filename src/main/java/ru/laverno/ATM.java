package ru.laverno;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.laverno.enums.enums.ATMDenomination;
import ru.laverno.exception.AmountRemainderException;

import java.util.HashMap;
import java.util.Map;

import static ru.laverno.enums.enums.ATMDenomination.*;

/**
 * Банкомат.
 * Инициализируется набором купюр и умеет выдавать купюры для заданной суммы, либо отвечать отказом.
 * При выдаче купюры списываются с баланса банкомата.
 * Допустимые номиналы: 50₽, 100₽, 500₽, 1000₽, 5000₽.
 *
 * Другие валюты и номиналы должны легко добавляться разработчиками в будущем.
 * Многопоточные сценарии могут быть добавлены позже (например резервирование).
 */
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class ATM {

    private Map<ATMDenomination, Integer> balance;

    public Map<ATMDenomination, Integer> withdraw(int amount) {
        var result = new HashMap<ATMDenomination, Integer>();
        withdrawBanknotes(amount, FIVE_THOUSAND, result);
        return result;
    }

    private Map<ATMDenomination, Integer> withdrawBanknotes(int amount, ATMDenomination currentDenomination, Map<ATMDenomination, Integer> result) {
        if (currentDenomination.getAmount() > amount || balance.get(currentDenomination) == null || balance.get(currentDenomination) == 0) {
            return withdrawBanknotes(amount, ATMDenomination.nextDenomination(currentDenomination), result);
        }

        issuanceOfBanknotes(result, currentDenomination);
        amount -= currentDenomination.getAmount();

        if (amount > 0) {
            return withdrawBanknotes(amount, currentDenomination, result);
        } else if (amount < 0) {
            throw new AmountRemainderException("Amount is negative");
        } else {
            return result;
        }
    }

    // 4550 | 4580 - 1000 | 3550 - 1000 | 2550 - 1000 | 1550 - 1000 | 550 - 500 | 50 - 50 | 0 -> return

    private void issuanceOfBanknotes(Map<ATMDenomination, Integer> banknotes, ATMDenomination denomination) {
        balance.put(denomination, balance.get(denomination) - 1);
        if (banknotes.containsKey(denomination)) {
            banknotes.put(denomination, banknotes.get(denomination) + 1);
        } else {
            banknotes.put(denomination, 1);
        }
    }
}
