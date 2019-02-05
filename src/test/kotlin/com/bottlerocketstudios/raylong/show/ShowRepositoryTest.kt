package com.bottlerocketstudios.raylong.show

import com.bottlerocketstudios.raylong.AssetAttributes
import com.bottlerocketstudios.raylong.ShowAssetManager
import com.bottlerocketstudios.raylong.image.Image
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.transaction.annotation.Transactional

@SpringBootTest(classes = [ShowAssetManager::class])
@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableJpaRepositories(basePackages = ["com.bottlerocketstudios.raylong"])
@EntityScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableAutoConfiguration
@RunWith(SpringRunner::class)
open class ShowRepositoryTest {

    private val log = LoggerFactory.getLogger(ShowRepositoryTest::class.java)

    @Autowired
    private lateinit var showService: ShowService

//    @Autowired
//    private lateinit var showRepository: ShowRepository

    @Before
    fun setUp() {
        val container = Show(
                id = 1L,
                version = 0L,
                name = "test show container",
                attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"),
                images = HashSet()
//                ads = hashMapOf("ad0" to "{'name': 'ad1', 'uri': '/ads/1}")
        )
        container.images!!.addAll(
                listOf(
                        Image( // TODO: if Image is actually a collection of files, lest rename the type
                            id = 1L,
                            name = "test image container 0 - in show 1",
                            attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name")),
                        Image(
                                id = 2L,
                                name = "test image container 1 - in show 1",
                                attributes = hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))))
        log.info("Built container: {}", container)

        val savedContainer = showService.save(container)
        log.info("Saved container to repo: {}", savedContainer)
    }

    @Transactional
    @Test
    open fun `Get Show From Repo and validate attributes`() {
        val container: Show? = showService.findOneShowById(1L)
        showService.populateImagesFromIds(container)

        assertThat(container).isNotNull
        assertThat(container?.id).isNotNull()
        assertThat(container?.createdDate).isNotNull()
        assertThat(container?.modifiedDate).isNotNull()
        assertThat(container?.attributes).isNotNull
        assertThat(container?.attributes).isNotEmpty
        assertThat(container?.attributes).isEqualTo(hashMapOf(AssetAttributes.ID to "1", AssetAttributes.NAME to "test name"))
        assertThat(container?.images).isNotNull
        assertThat(container?.images).isNotEmpty
//        assertThat(container?.ads).isNotNull
//        assertThat(container?.ads).isNotEmpty

        container!!.images!!.forEach {
            i -> run {
                assertThat(i).isNotNull
            assertThat(i!!.id).isNotNull()
            }
        }

        log.info("Retrieved container from repo: {}", container)
    }
}