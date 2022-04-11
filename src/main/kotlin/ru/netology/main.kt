package ru.netology

fun main() {

    // для чата
    val chatId = 1
    val ownerId = 1
    val recipientId = 2

    // для сообщения
    val messageId = 1
    val m_chatId = 1
    val m_ownerId = 1
    val m_recipientId = 2
    val text = "SOME TEXT"

    val chatService = ChatService()
   // val chat1 = chatService.createChat(ownerId, recipientId)

    chatService.createMessage(ownerId, recipientId, text)

}