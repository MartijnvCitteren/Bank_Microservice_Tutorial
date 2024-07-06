package com.example.cards.mapper;

import com.example.cards.dto.CardsDto;
import com.example.cards.entity.Cards;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CardsMapper {

    public static CardsDto toCardsDto(Cards cards) {
        return CardsDto.builder()
                .mobileNumber(cards.getMobileNumber())
                .cardNumber(cards.getCardNumber())
                .cardType(cards.getCardType())
                .amountUsed(cards.getAmountUsed())
                .totalLimit(cards.getTotalLimit())
                .availableAmount(cards.getAvailableAmount())
                .build();
    }

    public static Cards toCards(CardsDto cardsDto) {
        return Cards.builder()
                .mobileNumber(cardsDto.mobileNumber())
                .cardNumber(cardsDto.cardNumber())
                .cardType(cardsDto.cardType())
                .amountUsed(cardsDto.amountUsed())
                .totalLimit(cardsDto.totalLimit())
                .availableAmount(cardsDto.availableAmount())
                .build();
    }

}
