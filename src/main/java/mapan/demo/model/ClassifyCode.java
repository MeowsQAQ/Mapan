package mapan.demo.model;

import lombok.Data;
import lombok.Value;

public enum ClassifyCode {
    image("图片","image","https://www.easyicon.net/api/resizeApi.php?id=1218468&size=128"),
    music("音乐","music","https://www.easyicon.net/api/resizeApi.php?id=1233057&size=128"),
    video("视频","video","https://www.easyicon.net/api/resizeApi.php?id=1233139&size=128"),
    file("文件","file","https://www.easyicon.net/api/resizeApi.php?id=1233011&size=128"),
    other("其他","other","https://www.easyicon.net/api/resizeApi.php?id=1113608&size=128");

    private String classify;
    private String className;
    private String classUrl;


    ClassifyCode(String className,String classify, String classUrl) {
        this.className = className;
        this.classUrl = classUrl;
    }
    public String getClassName() {
        return className;
    }
    public String getClassUrl(){
        return classUrl;
    }
    public String getClassify(){
        return  classify;
    }
}
