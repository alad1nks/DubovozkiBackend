package com.alad1nks.dubovozkibackend.users.entities

sealed interface UserListResponse {
    val response: Any

    data class Valid(
        private val userList: List<UserEntity>
    ) : UserListResponse {
        override val response: Any
            get() = userList
    }

    data object Invalid : UserListResponse {
        override val response: Any
            get() = "Invalid Token"
    }
}
