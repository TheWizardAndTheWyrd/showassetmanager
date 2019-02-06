package com.bottlerocketstudios.raylong.container

import com.bottlerocketstudios.raylong.AssetAttributes
import com.bottlerocketstudios.raylong.ShowAssetManager
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
import org.junit.Test

import org.junit.Before
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner
import java.util.concurrent.ConcurrentSkipListSet

@SpringBootTest(classes = [ShowAssetManager::class])
@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableJpaRepositories(basePackages = ["com.bottlerocketstudios.raylong"])
@EntityScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableAutoConfiguration
@RunWith(SpringRunner::class)
class ContainerServiceTest {

    private val log = LoggerFactory.getLogger(this::class.java)

    @Autowired
    lateinit var containerService: ContainerService

    lateinit var containerDTO: ContainerDTO

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
    fun saveDTO() {
        val result: ContainerDTO = containerService.saveDTO(containerDTO)
        val json = result.toString()
        Assertions.assertThat(json).isNotNull()
        Assertions.assertThat(json).isNotEmpty()
        log.info("From result={}, saved DTO and generated json: {}", result, json)
    }
}