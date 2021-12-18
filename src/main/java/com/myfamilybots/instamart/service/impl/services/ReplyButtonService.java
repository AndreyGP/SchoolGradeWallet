package com.myfamilybots.instamart.service.impl.services;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

/**
 * telegrambots Created by Home Work Studio AndrHey [andreigp]
 * FileName: ReplyButtonService.java
 * Date/time: 18 декабрь 2021 in 8:13
 */
@Component
public class ReplyButtonService {

    public InlineKeyboardMarkup getRoleQuestionButtons() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();

        InlineKeyboardButton trainee = new InlineKeyboardButton();
        trainee.setText("Стажёр");
        InlineKeyboardButton picker = new InlineKeyboardButton();
        picker.setText("Сборщик");
        InlineKeyboardButton courier = new InlineKeyboardButton();
        courier.setText("Курьер");
        InlineKeyboardButton senior = new InlineKeyboardButton();
        senior.setText("Старший");

        trainee.setCallbackData("trainee");
        picker.setCallbackData("picker");
        courier.setCallbackData("courier");
        senior.setCallbackData("senior");

        List<InlineKeyboardButton> keyboardButtonsRow1 = new ArrayList<>();
        keyboardButtonsRow1.add(trainee);
        keyboardButtonsRow1.add(picker);

        List<InlineKeyboardButton> keyboardButtonsRow2 = new ArrayList<>();
        keyboardButtonsRow2.add(courier);
        keyboardButtonsRow2.add(senior);


        List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
        rowList.add(keyboardButtonsRow1);
        rowList.add(keyboardButtonsRow2);

        inlineKeyboardMarkup.setKeyboard(rowList);

        return inlineKeyboardMarkup;
    }
}
