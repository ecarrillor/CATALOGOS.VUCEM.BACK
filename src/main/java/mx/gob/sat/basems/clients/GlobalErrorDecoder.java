package mx.gob.sat.basems.clients;

import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * Stub local de GlobalErrorDecoder. Reemplazar con libreria base-ms en CI/CD.
 * Decodificador de errores HTTP para clientes Feign.
 */
public class GlobalErrorDecoder implements ErrorDecoder {

    @Override
    public Exception decode(final String methodKey, final Response response) {
        return new RuntimeException("Error HTTP " + response.status() + " en: " + methodKey);
    }
}
