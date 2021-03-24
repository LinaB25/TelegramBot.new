import entities.CurrencyEntity;
import entities.WeatherEntity;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;
import java.util.*;

public class Bot extends TelegramLongPollingBot {

    @Override
    public String getBotToken() {
        return "1669989937:AAFr38rHUAsmhHWCbe4y8pB04448fqtX86w";
    }

    @Override
    public String getBotUsername() {
        return "TutBotBot";
    }

    public void sendMsg(Message message, String text) { //метод получения id чата и id сообщения и отправки сообщения
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(message.getChatId().toString());
        sendMessage.setReplyToMessageId(message.getMessageId());
        sendMessage.setText(text);
        try {
            setButton(sendMessage);
            sendMessage(sendMessage);

        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onUpdateReceived(Update update) { //метод для приема сообщений
        WeatherEntity weatherEntity = new WeatherEntity();
        CurrencyEntity currencyEntity = new CurrencyEntity();
        Message message = update.getMessage();
        if (message != null && message.hasText()) {
        switch (message.getText()) {
                case "/start":
                    sendMsg(message, "Привет, дай следующую команду!");
                    break;
                case "/Currency":
                    sendMsg(message, "USD или EUR");
                    break;
                case "/Weather":
                    sendMsg(message, "Напишите город:");
                    break;
                case "/News":
                    sendMsg(message, "Топ 10 новостей:");
                    try {
                        Map<String, String> map = News.readRSSFeed();
                        for (Map.Entry<String, String> entry : map.entrySet()) {
                            sendMsg(message, String.valueOf(entry.getKey() + " " + entry.getValue()));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    try {
                        sendMsg(message, Weather.getWeather(message.getText(), weatherEntity));
                    } catch (IOException e) {
                        sendMsg(message, "Город не найден");
                    }
                    try {
                        sendMsg(message, Currency.getCurrency(message.getText(), currencyEntity));
                    } catch (IOException f) {
                        sendMsg(message, "Не найдено");
                    }

            }
        }
    }

    public void setButton(SendMessage sendMessage) { //установка клавиш
        ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();
        sendMessage.setReplyMarkup(replyKeyboardMarkup);
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);
        replyKeyboardMarkup.setOneTimeKeyboard(false);

        List<KeyboardRow> keyboardRowList = new ArrayList<>();
        KeyboardRow keyboardFirstRow = new KeyboardRow();

        keyboardFirstRow.add(new KeyboardButton("/Weather"));
        keyboardFirstRow.add(new KeyboardButton("/News"));
        keyboardFirstRow.add(new KeyboardButton("/Currency"));

        keyboardRowList.add(keyboardFirstRow);
        replyKeyboardMarkup.setKeyboard(keyboardRowList);
    }

}