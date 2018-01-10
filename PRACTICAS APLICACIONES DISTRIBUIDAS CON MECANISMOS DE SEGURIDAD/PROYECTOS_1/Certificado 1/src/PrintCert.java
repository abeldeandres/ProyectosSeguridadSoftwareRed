import java.io.*;
import java.security.PublicKey;
import java.security.cert.*;
public class PrintCert {
	public static void main(String args[]) {

	
			try {
				InputStreamReader Flujo = new InputStreamReader(System.in);
				BufferedReader teclado = new BufferedReader(Flujo);
				System.out.print("Introducir Nombre Certificado: " );
				String NameFile=teclado.readLine();
				System.out.println("Nombre fichero:"+NameFile );
				FileInputStream fr = new FileInputStream(NameFile);
				

				CertificateFactory cf = CertificateFactory.getInstance("X509");
				X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
				System.out.println(c.toString());

				System.out.println("Version:"+c.getVersion());
				System.out.println("Algoritmos:"+c.getSigAlgName());
				System.out.println("Nº Serie:"+c.getSerialNumber());
				System.out.println("Propietario:"+c.getSubjectDN());
				System.out.println("Emisor:"+c.getIssuerDN());
				System.out.println("Valido desde: "+c.getNotBefore()+"hasta: "+c.getNotAfter());
			
			} catch (Exception e) {
				e.printStackTrace( );
			}		
	}
}



