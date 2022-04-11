package ru.netology

data class Chat(
    val chatId: Int,
    val ownerId: Int,
    val recipientId: Int,
    val messages: MutableList<Message> = mutableListOf(),
    val itIsDeleted: Boolean = false
) {
}