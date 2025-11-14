package co.edu.uco.HumanSolution.crosscutting.messagecatalog.impl;

import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessageCatalog;
import co.edu.uco.HumanSolution.crosscutting.messagecatalog.MessagesEnum;

public final class MessageCatalogBase implements MessageCatalog {

    private static final MessageCatalog INSTANCE = new MessageCatalogBase();

    private MessageCatalogBase() {
        super();
    }

    public static MessageCatalog getMessageCatalog() {
        return INSTANCE;
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, new Object[0]);
    }

    @Override
    public String getMessage(String code, Object... params) {
        try {
            MessagesEnum message = MessagesEnum.valueOf(code);
            String messageText = message.getMessage();
            
            if (params != null && params.length > 0) {
                return String.format(messageText, params);
            }
            
            return messageText;
        } catch (IllegalArgumentException exception) {
            return MessagesEnum.TECHNICAL_GENERAL_PROBLEM.getMessage();
        }
    }
}

