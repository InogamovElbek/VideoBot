package org.example;

import lombok.SneakyThrows;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyBot extends TelegramLongPollingBot {
    static List<String> strnigs = new ArrayList<>();

    public MyBot() {
        super("7877821768:AAHkMKmzixa_X8GUylEsHqMgg1Qzk6cFVj0");
    }

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasChannelPost()) {
            Message message = update.getMessage();
            if (update.getChannelPost().hasVideo()) {
                System.out.println(update.getChannelPost().getVideo());
                String txt = update.getChannelPost().getVideo().getFileId();
                strnigs.add(txt);
                System.out.println(1);
            }
        }
        if (update.hasMessage()) {
            if (update.getMessage().hasText()) {
                if (update.getMessage().getText().equals("/start")) {
                    sendMessage(update.getMessage().getChatId(), "nma bu nma san san");
                }
                SendVideo video1 = new SendVideo();
                video1.setVideo(new InputFile(strnigs.get(Integer.parseInt(update.getMessage().getText()))));
                video1.setChatId(update.getMessage().getChatId());
                execute(video1);
            }
        }
    }

    public Message sendMessage(Long chatId, String text) {
        SendMessage message = new SendMessage();
        message.setChatId(chatId);
        message.setText(text);
        try {
            return execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return "https://t.me/bekYourOneSolution";
    }
}



