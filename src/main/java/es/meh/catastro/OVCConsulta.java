
package es.meh.catastro;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.10
 * Generated source version: 2.1
 * 
 */
@WebServiceClient(name = "OVCConsulta", targetNamespace = "http://www.catastro.meh.es/", wsdlLocation = "file:/D:/eclipse-workspace/consulta-catastro-ws-cliente/src/main/resources/wsdl/OVCConsulta.wsdl")
public class OVCConsulta
    extends Service
{

    private final static URL OVCCONSULTA_WSDL_LOCATION;
    private final static WebServiceException OVCCONSULTA_EXCEPTION;
    private final static QName OVCCONSULTA_QNAME = new QName("http://www.catastro.meh.es/", "OVCConsulta");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/eclipse-workspace/consulta-catastro-ws-cliente/src/main/resources/wsdl/OVCConsulta.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        OVCCONSULTA_WSDL_LOCATION = url;
        OVCCONSULTA_EXCEPTION = e;
    }

    public OVCConsulta() {
        super(__getWsdlLocation(), OVCCONSULTA_QNAME);
    }

    public OVCConsulta(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    /**
     * 
     * @return
     *     returns IOVCConsulta
     */
    @WebEndpoint(name = "BasicHttpBinding_IOVCConsulta")
    public IOVCConsulta getBasicHttpBindingIOVCConsulta() {
        return super.getPort(new QName("http://www.catastro.meh.es/", "BasicHttpBinding_IOVCConsulta"), IOVCConsulta.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IOVCConsulta
     */
    @WebEndpoint(name = "BasicHttpBinding_IOVCConsulta")
    public IOVCConsulta getBasicHttpBindingIOVCConsulta(WebServiceFeature... features) {
        return super.getPort(new QName("http://www.catastro.meh.es/", "BasicHttpBinding_IOVCConsulta"), IOVCConsulta.class, features);
    }

    private static URL __getWsdlLocation() {
        if (OVCCONSULTA_EXCEPTION!= null) {
            throw OVCCONSULTA_EXCEPTION;
        }
        return OVCCONSULTA_WSDL_LOCATION;
    }

}
