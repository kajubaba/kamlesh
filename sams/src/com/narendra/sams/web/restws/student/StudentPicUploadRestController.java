package com.narendra.sams.web.restws.student;

import com.narendra.sams.admission.service.StudentService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import javax.servlet.ServletContext;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping({"/ws/studentpic"})
public class StudentPicUploadRestController {
    @Autowired
    private ServletContext servletContext;
    @Autowired
    private StudentService studentService;

    @ResponseBody
    @RequestMapping(method = {RequestMethod.POST}, value = {"/upload"})
    public String uploadStudentPic(@RequestParam("file") MultipartFile uploadedFile, Long id, String studentID) {
        IOException e;
        JSONObject jsonObject;
        String studentImageName = new StringBuilder(String.valueOf(studentID)).append(".jpeg").toString();
        try {
            InputStream inputStream = uploadedFile.getInputStream();
            File newFile = new File(new StringBuilder(String.valueOf(this.servletContext.getRealPath("/resources/studentpics/"))).append("/").append(studentImageName).toString());
            if (!newFile.exists()) {
                newFile.createNewFile();
            }
            OutputStream outputStream = new FileOutputStream(newFile);
            OutputStream outputStream2;
            try {
                byte[] bytes = new byte[1024];
                while (true) {
                    int read = inputStream.read(bytes);
                    if (read == -1) {
                        break;
                    }
                    outputStream.write(bytes, 0, read);
                }
                outputStream.close();
                inputStream.close();
                this.studentService.updateImageName(id, studentImageName);
                outputStream2 = outputStream;
            } catch (IOException e2) {
                e = e2;
                outputStream2 = outputStream;
            }
        } catch (IOException e3) {
            e = e3;
            e.printStackTrace();
            jsonObject = new JSONObject();
            jsonObject.put("imageName", "resources/studentpics/" + studentImageName);
            System.out.println(jsonObject.toString());
            return jsonObject.toString();
        }
        jsonObject = new JSONObject();
        jsonObject.put("imageName", "resources/studentpics/" + studentImageName);
        System.out.println(jsonObject.toString());
        return jsonObject.toString();
    }
}
