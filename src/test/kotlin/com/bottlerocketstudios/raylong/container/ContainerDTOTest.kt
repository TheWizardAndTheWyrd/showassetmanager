package com.bottlerocketstudios.raylong.container

import com.bottlerocketstudios.raylong.AssetAttributes
import com.bottlerocketstudios.raylong.ad.Ad
import com.bottlerocketstudios.raylong.ad.AdDTO
import com.bottlerocketstudios.raylong.asset.Asset
import com.bottlerocketstudios.raylong.asset.AssetDTO
import com.bottlerocketstudios.raylong.image.Image
import com.bottlerocketstudios.raylong.image.ImageDTO
import com.bottlerocketstudios.raylong.show.Show
import com.bottlerocketstudios.raylong.show.ShowDTO
import com.bottlerocketstudios.raylong.video.Video
import com.bottlerocketstudios.raylong.video.VideoAttributes
import com.bottlerocketstudios.raylong.video.VideoDTO
import org.assertj.core.api.Assertions
import org.junit.Before
import org.junit.Test

import org.slf4j.LoggerFactory
import java.util.concurrent.ConcurrentSkipListSet

class ContainerDTOTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    private lateinit var containerDTO: ContainerDTO

    @Before
    fun setUp() {
        containerDTO = ContainerDTO(
                container = Container(
                        id = 1L,
                        name = "test ad container dto 0",
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")//,
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"),
                ads = ConcurrentSkipListSet(),
                images = ConcurrentSkipListSet(),
                shows = ConcurrentSkipListSet(),
                videos = ConcurrentSkipListSet()
        )
        containerDTO.assets.add(AssetDTO(
                asset = Asset(
                    id = 1L,
                    name = "test asset container 0",
                    attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")))
        containerDTO.ads.add(AdDTO(
                ad = Ad(
                        id = 1L,
                        name = "test ad container 0",
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
        containerDTO.images.add(ImageDTO(
                image = Image(
                        id = 1L,
                        name = "test imge container 0",
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
        containerDTO.shows.add(ShowDTO(
                show = Show(
                        id = 1L,
                        name = "test show container 0",
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
        containerDTO.videos.add(VideoDTO(
                video = Video(
                        id = 1L,
                        name = "test video container 0 - episode",
                        type = VideoAttributes.EPISODE,
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
        containerDTO.videos.add(VideoDTO(
                video = Video(
                        id = 1L,
                        name = "test video container 1 - movie",
                        type = VideoAttributes.MOVIE,
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
        containerDTO.videos.add(VideoDTO(
                video = Video(
                        id = 1L,
                        name = "test video container 2 - clip",
                        type = VideoAttributes.CLIP,
                        attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")
                ),
                assets = ConcurrentSkipListSet(),
                metadata = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        )
    }

    @Test
    fun test_toString() {
        val json = containerDTO.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getContainer() {
        val json = containerDTO.container.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getAssets() {
        val json = containerDTO.assets.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getMetadata() {
        val json = containerDTO.metadata.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getAds() {
        val json = containerDTO.ads.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getImages() {
        val json = containerDTO.images.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getShows() {
        val json = containerDTO.shows.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }

    @Test
    fun getVideos() {
        val json = containerDTO.videos.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
    }
}