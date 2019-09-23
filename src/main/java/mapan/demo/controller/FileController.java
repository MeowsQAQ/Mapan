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
    @ResponseBody
    public FileDTO upload(HttpServletRequest request) {
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = multipartRequest.getFile("upload-file");
        try {
            FileDTO fileDTO = new FileDTO();
            if(request.getSession().getAttribute("user")!=null){
                String fileUrl = uCloudProvider.upload(file.getInputStream(), file.getContentType(), file.getOriginalFilename());
                fileDTO.setSuccess(1);
                fileDTO.setFileUrl(fileUrl);
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
            return fileDTO;
        } catch (Exception e) {
            System.out.println(e);
            FileDTO fileDTO = new FileDTO();
            fileDTO.setSuccess(0);
            fileDTO.setMessage("上传失败");
            return fileDTO;
        }
    }
}
