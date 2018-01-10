import java.io.*;
import java.security.PublicKey;
import java.security.cert.*;
public class PrintCert {
	public static void main(String args[]) {

	
			try {
				InputStreamReader Flujo = new InputStreamReader(System.in);
				BufferedReader teclado = new BufferedReader(Flujo);
				
				System.out.print("Introducir Nombre del sitio web: " );
				String nombreSitioWeb=teclado.readLine();
				nombreSitioWeb="CN=" + nombreSitioWeb;

				String[] nombres_certificados = new String[4];
				nombres_certificados[0]="01.crt";
				nombres_certificados[1]="02.crt";
				nombres_certificados[2]="03.crt";
				nombres_certificados[3]="04.crt";
				
				
				for(int i=0;i<4;i++){
					FileInputStream fr4 = new FileInputStream(nombres_certificados[i]);
					CertificateFactory cf4 = CertificateFactory.getInstance("X509");
					X509Certificate c4 = (X509Certificate) cf4.generateCertificate(fr4);
					
					System.out.println("Leido Nombre fichero CA:"+nombres_certificados[i]);
					
					CertificateFactory cf = CertificateFactory.getInstance("X509");
					
					FileInputStream fr = new FileInputStream(nombres_certificados[i]);
					X509Certificate c = (X509Certificate) cf.generateCertificate(fr);
					System.out.println(c.toString());

					System.out.println("Version:"+c.getVersion());
					System.out.println("Algoritmos:"+c.getSigAlgName());
					System.out.println("Nº Serie:"+c.getSerialNumber());
					System.out.println("Propietario:"+c.getSubjectDN());
					System.out.println("Emisor:"+c.getIssuerDN());
					System.out.println("Valido desde: "+c.getNotBefore()+"hasta: "+c.getNotAfter());
	
					fr4.close();
							
					int resultado2 = c4.getSubjectDN().toString().indexOf(nombreSitioWeb);

					if(resultado2>0){
						System.out.println("Certificado recibido protege sitio Web");
					}else{
						System.out.println("Certificado recibido NO protege sitio Web!!");
					}
				}

			} catch (Exception e) {
				e.printStackTrace( );
			}		
	}//main
}//class



