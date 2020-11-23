package fabricadesoftware.com.io.response

import fabricadesoftware.com.model.User

data class LoginResponse(val success: Boolean, val user: User, val tokenResult: String)