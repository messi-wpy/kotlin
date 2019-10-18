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
open class user_1(val name:String,val age:Int)

open class user_2(var name:String="def",val age:Int=0){
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
        val u=user_2()
        name=named()
    }

}

class user_3():user_1("www",2){
   val id="00001"
}

class user_4:user_2{

    constructor():super(){

    }

}
//by---使用类委托
class CountingSet<T>(val innerSet:MutableCollection<T>):MutableCollection<T> by innerSet{
    private var added =0

    override fun add(element: T): Boolean {
        added++;
        print(added)
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        added+=elements.size
        println(added)
        return  innerSet.addAll(elements)
    }


}
fun main(){
    /*
    val user2=user_2("wpy",20)
    println(user2.address)
    val mySet=CountingSet(HashSet<Int>())
    mySet.add(2)
    mySet.addAll(listOf<Int>(1,2,3))
    println(mySet.contains(4))
*/
    CompareList()
}

//伴生对象--->就是一个类中静态字段，但可以访问private成员，就这点用处
//const --->编译期常量---只能修饰基本类型和string（编译时可以直接替换）
class CompanionTest(val name: String){
    private var age=20
    companion object{
        const val TAG="companion"
        fun cAge(){

            val temp=CompanionTest("sss")
            //可以访问private修饰的age，
            println(temp.age)
        }

    }

}
class User {
    val id: Int
    val name: String

    constructor(id: Int, name: String) {
        //👆 没有 public
        this.id = id
        this.name = name
    }
}
//顶层函数
fun testConstructor(){

}

object SingleTest{

    var time=0
    var name="Single"
    val nums= arrayOf(2,3,4)
    fun Single():Int{
        return time
    }

}

private class Wpy private constructor(val name: String){
    companion object{
        fun newWpy(name: String)= Wpy(name)
    }

}

fun CompareList(){
    //集合
    val list= listOf(1,2,3)
    //array1其实是装箱后的，Integer[]数组
    val array1= arrayOf(1,2,3)
    //array2时没有装箱的 ，int[]数组
    val array2= IntArray(100){i-> i+1}
    for (i in array2){
        println(i)
    }
}