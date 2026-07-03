package org.example.project.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Enterprise(
    @SerialName("name")
    val name: String,
    @SerialName("ip_addresses")
    val ip_addresses : List<String>,
)
