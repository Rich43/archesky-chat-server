package com.archesky.chat.dto

import com.arangodb.springframework.annotation.Document
import org.springframework.data.annotation.Id

const val MESSAGE = "message"
@Document(MESSAGE)
data class Message (
    @Id var id: String? = null,
    var message: String? = null
)
