import java.beans.Expression
import java.lang.IllegalArgumentException
import java.util.*

fun main(args:Array<String>){
    println("hello, world!")
    loopTest()
}

fun hello(args: Array<String>){
    val name=if (args.isNotEmpty()) args[0] else "kotlin"
    println("hello, $name")
    println("hello,${args[0]}")
    println("hello,${if (args.isNotEmpty()) args[0] else "kotlin"}")
}
fun max(a:Int,b:Int)=if (a>b) a else b

fun max2(a:Int,b:Int):Int{

    return if (a>b) a else b
}
//val-->value <----> java final reference
val answer="i am wpy"

//var-->variable  Non final reference
var answers="i am student"


//kotlin中建立这种类似c中struct的值对象很方便把
class Person(val name: String,val age:Int)
class Rectangle(val height:Int,val width:Int){
    val isSquare:Boolean
    get() = height==width

}
fun classtest(){
    val person=Person("Bob",20)
    println(person.age)
    val rec=Rectangle(20,20)
    println(rec.isSquare)
    val t=test()
    println(t.x)

}
class test{
    var x:Int = 0
}
enum class Color{
    RED,YELLOW,BLUE;

    fun getWarmth(color: Color)=when(color){
        RED->"warm"
        YELLOW,BLUE->"cold"

    }
}
enum class ColorRgb(val r:Int,val g:Int,val b:Int){
    RED(255,0,0),YELLOW(255,255,0),BLUE(0,0,255);

    fun rgb()=(r*256+g)*256+b
}


interface Expr
class Num(val value:Int):Expr
class Sum(val left:Expr,val right:Expr):Expr

fun eval(e:Expr):Int=
    when(e){
    is Num->
        e.value
    is Sum->
        eval(e.right)+eval(e.left)
    else->
        throw IllegalArgumentException("Unknown expression")

}

fun loopTest(){
    //  for (i in 100 downTo 1 step 2)
    //     println(i)
    for (i in 1..100 )
        println(i)
}

//迭代map
val binaryReps=TreeMap<Char,String>()
fun iterateMap(map:TreeMap<Char,String>){
    for (c in 'a'..'f'){
        val binary=Integer.toBinaryString(c.toInt())
        binaryReps[c]=binary
    }
    for ((letter,binary) in binaryReps){
        println("$letter = $binary")
    }
}

