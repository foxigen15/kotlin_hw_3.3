package ru.netology

data class Message(
    val messageId: Int,
    val chatId: Int,
    val ownerId: Int,
    val recipientId: Int,
    val text: String,
    val itIsRead: Boolean = false,
    val itIsDelete: Boolean = false
) {
}