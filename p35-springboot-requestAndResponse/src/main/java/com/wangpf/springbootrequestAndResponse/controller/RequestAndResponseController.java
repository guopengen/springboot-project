package com.wangpf.springbootrequestAndResponse.controller;

import com.wangpf.springbootrequestAndResponse.entity.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * @Description 接受参数
 * @Author wangpengfei101022
 * @Date 2019/12/30 15:21
 */
@RestController
@RequestMapping("/requestAndResponse")
public class RequestAndResponseController {
    @RequestMapping(value="/addUser")
    @ResponseBody
    public String addUser(String name,int age){
        return "name是"+name+",age是"+age;
    }

    @RequestMapping(value = "addUserByObject")
    @ResponseBody
    public String addUserByObject(User user){
        return "name是"+user.getName()+",age是"+user.getAge();
    }

    @RequestMapping(value = "addUserByDifName")
    @ResponseBody
    public String addUserByDifName(@RequestParam(name = "name",defaultValue = "zhaoliu") String u_name,@RequestParam(name="age",defaultValue="0") int u_age){
        return "name是"+u_name+",age是"+u_age;
    }

    @RequestMapping(value = "/addUserByHttpServletRequest")
    @ResponseBody
    public String addUserByHttpServletRequest(HttpServletRequest request){
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        return "name是"+name+",age是"+age;
    }

    @RequestMapping(value = "addUserByPathVariable/{name}/{age}")
    @ResponseBody
    public String addUserByPathVariable(@PathVariable String name, @PathVariable int age){
        return "name是" + name + ",age是" + age;
    }











    /**
     *@Description
     * userName的值是 {"userName":"wangpf"} 这样的，但是这样的格式，一般不符合业务要求
     *@Param userName
     *@Return java.lang.String
     *@Author wangpf
     *@Date 2019/12/30
     **/
    @RequestMapping("/addUsersByString")
    @ResponseBody
    public String addUsersByString(@RequestBody String userName){
        System.out.println(userName);
        return userName;
    }

    @RequestMapping("/addUsersByArray")
    @ResponseBody
    public String addUsersByArray(@RequestBody String[] array){
        System.out.println(array);
        return "";
    }


    @PostMapping(value = "/addByObjectJSON")
    @ResponseBody
    public String addUserByObjectJSON(@RequestBody User user){
        return "name是" + user.getName() + ",age是" + user.getAge();
    }

    @PostMapping(value = "/addByArrayJSON")
    @ResponseBody
    public String addByArrayJSON(@RequestBody User[] userArray){
        System.out.println(userArray);
        return "";
    }


    @RequestMapping("/addUserByListJson")
    @ResponseBody
    public String addUserByListJson(@RequestBody List<User> users){
        StringBuilder sb = new StringBuilder("{");
        if(null != users){
            for(User user : users){
                sb.append("{" + "name:" + user.getName() + ",age:" + user.getAge() + "}");
            }
        }
        sb.append("}");
        return sb.toString();
    }

    @RequestMapping(value = "/addUsersByMapJSON")
    @ResponseBody
    public String addUsersByMapJSON(@RequestBody Map<String, User> users){
        StringBuilder sb = new StringBuilder();
        if(null != users){
            Iterator it = users.keySet().iterator();
            while(it.hasNext()){
                User user = users.get(it.next());
                sb.append("{" + "name:" + user.getName() + ",age:" + user.getAge() + "}");
            }
        }
        return sb.toString();
    }




}
