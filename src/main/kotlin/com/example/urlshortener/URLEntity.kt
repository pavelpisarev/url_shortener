package com.example.urlshortener

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "url")
data class URLEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int = 0,

    @Column(name = "long_url", unique = true)
    var longUrl: String? = null,

    @Column(name = "short_url", unique = true)
    var shortUrl: String? = null,

    @Column(name = "date_created")
    var dateCreated: LocalDateTime? = null,

    @Column(name = "temporary")
    var temporary: Boolean = true
)