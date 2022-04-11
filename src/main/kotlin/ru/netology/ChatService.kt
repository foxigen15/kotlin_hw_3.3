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
            ?: throw ChatNotFoundException("chat does not exist")
        chats.remove(chat)
        return true
    }

    fun getListOfChats(userId: Int): MutableList<Chat> =
        chats.asSequence()
            .filter { it.ownerId == userId && !it.itIsDeleted || it.recipientId == userId || !it.itIsDeleted }
            .ifEmpty { throw ChatNotFoundException("chats does not exist") }
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

//    private fun MutableList<Chat>.findMessage(predicate: (Message) -> Boolean): Pair<Chat, Message>? {
//        forEach { chat ->
//            val tempMessage = chat.messages.find(predicate) ?: return@forEach
//            return chat to tempMessage
//        }
//        return null
//    }
//
//    private fun MutableList<Chat>.findMessageById(messageId: Int): Pair<Chat, Message>? = findMessage {
//        it.messageId == messageId
//    }

    private fun MutableList<Chat>.findMessageById(messageId: Int): Pair<Chat, Message>? {
        forEach { chat ->
            chat.messages.forEach { message ->
                if (message.messageId == messageId) {
                    return chat to message
                }
            }
        }
        return null
    }

//    fun test(messageId: Int){
//        println(chats.findMessageById2(messageId))
//    }

    fun editMessage(messageId: Int, text: String): Boolean {
        val (chat, message) = chats.findMessageById(messageId) ?: return false
        val newMessage = message.copy(text = text, itIsRead = true)
        chats.remove(chat)
        chat.messages.remove(message)
        chat.messages.add(newMessage)
        chats.add(chat)
        return true
    }

}

