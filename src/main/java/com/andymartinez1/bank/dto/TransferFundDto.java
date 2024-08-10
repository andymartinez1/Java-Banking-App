package com.andymartinez1.bank.dto;

public record TransferFundDto(Long fromAccountId,
                              Long toAccountId,
                              Double amount) {
}
