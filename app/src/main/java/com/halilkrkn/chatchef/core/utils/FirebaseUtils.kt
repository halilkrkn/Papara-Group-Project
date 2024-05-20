package com.halilkrkn.chatchef.core.utils

class FirebaseUtils {
    companion object {
        private const val emailRegex = "^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$"
        private const val nameRegex = "^[a-zA-ZüÜğĞıİşŞöÖçÇ]+$"

        fun emailAndPasswordControl(email: String, password: String): Boolean {
            return if (email.isEmpty() || password.isEmpty()) {
                false
            } else if (!email.matches(emailRegex.toRegex())) {
                false
            } else {
                true
            }
        }
        fun nameControl(name: String): Boolean {
            if (name.isEmpty()) {
                return false
            }
            return name.matches(nameRegex.toRegex())
        }
    }
}