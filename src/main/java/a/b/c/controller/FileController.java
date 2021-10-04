package a.b.c.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class FileController {
	
	@GetMapping("/multipartFile")
	public String goPage() {

		return "fileUpload";
	}
	
	
    @PostMapping("/multipartFile")
    public String MultopartHttpServletRequestUpload(MultipartHttpServletRequest mRequest,
                                                    HttpServletRequest req,
                                                    RedirectAttributes attributes) {
    	
        // 업로드 경로 설정
        String path = req.getServletContext().getRealPath("/upload/");
        System.err.println(path);
        
        // 파일 분리하기
        List<MultipartFile> fileList = mRequest.getFiles("myFile");
        String content = mRequest.getParameter("content");

        // 리다이렉트 메세지
        List<String> message = new ArrayList<>();

        // DB저장을 위한 파일 List
        List<HashMap<String, String>> upLoadFileList = new ArrayList<>();
        
        for(MultipartFile mf : fileList){

            // 파일명 설정
            String originalFilename = mf.getOriginalFilename();
            String fileExtension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uploadFileName = UUID.randomUUID().toString().replaceAll("-", "") + fileExtension;

            // 파일명 저장
            HashMap<String, String> map = new HashMap<>();
            map.put("oFIle", originalFilename);
            map.put("uFile", uploadFileName);

            // 파일 생성
            File file = new File(path + uploadFileName);

            try {
                mf.transferTo(file);
                message.add(originalFilename + " is uploaded");
                upLoadFileList.add(map);
            }
            catch (Exception e){
                message.add("File Upload Fail!! " + e.getMessage());
            }
        }

        // DB저장
        attributes.addFlashAttribute("message", message);
        return "redirect:/multipartFile";

    }
    
    @Autowired
    ServletContext servletContext;

    @GetMapping("/filedownloader")
    public ResponseEntity fileDownloader1(String filename, HttpServletRequest req) throws FileNotFoundException {

    	filename = filename.replace("'", "");

        // 경로 설정 (상대경로)
        String uploadPath = req.getServletContext().getRealPath("/upload/");
        
        // 파일 확장자
        String minType = servletContext.getMimeType(filename);
        MediaType mediaType = MediaType.parseMediaType(minType);

        File file = new File(uploadPath + File.separator + filename);
        InputStreamResource is = new InputStreamResource(new FileInputStream(file));

        // 다운로드창 셋팅
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + "originalName.pdf")
                .contentType(mediaType)
                .contentLength(file.length())
                .body(is);

    }
    
}
