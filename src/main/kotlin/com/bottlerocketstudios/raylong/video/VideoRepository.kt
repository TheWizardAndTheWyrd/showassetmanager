package com.bottlerocketstudios.raylong.video

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface VideoRepository : JpaRepository<Video?, Long?> {
    // TODO: add custom repo methods

    @Query(value = "select v from Video v where v.id = :id")
    fun findByVideoId(@Param(value = "id") id: Long?): Video?
}