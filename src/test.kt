fun main(){
    val html="""
        <p>
            尊敬的用户：<br />
            系统单点登录出现异常，请及时与系统管理员联系。<br />
     
       		身份验证超时！请检查网络通信是否正常。
     
         </p>
    """.trimIndent()

    println(html)
    print(html.contains("系统单点登录出现异常"))
}
