package org.hikit.er.data

data class Category(
        val _id: Int,
        val parent: Int,
        val name: String
) {
    companion object {
        val ID: String = "_id"
        val PARENT: String = "parent"
        val NAME: String = "name"
    }
}