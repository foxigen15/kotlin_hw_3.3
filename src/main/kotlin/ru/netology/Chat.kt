package ru.netology

data class Chat(
    val chatId: Int,
    val ownerId: Int,
    val recipientId: Int,
    val messages: List<Message> = listOf(),
    val itIsDeleted: Boolean = false
) {
}