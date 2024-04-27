package com.dynamic.dataConvert.contrl;

import com.dynamic.dataConvert.config.CentralLogger;
import com.dynamic.dataConvert.model.InputData;
import com.dynamic.dataConvert.service.ConvertionBL;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

@RestController
public class DataController {
    @Autowired

   private ConvertionBL bl;
    @Value("${spring.filepath}")
    public String filepath;
    @Value("${spring.jsonpath}")
    public String jsonpath;
    private List<Integer> dataList = new ArrayList<>();
    private ObjectMapper objectMapper = new ObjectMapper();
    private Workbook workbook = new XSSFWorkbook();
    private Sheet sheet = workbook.createSheet("Data");
    @GetMapping("/getdata")
    public String returnData()
    {
        return " controller returning data";

    }

    @PostMapping(value="/postdata")
    public String postData(@RequestBody InputData inputdata)
    {
        bl.addJsonData(inputdata);
        return "data is posted...";
    }

   @GetMapping("/updateDatatoExcel")
   public void updateData() throws IOException {

       try{

           bl.updateDatatoExcel(filepath);  }

       catch(Exception e)
       {
           CentralLogger.getInstance().logerror("Failed to convert the json data into excel...");
           e.printStackTrace();
       }
    }
   @GetMapping("convertdata")
   public void convertJsontoExcel(String jsonpath) throws IOException {
        try {
            bl.convertJsontoExcel(jsonpath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
