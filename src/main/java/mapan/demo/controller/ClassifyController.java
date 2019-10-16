package mapan.demo.controller;

import mapan.demo.Service.FileService;
import mapan.demo.dto.PaginationDTO;
import mapan.demo.model.ClassifyCode;
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
                          @RequestParam(name = "page",defaultValue = "1") Integer page,
                          @RequestParam(name = "size",defaultValue = "5") Integer size,
                          @RequestParam(name = "search",required = false)String search,
                          Model model){
        if("image".equals(className)){
            String classify = "image";
            model.addAttribute("classify", ClassifyCode.image);
            model.addAttribute("section","Image");
            PaginationDTO paginationDTO = fileService.list(search,page,size,classify);
            model.addAttribute("pagination",paginationDTO);
        }else if("music".equals(className)){
            String classify = "music";
            PaginationDTO paginationDTO = fileService.list(search,page,size,classify);
            model.addAttribute("section","Music");
            model.addAttribute("pagination",paginationDTO);

        }else if("video".equals(className)){
            String classify = "video";
            model.addAttribute("classify", ClassifyCode.video);
            PaginationDTO paginationDTO = fileService.list(search,page,size,classify);
            model.addAttribute("section","Video");
            model.addAttribute("pagination",paginationDTO);

        }else if("mfile".equals(className)){
            String classify = "file";
            model.addAttribute("classify", ClassifyCode.file);
            PaginationDTO paginationDTO = fileService.list(search,page,size,classify);
            model.addAttribute("section","File");
            model.addAttribute("pagination",paginationDTO);

        }else if("other".equals(className)){
            String classify = "other";
            model.addAttribute("classify", ClassifyCode.other);
            PaginationDTO paginationDTO = fileService.list(search,page,size,classify);
            model.addAttribute("section","Other");
            model.addAttribute("pagination",paginationDTO);

        }
        return "index";
    }
}
