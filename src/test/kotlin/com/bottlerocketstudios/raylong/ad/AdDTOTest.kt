package com.bottlerocketstudios.raylong.ad

import com.bottlerocketstudios.raylong.AssetAttributes
import com.bottlerocketstudios.raylong.asset.Asset
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentSkipListSet

class AdDTOTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var adDTO: AdDTO

    @Before
    fun setUp() {
        adDTO = AdDTO(
                ad = Ad(
                        id = 1L,
                        name = "test ad container 0",
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")//,
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")//,
        )
        adDTO.assets.add(Asset(
                id = 1L,
                name = "test asset 0",
                attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")//,
        ))
    }

    @Test
    fun test_toString() {
        val json = adDTO.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
        log.info("Serialized adDTO: {}", json)
    }

    @Test
    fun getAd() {
        val ad = adDTO.ad.toString()
        Assertions.assertThat(ad).isNotNull()
        Assertions.assertThat(ad).isNotEmpty()
        log.info("Serialized adDTO.ad: {}", ad)
    }

    @Test
    fun getAssets() {
        val assets = adDTO.assets.toString()
        Assertions.assertThat(assets).isNotNull()
        Assertions.assertThat(assets).isNotEmpty()
        log.info("Serialized assets: {}", assets)
    }

    @Test
    fun getMetadata() {
        val metadata = adDTO.metadata.toString()
        Assertions.assertThat(metadata).isNotNull()
        Assertions.assertThat(metadata).isNotEmpty()
        log.info("Serialized metadata: {}", metadata)
    }
}