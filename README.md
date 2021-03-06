# SimplifySetup(简化设置)
这是一个针对某一业务场景下，用于提高代码开发效率以及减少重复代码编写的业务工具包

**业务场景**
```$xslt  
    //用户实体类(保存于t_user表)
    public class user {
    
        private String userId;
            
        private String userName;
        
        private Integer age;
        
        private List<Order> orders;
        
        ....
    }
    
    //订单实体类(保存于t_order表中,并保存了用户userId作为外键)
    public class Order {
        
        private String productName;
        
        private Date buyDate;
        
        private Integer buyNum;
        
        ...
    }
    
    //正常的处理方式
    public List<user> getUesrInfo() {
        1.查询出所有的user信息
        List<User> users = queryUsers();
        
        2. 遍历user信息，获取userId,获取order
        for(User user : users) {
            List<Order> orders = queryOrders(user.getUserId());
            user.setOrders(orders);
        }
        
        return orders;
    }
如上所示: 该框架所要解决的就是这一类业务场景问题,将代码的耦合度进一步降低，同时减少这类代码的重复编写
```

配置:
1. 拉取下工程执行  mvn clean install -U 打成jar包
2. 在项目工程的pom.xml中添加如下依赖
```$xslt
<dependency>
   <groupId>com.simplify</groupId>
   <artifactId>setup</artifactId>
   <version>0.0.1-SNAPSHOT</version>
</dependency>

```
3. 在项目工程的启动入口添加如下配置(用于扫描该jar包下的注解)
```$xslt
@ComponentScan(basePackages = {"com.simplify.setup"})
```

用法:
```$xslt
1.创建实体类
public class User {
    
    private Long id;
    
    private String name;
    
    private Integer age;
    
    /**
    * 商品名称
    */
    @Link(targetBean = OrderMapper.class,methodName = "queryOrder",methodParam = {"id"},targetField = "productName")
    private String productName;
    
    /**
    * 用户订单详情
    */
    @Link(targetBean = OrderMapper.class,methodName = "queryOrderAll",methodParam = {"id"})
    private List<Order> orders;
}

public class Order {
    
    private String productName;
    
    private Date buyDate;
    
}

2. 创建Dao层Mapper接口

@Repository
public interfact UserMapper {
    
    List<User> queryUser();
}

@Repository
public interfact OrderMapper {
    
    /**
    * 查询全部订单记录
    */
    List<Order> queryOrderAll(@Param("userId)Long userId);
    
    /**
    * 查询单条订单记录
    */
    Order queryOrder(@Param("userId")Long userId);
}

3. 创建service层
public class UserService {
    
    @Autowired
    private UserMapper userMapper;
    
    @LinkSign
    public List<User> queryUser() {
        return userMapper.queryUser();
    }
}
```
