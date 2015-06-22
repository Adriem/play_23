package models

import play.api.Logger
import play.api.db.slick.Config.driver.simple._

case class Beer(name: String,
                details: String,
                img: String,
                provenance: String,
                vol: Double)

class Beers(tag: Tag) extends Table[Beer](tag, "BEER") {
  def name = column[String]("name", O.PrimaryKey)

  def details = column[String]("details")

  def img = column[String]("img")

  def provenance = column[String]("provenance")

  def vol = column[Double]("vol")

  def * = (name, details, img, provenance, vol) <> (Beer.tupled, Beer.unapply)
}

object Beers{

  private val beers = TableQuery[Beers]

  def list()(implicit s: Session) = {
    Logger.info("Listing all data")
    beers.list
  }

  def insert(beer: Beer)(implicit s: Session) = {
    Logger.info(s"Inserting new data: $beer")
    beers insert beer
  }
}