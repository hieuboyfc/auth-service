package com.zimji.auth.entity.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zimji.auth.utils.Constants;
import com.zimji.auth.utils.MapperUtils;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@MappedSuperclass
@FieldDefaults(level = AccessLevel.PRIVATE)
public abstract class PersistableEntity<ID> extends BaseEntity<ID> {

    @CreatedBy
    String createBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "Asia/Ho_Chi_Minh"
    )
    @CreatedDate
    Date createDate;

    @LastModifiedBy
    String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "Asia/Ho_Chi_Minh"
    )
    @LastModifiedDate
    Date modifiedDate;

    // Long companyId;

    @Version
    Long version;

    Integer status;

    @PrePersist
    void prePersist() {
        createDate = modifiedDate = new Date();
        createBy = (createBy == null) ? Constants.SYSTEM : createBy;
        updateBy = (updateBy == null) ? Constants.SYSTEM : updateBy;
        status = (status == null) ? Constants.STATUS.ACTIVE.getValue() : status;
    }

    @PreUpdate
    @PostLoad
    @Transactional
    void preUpdate() {
        modifiedDate = new Date();
    }

    public <DTO> DTO toDTO(Class<DTO> dto) {
        return MapperUtils.map(this, dto);
    }

}
