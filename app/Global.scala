import models.{Beer, Beers}
import play.api.db.DB
import play.api.libs.json.Json
import play.api.{Logger, Application, GlobalSettings}

import play.api.Play.current

import scala.io.Source
import scala.slick.driver.MySQLDriver.simple._

object Global extends GlobalSettings{

  implicit val beerFormat = Json.format[Beer]

  override def onStart(app: Application): Unit = {
    Logger.info(s"Starting the app - checking data saved")
    Database.forDataSource(DB.getDataSource()) withSession {
      implicit rs =>
        if(Beers.list().isEmpty){
          Logger.info("Empty database - populating")
          val beers = Json.parse(Source.fromFile(play.api.Play.getFile("/conf/data/beers.json")).map(_.toByte).toArray)
//          Logger.info(beer.toString())
          (beers \ "data").as[List[Beer]].foreach(beer => Beers.insert(beer))
        }
    }
  }
}
