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

    @Override
    public T save(Object body) {
        T entity = mapper.convertValue(body, getEntityClass());
        return getRepository().save(entity);
    }

    /*@Override
    public Optional<T> findById(ID id) {
        return getRepository().findById(id);
    }

    @Override
    public T update(ID id, Object body) throws JsonMappingException {

        T existing = getRepository().findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        T updated = mapper.convertValue(body, getEntityClass());

        // Mantener el ID original
        mapper.updateValue(existing, updated);

        return getRepository().save(existing);
    }*/

}
