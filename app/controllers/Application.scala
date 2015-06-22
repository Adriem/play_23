package controllers

import models._

import play.api._
import play.api.db.slick._
import play.api.libs.json.Json
import play.api.mvc._
import scala.Some

object Application extends Controller {

  implicit val beerFormat = Json.format[Beer]

  def index = Action {
    Ok(views.html.index("Your new application is ready."))
  }

  def getBeers = DBAction { implicit s =>
    Ok(Json.toJson(Beers.list()))
  }

  def postBeer() = DBAction(parse.json) { implicit result =>
    result.body.validate[Beer].map(
      beer => {
        Beers.insert(beer)
        Ok(Json.toJson(beer))
      }
    ).getOrElse(BadRequest)
  }
}