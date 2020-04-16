package com.pynguins.content.resolvers

import com.coxautodev.graphql.tools.GraphQLMutationResolver
import com.pynguins.auth.library.CheckTokenQuery
import com.pynguins.auth.library.TokenService
import com.pynguins.content.repository.ContentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class Mutation: GraphQLMutationResolver {
    @Autowired
    private val repository: ContentRepository? = null

    private fun findById(id: String): Content? {
        val result = repository!!.findById(id)
        return if (result.isPresent) result.get() else null
    }

    private fun validateToken(token: String): CheckTokenQuery.CheckToken {
        return TokenService().validateToken(token, "http://localhost:9090/graphql")
    }

    fun createContent(content: String, token: String): Content {
        validateToken(token)
        val contentData = Content()
        contentData.content = content
        repository!!.save(contentData)
        return contentData
    }

    fun updateContent(id: String, content: String, token: String): Content? {
        validateToken(token)
        val result = findById(id)
        if (result != null) {
            result.content = content
            repository!!.save(result)
        } else {
            return null
        }
        return result
    }

    fun deleteContent(id: String, token: String): Content? {
        validateToken(token)
        val result = findById(id)
        if (result != null) {
            repository!!.delete(result)
        } else {
            return null
        }
        return result
    }
}
