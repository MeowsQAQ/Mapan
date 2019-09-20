package mapan.demo.mapper;

import mapan.demo.dto.FileDTO;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface FileMapper {
    @Insert("INSERT INTO file(filename,file_url,owner_id) values (#{filename},#{fileUrl},#{ownerId})")
    void insert(FileDTO fileDTO);

    @Select("select * from file where filename =#{filename}")
    List<FileDTO> selectByFileName(@Param("filename")String filename);
}
