
package es.meh.catastro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the es.meh.catastro package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Atributos_QNAME = new QName("http://www.catastro.meh.es/", "Atributos");
    private final static QName _Estado_QNAME = new QName("http://www.catastro.meh.es/", "Estado");
    private final static QName _AtributosIDPeticion_QNAME = new QName("http://www.catastro.meh.es/", "IDPeticion");
    private final static QName _AtributosTimeStamp_QNAME = new QName("http://www.catastro.meh.es/", "TimeStamp");
    private final static QName _EstadoCodigoEstado_QNAME = new QName("http://www.catastro.meh.es/", "CodigoEstado");
    private final static QName _EstadoLiteralError_QNAME = new QName("http://www.catastro.meh.es/", "LiteralError");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: es.meh.catastro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ConsultaOut }
     * 
     */
    public ConsultaOut createConsultaOut() {
        return new ConsultaOut();
    }

    /**
     * Create an instance of {@link Atributos }
     * 
     */
    public Atributos createAtributos() {
        return new Atributos();
    }

    /**
     * Create an instance of {@link ConsultaBIIn }
     * 
     */
    public ConsultaBIIn createConsultaBIIn() {
        return new ConsultaBIIn();
    }

    /**
     * Create an instance of {@link Estado }
     * 
     */
    public Estado createEstado() {
        return new Estado();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Atributos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "Atributos")
    public JAXBElement<Atributos> createAtributos(Atributos value) {
        return new JAXBElement<Atributos>(_Atributos_QNAME, Atributos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Estado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "Estado")
    public JAXBElement<Estado> createEstado(Estado value) {
        return new JAXBElement<Estado>(_Estado_QNAME, Estado.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "IDPeticion", scope = Atributos.class)
    public JAXBElement<String> createAtributosIDPeticion(String value) {
        return new JAXBElement<String>(_AtributosIDPeticion_QNAME, String.class, Atributos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "TimeStamp", scope = Atributos.class)
    public JAXBElement<String> createAtributosTimeStamp(String value) {
        return new JAXBElement<String>(_AtributosTimeStamp_QNAME, String.class, Atributos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Estado }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "Estado", scope = Atributos.class)
    public JAXBElement<Estado> createAtributosEstado(Estado value) {
        return new JAXBElement<Estado>(_Estado_QNAME, Estado.class, Atributos.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "CodigoEstado", scope = Estado.class)
    public JAXBElement<String> createEstadoCodigoEstado(String value) {
        return new JAXBElement<String>(_EstadoCodigoEstado_QNAME, String.class, Estado.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.catastro.meh.es/", name = "LiteralError", scope = Estado.class)
    public JAXBElement<String> createEstadoLiteralError(String value) {
        return new JAXBElement<String>(_EstadoLiteralError_QNAME, String.class, Estado.class, value);
    }

}
