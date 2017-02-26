package config

import com.typesafe.config.ConfigFactory

/**
  * Created by jco59 on 26/02/2017.
  *
  * This object represents the application configuration (HOCON)
  */
object Settings {

  private val config = ConfigFactory.load()

  object WebLogGen {
    private val webLogGen = config.getConfig("clickstream")

    lazy val records = webLogGen.getInt("records")
    lazy val timeMultiplier = webLogGen.getInt("time_multiplier")
    lazy val pages = webLogGen.getInt("pages")
    lazy val visitors = webLogGen.getInt("visitors")
    lazy val filePath = webLogGen.getString("file_path")
  }
}
