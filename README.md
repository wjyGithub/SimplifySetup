# SimplifySetup
这是一个针对某一业务场景下，用于提高代码开发效率以及减少重复代码编写的业务工具包

业务场景
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
