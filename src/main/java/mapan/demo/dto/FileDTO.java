package mapan.demo.dto;


import lombok.Data;
import mapan.demo.model.User;

@Data
public class FileDTO {
    private Long id;
    private int success;
    private String message;
    private String fileUrl;
    private String filename;
    private Long ownerId;
    private User user;
    private Long gmtCreate;
    private String classify;
}
