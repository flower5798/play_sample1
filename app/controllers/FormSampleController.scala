package controllers

import javax.inject._
import play.api._
import play.api.data.Form
import play.api.mvc._
import play.filters.csrf.CSRF
import views.html.defaultpages.badRequest

import scala.collection.immutable.ListMap
//import play.api.data.Form

/**
  * This controller creates an `Action` to handle HTTP requests to the
  * application's home page.
  */
@Singleton
class FormSampleController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  def index() = Action { implicit request =>
    val token = CSRF.getToken(request)
    Ok(views.html.form_sample.index(token.toString()))
  }

  def post() = Action { implicit request: Request[AnyContent] =>

    // フォームのデータ取り出し
    // https://qiita.com/someone7140/items/606279d505823df3bb5b
    Ok(views.html.form_sample.post(request.body.asFormUrlEncoded.get("name")(0)))
  }
}
