/*
    Code developed for CMPSC 402 class 
    by Professor. Mohan using the Jsch Library.
    This class provides the methods such as running shell commands on remote machines, 
    copying data from one machine to another, etc.
*/
package cloud;
import java.util.ArrayList;
import org.w3c.dom.*;
import java.net.InetAddress;
import java.net.UnknownHostException;
public class Driver{
    public static void main(String[] args) throws Exception, InterruptedException{
        Shell sh = new Shell();
        String path = System.getProperty("user.dir");
        if (path.indexOf("\\") >= 0){
            path = path.substring(0, path.lastIndexOf("\\") + 1);
            path = path + "data\\config.xml";
        }
        else if (path.indexOf("/") >= 0){
            path = path.substring(0, path.lastIndexOf("/") + 1);
            path = path + "data/config.xml";
        }
        Document config = Utility.readFileAsDocument(path);
        String host = ((Element) config.getDocumentElement().getElementsByTagName("host").item(0)).getTextContent().trim();
        String upload_src = ((Element) config.getDocumentElement().getElementsByTagName("upload_src").item(0)).getTextContent().trim();
        String upload_dest = ((Element) config.getDocumentElement().getElementsByTagName("upload_dest").item(0)).getTextContent().trim();
        String download_src = ((Element) config.getDocumentElement().getElementsByTagName("download_src").item(0)).getTextContent().trim();
        String download_dest = ((Element) config.getDocumentElement().getElementsByTagName("download_dest").item(0)).getTextContent().trim();
        String user = ((Element) config.getDocumentElement().getElementsByTagName("user").item(0)).getTextContent().trim();
        String pem = ((Element) config.getDocumentElement().getElementsByTagName("pem").item(0)).getTextContent().trim();
        int port = Integer.parseInt(((Element) config.getDocumentElement().getElementsByTagName("port").item(0)).getTextContent().trim());
        sh.upload(upload_src, upload_dest, host, user, pem, port);
        System.out.println("#upload completed successfully");
        
        
        ArrayList<String> cmds = new ArrayList<String>();
        cmds.add("cd clock");
        cmds.add("chmod +x compile.sh");
        cmds.add("./compile.sh");
        cmds.add("chmod +x run.sh");
        cmds.add("./run.sh");
        cmds.add("exit");
        String logs = sh.execute(host, user, pem, port, cmds);
        System.out.println(logs);
        System.out.println("#commands executed successfully");
        
        sh.download(download_src, download_dest, host, user, pem, port);
        System.out.println("#download completed successfully");
        
    }
}    
    


