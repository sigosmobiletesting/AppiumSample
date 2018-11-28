package helper;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * Created by ssu on 8/5/2017.
 */
public class GetNonScecureEnsemble {

    public static void main(String[] args) {

        String[] ensembleServers = {
//                "172.16.1.204",
//                "213.95.69.43",
                "FRA-DTT-ARG-P01.deviceanywhere.com",
                "LON-ZAY-TCD-001.deviceanywhere.com",
                "LON-ZAY-TCD-002.deviceanywhere.com",
                "LON-ZAY-TCD-003.deviceanywhere.com",
                "LON-ZAY-TCD-004.deviceanywhere.com",
                "LON-ZAY-TCD-005.deviceanywhere.com",
                "LON-ZAY-TCD-008.deviceanywhere.com",
                "LON-ZAY-TCD-009.deviceanywhere.com",
                "LON-ZAY-TCD-016.deviceanywhere.com",
                "LON-ZAY-TCV-001.deviceanywhere.com",
                "LON-ZAY-TCV-002.deviceanywhere.com",
                "LON-ZAY-TCV-003.deviceanywhere.com",
                "mun-cws-tce-002.deviceanywhere.com",
                "NUE-CWS-DLC-P01.deviceanywhere.com",
                "NUE-CWS-DLC-V01.deviceanywhere.com",
                "NUR-CWS-TCD-001.deviceanywhere.com",
                "SFO-777-TCD-001.deviceanywhere.com",
                "SFO-777-TCD-002.deviceanywhere.com",
                "SFO-777-TCV-002.deviceanywhere.com",
                "SFO-AMP-AFB-P01.deviceanywhere.com",
                "SFO-AMP-CNH-P01.deviceanywhere.com",
                "SFO-AMP-DEM-003.deviceanywhere.com",
                "SFO-AMP-DEM-021.deviceanywhere.com",
                "SFO-AMP-DEM-032.deviceanywhere.com",
                "sfo-amp-dem-043.deviceanywhere.com",
                "sfo-amp-dem-102.deviceanywhere.com",
                "SFO-AMP-DEM-105.deviceanywhere.com",
                "SFO-AMP-IBB-P01.deviceanywhere.com",
                "SFO-AMP-IBB-P03.deviceanywhere.com",
                "SFO-AMP-IBB-V01.deviceanywhere.com",
                "SFO-AMP-IBB-V02.deviceanywhere.com",
                "SFO-AMP-IBB-V03.deviceanywhere.com",
                "SFO-AMP-INF-P01.deviceanywhere.com",
                "SFO-AMP-INF-P03.deviceanywhere.com",
                "SFO-AMP-PVM-P01.deviceanywhere.com",
                "SFO-AMP-SAP-P01.deviceanywhere.com",
                "SFO-AMP-SAP-P02.deviceanywhere.com",
                "SFO-AMP-SER-P07.deviceanywhere.com",
                "SFO-AMP-TCD-004.deviceanywhere.com",
                "SFO-AMP-TCD-005.deviceanywhere.com",
                "SFO-AMP-TCD-006.deviceanywhere.com",
                "SFO-AMP-TCD-009.deviceanywhere.com",
                "SFO-AMP-TCD-011.deviceanywhere.com",
                "SFO-AMP-TCD-012.deviceanywhere.com",
                "SFO-AMP-TCD-013.deviceanywhere.com",
                "SFO-AMP-TCD-014.deviceanywhere.com",
                "SFO-AMP-TCD-015.deviceanywhere.com",
                "SFO-AMP-TCD-016.deviceanywhere.com",
                "SFO-AMP-TCD-017.deviceanywhere.com",
                "SFO-AMP-TCD-018.deviceanywhere.com",
                "SFO-AMP-TCD-020.deviceanywhere.com",
                "SFO-AMP-TCD-021.deviceanywhere.com",
                "SFO-AMP-TCD-022.deviceanywhere.com",
                "SFO-AMP-TCD-023.deviceanywhere.com",
                "SFO-AMP-TCD-024.deviceanywhere.com",
                "SFO-AMP-TCD-025.deviceanywhere.com",
                "SFO-AMP-TCD-026.deviceanywhere.com",
                "SFO-AMP-TCD-027.deviceanywhere.com",
                "SFO-AMP-TCD-028.deviceanywhere.com",
                "SFO-AMP-TCD-029.deviceanywhere.com",
                "SFO-AMP-TCD-090.deviceanywhere.com",
                "SFO-AMP-TCD-092.deviceanywhere.com",
                "SFO-AMP-TCD-093.deviceanywhere.com",
                "SFO-AMP-TCD-095.deviceanywhere.com",
                "SFO-AMP-TCD-102.deviceanywhere.com",
                "SFO-AMP-TCD-103.deviceanywhere.com",
                "SFO-AMP-TCD-105.deviceanywhere.com",
                "SFO-AMP-TCD-106.deviceanywhere.com",
                "SFO-AMP-TCD-110.deviceanywhere.com",
                "SFO-AMP-TCD-117.deviceanywhere.com",
                "SFO-AMP-TCD-119.deviceanywhere.com",
                "SFO-AMP-TCD-120.deviceanywhere.com",
                "SFO-AMP-TCD-121.deviceanywhere.com",
                "SFO-AMP-TCD-122.deviceanywhere.com",
                "SFO-AMP-TCD-123.deviceanywhere.com",
                "SFO-AMP-TCD-127.deviceanywhere.com",
                "SFO-AMP-TCD-129.deviceanywhere.com",
                "SFO-AMP-TCD-130.deviceanywhere.com",
                "SFO-AMP-TCD-133.deviceanywhere.com",
                "SFO-AMP-TCD-138.deviceanywhere.com",
                "SFO-AMP-TCD-139.deviceanywhere.com",
                "SFO-AMP-TCD-140.deviceanywhere.com",
                "SFO-AMP-TCD-141.deviceanywhere.com",
                "SFO-AMP-TCD-142.deviceanywhere.com",
                "SFO-AMP-TCD-143.deviceanywhere.com",
                "SFO-AMP-TCD-144.deviceanywhere.com",
                "SFO-AMP-TCD-146.deviceanywhere.com",
                "SFO-AMP-TCD-156.deviceanywhere.com",
                "SFO-AMP-TCD-157.deviceanywhere.com",
                "SFO-AMP-TCD-158.deviceanywhere.com",
                "SFO-AMP-TCD-159.deviceanywhere.com",
                "SFO-AMP-TCD-162.deviceanywhere.com",
                "SFO-AMP-TCD-163.deviceanywhere.com",
                "SFO-AMP-TCD-164.deviceanywhere.com",
                "SFO-AMP-TCD-165.deviceanywhere.com",
                "SFO-AMP-TCD-166.deviceanywhere.com",
                "SFO-AMP-TCD-169.deviceanywhere.com",
                "SFO-AMP-TCD-170.deviceanywhere.com",
                "SFO-AMP-TCD-171.deviceanywhere.com",
                "SFO-AMP-TCD-172.deviceanywhere.com",
                "SFO-AMP-TCD-173.deviceanywhere.com",
                "SFO-AMP-TCD-174.deviceanywhere.com",
                "SFO-AMP-TCD-176.deviceanywhere.com",
                "SFO-AMP-TCD-177.deviceanywhere.com",
                "SFO-AMP-TCD-178.deviceanywhere.com",
                "SFO-AMP-TCD-179.deviceanywhere.com",
                "SFO-AMP-TCD-180.deviceanywhere.com",
                "SFO-AMP-TCD-181.deviceanywhere.com",
                "SFO-AMP-TCD-182.deviceanywhere.com",
                "SFO-AMP-TCD-184.deviceanywhere.com",
                "SFO-AMP-TCD-185.deviceanywhere.com",
                "SFO-AMP-TCD-186.deviceanywhere.com",
                "SFO-AMP-TCD-189.deviceanywhere.com",
                "SFO-AMP-TCD-190.deviceanywhere.com",
                "SFO-AMP-TCD-191.deviceanywhere.com",
                "SFO-AMP-TCD-192.deviceanywhere.com",
                "SFO-AMP-TCD-193.deviceanywhere.com",
                "SFO-AMP-TCD-194.deviceanywhere.com",
                "SFO-AMP-TCD-196.deviceanywhere.com",
                "SFO-AMP-TCD-198.deviceanywhere.com",
                "SFO-AMP-TCD-199.deviceanywhere.com",
                "SFO-AMP-TCD-200.deviceanywhere.com",
                "SFO-AMP-TCD-201.deviceanywhere.com",
                "SFO-AMP-TCD-202.deviceanywhere.com",
                "SFO-AMP-TCD-203.deviceanywhere.com",
                "SFO-AMP-TCD-204.deviceanywhere.com",
                "SFO-AMP-TCD-205.deviceanywhere.com",
                "SFO-AMP-TCD-212.deviceanywhere.com",
                "SFO-AMP-TCD-213.deviceanywhere.com",
                "SFO-AMP-TCD-214.deviceanywhere.com",
                "SFO-AMP-TCD-216.deviceanywhere.com",
                "SFO-AMP-TCD-217.deviceanywhere.com",
                "SFO-AMP-TCD-218.deviceanywhere.com",
                "SFO-AMP-TCD-221.deviceanywhere.com",
                "SFO-AMP-TCD-223.deviceanywhere.com",
                "SFO-AMP-TCD-224.deviceanywhere.com",
                "SFO-AMP-TCD-225.deviceanywhere.com",
                "SFO-AMP-TCD-226.deviceanywhere.com",
                "SFO-AMP-TCD-228.deviceanywhere.com",
                "SFO-AMP-TCD-230.deviceanywhere.com",
                "SFO-AMP-TCD-232.deviceanywhere.com",
                "SFO-AMP-TCE-002.deviceanywhere.com",
                "SFO-AMP-TCE-004.deviceanywhere.com",
                "SFO-AMP-TCE-015.deviceanywhere.com",
                "SFO-AMP-TCE-018.deviceanywhere.com",
                "SFO-AMP-TCE-019.deviceanywhere.com",
                "SFO-AMP-TCE-020.deviceanywhere.com",
                "SFO-AMP-TCE-021.deviceanywhere.com",
                "SFO-AMP-TCE-022.deviceanywhere.com",
                "sfo-amp-tce-026.deviceanywhere.com",
                "SFO-AMP-TCV-003.deviceanywhere.com",
                "SFO-AMP-TCV-004.deviceanywhere.com",
                "SFO-AMP-TCV-005.deviceanywhere.com",
                "SFO-AMP-TCV-007.deviceanywhere.com",
                "SFO-AMP-TCV-010.deviceanywhere.com",
                "SFO-AMP-TCV-011.deviceanywhere.com",
                "SFO-AMP-TCV-022.deviceanywhere.com",
                "SFO-AMP-TCV-024.deviceanywhere.com",
                "SFO-AMP-TCV-025.deviceanywhere.com",
                "SFO-AMP-TCV-026.deviceanywhere.com",
                "SFO-AMP-TCV-027.deviceanywhere.com",
                "SFO-AMP-TCV-028.deviceanywhere.com",
                "SFO-AMP-TCV-029.deviceanywhere.com",
                "SFO-AMP-TCV-030.deviceanywhere.com",
                "SFO-AMP-TCV-032.deviceanywhere.com",
                "SFO-AMP-TCV-033.deviceanywhere.com",
                "SFO-AMP-TCV-036.deviceanywhere.com",
                "SFO-AMP-TCV-037.deviceanywhere.com",
                "SFO-AMP-TCV-038.deviceanywhere.com",
                "SFO-AMP-TCV-039.deviceanywhere.com",
                "SFO-AMP-TCV-040.deviceanywhere.com",
                "SFO-AMP-TCV-041.deviceanywhere.com",
                "SFO-AMP-TCV-042.deviceanywhere.com",
                "SFO-AMP-TCV-043.deviceanywhere.com",
                "SFO-AMP-TCV-044.deviceanywhere.com",
                "SFO-AMP-TCV-046.deviceanywhere.com",
                "SFO-AMP-TCV-048.deviceanywhere.com",
                "SFO-AMP-TCV-049.deviceanywhere.com",
                "SFO-AMP-TCV-050.deviceanywhere.com",
                "SFO-AMP-TCV-051.deviceanywhere.com",
                "SFO-AMP-TCV-052.deviceanywhere.com",
                "SFO-AMP-TCV-053.deviceanywhere.com",
                "SFO-AMP-TCV-054.deviceanywhere.com",
                "SFO-AMP-TCV-055.deviceanywhere.com",
                "SFO-AMP-TCV-056.deviceanywhere.com",
                "SFO-AMP-TCV-057.deviceanywhere.com",
                "SFO-AMP-TCV-058.deviceanywhere.com",
                "SFO-AMP-TCV-059.deviceanywhere.com",
                "SFO-AMP-TCV-060.deviceanywhere.com",
                "SFO-AMP-TCV-061.deviceanywhere.com",
                "SFO-AMP-TCV-062.deviceanywhere.com",
                "SFO-AMP-TCV-063.deviceanywhere.com",
                "SFO-AMP-TCV-064.deviceanywhere.com",
                "SFO-AMP-TCV-065.deviceanywhere.com",
                "SFO-AMP-TCV-067.deviceanywhere.com",
                "SFO-AMP-TCV-068.deviceanywhere.com",
                "SFO-AMP-TCV-070.deviceanywhere.com",
                "SFO-AMP-TCV-071.deviceanywhere.com",
                "SFO-AMP-TCV-072.deviceanywhere.com",
                "SFO-AMP-TCV-073.deviceanywhere.com",
                "SFO-AMP-TCV-076.deviceanywhere.com",
                "SFO-AMP-TCV-077.deviceanywhere.com",
                "sfo-amp-tcv-079.deviceanywhere.com",
                "SFO-AMP-TCV-080.deviceanywhere.com",
                "SFO-AMP-TCV-081.deviceanywhere.com",
                "SFO-AMP-TCV-082.deviceanywhere.com",
                "SFO-AMP-TCV-083.deviceanywhere.com",
                "SFO-AMP-TCV-084.deviceanywhere.com",
                "SFO-AMP-TCV-085.deviceanywhere.com",
                "SFO-AMP-TCV-086.deviceanywhere.com",
                "SFO-AMP-TCV-087.deviceanywhere.com",
                "SFO-AMP-TCV-088.deviceanywhere.com",
                "SFO-AMP-TCV-089.deviceanywhere.com",
                "SFO-AMP-TCV-090.deviceanywhere.com",
                "SFO-AMP-TCV-091.deviceanywhere.com",
                "SFO-AMP-TCV-092.deviceanywhere.com",
                "SFO-AMP-ZIL-P01.deviceanywhere.com",
                "SFO-AMP-ZIL-V01.deviceanywhere.com",
                "SFOAMP-BMS9455.deviceanywhere.com",
                "SFOAMP-CAN9639.deviceanywhere.com",
                "SFOAMP-CCH9679.deviceanywhere.com",
                "SFOAMP-CIB9430.deviceanywhere.com",
                "SFOAMP-CNH9556.deviceanywhere.com",
                "SFOAMP-DEM9207.deviceanywhere.com",
                "SFOAMP-DEM9210.deviceanywhere.com",
                "SFOAMP-DEM9314.deviceanywhere.com",
                "sfoamp-dem9315.deviceanywhere.com",
                "SFOAMP-INF9481.deviceanywhere.com",
                "SFOAMP-NYL9513.deviceanywhere.com",
                "SFOAMP-SAP8962.deviceanywhere.com",
                "SFOAMP-TCD8029.deviceanywhere.com",
                "SFOAMP-TCD8950.deviceanywhere.com",
                "SFOAMP-TCD9160.deviceanywhere.com",
                "TOR-PR1-TCD-002.deviceanywhere.com",
                "TOR-PR1-TCV-001.deviceanywhere.com",
                "TOR-PR1-TCV-002.deviceanywhere.com"
        };


        MobileTestingHelper mobileTestingHelper = new MobileTestingHelper();

        for(int i = 0; i < ensembleServers.length; i++) {
            try {

                String url = "https://" +  ensembleServers[i] + ":8443/da/ensemble/device/mcds";

                JsonNode result =  mobileTestingHelper.restRequest(url, "GET", "application/json", "application/json", null);

                if(result == null) {
                    System.out.println(ensembleServers[i]);
                }

                //System.out.println(ensembleServers[i] + " - DONE");
            }
            catch (Exception e) {
                System.out.println(ensembleServers[i]);
            }
        }

        System.out.println("Execution completed successfully");

    }

}