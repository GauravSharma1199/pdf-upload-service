package com.ione.pdfuploadservice.scheduler;



import com.ione.commondata.dto.LogDTO;
import com.ione.commondata.utility.ZohoApiCall;
import com.ione.pdfuploadservice.service.IteamService;
import org.apache.oltu.oauth2.common.exception.OAuthProblemException;
import org.apache.oltu.oauth2.common.exception.OAuthSystemException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Clob;
import java.sql.SQLException;
import java.util.Date;


@Component
public class PdfUploadScheduler {
    @Autowired
    IteamService itemSer;
    @Autowired
    ZohoApiCall apicall;

    @Scheduled(cron = "${scheduler.PdfUploadScheduler}")
    public void getPdfUploadAPI() throws OAuthProblemException, SQLException, IOException, OAuthSystemException {

        System.out.println(new Date() + "--start getPdfUploadAPI()--");
        LogDTO log = new LogDTO();
        try {
            //generate-log-id======
            String parentId = LogDTO.generateUUID();
            log.setId(parentId);
            log.setProgramName(LogDTO.PDF_UPLOAD_API_NAME);
            //====================

            String tokengn = apicall.getAuthToken();

            Clob jason = itemSer.pdfUploadAPI();
            String response = getClobString(jason);



            System.out.println("getPdfUploadAPI oracle response = " + response);
            JSONObject jsnobject = new JSONObject(response);


            JSONArray jsonArray = null;
            if (jsnobject.length() > 0) {
                jsonArray = jsnobject.getJSONArray("data");
            }

            //=====================
            log.setApiName("XX_INVOICE_PDF_PAYLOAD");
            log.setContent(response);
            itemSer.pushLog(log);
            //====================

            if (jsonArray.length() > 0) {

                LogDTO childLog = null;
                String recordNo = "";
                String statusCode = "";
                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject explrObject = jsonArray.getJSONObject(i);
                        String documentNumber = explrObject.get("Document_Number") != null ?
                                (String) explrObject.get("Document_Number") :
                                "";
                        String documentType = explrObject.get("Document_Type") != null ?
                                (String) explrObject.get("Document_Type") :
                                "";
                        String fileLocation = explrObject.get("File_field") != null ?
                                (String) explrObject.get("File_field") :
                                "";
                        recordNo = explrObject.get("Record_No") != null ?
                                String.valueOf(explrObject.get("Record_No")) :
                                "";

                        File file =  getFile(fileLocation);

                        String request = "{\"data\":[" + jsonArray.get(i) + "]}";
                        System.out.println("request = " + request);
                        //====================
                        childLog = new LogDTO();
                        childLog.setId(LogDTO.generateUUID());
                        childLog.setParentId(parentId);
                        childLog.setApiName(ZohoApiCall.PDF_UPLOAD_API);
                        childLog.setProgramName("getPdfUploadAPI");
                        childLog.setContent(request);
                        //====================

                        String result = apicall.pdfUploadApi(
                                tokengn,
                                documentNumber,
                                documentType,
                                file
                        );
                        childLog.setDescription(result);
                        itemSer.pushLog(childLog);
                        JSONObject jsonres = null;
                        try {
                            jsonres = new JSONObject(result);
                        }catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            if (jsonres != null) {
                                statusCode = String.valueOf(jsonres.get("code"));
                            } else {
                                statusCode = "ERROR";
                            }
                        }catch (Exception e) {
                            e.printStackTrace();
                            result = e.getMessage();
                            statusCode = "ERROR";
                        }
                        itemSer.updatePDF(recordNo, statusCode, result);
//                        Thread.sleep(800);

                    }catch (Exception e) {
                        e.printStackTrace();
                        itemSer.updatePDF(recordNo, statusCode, e.getMessage());
                    }
                }
            } else {
                System.out.println(" No Data found from database");

            }


        }catch (Exception e) {
            e.printStackTrace();
            log.setContent(e.getMessage());
            itemSer.pushLog(log);
        }
        System.out.println(new Date() + "--END getPdfUploadAPI()--");

    }


    public static String getClobString(Clob clob) throws SQLException, IOException {
        BufferedReader stringReader = new BufferedReader(clob.getCharacterStream());
        String singleLine = null;
        StringBuffer strBuff = new StringBuffer();
        while ((singleLine = stringReader.readLine()) != null) {
            strBuff.append(singleLine);
        }
        return strBuff.toString();
    }

    public File getFile(String location) {
//        File myObj = new File("C:\\Users\\gaura\\Downloads\\Get_Started_With_Smallpdf.pdf");
        File myObj = new File(location);
        return myObj;
    }
}

