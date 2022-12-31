package controllers

import akka.stream.scaladsl.StreamConverters
import play.api.db.DBApi
import play.api.mvc._

import java.io._
import javax.inject._

// import java.io.FileOutputStream;
// import java.io.IOException;
import java.nio.charset.StandardCharsets
import java.util.zip.{ZipEntry, ZipOutputStream}

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

  def generateTextFile(filename: String, content: String){
    val text = content
    val file = new File(filename)
    val bw = new BufferedWriter(new FileWriter(file))
    bw.write(text)
    bw.close()
    return file
  }

  def text2() = Action { implicit request: Request[AnyContent] =>
//    val file1 = generateTextFile("file1.txt", "1111")
//    val file2 = generateTextFile("file2.txt", "2222")

    // ファイルは作成せずメモリ上にのみ保持
    val files = Seq("1111.txt", "2222.txt")
    val baos = generateZipOutputStream(files)

    val ios = new ByteArrayInputStream(baos.toByteArray)

    Ok.chunked(StreamConverters.fromInputStream(() => ios)).withHeaders(
      CONTENT_TYPE -> "application/zip",
      CONTENT_DISPOSITION -> s"attachment; filename = test114514.zip"
    )
  }

  def generateZipOutputStream(files: Seq[String]): ByteArrayOutputStream = {
    val os = new ByteArrayOutputStream()
    val zip = new ZipOutputStream(os)

    files.foreach { name =>
      zip.putNextEntry(new ZipEntry(name))
      // TODO bの内容を正しいもの(テキストファイルの内容)へ修正(tuple等を使用して、ファイル名とコンテンツの両方を入れる)
      val b = name.getBytes(StandardCharsets.UTF_8);
      zip.write(b)
      zip.closeEntry()
    }
    zip.close()

    os
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
