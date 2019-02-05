package com.bottlerocketstudios.raylong.container

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import org.hibernate.annotations.BatchSize
import java.time.Instant
import java.util.Date
import java.util.concurrent.ConcurrentSkipListMap
import javax.persistence.*

@Entity
@Table(name = "containers")
@JsonInclude(JsonInclude.Include.NON_NULL)
data class Container(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,

        @Version
        var version: Long? = null,

        var name: String? = null,

        @ElementCollection(fetch = FetchType.EAGER)
        @CollectionTable(name = "raw_attributes")
        @MapKeyJoinColumn(name = "attribute_key")
        @Column(name = "attribute_value")
        @BatchSize(size = 20)
        var attributes: ContainerAttributes = null,

        @Column(name = "created_date")
        var createdDate: Date? = null,

        @Column(name = "modified_date")
        var modifiedDate: Date? = null

) {

    @PrePersist
    fun prePersist() {
        val now = Date.from(Instant.now())
        this.createdDate = now
        this.modifiedDate = this.createdDate
    }

    @PreUpdate
    fun preUpdate() {
        val now = Date.from(Instant.now())
        this.modifiedDate = now
    }

    override fun toString(): String = ObjectMapper().writeValueAsString(this)
}