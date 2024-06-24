package com.zimji.auth.entity.custom;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zimji.auth.utils.Constants;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;

import java.util.Date;

@Setter
@Getter
@NoArgsConstructor
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
    Date createDate;

    @LastModifiedBy
    String updateBy;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(
            shape = JsonFormat.Shape.STRING,
            pattern = "yyyy-MM-dd'T'HH:mm:ssZ",
            timezone = "Asia/Ho_Chi_Minh"
    )
    Date modifiedDate;

    // Long companyId;

    @Version
    Long version;

    Integer status;

    @PrePersist
    void setInitialDate() {
        createDate = modifiedDate = new Date();
        createBy = (createBy == null) ? Constants.SYSTEM : createBy;
        updateBy = (updateBy == null) ? Constants.SYSTEM : updateBy;
        status = (status == null) ? Constants.STATUS.ACTIVE.getValue() : status;
    }

    @PreUpdate
    void updateDate() {
        modifiedDate = new Date();
    }

}
