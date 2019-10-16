package mapan.demo.model;


import lombok.Data;

@Data
public class File {
    private Long id;
    private int success;
    private String message;
    private String fileUrl;
    private String filename;
    private Long ownerId;
    private Long gmtCreate;
    private String className;
}

