package es.meh.catastro.impl;


import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import es.meh.catastro.ConsultaBIIn;
import es.meh.catastro.ConsultaOut;
import es.meh.catastro.IOVCConsulta;
import es.meh.catastro.IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage;




 // TODO: Auto-generated Javadoc
/**
  * The Class IOVCConsultaImpl.
  */
 class IOVCConsultaImpl extends WebServiceGatewaySupport implements IOVCConsulta {


	@Override
	public ConsultaOut obtenerConsulta(ConsultaBIIn consultaBIIn)
			throws IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage {
		ConsultaOut consultaOut = (ConsultaOut) getWebServiceTemplate().marshalSendAndReceive(consultaBIIn);
	
		return consultaOut;
	}


	
	

}
