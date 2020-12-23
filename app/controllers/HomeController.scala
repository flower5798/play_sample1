package controllers

import javax.inject._
import play.api._
import play.api.mvc._

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>
    var numbers = List.empty[Int]
    for(i <- 1 to 10){ numbers = i :: numbers }
    Ok(views.html.index("GET", "/", numbers))
  }

  def post() = Action { implicit request: Request[AnyContent] =>
    Ok(views.html.contact.post())
  }

  def put() = Action { implicit request: Request[AnyContent] =>
    var numbers = List.empty[Int]
    for(i <- 1 to 10){ numbers = i :: numbers }
    Ok(views.html.index("PUT", "/contact", numbers))
  }

  def text1() = Action { implicit request: Request[AnyContent] =>
    Ok("1111")
  }

//  def hello(): Action[AnyContent] = Action.apply(new Status(200)) // Scalaにおいてapplyメソッドは省略可能
//  def hello(): Action[AnyContent] = Action(new Status(200))
//  def hello(): Action[AnyContent] = Action(Ok) // OKが定数オブジェクトとして存在する
  def hello() = Action { implicit request: Request[AnyContent] => {
//      val result = Ok("Hello World")
      var aaa = 1
      Ok("Hello World").as("text/plain").withHeaders(
        CACHE_CONTROL -> "max-age=3600",
        ETAG -> "xx")

    }
  }

}
