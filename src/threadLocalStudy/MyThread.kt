package threadLocalStudy

import java.lang.ref.WeakReference
import java.util.function.BiConsumer

class MyThread(target:Runnable,name:String):Thread(target,name){
    var threadLocals:MyThreadLocal.MyThreadLocalMap?=null


}


