import java.io.File
import java.lang.StringBuilder

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

    print(alphabet())

}