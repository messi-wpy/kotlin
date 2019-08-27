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
    val user2=user_2("wpy",20)
    println(user2.address)
    val mySet=CountingSet(HashSet<Int>())
    mySet.add(2)
    mySet.addAll(listOf<Int>(1,2,3))
    println(mySet.contains(4))

}

//伴生对象--->就是一个类中静态字段，但可以访问private成员，就这点用处
class CompanionTest(val name: String){
    private var age=20
    companion object{
        fun cAge(){
            val temp=CompanionTest("sss")
            //可以访问private修饰的age，
            println(temp.age)
        }

    }

}

