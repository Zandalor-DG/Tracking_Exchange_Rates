package com.paliy_dmitriy.tracking_exchange_rates.presentation.navigation

sealed class Destinations(
  val route: String,
  val objectName: String? = null,
  val objectPath: String? = null
) {
  object Currencies : Destinations("currencies")
  object Filters : Destinations("filters")
  object Favorites : Destinations("favorites")

  // Dynamic destinations
//  object Details : Destinations(
//    route = "details",
//    objectName = "id",
//    objectPath = "/{id}"
//  ) {
//    fun createRoute(id: String) = "details/$id"
//  }
}