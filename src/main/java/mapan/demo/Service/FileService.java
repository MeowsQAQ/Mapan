package mapan.demo.Service;

import mapan.demo.dto.FileDTO;
import mapan.demo.dto.FileQueryDTO;
import mapan.demo.dto.PaginationDTO;
import mapan.demo.mapper.FileMapper;
import mapan.demo.mapper.UserMapper;
import mapan.demo.model.ClassifyCode;
import mapan.demo.model.File;
import mapan.demo.model.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;
    @Autowired
    private UserMapper userMapper;

    public boolean isFileNameExist(String filename){
        List<FileDTO> files = fileMapper.selectByFileName(filename);
        if(files.size()!=0){
            return true;
        }else {
            return false;
        }
    }
    public void insertFile(FileDTO fileDTO){
        if (!isFileNameExist(fileDTO.getFilename())){
            fileMapper.insert(fileDTO);
        }
    }

    public PaginationDTO list(String search ,Integer page, Integer size,String classify) {
        if(StringUtils.isNotBlank(search)){
            String[] tags = StringUtils.split(search," ");
            search = Arrays.stream(tags).collect(Collectors.joining("|"));
        }


        FileQueryDTO fileQueryDTO = new FileQueryDTO();
        fileQueryDTO.setSearch(search);
        Integer totalCount = fileMapper.countInNoSearch(fileQueryDTO);
        PaginationDTO paginationDTO = new PaginationDTO();
        paginationDTO.setPagination(totalCount,page,size);

        if(page<=1)page=1;
        if(page>=paginationDTO.getTotalPage())page=paginationDTO.getTotalPage();
        Integer offset = size*(page-1);
        fileQueryDTO.setSize(size);
        fileQueryDTO.setPage(offset);
        fileQueryDTO.setClassify(classify);
        List<File> files;
        if (StringUtils.isNotBlank(classify)){
            files = fileMapper.selectInClassifyAndNoSearch(fileQueryDTO);
        }else{
            files = fileMapper.selectInNoSearch(fileQueryDTO);
        }
        List<FileDTO> fileDTOs = new ArrayList<>();

        for (File file : files) {
            User user =userMapper.selectByPrimaryKey(file.getOwnerId());
            FileDTO fileDTO = new FileDTO();
            BeanUtils.copyProperties(file,fileDTO);
            fileDTO.setClassifyUrl(ClassifyCode.valueOf(file.getClassName()).getClassUrl());
            fileDTO.setUser(user);
            fileDTOs.add(fileDTO);
        }
        paginationDTO.setData(fileDTOs);

        return paginationDTO;
    }

}
