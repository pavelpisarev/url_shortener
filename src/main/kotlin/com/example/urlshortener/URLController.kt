package com.example.urlshortener

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/")
class URLController(private val urlService: URLService) {
    @GetMapping("/encode")
    fun shortify(@RequestParam(name = "url", required = false) url: String): ResponseEntity<*> {
        val result: URLEntity? = urlService.findByURL(url)
        if (result == null) {
            val r = urlService.save(URLEntity(longUrl = url, shortUrl = "", dateCreated = LocalDateTime.now()))
            val shortUrl = URLUtility.encode(r.id)
            r.shortUrl = shortUrl
            val t = urlService.save(r)
            return ResponseEntity(t, HttpStatus.OK)
        } else {
            return ResponseEntity(result, HttpStatus.OK)
        }
    }

    @GetMapping("/decode")
    fun unshortify(@RequestParam(name = "url", required = false) url: String): ResponseEntity<*> {
        val result = urlService.findByShortURL(url)
        if (result != null) {
            return ResponseEntity.ok(result.longUrl!!)
        } else {
            return ResponseEntity("Not found", HttpStatus.NOT_FOUND)
        }
    }
}