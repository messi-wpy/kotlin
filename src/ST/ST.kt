package ST

class BST<Key,Value>(val compare:(Key,Key)->Int) {

    private var root:Node<Key,Value>?=null
     class Node<Key,Value>(var key:Key,var value: Value){
        var left:Node?=null
        var right:Node?=null
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
    fun size(tree:Node<Key,Value>):Int{

    }
    fun size():Int{

        return 0;
    }
    fun min():Key{

    }
    fun max():Key{

    }
    fun floor(key: Key):Key{

    }
    fun ceiling(key: Key):Key{

    }
    fun rank(key: Key):Int{

    }
    fun select(k:Int):Key{

    }

}