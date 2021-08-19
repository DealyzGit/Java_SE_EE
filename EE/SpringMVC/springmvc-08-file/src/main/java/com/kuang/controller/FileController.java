package com.kuang.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;

@RestController
public class FileController {
    @RequestMapping("/upload")
    public String upload(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String uploadFilename = file.getOriginalFilename();
        if ("".equals(uploadFilename)) {
            return "redirect:/index.jsp";
        }
        System.out.println(uploadFilename);

        String path = request.getSession().getServletContext().getRealPath("/uplaod");

        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        System.out.println(realPath);

        InputStream is = file.getInputStream();
        FileOutputStream os = new FileOutputStream(new File(realPath, uploadFilename));

        int len = 0;
        byte[] buffer = new byte[1024];

        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
            os.flush();
        }
        os.close();
        is.close();
        return "redirect:/index.jsp";

    }

    @RequestMapping("upload2")
    public String upload2(@RequestParam("file") CommonsMultipartFile file, HttpServletRequest request) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("/upload");
        File realPath = new File(path);
        if (!realPath.exists()) {
            realPath.mkdir();
        }
        System.out.println(realPath);

        file.transferTo(new File(realPath + "/" + file.getOriginalFilename()));
        return "redirect:/index.jsp";
    }

    @RequestMapping("/download")
    public String downloads(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String path = request.getSession().getServletContext().getRealPath("/upload");
        String fileName = "2.png";

        response.reset();
        response.setCharacterEncoding("UTF-8");
        response.setContentType("multipart/form-data");

        response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName, "UTF-8"));

        File file = new File(path, fileName);
        FileInputStream input = new FileInputStream(file);

        OutputStream out = response.getOutputStream();

        byte[] buffer = new byte[1024];
        int index = 0;
        while ((index=input.read(buffer))!=-1){
            out.write(buffer,0,index);
            out.flush();
        }
        out.close();
        input.close();
        return "ok";
    }

}
