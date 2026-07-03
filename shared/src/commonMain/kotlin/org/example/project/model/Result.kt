package org.example.project.model

sealed class Result {
    abstract val enterprise: Enterprise
    abstract val ip: String

    data class Success(
        override val enterprise: Enterprise, override val ip: String
    ) : Result()

    data class Failure(
        override val enterprise: Enterprise,
        val message: String, override val ip: String
    ) : Result()
}
