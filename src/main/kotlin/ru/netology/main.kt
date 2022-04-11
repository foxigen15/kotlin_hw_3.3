package ru.netology

fun main() {

    // для чата
    val chatId = 1
    val ownerId = 1
    val recipientId = 1

    // для сообщения
    val messageId = 1
    val m_chatId = 1
    val m_ownerId = 1
    val m_recipientId = 2
    val text = "SOME TEXT"

    val chatService = ChatService()
    // val chat1 = chatService.createChat(ownerId, recipientId)

    chatService.createMessage(ownerId, recipientId, text)
    chatService.createMessage(ownerId, recipientId, "LKADKLASDFKLAJD")
//    chatService.createMessage(ownerId+1, recipientId, text + 3)

    chatService.test(2)


}