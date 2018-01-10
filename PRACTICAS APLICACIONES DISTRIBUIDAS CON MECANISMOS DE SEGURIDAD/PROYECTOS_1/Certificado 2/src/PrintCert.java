import java.io.*;
import java.security.PublicKey;
import java.security.cert.*;
public class PrintCert {
	public static void main(String args[]) {


		try {
			//03.crt protege BANCO_INDUSTRIAL
			InputStreamReader Flujo = new InputStreamReader(System.in);
			BufferedReader teclado = new BufferedReader(Flujo);

			System.out.print("Introducir Nombre del sitio web: " );
			String nombreSitioWeb=teclado.readLine();
			nombreSitioWeb="CN=" + nombreSitioWeb;

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

			//Encontrar el campo common Name	
			int resultado = c.getSubjectDN().toString().indexOf(nombreSitioWeb);

			if(resultado>0){
				System.out.println("Certificado recibido protege sitio Web");
			}else{
				System.out.println("Certificado recibido NO protege sitio Web!!");
			}

		} catch (Exception e) {
			e.printStackTrace( );
		}		
	}
}



