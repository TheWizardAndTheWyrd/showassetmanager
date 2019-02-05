package com.bottlerocketstudios.raylong.show

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ShowRepository : JpaRepository<Show?, Long?> {
    // TODO: add custom repo methods

    @Query(value = "select s from Show s where s.id = :id")
    fun findByShowId(@Param(value = "id") id: Long?): Show?
}