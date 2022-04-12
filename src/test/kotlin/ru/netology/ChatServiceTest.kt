package ru.netology

import org.junit.Assert.*
import org.junit.Test

class ChatServiceTest {

    @Test
    fun createMessage() {
        val ownerId = 1
        val recipientId = 2
        val text = "Some text"
        val service = ChatService()
        val result = service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)

        assertTrue(result)
    }

    @Test
    fun deleteChat() {

    }

}