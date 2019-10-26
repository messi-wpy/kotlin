package ST


fun main(){
    println("hello ")
    val map=BST<Int,String>{a,b->a-b}
    map.put(2,"asd")
    map.put(4,"aaa")
    map.put(34,"eeee")
    map.put(23,"123")
    map.put(2222,"cccc222")
    println(map.get(23))
    println(map.floor(1))

}
class BST<Key,Value>(val compare:(Key,Key)->Int) {

    private var root:Node<Key,Value>?=null
     class Node<Key,Value>(var key:Key,var value: Value){
        var left:Node<Key,Value>?=null
        var right:Node<Key,Value>?=null
        var n:Int=0
    }

    fun put(key:Key,value:Value){

        fun putImpl(x:Node<Key,Value>?,k: Key,v:Value):Node<Key,Value>{
            if (x==null) return Node(k,v)
            val cmp=compare(k,x.key)
            when {
                cmp<0 -> x.left=putImpl(x.left,k,v)
                cmp>0 -> x.right=putImpl(x.right,k,v)
                cmp==0 -> x.value=v
            }
            x.n=size(x.left)+size(x.right)
            return x
        }

        root=putImpl(root,key,value)
    }

    fun get(key: Key):Value?{

        fun getImpl(x:Node<Key,Value>?,k: Key):Value?{
            if (x==null) return null
            val cmp=compare(k,x.key)
            when{
                cmp>0 ->return getImpl(x.right,k)
                cmp<0 ->return getImpl(x.left,k)
            }
            return x.value
        }
       return getImpl(root,key)
    }


    fun delete(key: Key){}
    fun isEmpty():Boolean{
        return false
    }
    fun contains():Boolean{
        return  false
    }
    fun size(tree:Node<Key,Value>?):Int{
        return 0;
    }
    fun size():Int{

        return 0;
    }

    fun min():Key?{

        fun minImpl(root:Node<Key,Value>):Node<Key,Value>{
            return root.left?.let {  minImpl(it) }?:root
        }
        val targetNode= root?.let { minImpl(it) }
        return targetNode?.key

    }


    //小于等于key的最大键
    fun floor(key: Key):Key?{

        fun floorImpl(root:Node<Key,Value>?):Node<Key,Value>?{
            if (root==null)return null
            val cmp=compare(key,root.key)

            when{
                cmp==0 -> return root
                cmp<0 -> return floorImpl(root.left)
            }
            val temp=floorImpl(root.right)
            return temp?:root

        }
        val res=root?.let { floorImpl(it) }
        return res?.key

    }
    /*
    //大于等于key的最小键
    fun ceiling(key: Key):Key{

    }
    //小于key的数量
    fun rank(key: Key):Int{

    }
    //排名为k的键
    fun select(k:Int):Key{

    }
*/

}