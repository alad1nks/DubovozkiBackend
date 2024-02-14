package com.alad1nks.dubovozkibackend.admins.entities

sealed interface AdminListResponse {
    val response: Any

    data class Valid(
        private val adminList: List<AdminEntity>
    ) : AdminListResponse {
        override val response: Any
            get() = adminList
    }

    data object Invalid : AdminListResponse {
        override val response: Any
            get() = "Invalid Token"
    }
}
