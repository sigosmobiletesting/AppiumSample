package helper;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONObject;

import javax.net.ssl.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.cert.X509Certificate;

/**
 * Created by Moorthi.Subramani on 10/25/2016.
 */
public class MobileTestingHelper {

    String accessServerUrl = "http://visa.deviceanywhere.com:6232/";
    String userName = "username";
    String password = "password";
    int mcd = 9326;
    int muserId = -1;

    String jsonType = "application/json";

    String ensembleServerURL = null;
    String sessionID = "";
    String ensembleSessionId = "";
    public String appiumUrl = "";

    String appName = "test1";
    String appType = "ANDROID_APK";
    String appVersion = "1";

    AddApplicationRestResponse applicationInfo;

    public AddApplicationRestResponse getApplicationInfo() {
        return applicationInfo;
    }


    public  MobileTestingHelper() {
        applicationInfo = new AddApplicationRestResponse();
    }

    public String startAndGetAppiumUrl() {

        ensembleServerURL = null;
        sessionID = "";
        ensembleSessionId = "";
        appiumUrl = "";

        // create session, positive test case
        sessionID = createKeyNoteSession();
        if(sessionID == null || sessionID.isEmpty()) {
            System.out.println("Problem logging in to Mobile Testing, please verify UserName, Password or Access Server Url");
        }

        System.out.println("Session id " + sessionID);


        // lock device
        ensembleSessionId = lockDevice();

        if(ensembleSessionId == null || ensembleSessionId.isEmpty()) {
            System.out.println("Problem logging in to Mobile Testing, please verify UserName, Password or Access Server Url");
        }
        else {
            System.out.println("Ensemble URL " + ensembleServerURL);
            startAppiumServer();
            System.out.println("Appium URL is " + appiumUrl);
        }


        return appiumUrl;

    }

    //Start mobile testing with local file
    public boolean start(int imcd, File appFile) {

        mcd = imcd;

        return startProcess(appFile, null, -1);

    }

    //Start mobile testing with local file
    public boolean start(int imcd, int applicationId) {

        mcd = imcd;

        return startProcess(null, null, applicationId);

    }

    //Start mobile testing with local file
    public boolean start(int imcd, String fileUrl) {

        mcd = imcd;

        return startProcess(null, fileUrl, -1);

    }

    public int uploadApplication(File appFile) {
        int appId = -1;

        String session = createKeyNoteSession();

        this.applicationInfo = new AddApplicationRestResponse();

        if(session != null) {
            uploadApplication(appFile, null);
            appId = this.applicationInfo.applicationId;
        }

        return  appId;
    }

    private boolean startProcess(File appFile, String fileUrl, int applicationId) {

        boolean status = false;

        ensembleServerURL = null;
        sessionID = "";
        ensembleSessionId = "";
        appiumUrl = "";

        // create session, positive test case
        sessionID = createKeyNoteSession();
        if(sessionID == null || sessionID.isEmpty()) {
            System.out.println("Problem logging in to Mobile Testing, please verify UserName, Password or Access Server Url");
        }

        System.out.println("Session Created : " + sessionID );
        System.out.println("Session UserId: " + muserId );

        if(appFile != null)
          System.out.println("Uploading application - " + appFile.getName());
        else if(fileUrl != null && !fileUrl.isEmpty())
           System.out.println("Uploading application - " + fileUrl);
        else {
            System.out.println("getting application info  - " + applicationId);
        }

        boolean isUploadSuccess = false;


        if(applicationId > 0) {
            isUploadSuccess = getApplicationInformation(applicationId);
        }
        else {
            isUploadSuccess = uploadApplication(appFile, fileUrl);
        }

        if(isUploadSuccess) {

            System.out.println("Uploading application Success applicationId: " +  applicationInfo.applicationId);

            // lock device
            System.out.println("Locking the Device" );

            ensembleSessionId = lockDevice();

            if(ensembleSessionId == null || ensembleSessionId.isEmpty()) {
                System.out.println("Problem logging in to Mobile Testing, please verify UserName, Password or Access Server Url");
            }
            else {
                System.out.println("Ensemble URL " + ensembleServerURL);
                System.out.println("Ensemble sessionID ::" + ensembleSessionId);

                System.out.println("installing application - " + applicationInfo.applicationId);
                boolean installSuccess = installApplication(applicationInfo.applicationId);

                if(installSuccess) {
                    System.out.println("installing application completed successfully- " + applicationInfo.applicationId);

                    startAppiumServer();

                    System.out.println("Appium URL is " + appiumUrl);
                    status = true;
                }
                else {
                    System.out.println("App Install failed ");
                }


            }
        }

        return status;
    }

    //Create keynote session for user
    private String createKeyNoteSession() {
        try {

            JSONObject cred   = new JSONObject();
            cred.put("email", userName);
            cred.put("password", password);

            String url = accessServerUrl + "portal/establish-api-session/";

            ObjectMapper mapper = new ObjectMapper();

            JsonNode node = restRequest(url, "POST", jsonType, jsonType, cred.toString());

            sessionID = node.get("sessionID").textValue();
            muserId = node.get("muserId").asInt();

            return sessionID;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //Lock device
    public String lockDevice() {

        try {
            JSONObject cred   = new JSONObject();
            cred.put("sessionID", sessionID);

            String url = accessServerUrl + "device/lock-device/" + mcd;

            JsonNode node = restRequest(url, "POST", jsonType, jsonType, cred.toString());

            ensembleServerURL = node.get("ensembleServerURL").textValue();
            ensembleSessionId = node.get("sessionId").textValue();
            appiumUrl = node.get("appiumURL").textValue();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return ensembleSessionId;
    }

    //Start appium Driver
    private void startAppiumServer() {
        try{
            String url = accessServerUrl + "device/" + sessionID +"/start-appium/" + mcd;
            //restRequest(url, "GET", null, "text/plain", null);
            URL u = new URL(url);
            HttpURLConnection conn = null;

            if(accessServerUrl.startsWith("https")) {
                conn = getSecureConnection(u);
            }
            else {
                conn = (HttpURLConnection) u.openConnection();
            }

            conn.setDoOutput(true);
            conn.setRequestMethod("GET");

            System.out.println("Response :: " + conn.getResponseMessage());
            System.out.println("Response :: " + conn.getResponseCode());

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Upload application to Mobile testing repository
    private boolean uploadApplication(File file, String fileUrl) {
        try {

            AddApplicationRestRequest addApplicationRestRequest = new AddApplicationRestRequest();

            addApplicationRestRequest.isEnableApp = true;
            addApplicationRestRequest.isSignApp = true;

            if(file != null) {
                addApplicationRestRequest.fileContent = javax.xml.bind.DatatypeConverter.printBase64Binary(FileUtils.readFileToByteArray(file));
                //Base64.encodeBase64URLSafeString(FileUtils.readFileToByteArray(file));
            }
            else  {
                addApplicationRestRequest.fileUrl = fileUrl;
            }


            if(file != null) {
                if(file.getName().endsWith("ipa")) {
                    addApplicationRestRequest.appType = "IPHONE";
                }
                else {
                    addApplicationRestRequest.appType = "ANDROID_APK";
                }
            }
            else {
                if(fileUrl.endsWith("ipa")) {
                    addApplicationRestRequest.appType = "IPHONE";
                }
                else {
                    addApplicationRestRequest.appType = "ANDROID_APK";
                }
            }


            String uploadUrl = accessServerUrl + "applications/" + sessionID + "/upload/" + mcd + "/" +  muserId;

            ObjectMapper mapper = new ObjectMapper();

            applicationInfo = new AddApplicationRestResponse();

            JsonNode resultNode = restRequest(uploadUrl, "POST", jsonType, jsonType, mapper.writeValueAsString(addApplicationRestRequest));

            applicationInfo.appType = resultNode.get("appType").textValue();
            applicationInfo.appName = resultNode.get("appName").textValue();
            applicationInfo.appVersion = resultNode.get("appVersion").textValue();
            applicationInfo.appPackage = resultNode.get("appPackage").textValue();
            applicationInfo.appActivity = resultNode.get("appActivity").textValue();
            applicationInfo.bundleId = resultNode.get("bundleId").textValue();
            applicationInfo.applicationId = resultNode.get("applicationId").asInt();

            if(applicationInfo != null && applicationInfo.applicationId > 0) {
                return true;
            }
            else
                return false;

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    private boolean getApplicationInformation(int applicationId) {

        boolean isAppInfoReceived = false;

        String url = accessServerUrl + "applications/getByApplicationID/" + applicationId;

        applicationInfo = new AddApplicationRestResponse();

        JsonNode resultNode = restRequest(url, "GET", null, jsonType, null);

        applicationInfo.appType = resultNode.get("applicationDescriptor").get(0).get("applicationType").textValue();
        applicationInfo.appName = resultNode.get("applicationDescriptor").get(0).get("applicationName").textValue();
        applicationInfo.appVersion = resultNode.get("applicationDescriptor").get(0).get("applicationVersion").textValue();
        applicationInfo.appPackage = resultNode.get("applicationDescriptor").get(0).get("applicationPackage").textValue();
        applicationInfo.appActivity = resultNode.get("applicationDescriptor").get(0).get("applicationActivity").textValue();
        applicationInfo.bundleId = resultNode.get("applicationDescriptor").get(0).get("applicationBundleId").textValue();
        applicationInfo.applicationId = applicationId;

        if(applicationInfo != null && applicationInfo.applicationId > 0) {
            isAppInfoReceived = true;
        }
        else
            isAppInfoReceived = false;

        return  isAppInfoReceived;

    }

    //Install app to device
    private boolean installApplication(int applicationId) {
        try {

            String installUrl = ensembleServerURL + "/install-application/";

            System.out.println("installUrl: " + installUrl);

            InstallApplicationRequest installApplicationRequest = new InstallApplicationRequest();
            installApplicationRequest.setApplicationId(applicationId);

            ObjectMapper mapper = new ObjectMapper();

            JsonNode resultNode = restRequest(installUrl, "POST", jsonType, jsonType, mapper.writeValueAsString(installApplicationRequest));

            String status = resultNode.get("status").textValue();

            if(status.equals("SUCCESS")) {
                return true;
            }
            else {
                String reason = resultNode.get("reason").textValue();

                if(reason == null || reason.equalsIgnoreCase(""))
                    return true;
                else
                    return false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getApplicationURL() {
        try {

            String applicationURL = "";

            JSONObject cred   = new JSONObject();
            cred.put("appName", appName);
            cred.put("appType", appType);
            cred.put("appVersion", appVersion);
            String type = "application/json";


            String url = accessServerUrl + "applications/" + sessionID + "/get-application-url";

            JsonNode resultNode = restRequest(url, "POST", jsonType, jsonType, cred.toString());

            applicationURL = resultNode.get("value").textValue();

            System.out.println("Application URL is: " + applicationURL);

            return applicationURL;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    //Log out Keynote session
    public String logoutSession()
    {
        try {

            JSONObject cred   = new JSONObject();
            cred.put("sessionID", sessionID);

            String url = accessServerUrl + "portal/logout-api-session";
            JsonNode node = restRequest(url, "POST", jsonType, jsonType, cred.toString());

        } catch(Exception e1) {
            e1.printStackTrace();
        }
        return null;


    }

   @JsonIgnoreProperties(ignoreUnknown = true)
    protected class AddApplicationRestRequest {

        @JsonProperty("isSignApp")
        public boolean isSignApp;

        @JsonProperty("isEnableApp")
        public boolean isEnableApp;

        @JsonProperty("appType")
        public String appType;

        @JsonProperty("appName")
        public String appName;

        @JsonProperty("appVersion")
        public String  appVersion;

        @JsonProperty("fileName")
        public String fileName;

        @JsonProperty("fileContent")
        public String fileContent;

       @JsonProperty("fileUrl")
       public String fileUrl;

        @JsonProperty("isGetDownloadUrl")
        public boolean isGetDownloadUrl;
    }


    public class AddApplicationRestResponse {

        public AddApplicationRestResponse()
        {

        }

        @JsonProperty("appType")
        public String appType;

        @JsonProperty("appName")
        public String appName;

        @JsonProperty("appVersion")
        public String  appVersion;

        @JsonProperty("appPackage")
        public String  appPackage;

        @JsonProperty("appActivity")
        public String  appActivity;

        @JsonProperty("bundleId")
        public String  bundleId;

        @JsonProperty("applicationId")
        public int applicationId;


    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    protected class InstallApplicationRequest {


        @JsonProperty("applicationID")
        public int applicationId;

        public int getApplicationId() {
            return applicationId;
        }

        public void setApplicationId(int applicationId) {
            this.applicationId = applicationId;
        }
    }

    private JsonNode restRequest(String urlIN, String method, String contentType, String acceptType, String body)
    {
        InputStream response = null;
        InputStream responseError = null;
        OutputStream outNow = null;
        HttpURLConnection conn = null;
        JsonNode result = null;
        try {
            if ( urlIN.indexOf( "https:") >= 0) {
                URL url = new URL(null, urlIN, new sun.net.www.protocol.https.Handler());
                conn = getSecureConnection(url);//(HttpsURLConnection) url.openConnection();
            }
            else {
                conn = (HttpURLConnection) new URL(urlIN).openConnection();
            }

            conn.setRequestMethod(method);

            if(contentType != null)
                conn.setRequestProperty("Content-Type", contentType);

            conn.setRequestProperty("Accept", acceptType);
            conn.setRequestProperty("Content-transfer-encoding", "UTF-8");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.2; .NET CLR 1.0.3705;)");

            conn.setDoInput(true);
            conn.setDoOutput(true);

            if (body != null) {
                conn.setDoOutput(true);

                final OutputStream output = conn.getOutputStream();
                final OutputStreamWriter osWriter = new OutputStreamWriter(output, "UTF-8");

                osWriter.write(body);
                osWriter.flush();
                osWriter.close();
                output.close();
            }

            conn.connect();
            responseError = conn.getErrorStream();

            if(acceptType.equals(jsonType)) {
                ObjectMapper mapper = new ObjectMapper();
                result =  mapper.readTree(conn.getInputStream());
            }
            //System.out.println("Response :: " +conn.getResponseMessage());
            //System.out.println("Response :: " +conn.getInputStream());

            conn.disconnect();
        } catch (final Exception e) {
            System.out.println("Errors occurred while attempting to load the URL...");
            e.printStackTrace();
        }

        return result;
    }

    private HttpURLConnection getSecureConnection(URL u) {

        HttpURLConnection conn = null;

        try {
            TrustManager[] trustAllCerts = new TrustManager[] {
                    new X509TrustManager() {
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() { return null; }
                        public void checkClientTrusted(X509Certificate[] certs, String authType) { }
                        public void checkServerTrusted(X509Certificate[] certs, String authType) { }

                    } };

            SSLContext sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

            // Create all-trusting host name verifier
            HostnameVerifier allHostsValid = new HostnameVerifier() {
                public boolean verify(String hostname, SSLSession session) { return true; }
            };
            // Install the all-trusting host verifier
            HttpsURLConnection.setDefaultHostnameVerifier(allHostsValid);

            conn = (HttpsURLConnection) u.openConnection();
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return conn;

    }
}
