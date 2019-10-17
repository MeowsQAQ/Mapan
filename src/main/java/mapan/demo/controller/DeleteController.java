package mapan.demo.controller;

import mapan.demo.Service.FileService;
import mapan.demo.dto.FileDTO;
import mapan.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DeleteController {
    @Autowired
    private FileService fileService;

    @RequestMapping(value = "/delete/{fileId}",method = RequestMethod.GET)
    public String deleteFile(HttpServletRequest request,
                             @PathVariable(name = "fileId") String fileId,
                             Model model){
        User loginUser = (User)request.getSession().getAttribute("user");
        fileService.deleteFileByIdWithUserIdentification(fileId,loginUser);
        return "redirect:/";
    }
}
