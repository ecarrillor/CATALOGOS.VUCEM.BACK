package com.example.vucem_catalogos_service.business;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Transactional
public abstract class AbstractCatalogService<T, ID>
        implements CatalogService<T, ID> {

    @Autowired
    protected ObjectMapper mapper;

    protected abstract JpaRepository<T, ID> getRepository();

    protected abstract Class<ID> getIdClass();

    @Override
    public T save(Object body) {
        T entity = mapper.convertValue(body, getEntityClass());
        return getRepository().save(entity);
    }

    @Override
    public Optional<T> findById(String id) {
        ID convertedId = convertId(id);
        return getRepository().findById(convertedId);
    }

    @Override
    public T update(String id, Object body) throws JsonMappingException {

        ID convertedId = convertId(id);

        T existing = getRepository().findById(convertedId)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        // Actualiza SOLO los campos que vienen en el body
        mapper.updateValue(existing, body);

        return getRepository().save(existing);
    }





    @SuppressWarnings("unchecked")
    private ID convertId(String id) {

        Class<ID> idClass = getIdClass();

        if (idClass.equals(Long.class)) {
            return (ID) Long.valueOf(id);
        } else if (idClass.equals(Integer.class)) {
            return (ID) Integer.valueOf(id);
        } else if (idClass.equals(String.class)) {
            return (ID) id;
        }

        throw new IllegalArgumentException("Tipo de ID no soportado");
    }

}
