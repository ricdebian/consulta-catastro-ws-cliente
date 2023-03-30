package es.meh.catastro.lanzador;

import es.meh.catastro.exception.CatastroException;

public class PeticCatastroVo {

	private static final String VALIDA_RC = "[0-9A-Z]{7,7}";
	private static final String VALIDA_CARGO = "[0-9]{4,4}";
	
	private String asiento; 
	private String fecha;
	private String rc1;
	private String rc2; 
	private String cargo;

	/**
	 * Constructor por atributos.
	 * 
	 * @param asiento
	 * @param fecha
	 * @param rc1
	 * @param rc2
	 * @param cargo
	 */
	public PeticCatastroVo(final String asiento, final String fecha, final String rc1, final String rc2, final String cargo) {
		super();
		this.asiento = asiento;
		this.fecha = fecha;
		this.rc1 = rc1;
		this.rc2 = rc2;
		this.cargo = cargo;
	}
	
	/**
	 * Constructor por línea de datos.
	 * 
	 * La línea tiene el formato: 0000003968201201161379101EE7217N0016
	 * 
	 *   asiento, tamaño (10), posiciones (1-10)
	 *   fecha, tamaño (8) posiciones (11-18)
	 *   rc1, tamaño (7) posiciones (19-25)
	 *   rc2, tamaño (7) posiciones (26-32)
	 *   cargo, tamaño (4) posiciones (33-36)
	 *   
	 * @param linea
	 * 
	 * @throws CatastroException 
	 */
	public PeticCatastroVo(final String linea) throws CatastroException {
		super();

		int tamLinea;
		if (linea==null){
			throw new CatastroException("Línea '"+linea+"' de longitud cero");
		} else {
			
			tamLinea = linea.replaceAll("\\s+$","").length(); 
			if (tamLinea!=36 && tamLinea!=32){
				throw new CatastroException("L�nea '"+linea+"' de longitud inadeacuada");
			}
		}
		
		if (tamLinea==36){
			final String cargo = linea.substring(32, 36);
			final String rc1 = linea.substring(18, 25);
			final String rc2 = linea.substring(25, 32);
			if (!cargo.matches(VALIDA_CARGO)){
				throw new CatastroException("Línea '"+linea+"' cargo '"+cargo+"' no numérico");
			}
			if (!rc1.matches(VALIDA_RC)){
				throw new CatastroException("Línea '"+linea+"' rc1 '"+rc1+"' no válido");
			}
			if (!rc2.matches(VALIDA_RC)){
				throw new CatastroException("Línea '"+linea+"' rc2 '"+rc2+"' no válido");
			}
		}
		
		if (tamLinea==32){
			final String rc1 = linea.substring(18, 25);
			final String rc2 = linea.substring(25, 32);
			if (!rc1.matches(VALIDA_RC)){
				throw new CatastroException("Línea '"+linea+"' rc1 '"+rc1+"' no válido");
			}
			if (!rc2.matches(VALIDA_RC)){
				throw new CatastroException("Línea '"+linea+"' rc2 '"+rc2+"' no válido");
			}
		}
		
		final StringBuilder sbFecha = new StringBuilder();
		sbFecha.append(linea.substring(10, 14));
		sbFecha.append("-");
		sbFecha.append(linea.substring(14, 16));
		sbFecha.append("-");
		sbFecha.append(linea.substring(16, 18));
		this.fecha = sbFecha.toString();
		this.asiento = linea.substring(0, 10);
				
		if (tamLinea==36){
			this.rc1 = linea.substring(18, 25);
			this.rc2 = linea.substring(25, 32);
			this.cargo = linea.substring(32, 36);
		} else { //Sin cargo
			this.rc1 = linea.substring(18, 25);
			this.rc2 = linea.substring(25, 32);
		}
	}

	public String getAsiento() {
		return asiento;
	}
	
	public String getFecha() {
		return fecha;
	}
	
	public String getRc1() {
		return rc1;
	}
	
	public String getRc2() {
		return rc2;
	}
	
	public String getCargo() {
		return cargo;
	}
}
