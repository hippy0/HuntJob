package dev.hippy.huntjob.mapper;

import dev.hippy.huntjob.model.BaseEntity;
import jakarta.persistence.EntityManager;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.TargetType;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(
    componentModel = ComponentModel.SPRING
)
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class ReferenceMap {

    @Autowired
    EntityManager entityManager;

    public <T extends BaseEntity> T toEntity(Long id, @TargetType Class<T> entityClass) {
        return id != null ? entityManager.find(entityClass, id) : null;
    }
}
