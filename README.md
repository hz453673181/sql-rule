基于SQL的轻量级规则引擎，封装apache calcite，将SQL作为DSL语言，降低业务人员的使用成本

关于规则引擎先简单介绍下DSL  

DSL（Domain Specified Language）领域专用语言。 
它的出现是为了解决能够让领域专家通过简单编程的方式描述领域中的所有活动和规则，这里的领域专家指的是各业务中的专家，比如营销活动专家、风控模型专家，数据
分析专家等。
DSL面向的使用对象是领域专家，而非程序员，它隐藏了很对实现细节，是程序员在一种通用编程语言上构建出来的另外一种语言。
举个例子：很多数据分析专家需要从数仓中提取数据，其过程是通过hive客户端提交一段SQL，然后得到自己想要的数据列表。hive实际上是将用户的SQL翻译成MR任务，
在hadoop集群上执行，而在hive出现以前，这种提取任务需要编写大量的MR程序，在这个例子中，SQL实际上就是作为了一种DSL语言，业务专家，数据专家，只需要会写
SQL就可以了，完全不必了解MR到底是个什么东西。

言归正传，sql-rule是借鉴了sql on hadoop的思想，将整个规则匹配的执行过程，看做是一次SQL查询
比如，有个Student的JavaBean，有三个属性，id,age,sex.当业务人员要制定一个规则，年龄大于25，性别为男，同事满足这两个条件才能进行后续的业务，
通过硬编码的方式：
if(student.getAge()>25 && student.getSex()==1){
  // TODO
}
通过SQL的方式
我们可以把Student 看成一张表，虚拟的数据库表，这张表有三列id,age,sex,对于这张虚拟表，它只有一行数据，就是通过JavaBean setXXX的几个属性
实现上面的规则可以看做一次查询
select * from Student where age>25 and sex=1
返回行数为1，表示满足规则，为0，则不满足规则
或者
select count(*) from Student where age>25 and sex=1,亦可。

比较两种方式，第二种方式其实就是一种DSL的实现，业务人员要配置规则，只需要编写SQL就可以。

