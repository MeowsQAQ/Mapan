package mapan.demo.Service;

import mapan.demo.dto.FileDTO;
import mapan.demo.exception.CustomizeErrorCode;
import mapan.demo.exception.CustomizeException;
import mapan.demo.mapper.FileMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileService {
    @Autowired
    private FileMapper fileMapper;

    public boolean isFileNameExist(String filename){
        List<FileDTO> files = fileMapper.selectByFileName(filename);
        if(files.size()!=0){
            return true;
        }else {
            return false;
        }
    }
    public void insertFile(FileDTO fileDTO){
        if (!isFileNameExist(fileDTO.getFileName())){
            fileMapper.insert(fileDTO);
        }
    }


}
