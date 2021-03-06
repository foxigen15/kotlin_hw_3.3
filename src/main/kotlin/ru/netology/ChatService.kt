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

    fun deleteChat(chatId: Int, userId: Int): Boolean {
        val chat = chats.firstOrNull { it.chatId == chatId && it.ownerId == userId && !it.itIsDeleted }
            ?: throw NotFoundException("chat does not exist")
        val deleteChat = chat.copy(itIsDeleted = true)
        chats.remove(chat)
        chats.add(deleteChat)
        return true
    }

    fun getListOfChats(userId: Int): MutableList<Chat> =
        chats
            .filter { it.ownerId == userId && !it.itIsDeleted || it.recipientId == userId && !it.itIsDeleted }
            .ifEmpty { throw NotFoundException("chats does not exist") }
            .toMutableList()


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

    private fun MutableList<Chat>.findMessageById(messageId: Int): Pair<Chat, Message>? {
        forEach { chat ->
            chat.messages.forEach { message ->
                if (message.messageId == messageId) return chat to message
            }
        }
        return null
    }


    fun editMessage(messageId: Int, text: String): Boolean {
        val (chat, message) = chats.findMessageById(messageId) ?: throw NotFoundException("message does not exist")
        val newMessage = message.copy(text = text, itIsRead = true)
        chats.remove(chat)
        chat.messages.remove(message)
        chat.messages.add(newMessage)
        chats.add(chat)
        return true
    }

    fun deleteMessage(messageId: Int): Boolean {
        val (chat, message) = chats.findMessageById(messageId) ?: throw NotFoundException("message does not exist")
        chat.messages.remove(message)
        if (chat.messages.isEmpty()) chats.remove(chat)
        return true
    }

    fun getUnreadChatsCount(userId: Int): Int =
        chats.asSequence()
            .filter { it.ownerId == userId || it.recipientId == userId }
            .count { chat ->
                chat.messages
                    .any { it.ownerId != userId && !it.itIsRead }
            }


    fun readMessage(userId: Int, messageId: Int): Boolean {
        val (chat, message) = chats.findMessageById(messageId) ?: throw NotFoundException("message does not exist")
        if (message.messageId == userId) {
            val newMessage = message.copy(itIsRead = true)
            chats.remove(chat)
            chat.messages.remove(message)
            chat.messages.add(newMessage)
            chats.add(chat)
        }
        return true
    }

}

