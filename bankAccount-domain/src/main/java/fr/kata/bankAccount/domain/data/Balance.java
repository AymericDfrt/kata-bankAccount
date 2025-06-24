package fr.kata.bankaccount.domain.data;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Builder
@Data
public class Balance {
    private BigDecimal totalBalance;
    private String currency;
}
