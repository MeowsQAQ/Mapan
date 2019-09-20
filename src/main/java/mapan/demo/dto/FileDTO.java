package mapan.demo.dto;


import lombok.Data;

@Data
public class FileDTO {
    private int success;
    private String message;
    private String fileUrl;
    private String fileName;
    private Long ownerId;
}
