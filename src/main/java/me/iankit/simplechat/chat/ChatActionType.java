package me.iankit.simplechat.chat;

public enum ChatActionType {
    ENTER_GROUP(EnterGroupAction.class),
    CREATE_GROUP(CreateGroupAction.class),
    SEND_MESSAGE(SendMessageAction.class);


    private final Class<? extends ChatAction> clazz;

    ChatActionType(Class<? extends ChatAction> clazz) {
        this.clazz = clazz;
    }

    public Class<? extends ChatAction> getClazz() {
        return this.clazz;
    }
}
