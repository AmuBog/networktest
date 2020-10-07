package no.amund.networktest.network

data class ErrorMessage (
    val status: Int,
    val message: String,
    val type: String
)
