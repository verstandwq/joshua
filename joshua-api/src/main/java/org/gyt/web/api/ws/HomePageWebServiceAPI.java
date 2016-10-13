package org.gyt.web.api.ws;

import org.gyt.web.api.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 文章web接口
 * Created by Administrator on 2016/9/18.
 */
@RestController
@RequestMapping("/api/static")
public class HomePageWebServiceAPI {

    private static final int MAX_GALLERY_COUNT = 7;

    @Value("${joshua.dir.gallery}")
    private String galleryPath;

    @Autowired
    private ArticleService articleService;

    @RequestMapping(value = "/gallery", method = RequestMethod.POST)
    public String upload(@RequestParam MultipartFile file) {

        try {
            if (Files.list(Paths.get(galleryPath)).count() > MAX_GALLERY_COUNT) {
                return "大图数量已达到上线";
            }

            Files.copy(file.getInputStream(), Paths.get(galleryPath, file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }


    @RequestMapping(value = "/gallery/delete", method = RequestMethod.POST)
    public String delete(@RequestParam String filename) {

        try {
            Files.list(Paths.get(galleryPath)).forEach(path -> {
                String finalName = filename.split("/")[filename.split("/").length - 1];
                if (path.getFileName().toString().equals(finalName)) {
                    try {
                        Files.delete(Paths.get(galleryPath, finalName));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
            return "success";
        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }
    }

}
