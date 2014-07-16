package controllers

import play.api._
import play.api.mvc._
import commons.HelloCommons
import features.HelloFeatures


object Application extends Controller {

  def index = Action {
    println(HelloCommons.name)
    println(HelloFeatures.name)
    Ok(views.html.index("Your new application is ready."))
  }
}