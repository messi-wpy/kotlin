package threadLocalStudy

import java.lang.ref.WeakReference
import java.util.function.BiConsumer

class MyThread(target:Runnable,name:String):Thread(target,name){
    var threadLocals:MyThreadLocal.MyThreadLocalMap?=null


}

open class MyThreadLocal<T>{

    private fun getMap(t:MyThread):MyThreadLocalMap?=t.threadLocals

    fun set(value:T){
        val t=Thread.currentThread() as MyThread
        val map=getMap(t)
        if (map!=null)
            map.set(this,value)
        else
            createMap(t,value)
    }

    fun get():T?{
        val t=Thread.currentThread() as MyThread
        val map=getMap(t)
        if (map!=null){
            val e=map.get(this)
            if (e!= null)
                return e as T?
        }
        return setInitialValue()
    }

    fun remove(){
        val m=getMap(Thread.currentThread() as MyThread)
        m?.remove(this)
    }
    private fun setInitialValue():T?{
        val  value=initialValue()
        val  t=Thread.currentThread() as MyThread
        val  map=getMap(t)
        if (map != null)
            map.set(this, value as Any?)
        else
            createMap(t, value)
        return value
    }
    open fun initialValue():T?{
        return null
    }
    private fun createMap(t:MyThread,value: T?){
        t.threadLocals=MyThreadLocalMap(this,value)
    }




    open class MyThreadLocalMap(firstKey:MyThreadLocal<*>,firstValue:Any?){


        private var mMap:MutableMap<WeakReference<MyThreadLocal<*>>,Any?>?=null

        init {
            mMap= mutableMapOf(WeakReference(firstKey) to firstValue)
        }


        fun set(key:MyThreadLocal<*>,value:Any?){
            expungeStaleEntry()
            mMap?.let { map ->
                var keyExist=false
                map.forEach {
                    if (it.key.get()==key){
                        map[it.key]=value
                        keyExist=true
                    }

                }

                if (!keyExist){
                    map[WeakReference(key)]=value
                }

            }

        }


        fun get(key:MyThreadLocal<*>):Any?{
            expungeStaleEntry()

            mMap?.forEach { (k, v) ->
                if (k.get() == key) {
                    return v
                }
            }
            return null

        }
        fun test(f:(Int,String)->Int){
            f(2,"a")
        }

        fun remove(key: MyThreadLocal<*>){
            expungeStaleEntry()
            test {i, s ->
                print("a  $i")
                return@test 2

            }
            //kotlin函数类型实例化的方式
            /*
            mMap?.forEach(fun (m:Map.Entry<WeakReference<MyThreadLocal<*>>,Any?>):Unit {

            })
            */
            mMap?.forEach{(k,v)->
                if (k.get()==key){
                    mMap?.remove(k)
                }

            }

        }

        private fun expungeStaleEntry(){
            mMap?.forEach { (k,v)->
                run {
                    if (k.get() == null) {
                        mMap!!.remove(k)
                    }
                }
            }
        }

    }

}

