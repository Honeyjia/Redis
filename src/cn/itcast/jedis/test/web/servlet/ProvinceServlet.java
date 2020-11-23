package cn.itcast.jedis.test.web.servlet;

import cn.itcast.jedis.test.domain.Province;
import cn.itcast.jedis.test.service.Impl.provinceServiceImpl;
import cn.itcast.jedis.test.service.provinceService;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/provinceServlet")
public class ProvinceServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/*//        1.调用service查询
        provinceService service = new provinceServiceImpl();
        List<Province> list = service.findAll();   //获取返回结果
        System.out.println(list);
//        2.序列化list为json
//        创建json核心对象
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(list);*/

        provinceService service = new provinceServiceImpl();
        String json = service.findAllJson();
        System.out.println(json);

//        3.响应结果
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
