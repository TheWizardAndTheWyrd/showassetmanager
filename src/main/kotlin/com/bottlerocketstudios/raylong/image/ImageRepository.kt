package com.bottlerocketstudios.raylong.image

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ImageRepository : JpaRepository<Image?, Long?> {
    // TODO: add custom repo methods

    @Query(value = "select i from Image i where i.id = :id")
    fun findByImageId(@Param(value = "id") id: Long?): Image?
}