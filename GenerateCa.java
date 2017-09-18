package SSL_Test;

import java.io.FileNotFoundException;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.security.cert.CertificateEncodingException;  
import java.security.cert.X509Certificate;  

/** 
* @group : sic-ca 
* @Date : 2017/9/14 
* @Comments : 测试证书类 cer
* @Version : 1.0.0 
*/  
public class GenerateCa {  
  private static String certPath = "D:/lq6265391.cer";  
  public static void main(String[] args) {  
      BaseCert baseCert = new BaseCert();  
      X509Certificate cert = baseCert.generateCert("lq6265391");  
      System.out.println(cert.toString());  

      // 导出为 cer 证书  
      try {  
          FileOutputStream fos = new FileOutputStream(certPath);  
          fos.write(cert.getEncoded());  
          fos.close();  
      } catch (FileNotFoundException e) {  
          e.printStackTrace();  
      } catch (CertificateEncodingException e) {  
          e.printStackTrace();  
      } catch (IOException e) {  
          e.printStackTrace();  
      }  
  }  
}