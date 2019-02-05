package com.bottlerocketstudios.raylong.container

import com.bottlerocketstudios.raylong.ShowAssetManager
import org.assertj.core.api.Assertions.assertThat as assertThat
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

@SpringBootTest(classes = [ShowAssetManager::class])
@ComponentScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableJpaRepositories(basePackages = ["com.bottlerocketstudios.raylong"])
@EntityScan(basePackages = ["com.bottlerocketstudios.raylong"])
@EnableAutoConfiguration
@RunWith(SpringRunner::class)
class ContainerRepositoryTest {

    private val log = LoggerFactory.getLogger(ContainerRepositoryTest::class.java)

    @Autowired
    private lateinit var containerRepository: ContainerRepository

    @Before
    fun setUp() {
        val container = Container(
                id = 1L,
                version = 0L,
                name = "test container",
                attributes = hashMapOf("key0" to "value0", "key1" to "value1")
        )
        val savedContainer = containerRepository.save(container)
        containerRepository.flush()
        log.info("Saved container to repo: {}", savedContainer)
    }

    @Test
    fun `Get Container From Repo and validate top level attributes`() {
        val container: Container? = containerRepository.getOne(1L)
        assertThat(container).isNotNull
        assertThat(container?.id).isNotNull()
        assertThat(container?.createdDate).isNotNull()
        assertThat(container?.modifiedDate).isNotNull()
        assertThat(container?.attributes).isNotNull
        assertThat(container?.attributes).isNotEmpty
        assertThat(container?.attributes).isEqualTo(hashMapOf("key0" to "value0", "key1" to "value1"))

        log.info("Retrieved container from repo: {}", container)
    }
}