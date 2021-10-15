##JVM调优
###1.JVM的参数类型
```
标准参数
-help
-server -client
-verson -showversion
-cp -classpath
X参数
-Xint:解释执行
-Xcomp:第一次使用就编译成本地代码
-Xmixed:混合模式，JVM自己来决定是否编译成本地代码   
--XX参数
Boolean类型
格式:[+-]<name>表示启用或者禁用name属性
例子:-XX:+UseConcMarkSweepGC
-XX:+UseG1GC
非Boolean类型
格式:<name>=<value>表示name属性的值是value
例子:-XX:MaxGCPauseMills=500
-XX:GCTimeRation=19
-Xmx -Xms 不是X参数,而是XX参数 最大内存与最小内存
-Xmx等价于-XX:MaxHeapSize
-Xms等价于-XX:InitalHeapSize
-xss等价于-XX:ThreadStackSize 栈内存 默认1024k
```

###2.查看JVM运行时参数
```
-XX:+PrintFlagsInitial查看初始值
-XX:+PrintFlagsFinal查看最终值
-XX:+UnlockExperimentalVMOptions解锁实验参数  
``` 
