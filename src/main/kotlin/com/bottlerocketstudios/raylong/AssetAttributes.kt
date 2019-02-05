package com.bottlerocketstudios.raylong

enum class AssetAttributes(val enumVal: String) {
    ID ("id") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return ID

            return null
        }

    },
    NAME("name") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return NAME

            return null
        }

    },
    TYPE("type") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return TYPE

            return null
        }

    },
    URL("url") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return URL

            return null
        }

    },
    EXPIRY("expiry") {
        override fun valueOfEnum(enumVal: String): AssetAttributes? {
            if (enumVal == this.enumVal) return EXPIRY

            return null
        }

    };

    abstract fun valueOfEnum(enumVal: String): AssetAttributes?
}