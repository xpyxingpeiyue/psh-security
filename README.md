#### Spring-Security权限管理
## 
#### 模块介绍
1. psh-security-core  
核心代码库，依赖所有第三方库
2. psh-security-app  
处理app安全
3. psh-security-browser  
处理浏览器安全 
4. psh-security-demo  
程序入口，调用browser和 app  

#### RESTful API 
GET /user?name=tom   查询  
GET /user/1    详情    
POST /user     增加   
PUT /user/1     修改  
DELETE /user/1  删除 

1. 使用URL描述资源  
2. 使用HTTP方法描述行为，使用HTTP状态码来表示不同的结果  
3. 使用json交互数据   
4. RESTful只是标准  

#### 使用MockMvc进行Junit单元测试   
简单来说就是在我们增加或者改动一些代码以后对所有逻辑的一个检测，尤其是在我们后期修改后（不论是增加新功能，修改bug），都可以做到重新测试的工作。以减少我们在发布的时候出现更过甚至是出现之前解决了的问题再次重现  
这里主要是使用MockMvc对我们的系统的Controller进行单元测试。  
对数据库的操作使用事务实现回滚，及对数据库的增删改方法结束后将会还远数据库。  

方法解析：  
-  perform：执行一个RequestBuilder请求，会自动执行SpringMVC的流程并映射到相应的控制器执行处理；  
- get:声明发送一个get请求的方法。MockHttpServletRequestBuilder get(String urlTemplate, Object... urlVariables)：根据uri模板和uri变量值得到一个GET请求方式的。另外提供了其他的请求的方法，如：post、put、delete等。  
- param：添加request的参数，如上面发送请求的时候带上了了pcode = root的参数。假如使用需要发送json数据格式的时将不能使用这种方式，可见后面被@ResponseBody注解参数的解决方法  
- andExpect：添加ResultMatcher验证规则，验证控制器执行完成后结果是否正确（对返回的数据进行的判断）；  
- andDo：添加ResultHandler结果处理器，比如调试时打印结果到控制台（对返回的数据进行的判断）；  
- andReturn：最后返回相应的MvcResult；然后进行自定义验证/进行下一步的异步处理（对返回的数据进行的判断）；    
#### Valid  BindResult  日期处理  自定义校验规则  
#### SpringBoot 默认处理异常机制  
- SpringBoot如果是浏览器请求返回的是html错误，如果是客户端请求会返回json错误数据  
同一个请求产生两种不同的数据格式  
BasicErrorController 类中做处理   
ResourceProperties    
@RequestMapping(produces = {"text/html"})  
@RequestMapping   
通过 produces 来判断浏览器还是客户端错误请求

- 自定义异常信息  
SpringBoot 默认异常只会getMessage异常信息(不管是否继承RuntimeException)，不会读取消息以外的其他信息,无法输出自定义字段，只能添加错误器的控制处理器@ControllerAdvice注解标识  
@ControllerAdvice 只负责其他controller抛出来的异常  
浏览器和客户端页面都会跳转到此异常，不会再访问默认异常  

#### RESTful API拦截   
 执行顺序  
 filter->interceptor->aspect->controller  
- 过滤器（Filter）   
Servlet提供，与Spring无关，所以无法使用spring的任何东西（无法获取那个controller的那个方法请求）    
- 拦截器（Interceptor）  
 Spring提供 实现HandlerInterceptor  配置继承WebMvcConfigurerAdapter   重写addInterceptors方法
- 切片（Aspect）   
 execution：方法签名需要满足execution中描述的方法签名  
 within：包或者类型满足within中描述的包或者类型的类的所有非私有方法  
 this：Spring AOP 的代理实例的类型满足this中的描述的类型  
 target：业务实例对象（非代理实例）的类型满足target 中的描述的类型  
 args： 方法的参数满参数类型为args中描述的类型  
 @target： 类型拥有@target描述中给出的annotation  
 @args: 方法运行时传入的参数的实际类型拥有@args描述中给出的annotation  
 @within: 类型拥有@target描述中给出的annotation,其中@target和@within的区别在于@within要求的annotation的级别为CLASS，而@target为RUNTIME  
 @annotation:方法拥有@annotation 描述中给出的annotation    
 bean(idOrNameOfBean):bean的名字或者为bean描述中的名字或者Id  

Pointcut可以有下列方式来定义或者通过&& || 和!的方式进行组合.
 
匹配任意的公共方法   
execution(public * *(..))   


匹配任意方法名已set开头的方法   
execution(* set*(..))   


匹配任意AccountService接口中的方法   
execution(* com.xyz.service.AccountService.*(..))  


匹配任意AccountService接口中的方法   
execution(* com.xyz.service.AccountService.*(..))  


匹配任意service包中任意类型中的方法   
execution(* com.xyz.service.*.*(..))   


匹配任意service包或者service的子包中任意类型中的方法   
execution(* com.xyz.service..*.*(..))   


匹配任意service包中任意类型中的方法   
within(com.xyz.service.*)  


匹配任意service包或者service的子包中任意类型中的方法   
within(com.xyz.service..*)  


匹配任意实现了AccountService接口的Spring AOP代理对象的任意方法   
this(com.xyz.service.AccountService)   


匹配任意实现了AccountService接口的实际业务对象的任意方法   
this(com.xyz.service.AccountService)   


匹配任意一个接收一个参数，并且运行时参赛类型为Serializable的方法   
args(java.io.Serializable)   


匹配拥有@Transactional annotation类型的任意方法   
@target(org.springframework.transaction.annotation.Transactional)  



匹配拥有@Transactional annotation类型的任意方法   
@within(org.springframework.transaction.annotation.Transactional)  


匹配拥有@Transactional annotation的方法   
@annotation(org.springframework.transaction.annotation.Transactional)  


匹配任意一个接收一个参数，并且运行时参赛类型拥有@Classified annotation的方法   
@args(com.xyz.security.Classified)  


匹配bean的Id或者名字为tradeService的任意方法   
bean(tradeService)  


匹配bean的Id或者名字以Service结尾的任意方法   
bean(*Service)   

execution(* com.travelsky.ccboy.dao..*.find*(..)) || execution(* com.travelsky.ccboy.dao..*.query*(..))
 
 #### 异步处理REST服务  
 - 使用Runnable异步处理rest服务  
 - 使用DeferredResult异步处理Rest服务（*）  
 - 异步处理配置    
 
 #### 使用wsagger生成API文档  
 /swagger-ui.html  
 
 #### 使用WireMock快速伪造RESTful服务  
 在未开始前为前台提供伪数据  
 官网：http://wiremock.org/  
 http://wiremock.org/docs/running-standalone/  
 docs->Running as a Standalone Process->java -jar wiremock-standalone-2.24.0.jar --port 8088  
 
 
 