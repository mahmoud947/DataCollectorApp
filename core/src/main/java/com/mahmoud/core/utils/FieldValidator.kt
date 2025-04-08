package com.mahmoud.core.utils

interface FieldValidator {
    fun isNotEmpty(text: String): Boolean
    fun isValidEmail(text: String): Boolean
    fun isValidPhoneNumber(text: String): Boolean
    fun isMinLength(text: String, minLength: Int): Boolean
    fun isMaxLength(text: String, maxLength: Int): Boolean
    fun matchesPattern(text: String, regex: String): Boolean
}

class DefaultFieldValidator : FieldValidator {

    override fun isNotEmpty(text: String): Boolean {
        return text.isNotEmpty()
    }

    override fun isValidEmail(text: String): Boolean {
        val emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}"
        return text.matches(emailPattern.toRegex())
    }

    override fun isValidPhoneNumber(text: String): Boolean {
        val phonePattern = "^(\\+?[0-9]{1,4}[- ]?)?([0-9]{10})$" // Example pattern for phone number
        return text.matches(phonePattern.toRegex())
    }

    override fun isMinLength(text: String, minLength: Int): Boolean {
        return text.length >= minLength
    }

    override fun isMaxLength(text: String, maxLength: Int): Boolean {
        return text.length <= maxLength
    }

    override fun matchesPattern(text: String, regex: String): Boolean {
        return text.matches(regex.toRegex())
    }
}