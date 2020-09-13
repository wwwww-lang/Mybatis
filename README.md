# Mybatis# Mybatis

## 配置解析

### 1.生命周期和作用域

![image-20200822110451897](/Users/weibo/Library/Application Support/typora-user-images/image-20200822110451897.png)

生命周期和作用域是至关重要的，因为错误的使用会导致非常严重的并发问题。

#### 1.1 SqlSessionFactoryBuilder

- 这个类可以被实例化、使用和丢弃，一旦创建了 SqlSessionFactory，就不再需要它了。
- 局部方法变量

#### 1.2 SqlSessionFactory

- 可以想象为：数据库连接池
- 一旦被创建就应该在应用的运行期间一直存在，没有任何理由丢弃它或重新创建另一个实例。
- 因此 SqlSessionFactory 的最佳作用域是应用作用域。 （程序开始就开始，结束就结束）
- 最简单的就是使用单例模式或者静态单例模式。

#### 1.3 SqlSession

- 连接到连接池的请求。
- 最佳的作用域是请求或方法作用域。
- 用完后要立刻关闭，否则资源被占用。

![image-20200822112115838](/Users/weibo/Library/Application Support/typora-user-images/image-20200822112115838.png)

这里的每个mapper就代表一个业务。

### 2.ResultMap（解决属性名和字段名不一致的问题）

#### 2.1问题

数据库中的字段

![image-20200822112603408](/Users/weibo/Library/Application Support/typora-user-images/image-20200822112603408.png)

新建一个项目，拷贝之前，测试实体类字段不一致的情况

```java
public class User {
    private int Id;
    private String Name;
    private String password;
}
```

测试出现问题

![image-20200822113211905](/Users/weibo/Library/Application Support/typora-user-images/image-20200822113211905.png)

![image-20200822113339283](/Users/weibo/Library/Application Support/typora-user-images/image-20200822113339283.png)



解决方法：

- 起别名

![image-20200822113406068](/Users/weibo/Library/Application Support/typora-user-images/image-20200822113406068.png)

#### 2.2 ResultMap

结果集映射

```html
id name pwd
id name password
```

![image-20200822114011790](/Users/weibo/Library/Application Support/typora-user-images/image-20200822114011790.png)

- `resultMap` 元素是 MyBatis 中最重要最强大的元素。
- ResultMap 的设计思想是，对简单的语句做到零配置，对于复杂一点的语句，只需要描述语句之间的关系就行了。



### 3.日志

#### 3.1日志工厂

如果一个数据库操作，出现了异常，需要排错

曾经：sout，debug

现在：日志工厂

![image-20200822120106813](/Users/weibo/Library/Application Support/typora-user-images/image-20200822120106813.png)

- SLF4J 
-  LOG4J 
- LOG4J2 
-  JDK_LOGGING 
-  COMMONS_LOGGING 
-  STDOUT_LOGGING 
-  NO_LOGGING



在Mybatis中具体使用哪一个日志实现，在设置中设定

**STDOUT_LOGGING标准日志输出**

```xml
 <settings>
     <setting name="logImpl" value="STDOUT_LOGGING"/>
 </settings>
```



![image-20200824161718930](/Users/weibo/Library/Application Support/typora-user-images/image-20200824161718930.png)



#### 3.2 LOG4J 

什么是LOG4J ？

- Log4j是[Apache](https://baike.baidu.com/item/Apache/8512995)的一个开源项目，通过使用Log4j，我们可以控制日志信息输送的目的地是[控制台](https://baike.baidu.com/item/控制台/2438626)、文件、[GUI](https://baike.baidu.com/item/GUI)组件，甚至是套接口服务器、[NT](https://baike.baidu.com/item/NT/3443842)的事件记录器、[UNIX](https://baike.baidu.com/item/UNIX) [Syslog](https://baike.baidu.com/item/Syslog)[守护进程](https://baike.baidu.com/item/守护进程/966835)等。
- 也可以控制每一条日志的输出格式。
- 通过定义每一条日志信息的级别，我们能够更加细致地控制日志的生成过程。
- 这些可以通过一个[配置文件](https://baike.baidu.com/item/配置文件/286550)来灵活地进行配置，而不需要修改应用的代码。

1.导入log4j

```xml
<!-- https://mvnrepository.com/artifact/log4j/log4j -->
<dependency>
    <groupId>log4j</groupId>
    <artifactId>log4j</artifactId>
    <version>1.2.12</version>
</dependency>
```

2.log4j.properties

```properties
#将等级为DEBUG的日志信息输出到console和file这两个目的地，console和file的定义在下面的代码
log4j.rootLogger=DEBUG,console,file

\#控制台输出的相关设置
log4j.appender.console = org.apache.log4j.ConsoleAppender
log4j.appender.console.Target = System.out
log4j.appender.console.Threshold=DEBUG
log4j.appender.console.layout = org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=【%c】-%m%n

\#文件输出的相关设置
log4j.appender.file = org.apache.log4j.RollingFileAppender
log4j.appender.file.File=./log/kuang.log
log4j.appender.file.MaxFileSize=10mb
log4j.appender.file.Threshold=DEBUG
log4j.appender.file.layout=org.apache.log4j.PatternLayout
log4j.appender.file.layout.ConversionPattern=【%p】【%d{yy-MM-dd}】【%c】%m%n

\#日志输出级别
log4j.logger.org.mybatis=DEBUG
log4j.logger.java.sql=DEBUG
log4j.logger.java.sql.Statement=DEBUG
log4j.logger.java.sql.ResultSet=DEBUG
log4j.logger.java.sql.PreparedStatement=DEBUG
```

3.配置log4j的实现

```xml
<settings>
        <setting name="logImpl" value="LOG4J"/>
    </settings>
```



**简单使用**

1.在要使用Log4j的类中，导入包import org.apache.log4j.Logger;

2.日志对象，参数为当前类的class

```java
 static Logger logger=Logger.getLogger(String.valueOf(UserDaoTest.class));
```

### 4.分页

为什么分页？

- 减少数据处理量

#### **4.1使用Mybatis实现limit分页**

1.接口

```java
List<User> getUserByLimit(Map<String,Integer> map);
```

2.Mapper.xml

```xml
<select id="getUserByLimit" parameterType="map" resultType="User">
    select * from Mybatis.user limit #{startIndex},#{pageSize}
</select>
```

3.测试

```java
@Test
public void getUserBylimit(){
    SqlSession sqlSession=MybatisUtils.getSqlsession();
    UserDaoMapper mapper=sqlSession.getMapper(UserDaoMapper.class);

    HashMap<String,Integer> map=new HashMap<String, Integer>();
    map.put("startIndex",0);
    map.put("pageSize",2);

    List<User> UserLimit=mapper.getUserByLimit(map);
    for (User user:UserLimit)
    {
        System.out.println(user);
    }
}
```



### 5.使用注解开发

#### **5.1 使用注解开发**

1.注解在接口上实现

```java
@Select("select * from user")
List<User> getUsers();
```

2.需要在核心配置文件绑定接口

```xml
<!--绑定接口-->
<mappers>
    <mapper class="com.wei.Dao.UserDaoMapper"/>
</mappers>
```

3.测试

本质：反射机制实现

底层：动态代理

![image-20200825102451370](/Users/weibo/Library/Application Support/typora-user-images/image-20200825102451370.png)



#### **5.2 CRUD**

自动提交，将opensession中的autoCommit设置为true，默认时是false，要用sql.commit提交

```java
public static SqlSession getSqlsession(){
    return sqlSessionFactory.openSession(true);
}
```

编写接口，增加注解

```java
@Select("select * from user")
List<User> getUsers();

//方法存在多个参数时，所有参数前必须加上@Param("")注解
@Select("select * from user where Id=#{Id}")
User getUserById(@Param("Id") int Id);
```

 测试

【注意：我们必须要将接口注册绑定到核心配置文件中】



**关于@Param()**

- 基本类型的参数或者String类型，需要加上
- 引用类型不用加
- 如果只有一个基本类型的话，可以忽略，但是建议加上
- 我们在sql中引用的就是@Param()中设定的属性名



### 6.Lombok

1.IDEA安装Lombok插件

2.导入依赖

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <version>1.18.12</version>
</dependency>
```

3.说明

```java
@Data：无参构造，get,set,tostring,hashcode,equals
@AllArgsConstructor
@NoArgsConstructor
```



### 7.多对一处理

![image-20200828110622684](/Users/weibo/Library/Application Support/typora-user-images/image-20200828110622684.png)

- 多个学生对应一个老师
- 对于学生而言，关联——多个学生关联一个老师【多对一】
- 对于老师而言，集合——一个老师有很多学生【一对多】

![image-20200828112251361](/Users/weibo/Library/Application Support/typora-user-images/image-20200828112251361.png)

```sql
CREATE TABLE `teacher` (
`id` INT(10) NOT NULL,
`name` VARCHAR(30) DEFAULT NULL,
PRIMARY KEY (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8

INSERT INTO teacher(`id`, `name`) VALUES (1, '秦老师'); 

CREATE TABLE `student` (
`id` INT(10) NOT NULL,
`name` VARCHAR(30) DEFAULT NULL,
`tid` INT(10) DEFAULT NULL,
PRIMARY KEY (`id`),
KEY `fktid` (`tid`),
CONSTRAINT `fktid` FOREIGN KEY (`tid`) REFERENCES `teacher` (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('1', '小明', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('2', '小红', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('3', '小张', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('4', '小李', '1'); 
INSERT INTO `student` (`id`, `name`, `tid`) VALUES ('5', '小王', '1');
```

