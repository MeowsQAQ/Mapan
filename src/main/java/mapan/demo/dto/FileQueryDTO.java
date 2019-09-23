package mapan.demo.dto;

import lombok.Data;

@Data
public class FileQueryDTO {
    private String search;
    private Integer page;
    private Integer size;
}
