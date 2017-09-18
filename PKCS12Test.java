package SSL_Test;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.security.auth.x500.X500Principal;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.x509.X509V3CertificateGenerator;

/** 
* @group : sic-ca 
* @Date : 2017/9/14
* @Comments : 测试证书类pfx 
* @Version : 1.0.0 
*/  
@SuppressWarnings("deprecation")
public class PKCS12Test {  

  static {  
      // 系统添加BC加密算法 以后系统中调用的算法都是BC的算法  
      Security.addProvider(new BouncyCastleProvider());  
  }  

  @SuppressWarnings("deprecation")
public static void main(String args[]) throws NoSuchAlgorithmException,  
          InvalidKeyException, SecurityException, SignatureException,  
          KeyStoreException, CertificateException, IOException {  

      String certPath = "d:/lq.pfx";  

      // 创建KeyStore  
       KeyStore store = KeyStore.getInstance("PKCS12");  
       store.load(null, null);  

      /* RSA算法产生公钥和私钥 */  
      KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");  
      kpg.initialize(2048);  
      KeyPair keyPair = kpg.generateKeyPair();  
      // 组装证书  
      String issuer = "C=CN,ST=BJ,L=BJ,O=SICCA,OU=SC,CN=SICCA";  
      String subject = issuer;  

      X509V3CertificateGenerator certGen = new X509V3CertificateGenerator();  

      certGen.setSerialNumber(BigInteger.valueOf(System.currentTimeMillis()));  
      certGen.setIssuerDN(new X500Principal(issuer));  
      certGen.setNotBefore(new Date(System.currentTimeMillis() - 50000));  
      certGen.setNotAfter(new Date(System.currentTimeMillis() + 50000));  
      certGen.setSubjectDN(new X500Principal(subject));  
      certGen.setPublicKey(keyPair.getPublic());  
      certGen.setSignatureAlgorithm("SHA256WithRSAEncryption");  
      X509Certificate cert = certGen.generateX509Certificate(keyPair.getPrivate());  
      System.out.println(cert.toString());  
      //System.out.println(keyPair.getPrivate());  
      //store.setCertificateEntry(alias, cert);  
    
      store.setKeyEntry("atlas", keyPair.getPrivate(),     
              "lq6265391".toCharArray(), new Certificate[] { cert });  

      FileOutputStream fout =new FileOutputStream(certPath);  
      store.store(fout, "lq6265391".toCharArray());         
      fout.close();    
  }  
 
}