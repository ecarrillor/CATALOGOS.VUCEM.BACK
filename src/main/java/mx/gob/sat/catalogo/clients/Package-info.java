/**
 * <b>Class:</b> Package-info.java <br>
 * <b>Description:</b>
 * <p> Este paquete contiene las clases que representan el consumo de los microservicios.
 *     <br/>
 *      1.-Representa la comunicación con el microservicio APIRest.
 *         Utiliza el sufijo <b>Client</b>.
 *         Ejemplo: <b>CatalogoClient</b>.
 *         Anotación: @FeignClient
 * 
 *      2.-Representa la interfaz de servicio externo.
 *         Utiliza el prefijo <b>MS</b> y el sufijo <b>Service</b>.
 *         Ejemplo: <b>MSCatalogoService</b>.
 * 
 *      3.-Representa el servicio externo de Implementación
 *         Utiliza el sufijo <b>ServiceImpl</b>.
 *         Ejemplo: <b>CatalogoServiceImpl</b>.
 *         Anotación: @Service
 *         Para el manejo de los logs se utiliza la anotación @Slf4j.
 * </p>
 * 
 * <hr/>
 * <b>Convención de documentación para clases Client:</b><br/>
 * Las interfaz de consumo de microservicio deben seguir el siguiente formato de documentación:
 *
 * {
 * /**
 *  * <b>Class:</b> <Nombre>Client.java <br>
 *  * <b>Description:</b>
 *  * <p>
 *  * Interfaz que permite consumir los servicios del microservicio <ms-catalogo>.
 *  * </p>
 *  *
 *  * @author <Nombre completo>
 *  * @email <Correo institucional del autor>
 *  *
 *  * @created <día de mes del año>
 *  * @version 1.0
 *  * @category Interfaz de consumo de microservicio
 *  *\
 * }
 * <hr/>
 * 
 * <hr/>
 * <b>Convención de documentación para clases MS<>Service:</b><br/>
 * Las interfaz de servicio deben seguir el siguiente formato de documentación:
 *
 * {
 * /**
 *  * <b>Class:</b> MS<Nombre>Service.java <br>
 *  * <b>Description:</b>
 *  * <p>
 *  * Interfaz que permite accesar a los servicios del microservicio <ms-catalogo>.
 *  * </p>
 *  *
 *  * @author <Nombre completo>
 *  * @email <Correo institucional del autor>
 *  *
 *  * @created <día de mes del año>
 *  * @version 1.0
 *  * @category Interfaz de consumo de microservicio
 *  *\
 * }
 * <hr/>
 * 
 * 
 * <hr/>
 * <b>Convención de documentación para clases MS<>ServiceImpl:</b><br/>
 * Los servicios de implementación deben seguir el siguiente formato de documentación:
 *
 * {
 * /**
 *  * <b>Class:</b> MS<Nombre>ServiceImpl.java <br>
 *  * <b>Description:</b>
 *  * <p>
 *  * Clase que implementa los servicios de la intefaz {@code MS<Nombre>Service}.
 *  * </p>
 *  *
 *  * @author <Nombre completo>
 *  * @email <Correo institucional del autor>
 *  *
 *  * @created <día de mes del año>
 *  * @version 1.0
 *  * @category Servicio de Implementación
 *  *\
 * }
 * <hr/>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 4 de julio del 2025
 * @version 1.0
 * @category Informativo
 */
package mx.gob.sat.catalogo.clients;
