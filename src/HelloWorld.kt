fun main(args:Array<String>){
    println("hello, world!")
    hello(Array(2) {"wpy,messi"})
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

