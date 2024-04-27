package com.dynamic.dataConvert.service;

import com.dynamic.dataConvert.config.CentralLogger;
import com.dynamic.dataConvert.model.InputData;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class ConvertionBL {


    List<InputData> JsonData = new ArrayList<>();
    public List<InputData> addJsonData(InputData input)
    {
        JsonData.add(input);

      return JsonData;
    }

    public void updateDatatoExcel(String filepath) throws IOException {

        CentralLogger.getInstance().logInfo("Data is updating into Excel..........");
        Workbook workbook = new XSSFWorkbook();


        Sheet sheet = workbook.createSheet("Data");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Column 1");
        headerRow.createCell(1).setCellValue("Column 2");
        headerRow.createCell(2).setCellValue("Column 3");


        int rowNum = 1;
        for (InputData jsonData : JsonData) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(jsonData.getColumn_1());
            row.createCell(1).setCellValue(jsonData.getColumn_2());
            row.createCell(2).setCellValue(jsonData.getColumn_3());
        }


        try (FileOutputStream fos = new FileOutputStream(filepath)) {
            workbook.write(fos);
        } catch (IOException e) {
            CentralLogger.getInstance().logwarn("Input file is not found, please verify the input filepath");
            throw new RuntimeException(e);
        }


        try {
            workbook.close();
        } catch (IOException e) {

            throw new RuntimeException(e);
        }
    }


    public void convertJsontoExcel(String jsonpath) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        List<InputData> jsonDataList = objectMapper.readValue(new File(jsonpath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, InputData.class));

        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Data");


        int rowNum = 0;
        for (InputData inputData : jsonDataList) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(inputData.getColumn_1());
            row.createCell(1).setCellValue(inputData.getColumn_2());
            row.createCell(2).setCellValue(inputData.getColumn_3());
        }

        try (FileOutputStream fos = new FileOutputStream("excledata.xlsx")) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    }





