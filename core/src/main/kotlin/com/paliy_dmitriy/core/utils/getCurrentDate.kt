package com.paliy_dmitriy.core.utils

import java.util.Calendar
import java.util.Locale

fun getCurrentDate(): String {
  val calendar = Calendar.getInstance()
  val year = calendar[Calendar.YEAR]
  val month = calendar[Calendar.MONTH] + 1
  val day = calendar[Calendar.DAY_OF_MONTH]
  return String.format(Locale.ROOT, "%04d-%02d-%02d", year, month, day)
}