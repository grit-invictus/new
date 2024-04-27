package com.example.backup.Contr;

import com.example.backup.blogic.BackupBusinessLogic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BackupController {

    @Value("${backup.source}")
      private String bkp_source;
     @Value("${backup.destination}")
      private String bkp_destination;
    @Autowired
    private BackupBusinessLogic bbl;
    @GetMapping("/getdata")
    public String getdata()

    {
        return "site reachable...";
    }

   @GetMapping("/runbackup")
   public String run_backup()
    {   System.out.println(bkp_destination);
        System.out.println(bkp_source);
        bbl.runBackup(bkp_source,bkp_destination);
        return "Backup running..check the logs for more info...";
    }



}
