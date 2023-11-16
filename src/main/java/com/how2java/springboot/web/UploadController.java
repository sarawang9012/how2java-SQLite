package com.how2java.springboot.web;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * ClassName: UploadController
 * Package: com.how2java.springboot.web
 * Description:
 *
 * @Author Sara Wang
 * @Create 2023/11/15 11:23
 * @Version 1.0
 */
@Controller
public class UploadController {
    @RequestMapping("/uploadPage")
    public String uploadPage(){
        return "uploadPage";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String upload(HttpServletRequest req, @RequestParam("file")MultipartFile file,
                         Model m){
       try{
           String fileName = System.currentTimeMillis() + file.getOriginalFilename();
           String destFileName =
                   req.getServletContext().getRealPath("")+"uploaded"+ File.separator+fileName;
           File destFile = new File(destFileName);
           destFile.getParentFile().mkdirs();
           file.transferTo(destFile);
           m.addAttribute("fileName", fileName);
       } catch (FileNotFoundException e){
           e.printStackTrace();
           return "Upload failed,"+e.getMessage();
       } catch (IOException e){
           e.printStackTrace();
           return "Upload failed,"+e.getMessage();
       }
       return "showImg";
    }
}
