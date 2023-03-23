
package es.meh.catastro;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.3.2
 * Generated source version: 2.1
 * 
 */
@WebService(name = "IOVCConsulta", targetNamespace = "http://www.catastro.meh.es/")
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
@XmlSeeAlso({
    com.microsoft.schemas._2003._10.serialization.ObjectFactory.class,
    es.meh.catastro.ObjectFactory.class
})
public interface IOVCConsulta {


    /**
     * 
     * @param consultaBIIn
     * @return
     *     returns es.meh.catastro.ConsultaOut
     * @throws IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage
     */
    @WebMethod(operationName = "ObtenerConsulta", action = "http://www.catastro.meh.es/IOVCConsulta/ObtenerConsulta")
    @WebResult(name = "Consulta_Out", targetNamespace = "http://www.catastro.meh.es/", partName = "Consulta_Out")
    public ConsultaOut obtenerConsulta(
        @WebParam(name = "ConsultaBI_In", targetNamespace = "http://www.catastro.meh.es/", partName = "ConsultaBI_In")
        ConsultaBIIn consultaBIIn)
        throws IOVCConsultaObtenerConsultaErrorFaultFaultFaultMessage
    ;

    /**
     * 
     * @param consultaBIIn
     * @return
     *     returns es.meh.catastro.ConsultaOut
     * @throws IOVCConsultaObtenerConsultaVdRErrorFaultFaultFaultMessage
     */
    @WebMethod(operationName = "ObtenerConsultaVdR", action = "http://www.catastro.meh.es/IOVCConsulta/ObtenerConsultaVdR")
    @WebResult(name = "Consulta_Out", targetNamespace = "http://www.catastro.meh.es/", partName = "Consulta_Out")
    public ConsultaOut obtenerConsultaVdR(
        @WebParam(name = "ConsultaBI_In", targetNamespace = "http://www.catastro.meh.es/", partName = "ConsultaBI_In")
        ConsultaBIIn consultaBIIn)
        throws IOVCConsultaObtenerConsultaVdRErrorFaultFaultFaultMessage
    ;

}
