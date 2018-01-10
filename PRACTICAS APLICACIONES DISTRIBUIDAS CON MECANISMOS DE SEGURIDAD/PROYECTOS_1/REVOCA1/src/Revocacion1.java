

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509Certificate;


public class Revocacion1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Introducir Nombre Certificado: " );
			String NameFile=teclado.readLine();
			System.out.println("Nombre fichero:"+NameFile );
			FileInputStream fr = new FileInputStream(NameFile);
			

			CertificateFactory cf = CertificateFactory.getInstance("X.509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
			System.out.println(c.toString());

			System.out.println("Version:"+c.getVersion());
			System.out.println("Algoritmos:"+c.getSigAlgName());
			System.out.println("Nº Serie:"+c.getSerialNumber());
			System.out.println("Propietario:"+c.getSubjectDN());
			System.out.println("Emisor:"+c.getIssuerDN());
			System.out.println("Valido desde: "+c.getNotBefore()+"hasta: "+c.getNotAfter());	
			
			
		InputStreamReader Flujo2 = new InputStreamReader(System.in);
		BufferedReader teclado2 = new BufferedReader(Flujo2);
		System.out.println("Introducir Nombre feichero certificados revocados (*.pem)");
		String NameFile2=teclado2.readLine();
		System.out.println("Leido Nombre fichero clr:"+NameFile2);
		
		FileInputStream inStream= new FileInputStream(NameFile2);
		
		CertificateFactory cf1 = CertificateFactory.getInstance("X509");
		X509CRL crl = (X509CRL)cf1.generateCRL(inStream);
		
		inStream.close();
		
		System.out.println("\n");
		
		if(crl.isRevoked(c)){
			System.out.println("Certificado Revocado");
		}else{
			System.out.println("Certificado NO revocado");
		}
		
		} catch (Exception e) {
			e.printStackTrace( );
		}
	}

}
