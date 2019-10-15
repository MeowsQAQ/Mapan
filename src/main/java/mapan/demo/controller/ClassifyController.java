package mapan.demo.controller;

import mapan.demo.Service.FileService;
import mapan.demo.dto.PaginationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


@Controller
public class ClassifyController {
    @Autowired
    private FileService fileService;
    @GetMapping("/classify/{className}")
    public String profile(HttpServletRequest request,
                          @PathVariable(name = "className") String className,
                          @PathVariable(name = "action") String action,
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,
                          @RequestParam(name = "search",required = false)String search,
                          Model model){
        if("image".equals(className)){
            model.addAttribute("classify","image");
            model.addAttribute("className","图片");
            PaginationDTO paginationDTO = fileService.list(search,page,size,className);
            model.addAttribute("pagination",paginationDTO);
        }else if("music".equals(className)){
            model.addAttribute("classify","image");
            model.addAttribute("className","图片");
            PaginationDTO paginationDTO = fileService.list(search,page,size,className);
            model.addAttribute("pagination",paginationDTO);

        }else if("video".equals(className)){
            model.addAttribute("classify","image");
            model.addAttribute("className","图片");
            PaginationDTO paginationDTO = fileService.list(search,page,size,className);
            model.addAttribute("pagination",paginationDTO);

        }else if("file".equals(className)){
            model.addAttribute("classify","image");
            model.addAttribute("className","图片");
            PaginationDTO paginationDTO = fileService.list(search,page,size,className);
            model.addAttribute("pagination",paginationDTO);

        }else if("other".equals(className)){
            model.addAttribute("classify","image");
            model.addAttribute("className","图片");
            PaginationDTO paginationDTO = fileService.list(search,page,size,className);
            model.addAttribute("pagination",paginationDTO);

        }
    }
}
