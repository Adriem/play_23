import models.{Beer, Beers}
import play.api.db.DB
import play.api.libs.json.Json
import play.api.{Logger, Application, GlobalSettings}

import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.Play.current
import play.api.mvc._

import scala.io.Source
import scala.slick.driver.MySQLDriver.simple._

class CorsFilter extends EssentialFilter {
  def apply(next: EssentialAction) = new EssentialAction {
    def apply(requestHeader: RequestHeader) = {
      next(requestHeader).map { result =>
        result.withHeaders("Access-Control-Allow-Origin" -> "*",
          "Access-Control-Expose-Headers" -> "WWW-Authenticate, Server-Authorization",
          "Access-Control-Allow-Methods" -> "POST, GET, OPTIONS, PUT, DELETE",
          "Access-Control-Allow-Headers" -> "x-requested-with,content-type,Cache-Control,Pragma,Date")
      }
    }
  }
}

object Global extends WithFilters(new CorsFilter) with GlobalSettings{

  implicit val beerFormat = Json.format[Beer]

  override def onStart(app: Application): Unit = {
    Logger.info(s"Starting the app - checking data saved")
    Database.forDataSource(DB.getDataSource()) withSession {
      implicit rs =>
        if(Beers.list().isEmpty){
          Logger.info("Empty database - populating")
          val beers = Json.parse(Source.fromURL(app.classloader.getResource("data/beers.json")).map(_.toByte).toArray)
          (beers \ "data").as[List[Beer]].foreach(beer => Beers.insert(beer))
        }
    }
  }



}
