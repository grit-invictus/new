package com.example.backup.blogic;

import com.example.backup.Logger.BackupLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;
import java.time.LocalTime;
import java.util.Iterator;

@Service
public class BackupBusinessLogic {

//       @Value("${backup.source}")
//       private String bkp_source;
//       @Value("${backup.destination}")
//       private String bkp_destination;


       public void runBackup(String source,String destination)
       {
              BackupLogger.getInstance().loginfo("Backup run started at time"+ LocalTime.now());
              try{
                  move_files(source,destination);
                     BackupLogger.getInstance().loginfo("File got successfully backup in the destination"+destination+ "at the time"+ LocalTime.now());
              }
              catch(Exception e)
              {
                     BackupLogger.getInstance().loginfo("BackupFailed...............");
                     e.printStackTrace();
              }


       }
       private void move_files(String source, String destination)
       {
              Path  bkp_source = Paths.get(source);
              Path  bkp_destination = Paths.get(destination);
              if(!Files.exists(bkp_source) || !Files.isDirectory(bkp_source))
              {      BackupLogger.getInstance().logerror("FileMove can not performed");
                     throw new IllegalArgumentException("Source path provided is in correct or source path may not be a directory");

              }
              else if(!Files.exists(bkp_destination) || !Files.isDirectory(bkp_destination))
              {      BackupLogger.getInstance().logerror("FileMove can not performed");
                     throw new IllegalArgumentException("Destination path provided is in correct or destination path may not be a directory");

              }
              else{
                     try{

                            DirectoryStream<Path> directoryStream = Files.newDirectoryStream(bkp_source) ;

                              for(Path path : directoryStream) {

                                 if(Files.isRegularFile(path)) {

                                        Files.move(path,bkp_destination.resolve(path.getFileName()), StandardCopyOption.REPLACE_EXISTING);
                                        BackupLogger.getInstance().logerror("Files got Moved successfully");
                                 }

                              }

                            }
                     catch (IOException e) {
                         throw new RuntimeException(e);
                     }
              }

              }






       }







