package mx.gob.sat.catalogo.controller;

import mx.gob.sat.catalogo.util.consts.UrlConst;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class:</b> ApiBaseController.java <br>
 * <b>Description:</b>
 * <p> Controlador base que expone la url base del microservicio. </p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx

 * @created 17 de febrero del 2025
 * @version 1.0
 * @category Controlador
 */
@RestController
@RequestMapping(value = UrlConst.URL_BASE)
public class ApiBaseController {
}
