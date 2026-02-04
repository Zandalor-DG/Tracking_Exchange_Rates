package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

sealed class Destinations(
  val route: String,
  val objectName: String? = null,
  val objectPath: String? = null
) {
  object Main : Destinations("main") {
    object Currencies : Destinations("currencies")
    object Favorites : Destinations("favorites")
  }
  object Filters : Destinations("filters")

  // Dynamic destinations
//  object Details : Destinations(
//    route = "details",
//    objectName = "id",
//    objectPath = "/{id}"
//  ) {
//    fun createRoute(id: String) = "details/$id"
//  }
}