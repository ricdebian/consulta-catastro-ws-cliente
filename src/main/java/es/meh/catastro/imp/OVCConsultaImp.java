package es.meh.catastro.imp;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;


import es.meh.catastro.ConsultaBIIn;
import es.meh.catastro.ConsultaOut;
import es.meh.catastro.IOVCConsulta;
import es.meh.catastro.IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage;
import es.meh.catastro.IOVCConsultaObtenerConsultaVdRErrorFaultFaultFaultMessage;

public class OVCConsultaImp extends WebServiceGatewaySupport implements IOVCConsulta{

	@Override
	public ConsultaOut obtenerConsulta(ConsultaBIIn consultaBIIn)
			throws IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage {
		// TODO Auto-generated method stub
		ConsultaOut response = (ConsultaOut) getWebServiceTemplate().marshalSendAndReceive(consultaBIIn);
		return response;
	}

	@Override
	public ConsultaOut obtenerConsultaVdR(ConsultaBIIn consultaBIIn)
			throws IOVCConsultaObtenerConsultaVdRErrorFaultFaultFaultMessage {
		// TODO Auto-generated method stub
		ConsultaOut response = (ConsultaOut) getWebServiceTemplate().marshalSendAndReceive(consultaBIIn);
		return response;
	}


}
