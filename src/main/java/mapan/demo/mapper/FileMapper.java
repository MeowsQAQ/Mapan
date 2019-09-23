package mapan.demo.mapper;

import mapan.demo.dto.FileDTO;
import mapan.demo.dto.FileQueryDTO;
import mapan.demo.model.File;
import org.apache.ibatis.annotations.*;
import java.util.List;


@Mapper
public interface FileMapper {
    @Insert("INSERT INTO file(filename,file_url,owner_id,gmt_create) values (#{filename},#{fileUrl},#{ownerId},#{gmtCreate})")
    void insert(FileDTO fileDTO);

    @Select("select * from file where filename =#{filename}")
    List<FileDTO> selectByFileName(@Param("filename")String filename);

    @Select("select count(1) from file")
    Integer select();
//    if(isnull(#{search}),select count(1) from file,select count(1) from file where filename regex #{search})
    @Select("select count(1) from file ")
    Integer countBySearch(FileQueryDTO fileQueryDTO);
    @Select("select * from file where filename  order by gmt_create desc limit #{page},#{size}")
    List<File> selectBySearch(FileQueryDTO fileQueryDTO);
}
