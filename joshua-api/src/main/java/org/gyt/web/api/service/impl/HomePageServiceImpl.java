package org.gyt.web.api.service.impl;

import org.gyt.web.api.service.HomePageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class HomePageServiceImpl implements HomePageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(HomePageServiceImpl.class);

    @Value("${joshua.dir.gallery}")
    private String galleryPath;

    @Override
    public List<String> getPictures() {
        List<String> galleries = new ArrayList<>();

        try {
            Files.createDirectories(Paths.get(galleryPath));
            Files.list(Paths.get(galleryPath)).forEach(path -> galleries.add(String.format("/assets/images/gallery/%s", path.getFileName().toString())));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return galleries;
    }
}
