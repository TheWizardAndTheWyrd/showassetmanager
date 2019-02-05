package com.bottlerocketstudios.raylong.asset

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface AssetRepository : JpaRepository<Asset?, Long?> {
    // TODO: add custom repo methods

    @Query(value = "select a from Asset a where a.id = :id")
    fun findByAssetId(@Param(value = "id") id: Long?): Asset?
}