package com.voteva.remittance.api.v1.mapper;

import com.voteva.remittance.api.v1.dto.TransactionDto;
import com.voteva.remittance.entity.Transaction;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TransactionMapper {

    public static Transaction from(TransactionDto transactionDto) {
        if (transactionDto == null) return null;

        Transaction transaction = new Transaction();
        transaction.setId(transactionDto.getId());
        transaction.setSenderId(transactionDto.getSenderId());
        transaction.setRecipientId(transactionDto.getRecipientId());
        transaction.setAmount(new BigDecimal(transactionDto.getAmount()));
        transaction.setDate(transactionDto.getDate());

        return transaction;
    }

    public static TransactionDto from(Transaction transaction) {
        if (transaction == null) return null;
        return new TransactionDto(
                transaction.getId(),
                transaction.getSenderId(),
                transaction.getRecipientId(),
                transaction.getAmount().toString(),
                transaction.getDate());
    }

    public static List<TransactionDto> from(List<Transaction> transactionList) {
        List<TransactionDto> transactionDtoList = new ArrayList<>();
        if (transactionList == null) return transactionDtoList;

        for (Transaction transaction : transactionList) {
            transactionDtoList.add(from(transaction));
        }

        return transactionDtoList;
    }
}
