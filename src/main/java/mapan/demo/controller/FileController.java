package mapan.demo.controller;

import mapan.demo.Service.FileService;
import mapan.demo.dto.FileDTO;
import mapan.demo.exception.CustomizeErrorCode;
import mapan.demo.exception.CustomizeException;
import mapan.demo.model.User;
import mapan.demo.provider.UCloudProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;

@Controller
public class FileController {
    @Autowired
    private UCloudProvider uCloudProvider;
    @Autowired
    private FileService fileService;

    @RequestMapping("/file/upload")
    public String upload(@RequestParam("classify") String classify, HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upload-file");
        try {
            FileDTO fileDTO = new FileDTO();
            if(request.getSession().getAttribute("user")!=null){
                String fileUrl = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
                fileDTO.setSuccess(1);
                fileDTO.setFileUrl(fileUrl);
                String className;
                switch (classify){
                    case "图片":className= "image";break;
                    case "音乐":className= "music";break;
                    case "视频":className= "video";break;
                    case "文件":className= "file";break;
                    case "其他":className= "other";break;
                    default:className= "other";
                }
                fileDTO.setClassify(className);
                fileDTO.setMessage("上传成功");
                fileDTO.setFilename(file.getOriginalFilename());
                User currentUser = (User)request.getSession().getAttribute("user");
                fileDTO.setOwnerId(currentUser.getId());
                fileDTO.setUser(currentUser);
                fileDTO.setGmtCreate(System.currentTimeMillis());
                if(!fileService.isFileNameExist(fileDTO.getFilename())){
                    fileService.insertFile(fileDTO);
                }else{
                    throw new CustomizeException(CustomizeErrorCode.FILE_ALREADY_EXIST);
                }
            }else {
                throw new CustomizeException(CustomizeErrorCode.NOT_LOGIN);
            };
            return "redirect:/";
        } catch (Exception e) {
            System.out.println(e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败,"+e.toString().split(":")[1]);
            throw new CustomizeException(CustomizeErrorCode.FILE_UPLOAD_ERROR);
        }
    }
}
