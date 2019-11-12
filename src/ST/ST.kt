package ST


fun main() {
    println("hello ")
    val map = BST<Int, String> { a, b -> a - b }
    map.put(2, "asd")
    map.put(4, "aaa")
    map.put(34, "eeee")
    map.put(23, "123")
    map.put(2222, "cccc222")
    println(map.ceiling(35))
    println(map.rank(44))
    println(map.max())
    map.delete(2222)
    println(map.max())
}

class BST<Key, Value>(val compare: (Key, Key) -> Int) {

    private var root: Node<Key, Value>? = null

    class Node<Key, Value>(var key: Key, var value: Value) {
        var left: Node<Key, Value>? = null
        var right: Node<Key, Value>? = null
        var n: Int = 0
    }

    fun put(key: Key, value: Value) {

        fun putImpl(x: Node<Key, Value>?, k: Key, v: Value): Node<Key, Value> {
            if (x == null) return Node(k, v)
            val cmp = compare(k, x.key)
            when {
                cmp < 0 -> x.left = putImpl(x.left, k, v)
                cmp > 0 -> x.right = putImpl(x.right, k, v)
                cmp == 0 -> x.value = v
            }
            x.n = size(x.left) + size(x.right)
            return x
        }

        root = putImpl(root, key, value)
    }


    fun get(key: Key): Value? {

        fun getImpl(x: Node<Key, Value>?, k: Key): Value? {
            if (x == null) return null
            val cmp = compare(k, x.key)
            when {
                cmp > 0 -> return getImpl(x.right, k)
                cmp < 0 -> return getImpl(x.left, k)
            }
            return x.value
        }
        return getImpl(root, key)
    }


    fun deleteMin(){
        root=deleteMinImpl(root)
    }

    private fun deleteMinImpl(root:Node<Key,Value>?):Node<Key,Value>?{
        if (root==null)
            return null
        if (root.left==null)
            return root.right
        root.left=deleteMinImpl(root.left)
        root.n=size(root.left)+size(root.right)+1
        return root

    }
    fun delete(key: Key) {
        fun deleteImpl(root: Node<Key, Value>?):Node<Key,Value>?{
            if (root == null)
                return null
            val cmp = compare(key, root.key)
            return when {
                cmp < 0 -> {
                    root.left = deleteImpl(root.left)
                    root
                }
                cmp > 0 -> {
                    root.right = deleteImpl(root.right)
                    root
                }
                else -> {
                    if (root.left==null)return root.right
                    if (root.right==null)return root.left
                    val temp=root.right?.let { min(it) }
                    temp?.left=root.left
                    temp?.right=deleteMinImpl(root.right)
                    temp?.let {
                        temp.n=size(temp.left)+size(temp.right)+1
                    }
                    temp
                }

            }


        }
       root= deleteImpl(root)

    }

    fun isEmpty(): Boolean {
        return root==null
    }

    fun contains(): Boolean {
        return false
    }

    fun size(tree: Node<Key, Value>?): Int {

        if (tree == null)
            return 0
        val left = size(tree.left)
        val right = size(tree.right)

        return left + right + 1;
    }

    fun size(): Int {

        return (root?.n ?: -1) + 1;
    }

    fun min(): Node<Key,Value>? {

        fun minImpl(root: Node<Key, Value>): Node<Key, Value> {
            return root.left?.let { minImpl(it) } ?: root
        }

        return root?.let<Node<Key, Value>, Node<Key, Value>> { minImpl(it) }

    }
    fun min(root: Node<Key, Value>): Node<Key, Value> {
        return root.left?.let { min(it) } ?: root
    }

    fun max(): Key? {
        fun maxImpl(root: Node<Key, Value>): Node<Key, Value> {
            return root.right?.let { maxImpl(it) } ?: root

        }

        val target = root?.let { maxImpl(it) }
        return target?.key

    }

    //小于等于key的最大键
    fun floor(key: Key): Key? {

        fun floorImpl(root: Node<Key, Value>?): Node<Key, Value>? {
            if (root == null) return null
            val cmp = compare(key, root.key)

            when {
                cmp == 0 -> return root
                cmp < 0 -> return floorImpl(root.left)
            }
            val temp = floorImpl(root.right)
            return temp ?: root

        }

        val res = root?.let { floorImpl(it) }
        return res?.key

    }

    //大于等于key的最小键
    fun ceiling(key: Key): Key? {
        fun ceilImpl(root: Node<Key, Value>?): Key? {
            if (root == null)
                return null
            val cmp = compare(key, root.key)
            return when {
                cmp < 0 -> ceilImpl(root.left) ?: root.key
                cmp > 0 -> ceilImpl(root.right)
                else -> root.key
            }
        }
        return ceilImpl(root)

    }

    fun rank(key: Key): Int {
        fun rankImpl(root: Node<Key, Value>?): Int {
            if (root == null)
                return 0;
            val cmp = compare(key, root.key)
            return when {
                cmp < 0 -> rankImpl(root.left)
                cmp > 0 -> 1 + rankImpl(root.right) + size(root.left)
                else -> size(root.left)
            }

        }
        return root?.let { rankImpl(root) } ?: 0
    }


    //排名为k的键
    fun select(k: Int): Key? {
        fun selectImpl(node: Node<Key, Value>?, k: Int): Key? {
            if (node == null)
                return null
            val length = size(node.left)
            return when {
                length > k -> selectImpl(node.left, k)
                length < k -> selectImpl(node.right, k - length - 1)
                else -> return node.key
            }


        }
        return selectImpl(root, k)
    }

}