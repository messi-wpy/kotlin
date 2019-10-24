import java.io.File
import kotlin.text.StringBuilder

fun flatMapTest() {
    val strings = listOf("abc", "def")
    println(strings.flatMap { it.toList() })

}
fun groupTest(){
    val lambda={s:String->s.first()}
    val list= listOf("abc","baa","a","b","ca")
    println(list.groupBy(lambda))
    println(list.groupBy(String::first))

}

/**
 * kotlin sequence 序列懒加载
 * 区别于集合
 */
fun sequenceTest(){
    val naturalNumbers= generateSequence (0){it+1  }
    val numTo100=naturalNumbers.takeWhile { it<=100 }
    println(numTo100.toList())
    println(numTo100.sum())


}
fun defParameter(name:String="def",age:Int){
}
fun defParameter2(name:String,age: Int=0){}
fun testdef(){
    defParameter2("w",2)
    defParameter2("www")
    //defParameter(2)
}

fun File.isInsideHiddenDirectory()= generateSequence (this){it.parentFile  }.any{it.isHidden}

fun alphabet():String{
    val sb=StringBuilder()
    with(sb){
        for (letter in 'A'..'Z'){
            this.append(letter)
        }
        append("\nNow this is alphabet")
    }
    return sb.toString()
}

//函数表达体写法--->可以省去return
fun alphabet2()= with(StringBuilder()){
    for (letter in 'A'..'Z'){
        append(letter)
    }

    append("\nNow this is alphabet")
    toString()
}

//apply--->kotlin自带的构造这模式
fun alphabet3()=StringBuilder().apply {
    for (letter in 'A'..'Z'){
        this.append(letter)
    }
    append("\nNow this is alphabet")
}.toString()


fun main(){

    //print(alphabet())

    test2()
    val a= listOf("asd","ASd","ASd","Ddd")
    println(a.joinString(){it[2].toString()})

}

class Student(val school:String,val name: String){
    var grade:Int=0
    constructor(num:Int,name: String):this("www",name){
        grade=num
    }
    fun show()= print("school:$school,name:$name")
    fun test(){
        val list= listOf(21,40,11,33,78)
        list.filter { it%3==0 }.forEach{ println(it)}

    }
}

fun test2(){
    var s="asdsadaaaadsda"
    s=s.filter { it=='a' }
    println(s)
}


val sum={a:Int,y:Int->a+y}

fun String.filter(pred:(Char)->Boolean):String{
    val sb=StringBuilder()
    println("my fun")
    for (index in 0 until length){
        val element=get(index)
        if (pred(element))
            sb.append(element)
    }
    return sb.toString()

}

//kotlin 语法约定：如果lambda表达式时函数调用的最后一个实参，可以放到括号外边，
//如果是唯一实参，可以去掉括号
fun <T>Collection<T>.joinString(
    separator:String=",",
    prefix:String="",
    postfix:String="",
    transform:((T)->String)?=null

):String{
    val  result=StringBuilder(prefix)

    for ((index,element) in this.withIndex()){
        if (index>0)result.append(separator)
        result.append(transform?.invoke(element)?:element.toString())
    }
    result.append(postfix)
    return result.toString()
}

//返回函数的函数

enum class Delivery{SSSS,TTTT}
class Order(val itemCount:Int)

fun getMethod(d:Delivery):(Order)->Double{
    if (d==Delivery.SSSS)
        return { order ->
            print("test")
            0.11
        }

    return { 1.2*it.itemCount }

}

fun  testReturn(){
    val calcutor=getMethod(Delivery.SSSS)
    println(calcutor(Order(999)))

}


