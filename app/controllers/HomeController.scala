package controllers

import javax.inject._
import play.api._
import play.api.db.DBApi
import play.api.mvc._

import java.sql.ResultSet

/**
 * This controller creates an `Action` to handle HTTP requests to the
 * application's home page.
 */
@Singleton
class HomeController @Inject()(dbApi : DBApi, val controllerComponents: ControllerComponents) extends BaseController {

  /**
   * Create an Action to render an HTML page.
   *
   * The configuration in the `routes` file means that this method
   * will be called when the application receives a `GET` request with
   * a path of `/`.
   */
  def index() = Action { implicit request: Request[AnyContent] =>

    // DBを複数使う場合はapplication.confに設定を追加し、↓のdefaultの部分を変えて呼び出す
    val db = dbApi.database("default")

    db.withConnection { implicit conn =>
      // do whatever you need with the connection

      val stmt = conn.createStatement()
//      var query = "SELECT * FROM users"
      var query = "CREATE TABLE IF NOT EXISTS users(id INTEGER, name TEXT);"
      stmt.execute(query)

//      val statement = connection.createStatement()
//      val resultSet = statement.executeQuery("SELECT name from person where id = 1")
//
//      while (resultSet.next()) {
//        // 結果の取得(outStringへshikaが追記される)
//        outString += resultSet.getString("name")
//      }

    }

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

  // テキストを吐き出す場合は文字列を渡す
  // APIを作る場合も同じ手法(ただしheaderのcontent-typeを変更して返すようにする)
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
