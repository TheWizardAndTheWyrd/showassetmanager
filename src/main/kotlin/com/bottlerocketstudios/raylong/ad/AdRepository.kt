package com.bottlerocketstudios.raylong.ad

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AdRepository : JpaRepository<Ad?, Long?> {
    // TODO: add custom repo methods

    @Query(value = "select a from Ad a where a.id = :id")
    fun findByAdId(@Param(value = "id") id: Long?): Ad?
}