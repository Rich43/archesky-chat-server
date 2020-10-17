package com.archesky.chat.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

const val CONTENT = "chat"
@Document(CONTENT)
data class Chat (
    @Id var id: String? = null,
    var chat: String? = null
)
