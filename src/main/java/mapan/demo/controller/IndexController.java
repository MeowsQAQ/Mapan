package mapan.demo.controller;

import mapan.demo.Service.FileService;
import mapan.demo.dto.PaginationDTO;
import mapan.demo.mapper.UserMapper;
import mapan.demo.model.ClassifyCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private FileService fileService;

    @GetMapping("/")
    public String index(HttpServletRequest request,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size,
                        @RequestParam(name = "search",required = false)String search,
                        Model model){
        PaginationDTO pagination = fileService.list(search,page,size,"");
        model.addAttribute("section","AllFile");
        model.addAttribute("pagination",pagination);
        model.addAttribute("search",search);
        return "index";
    }
}
