package mapan.demo.advise;

import com.alibaba.fastjson.JSON;

import mapan.demo.dto.ResultDTO;
import mapan.demo.exception.CustomizeErrorCode;
import mapan.demo.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
public class CustomizeExceptionHandler {
    @ExceptionHandler(Exception.class)
    ModelAndView handle(Throwable ex, Model model,
                        HttpServletRequest request,
                        HttpServletResponse response) {
        String contentTpye = request.getContentType();
        if("application/json".equals(contentTpye)){
            ResultDTO resultDTO;
            //返回JSON
            if (ex instanceof CustomizeException){
                resultDTO= ResultDTO.errorOf((CustomizeException)ex);
            }else{
                resultDTO=ResultDTO.errorOf(CustomizeErrorCode.STS_ERROR);
            }
            try {
                response.setContentType("application/json");
                response.setStatus(200);
                response.setCharacterEncoding("utf-8");
                PrintWriter writer = response.getWriter();
                writer.write(JSON.toJSONString(resultDTO));
                writer.close();
            } catch (IOException ioe) {
            }
            return null;
        }else{
            if (ex instanceof CustomizeException){
                model.addAttribute("message",ex.getMessage());
            }else{
                model.addAttribute("message",CustomizeErrorCode.STS_ERROR.getMessage());
            }
            return new ModelAndView("error");
        }
    }
}
