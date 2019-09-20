package mapan.demo.model;


import lombok.Data;

@Data
public class File {
    private int success;
    private String message;
    private String fileUrl;
    private String fileName;
    private Long ownerId;
}

