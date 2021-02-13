package com.example.urlshortener

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface URLService: JpaRepository<URLEntity, Int> {
    @Query(value = """
        SELECT * FROM url WHERE long_url = :longURL
    """, nativeQuery = true)
    fun findByURL(@Param("longURL") longURL: String): URLEntity?

    @Query(value = """
        SELECT * FROM url WHERE short_url = :shortURL
    """, nativeQuery = true)
    fun findByShortURL(@Param("shortURL") shortURL: String): URLEntity?
}