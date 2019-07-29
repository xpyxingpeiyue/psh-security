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