package com.app.notas.service;

import com.app.notas.models.Nota;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramBoot extends TelegramLongPollingBot {

    @Override
    public String getBotUsername() {
        return "notasapp_bot";
    }

    @Override
    public String getBotToken() {
        return "5776347365:AAHTrG7-l2ncX-KwLsX70XZRAEUDap5Tt_s";
    }


    @Override
    public void onUpdateReceived(Update update) {
        //se obitiene el mensaje escrito por el usuario

        final String messageTextReceived = update.getMessage().getText();
        System.out.println("Escribieron en el bot" + messageTextReceived);

        //Se crea un objeto mensaje
        SendMessage sendMessage = new SendMessage();
        sendMessage.setText("Gracias por escribirnos");

        try{
            //Se envia el mensaje
            execute(sendMessage);
        }catch (TelegramApiException e) {
            e.printStackTrace();
        }

    }

    public  void notificarNotaCreada(Nota nota){
        TelegramBoot telegramBoot = new TelegramBoot();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId("1334455564");
        sendMessage.setText("Nota Creada con los siguientes datos: "+ nota.toString());
        try {
            telegramBoot.execute(sendMessage);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
