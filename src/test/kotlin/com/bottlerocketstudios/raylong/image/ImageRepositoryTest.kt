package com.bottlerocketstudios.raylong.image

//import com.bottlerocketstudios.raylong.AbstractSpringIntegrationTest
import com.bottlerocketstudios.raylong.ShowAssetManager
import org.assertj.core.api.Assertions.assertThat as assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.test.context.junit4.SpringRunner

@SpringBootTest(classes = [ShowAssetManager::class])
@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableJpaRepositories(basePackages = ["com.bottlerocketstudios.raylong"])
@EntityScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableAutoConfiguration
@RunWith(SpringRunner::class)
open class ImageRepositoryTest {

    private val log = LoggerFactory.getLogger(ImageRepositoryTest::class.java)

    @Autowired
    private lateinit var imageRepository: ImageRepository

    @Before
    fun setUp() {
        val container = Image(
                id = 1L,
                version = 0L,
                name = "test image container",
                attributes = hashMapOf("key0" to "value0", "key1" to "value1")//,
        )
        val savedContainer = imageRepository.save(container)
        imageRepository.flush()
        log.info("Saved container to repo: {}", savedContainer)
    }

    @Test
    fun `Get Image From Repo and validate attributes`() {
        val container: Image? = imageRepository.getOne(1L)
        assertThat(container).isNotNull
        assertThat(container?.id).isNotNull()
        assertThat(container?.createdDate).isNotNull()
        assertThat(container?.modifiedDate).isNotNull()
        assertThat(container?.attributes).isNotNull
        assertThat(container?.attributes).isNotEmpty
        assertThat(container?.attributes).isEqualTo(hashMapOf("key0" to "value0", "key1" to "value1"))
//        assertThat(container?.images).isNotNull
//        assertThat(container?.images).isNotEmpty
//        assertThat(container?.ads).isNotNull
//        assertThat(container?.ads).isNotEmpty

        log.info("Retrieved container from repo: {}", container)
    }
}