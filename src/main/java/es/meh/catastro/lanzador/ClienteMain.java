package es.meh.catastro.lanzador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.io.InputStream;
//import java.util.Properties;

//import org.apache.log4j.chainsaw.Main;

import org.apache.log4j.Level;

//import javax.xml.rpc.ServiceException;

import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.RollingFileAppender;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//import org.springframework.ws.soap.client.SoapFaultClientException;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

//import es.ine.sgtic.catastro.app.AppConsultaCatastro;
//import es.ine.sgtic.catastro.negocio.GestorPeticionesCatastro;

//import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import es.meh.catastro.exception.CatastroException;

import es.meh.catastro.ConsultaBIIn;
import es.meh.catastro.ConsultaOut;
import es.meh.catastro.IOVCConsulta;
import es.meh.catastro.IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage;

//import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
//import org.jasypt.encryption.pbe.config.EnvironmentStringPBEConfig;
// TODO: Auto-generated Javadoc
/**
 * The Class ClienteMain.
 */
public class ClienteMain {
	private static Logger logger;

	private static String fich_peticiones;
	private static String fich_error;
	private static String fich_comunes;
	private static String fich_titulares;
	private static String fich_construcciones;
	private static String fich_padron;

	private static ApplicationContext ctx;

	/*
	 * private static final String TS_FILE = "C:/hipotecas/ws-catastro-ts.jks";
	 * private static final String KS_FILE = "C:/hipotecas/ws-catastro-ks.p12";
	 */
	public static void main(String args[]) throws/* ServiceException, */IOException, CatastroException,
			IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage {

		// Verificamos que se reciba como parámetro el fichero a tratar
		if (args.length == 0) {
			System.out.println("ERROR. Es obligatorio el parámetro de entrada con el path del fichero a tratar.");
			System.exit(1);
		}

		// Verificamos la existencia del fichero de entrada
		final String fichPetic = args[0];
		fich_peticiones = fichPetic;

		final File fichDatos = new File(fichPetic);
		if (!fichDatos.exists()) {
			System.out.println(String.format("ERROR. El fichero recibido como parámetro [%s] no existe.", fichPetic));
			System.exit(2);
		}
		if (fichDatos.exists() && fichDatos.isDirectory()) {
			System.out.println(
					String.format("ERROR. El fichero recibido como parámetro [%s] es un directorio.", fichPetic));
			System.exit(3);
		}

		// Obtenemos el path del fichero a tratar
		final int p = fichPetic.lastIndexOf(File.separator);
		final String pathFichero = fichPetic.substring(0, p + 1);

		// Obtenemos el nombre del fichero a tratar
		final String nombreFichero = fichPetic.substring(p + 1);

		// Definimos el path de los ficheros de salida
		final String fichLog = pathFichero + nombreFichero + ".log";
		// final String fichErr = pathFichero + nombreFichero + ".error";
		// final String
		fich_error = pathFichero + nombreFichero + ".error";
		// final String
		fich_comunes = pathFichero + nombreFichero + ".comun";
		// final String
		fich_titulares = pathFichero + nombreFichero + ".titular";
		// final String
		fich_construcciones = pathFichero + nombreFichero + ".construccion";
		// final String
		fich_padron = pathFichero + nombreFichero + ".padron";

		// Ajustamos el nivel de log
		final Logger logger = ajustaLog(fichLog);

		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println(
				"Iniciando el procesamiento del fichero de referencias catastrales (RC), se van a generar los ficheros:");
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		System.out.println(
				String.format("IBI.     Datos del IBI de las RC procesadas correctamente. Fichero [%s]", fich_comunes));
		System.out.println(String.format("TITULAR. Datos del TITULAR de las RC procesadas correctamente. Fichero [%s]",
				fich_titulares));
		System.out.println(String.format("CONTRUC. Datos del CONTRUC de las RC procesadas correctamente. Fichero [%s]",
				fich_construcciones));
		System.out.println(String.format("PADRON.  Datos del PADRON de las RC procesadas correctamente. Fichero [%s]",
				fich_padron));
		System.out.println(String.format("ERRORES. RC procesadas con error. Fichero [%s]", fich_error));
		System.out.println(String.format("LOG.     Trazas de log. Fichero [%s]", fichLog));
		System.out.println(
				"------------------------------------------------------------------------------------------------------");
		// System.out.println(" Antes de Cargar almacenes de certificados");
		// Inicializamos hanshaking SSL
		// UtilSSL.initSSL();

		// System.out.println("Cargados almacenes de certificados");

		// Procesamos el fichero de peticiones

		procesaFicheroPeticiones();
	}

	public static void procesaFicheroPeticiones()
			throws IOException, CatastroException, IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage {

		// Para no poner la password en claro en el código.
		// declaramos objeto que cifra
		 
		/*
		 * StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
		 * 
		 * // //Declaramos objeto donde se define la // configuración para el objeto que
		 * cifra
		 * 
		 * EnvironmentStringPBEConfig pbeconfig = new EnvironmentStringPBEConfig();
		 * 
		 * // Se asigna el algoritmo con el que está cifrada la password en la variable
		 * // de entorno
		 * 
		 * //pbeconfig.setAlgorithm("PBEWITHHMACSHA1ANDAES_256");
		 * 
		 * // Se declara como se llama la variable de entorno donde se leerá la password
		 * // maestra para descifrar la clave del certificado
		 * 
		 * 
		 * pbeconfig.setPasswordEnvName("APP_ENCRYPTION_PASSWORD"); //se añade la
		 * System.out.println( pbeconfig.getPassword()); // configuración al objeto
		 * cifrador encryptor.setConfig(pbeconfig);
		 * 
		 * // cargo fichero de propiedades donde está la clave cifrada del certificado
		 * // "claves.properties"
		 * 
		 * Properties prop = new Properties();
		 * 
		 * ClassLoader loader = Main.class.getClassLoader(); InputStream stream =
		 * loader.getResourceAsStream("cliente-ws.properties"); prop.load(stream);
		 * 
		 * // Recupero valor de la propeidad passenc, donde está el valor cifrado.
		 * String claveenc = prop.getProperty("cliente.ws.passwdalmacen-ks");
		 * 
		 * // Descifro el hash que contiene la clave cifrada y se guarda en un string
		 * 
		 * System.out.println("1-"+pbeconfig.getPassword());
		 * System.out.println("2-"+claveenc);
		 * 
		 * 
		 * 
		 * // System.out.println(encryptor.decrypt(claveenc));
		 * 
		 * String passwd = encryptor.decrypt(claveenc); System.out.println(passwd);
		 */
		/*
		 * System.out.println(passwd); System.setProperty("javax.net.ssl.trustStore",
		 * TS_FILE); System.setProperty("javax.net.ssl.trustStorePassword", "ineine");
		 * System.setProperty("javax.net.ssl.trustStoreType", "jks");
		 * 
		 * // Ajustamos las propiedades del sistema para keystore //
		 * System.setProperty("javax.net.ssl.keyStore", pathKS);
		 * System.setProperty("javax.net.ssl.keyStore", KS_FILE);//ws-catastro-ks.p12
		 * System.setProperty("javax.net.ssl.keyStorePassword", passwd);
		 * System.setProperty("javax.net.ssl.keyStoreType", "pkcs12");
		 */
		 
		
		
		ctx = new ClassPathXmlApplicationContext("application-context.xml");

		IOVCConsulta client = (IOVCConsulta) ctx.getBean("servicio");


		Wss4jSecurityInterceptor wss = (Wss4jSecurityInterceptor) ctx.getBean("wsSecurityInterceptorRespuesta");
		// Asignamos los ficheros de trabajo

		// int lineaTratada = 1;
		try {
			// Iniciamos la lectura del fichero recibido por parÃ¡metro
			final File f = new File(fich_peticiones);
			final FileReader fr = new FileReader(f);
			final BufferedReader bfr = new BufferedReader(fr);
			String lineaPetic = bfr.readLine();

		//	ConsultaBIIn cbi = new ConsultaBIIn();// petición request
			ConsultaOut cout = new ConsultaOut();// Respuesta response
			// ArrayList<ConsultaOut> cout = new ArrayList<ConsultaOut>();
			while (lineaPetic != null) {

				// Parseamos la línea de peticion
				try {
					PeticCatastroVo petic = new PeticCatastroVo(lineaPetic);
					wss.setSecurementEncryptionParts(
							"{Content}{http://es/meh/catastro}ConsultaBIIn");
					final ConsultaBIIn pet = new ConsultaBIIn();
				    
					// System.out.println("Parámetro id: "+args[1]);
					pet.getContent().add(petic);
					/*
					 * * <control> <TimeStamp>24/04/2013 10:20:13</TimeStamp> </control>
					 * <solicitante> <nif>02871080J</nif> <finalidad>FINALIDAD</finalidad>
					 * </solicitante> <lden> <den> <idbil> <fecha>20120130</fecha> <rcl>
					 * <pc1>3823108</pc1> <pc2>NG1632S</pc2> </rcl> </idbil> </den> </lden>
					 */
					// Cargo el objeto petición

					//cbi.getContent().add(petic);

					cout = client.obtenerConsulta(pet);

					System.out.println(cout.getContent().toString());

				} catch (CatastroException e) {
					addLineaFichError(lineaPetic.substring(0, 10), e.getMessage());
				} finally {
					// Cargamos la siguiente lÃ­nea
					lineaPetic = bfr.readLine();
					/// lineaTratada++;
				}
			}
			fr.close();
			bfr.close();

		} catch (FileNotFoundException e) {

			System.out.println("Error al abrir el fichero");

		}

	}

	private static void addLineaFichError(final String asiento, final String mensaje) {

		final String linea = new StringBuilder("Asiento [").append(asiento).append("]. ").append(mensaje).toString();

		try {

			// Abrimos el fichero
			final File fich = new File(fich_error);

			// final FileWriter fw = new FileWriter(fichero,true);
			final OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream(fich, true), "ISO-8859-1");
			final BufferedWriter bfw = new BufferedWriter(osw);

			// Metemos la línea
			bfw.write(linea + "\n");
			bfw.flush();

			// Cerramos el fichero de salida
			bfw.close();
			osw.close();

		} catch (Exception e) {
			// logger.error(String.format("Error añadiendo línea [%s] en fichero [%s]",
			// linea, fich_error));
			System.out.printf("Error añadiendo línea [%s] en fichero [%s]", linea, fich_error);
		}
	}

	private static Logger ajustaLog(final String fichLog) throws IOException {

		final Logger logger = Logger.getLogger(ClienteMain.class);

		// Definimos la plantilla de la traza
		final PatternLayout layout = new PatternLayout(
				"[%d{dd-MM-yyyy HH\\:mm\\:ss,SSS}] thread\\:[%t] nivelTraza\\:[%p] lugarTraza\\:[%c] mensajeTraza[%m]%n");

		// Definimos el appender
		final RollingFileAppender appender = new RollingFileAppender(layout, fichLog, true);
		appender.setMaxBackupIndex(10);
		appender.setMaxFileSize("99999KB");
		logger.addAppender(appender);

		// Definimos el nivel de traza
		logger.setLevel(Level.DEBUG);

		return logger;
	}



}