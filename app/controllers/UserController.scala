package controllers

//
import akka.http.scaladsl.model.HttpHeader.ParsingResult.Ok
import play.api.data._
import play.api.data.Forms._
import models._
import play.mvc.Controller

object UserController extends Controller {

  val userDataForm: Form[User] = Form (
    mapping(
      "name" -> text, //textは空文字でも良いrequiredなString型文字列
      "email"-> text,
    )(User.apply)(User.unapply)
  )

//  def create = Action { implicit request =>
//    userDataForm.fold(
//      errors => BadRequest(views.html.errors(errors)),
//      // userDataFormを定義した時のmappingのvalueを参考にValidationを行う。
//      // Validationに引っかかったらerrorsの処理を行う。
//      userdata => {  //この後で利用する変数名
//        //ここがuserなら↓のuserdata.nameはuser.nameになる。
//        println(userdata.name)
//        println(userdata.email)
//        //ここでPOSTデータを受け取った後の処理を行う。
//        //ログインとか
//        Ok(views.html.form_sample.index())
//      }
//    )
//  }

}