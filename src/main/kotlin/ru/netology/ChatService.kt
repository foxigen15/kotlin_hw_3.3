package ru.netology

class ChatService {
    private val chats = mutableListOf<Chat>()
    private var messageId = 0
    private var chatId = 0


    private fun createChat(ownerId: Int, recipientId: Int): Chat {
        chatId += 1
        val chat = Chat(ownerId = ownerId, recipientId = recipientId, chatId = chatId)
        chats.add(chat)
        return chat
    }

    private fun deleteChat(chatId: Int): Boolean {
        val chat = chats.firstOrNull { it.chatId == chatId } ?: throw ChatNotFoundException("chat does not exist")
        chats.remove(chat)
        return true
    }

    private fun getListOfChats(ownerId: Int): MutableList<Chat> =
        chats.asSequence()
            .filter { it.ownerId == ownerId }
            .ifEmpty { throw ChatNotFoundException ("chats does not exist") }
            .toMutableList()


    private fun createMessage(ownerId: Int, recipientId: Int, text: String): Boolean {
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