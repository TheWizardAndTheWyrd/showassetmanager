package com.bottlerocketstudios.raylong.container

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ContainerRepository : JpaRepository<Container?, Long?> {
    // TODO: add custom repo methods
}