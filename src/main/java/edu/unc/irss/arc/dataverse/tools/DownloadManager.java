package edu.unc.irss.arc.dataverse.tools;

import edu.unc.irss.arc.dataverse.client.DataverseClientConfig;
import edu.unc.irss.arc.dataverse.client.Jersey2DataverseClient;
import edu.unc.irss.arc.dataverse.client.util.GenericBuilder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Akio Sone, Univ, of North Carolina at Chapel Hill, H.W. Odum Inst.
 */
public class DownloadManager {
    
    
    private static final Logger logger = 
            Logger.getLogger(DownloadManager.class.getName());
    
//    DataverseClientConfig clientConfig;
//    
//    Jersey2DataverseClient dataverseClient;
    
    
    
    
    public static void main(String[] args) {
        
        if (args.length < 4 || args.length > 5) {
            System.out.println("usage: "
                    + "java -jar "
                    + "dataverse-download-manager-1.0.0-launcher.jar "
                    + "dataverse_server, Api_key "
                    + "persistentId "
                    + "destination_dir "
                    + "zipfile name (optional)");
            System.out.println("Note: persistent Id "
                    + "such as doi:10.5072/FK2/U2ASZ1 "
                    + "is found in the Metadata tab of a dataset page");
            logger.log(Level.SEVERE, "Four arguments: dataverse_server, Api_key, "
                    + "persistentId, destination_dir "
                    + "or Five arguments (+ zipfile name) are expected");
            logger.log(Level.INFO, "persistent Id such as doi:10.5072/FK2/U2ASZ1 is found in the Metadata tab of a dataset page");
            throw new IllegalArgumentException("The number of arguments must be 4 or 5.");
        }
        
        for (String arg: args){
            logger.log(Level.INFO, "arg={0}", arg);
        }
        
        
        if (StringUtils.isBlank(args[0])) {
            logger.log(Level.SEVERE, "dataverse server should not be blank");
            throw new IllegalArgumentException("dataverse URL should not be blank");
        }
        
        if (StringUtils.isBlank(args[1])) {
            logger.log(Level.SEVERE, "API key should not be blank");
            throw new IllegalArgumentException("API Key should not be blank");
        }
        
        if (StringUtils.isBlank(args[2])) {
            logger.log(Level.SEVERE, "persistentId should not be blank");
            throw new IllegalArgumentException("persistentId should not be blank");
        }
        
        if (StringUtils.isBlank(args[3])) {
            logger.log(Level.SEVERE, "destination_dir should not be blank");
            throw new IllegalArgumentException("destination_dir should not be blank");
        }
        
        if (args.length == 4) {
            logger.log(Level.INFO, "zipfile name is set to the identifier of the dataset");
        }
        
        logger.log(Level.INFO, "client config instance is set up");
        DataverseClientConfig config = GenericBuilder
                .of(DataverseClientConfig::new)
                .with(DataverseClientConfig::setServer, args[0])
                .with(DataverseClientConfig::setApiKey, args[1])
                .build();
        
        logger.log(Level.INFO, "dataverse client is set up");
        Jersey2DataverseClient dataverseClient = new Jersey2DataverseClient(config);
        logger.log(Level.INFO, "start downloading");
        dataverseClient.downloadDatafilesByDatasetId(args[2], args[3], "");
        logger.log(Level.INFO, "end of downloaing");
    }
}
