package com.company.example.aem.common

import com.google.gson.GsonBuilder

object JsonUtils {

  val GSON = GsonBuilder().serializeNulls().setPrettyPrinting().create()

}
