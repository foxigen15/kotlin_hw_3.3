package ru.netology

class ChatService {
    private val chats = mutableListOf<Chat>()
    private var messageId = 0
    private var chatId = 0


    fun createChat(ownerId: Int, recipientId: Int): Chat {
        chatId += 1
        val chat = Chat(ownerId = ownerId, recipientId = recipientId, chatId = chatId)
        chats.add(chat)
        return chat
    }

    fun createMessage(ownerId: Int, recipientId: Int, text: String): Boolean {
        messageId += 1
        val chat =
            chats.firstOrNull { it.ownerId == ownerId && it.recipientId == recipientId || it.ownerId == recipientId && it.recipientId == ownerId }
                ?: createChat(ownerId, recipientId)

        val newMessage =
            Message(messageId = messageId, chatId = chatId, ownerId = ownerId, recipientId = recipientId, text = text)

        val tempChat = chat.copy()
        tempChat.messages.add(newMessage)
        chats.remove(chat)
        chats.add(tempChat)
        return true
    }


}