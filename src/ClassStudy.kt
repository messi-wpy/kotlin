import kotlin.test.fail

interface Clickable{
    fun click()
    fun showOff() = println("I'm clickable")
}

interface Focusable{
    fun setFocus(b:Boolean) = println("I ${if (b) "got" else "lost"} focus")
    fun showOff()= println("I'm focusable")

}

class Button:Clickable,Focusable{
    override fun click() {
        println("I'm clickable")
    }

    override fun showOff() {
        super<Clickable>.showOff()
    }


}
//构造函数练习
//默认主构造函数---一定会被调用
class user_1(val name:String,val age:Int)

open class user_2(var name:String,val age:Int){
    private var school:String
    val address:String
        get() = name+ field



    init {
        school="no"
        address="china"
    }
    constructor(name: String,age: Int,school:String):this(name,age){
        this.school=school
    }

   fun getSchool()=school

    open fun test( namex: String){
        fun named():String{
            return namex+"hahaha"
        }
        println(named())
        name=named()
    }

}

class user_3{
    val name="mike"

    val age:String="0"
}

fun main(){
    val user2=user_2("wpy",20)
    println(user2.address)

}

