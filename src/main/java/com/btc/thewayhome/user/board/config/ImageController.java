package com.btc.thewayhome.user.board.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.util.UUID;

@Log4j2
@Controller
public class ImageController {

    private String uploadDir = "C:\\localImage\\";

    @PostMapping(value = "/image/upload")
    public ModelAndView imageUpload(MultipartHttpServletRequest request) throws Exception {
       log.info("imageUpload()");

        ModelAndView mav = new ModelAndView("jsonView");

        MultipartFile uploadFile = request.getFile("upload");

        String originalFileName = uploadFile.getOriginalFilename();
        String ext = originalFileName.substring(originalFileName.indexOf("."));
        String newFileName = UUID.randomUUID() + ext;

        File saveFile = new File(uploadDir + "\\" + newFileName);
        uploadFile.transferTo(saveFile);

        System.out.println("save "+ saveFile);

        mav.addObject("uploaded", true);
        mav.addObject("url", "/UploadImg/"+ newFileName);

        return mav;
    }


}
