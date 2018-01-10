

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.security.PublicKey;
import java.security.cert.CertificateFactory;
import java.security.cert.X509CRL;
import java.security.cert.X509CRLEntry;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

public class Revocacion4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try{
			InputStreamReader Flujo = new InputStreamReader(System.in);

			BufferedReader teclado = new BufferedReader(Flujo);
			System.out.print("Introducir Nombre Certificado: " );
			String NameFile=teclado.readLine();
			System.out.println("Nombre fichero:"+NameFile );
			FileInputStream fr = new FileInputStream(NameFile);



			CertificateFactory cf = CertificateFactory.getInstance("X509");
			X509Certificate c = (X509Certificate) cf.generateCertificate(fr);

			System.out.println("Certificado Leido \n");



			//lectura certificado CA
			InputStreamReader Flujo3 = new InputStreamReader(System.in);
			BufferedReader teclado3 = new BufferedReader(Flujo3);
			System.out.print("Introducir Nombre Certificado CA(*.pem): " );
			String NameFile3=teclado3.readLine();
			System.out.println("Leido nombre fichero CA:"+NameFile3 );


			FileInputStream fr3 = new FileInputStream(NameFile3);

			CertificateFactory cf3 = CertificateFactory.getInstance("X509");
			X509Certificate c3 = (X509Certificate) cf3.generateCertificate(fr3);

			PublicKey publicKeyCA=c3.getPublicKey();
			fr3.close();
			c.verify(publicKeyCA);

			//lectura del fichero crl.pem

			InputStreamReader Flujo2 = new InputStreamReader(System.in);
			BufferedReader teclado2 = new BufferedReader(Flujo2);
			System.out.print("Introducir Nombre fichero certificados revocados(*.pem): " );
			String NameFile2=teclado2.readLine();
			System.out.println("Leido nombre fichero crl:"+NameFile2);

			FileInputStream inStream = new FileInputStream(NameFile2);

			CertificateFactory cf1 = CertificateFactory.getInstance("X.509");

			X509CRL crl = (X509CRL) cf1.generateCRL(inStream);

			//Imprime los campos del certificado en forma de string

			inStream.close();

			FileWriter fichSalida = new FileWriter("log.txt");
			BufferedWriter fs= new BufferedWriter(fichSalida);

			if(crl.isRevoked(c)){
				System.out.println("Certificado "+NameFile+" Revocado");
				fs.write("\n Certificado "+NameFile+" Revocado");
			}else{
				System.out.println("Certificado "+NameFile+" NO revocado");
				fs.write("\n Certificado "+NameFile+" NO revocado");
			}	
			crl.verify(publicKeyCA);						

			System.out.println("\n Fichero CRL "+NameFile2+" HA SIDO FIRMADO POR LA CA");
			fs.write("\n Fichero CRL "+NameFile2+" HA SIDO FIRMADO POR LA CA");
			
			Set setEntries=crl.getRevokedCertificates();
			if(setEntries!=null && setEntries.isEmpty()==false){
				for(Iterator iterator = setEntries.iterator(); iterator.hasNext();){
					X509CRLEntry x509crlentry = (X509CRLEntry) iterator.next();
					System.out.println("serial number="+x509crlentry.getSerialNumber());
					System.out.println("revocacion date="+x509crlentry.getRevocationDate());
					System.out.println("extensions="+x509crlentry.hasExtensions());
				}
			}		

			fs.close();

		}catch (Exception e) {
			e.printStackTrace( );
		}
	}
}
