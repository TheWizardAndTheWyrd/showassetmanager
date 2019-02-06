package com.bottlerocketstudios.raylong.asset

import com.bottlerocketstudios.raylong.container.BaseDTO
import com.bottlerocketstudios.raylong.container.ContainerAttributes
import com.fasterxml.jackson.databind.ObjectMapper
import java.util.concurrent.ConcurrentSkipListSet

data class AssetDTO(
        val asset: Asset,
        val assets: ConcurrentSkipListSet<Asset>,
        val metadata: ContainerAttributes
) : Comparable<AssetDTO>, BaseDTO() {
    override fun compareTo(other: AssetDTO): Int {
        this.asset.id?.let {

            other.asset.id?.let {
                return when {
                    this.asset.id == other.asset.id -> {
                        0
                    }
                    this.asset.id!! > other.asset.id!! -> {
                        1
                    }
                    this.asset.id!! < other.asset.id!! -> {
                        -1
                    }
                    else -> {
                        0
                    }
                }
            }
        }

        return 0
    }

    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}