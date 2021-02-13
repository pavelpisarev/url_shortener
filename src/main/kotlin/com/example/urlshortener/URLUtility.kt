package com.example.urlshortener

import org.springframework.context.annotation.Bean

class URLUtility {
    companion object {
        // https://github.com/delight-im/ShortURL
        private val ALPHABET: String = "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ"
        private val BASE: Int = ALPHABET.length

        fun encode(num: Int): String {
            var number = num
            val stringBuilder = StringBuilder()
            while (number > 0) {
                stringBuilder.insert(0, ALPHABET[number % BASE])
                number /= BASE
            }

            return stringBuilder.toString()
        }

        fun decode(string: String): Int {
            var number = 0
            for (element in string) {
                number = number * BASE + ALPHABET.indexOf(element)
            }

            return number
        }
    }
}