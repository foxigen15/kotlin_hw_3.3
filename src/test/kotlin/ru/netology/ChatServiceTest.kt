package ru.netology

import org.junit.Assert.*
import org.junit.Test

class ChatServiceTest {


    @Test
    fun deleteChat() {
        val userId = 1
        val chatId = 1
        val text = "Some text"
        val ownerId = 1
        val recipientId = 2

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)
        val result = service.deleteChat(userId = userId, chatId = chatId)

        assertTrue(result)
    }

    @Test(expected = NotFoundException::class)
    fun deleteChatNotfoundException() {
        val userId = 1
        val chatId = 25
        val text = "Some text"
        val ownerId = 1
        val recipientId = 2

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)
        service.deleteChat(userId = userId, chatId = chatId)
    }

    @Test
    fun getListOfChats() {
        val text = "Some text"
        val ownerId = 1
        val recipientId = 2

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)

        val result = service.getListOfChats(userId = ownerId).isEmpty()

        assertFalse(result)
    }

    @Test(expected = NotFoundException::class)
    fun getListOfChatsNotfoundException() {
        val userId = 25
        val ownerId1 = 1
        val ownerId2 = 3
        val recipientId1 = 2
        val recipientId2 = 4
        val text1 = "Some text"
        val text2 = "Some text"

        val service = ChatService()
        service.createMessage(ownerId = ownerId1, recipientId = recipientId1, text = text1)
        service.createMessage(ownerId = ownerId2, recipientId = recipientId2, text = text2)

        service.getListOfChats(userId = userId)
    }

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
    fun editMessage() {
        val ownerId = 1
        val recipientId = 2
        val text = "Some text"

        val messageId = 1
        val text2 = "New text"

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)
        val result = service.editMessage(messageId = messageId, text = text2)

        assertTrue(result)
    }

    @Test(expected = NotFoundException::class)
    fun editMessageNotFound() {
        val ownerId = 1
        val recipientId = 2
        val text = "Some text"

        val messageId = 25
        val text2 = "New text"

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)
        service.editMessage(messageId = messageId, text = text2)

    }

    @Test
    fun deleteMessage() {
        val ownerId = 1
        val recipientId = 2
        val text = "Some text"
        val messageId = 1

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)

        val result = service.deleteMessage(messageId)

        assertTrue(result)
    }

    @Test(expected = NotFoundException::class)
    fun deleteMessageNotFoundException() {
        val ownerId = 1
        val recipientId = 2
        val text = "Some text"
        val messageId = 25

        val service = ChatService()
        service.createMessage(ownerId = ownerId, recipientId = recipientId, text = text)

        service.deleteMessage(messageId)
    }

    @Test
    fun getUnreadChatsCount_userOwner() {
        val ownerId1 = 1
        val ownerId2 = 3
        val recipientId1 = 2
        val text1 = "Some text"
        val userId = 1
        val text2 = "Some text"

        val service = ChatService()
        service.createMessage(ownerId = ownerId1, recipientId = recipientId1, text = text1)
        service.createMessage(ownerId = ownerId2, recipientId = recipientId1, text = text2)
        val result = service.getUnreadChatsCount(userId = userId) == 0

        assertTrue(result)
    }

    @Test
    fun getUnreadChatsCount() {
        val ownerId1 = 1
        val ownerId2 = 3
        val recipientId1 = 2
        val text1 = "Some text"
        val userId = 2
        val text2 = "Some text"

        val service = ChatService()
        service.createMessage(ownerId = ownerId1, recipientId = recipientId1, text = text1)
        service.createMessage(ownerId = ownerId2, recipientId = recipientId1, text = text2)
        val result = service.getUnreadChatsCount(userId = userId) == 2

        assertTrue(result)
    }


}