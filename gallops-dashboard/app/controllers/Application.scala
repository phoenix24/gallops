package controllers

import play.api.mvc._
import commons.HelloCommons
import features.HelloFeatures


object Application extends Controller {

  def index = Action {
    Ok(views.html.index(s"Your new application is ready : with ${HelloCommons.name} && ${HelloFeatures.name} dependencies."))
  }
}