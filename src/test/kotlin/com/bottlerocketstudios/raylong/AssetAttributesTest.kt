package com.bottlerocketstudios.raylong

import org.assertj.core.api.Assertions
import org.junit.Test

import org.junit.Assert.*

class AssetAttributesTest {

    @Test
    fun valueOfEnumVal() {
        val id = AssetAttributes.ID.valueOfEnum("id")
        Assertions.assertThat(id).isEqualTo(AssetAttributes.ID)

        val name = AssetAttributes.NAME.valueOfEnum("name")
        Assertions.assertThat(name).isEqualTo(AssetAttributes.NAME)

        val type = AssetAttributes.TYPE.valueOfEnum("type")
        Assertions.assertThat(type).isEqualTo(AssetAttributes.TYPE)

        val url = AssetAttributes.URL.valueOfEnum("url")
        Assertions.assertThat(url).isEqualTo(AssetAttributes.URL)

        val expiry = AssetAttributes.EXPIRY.valueOfEnum("expiry")
        Assertions.assertThat(expiry).isEqualTo(AssetAttributes.EXPIRY)
    }

    @Test
    fun getEnumVal() {
        val id = AssetAttributes.ID.enumVal
        Assertions.assertThat(id).isEqualTo("id")

        val name = AssetAttributes.NAME.enumVal
        Assertions.assertThat(name).isEqualTo("name")

        val type = AssetAttributes.TYPE.enumVal
        Assertions.assertThat(type).isEqualTo("type")

        val url = AssetAttributes.URL.enumVal
        Assertions.assertThat(url).isEqualTo("url")

        val expiry = AssetAttributes.EXPIRY.enumVal
        Assertions.assertThat(expiry).isEqualTo("expiry")
    }
}