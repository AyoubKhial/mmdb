package com.movies.mmdb.model.audit;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * <code>DateAudit</code> class is the abstract superclass for all domain models class
 * which have <code>createdAt</code> and <code>updatedAt</code> fields.
 * <p>
 * This class will have a createdAt and an updatedAt field. Other domain models that need
 * these auditing fields will simply extend this class.
 * <p>
 * Using mapped superclass strategy That allows us to share the <code>createdAt</code> and <code>updatedAt</code>
 * attributes definitions between multiple entities.
 * <p>
 * Using JPA's {@link AuditingEntityListener} to automatically populate <code>createdAt</code>
 * and <code>updatedAt</code> values when we persist an entity.
 * <p>
 * Using {@link JsonIgnoreProperties} because we don’t want the clients of the rest api to supply the
 * <code>createdAt</code> and <code>updatedAt</code> values. If they supply these values then we’ll simply ignore them.
 * However, we’ll include these values in the JSON response.
 * @author Ayoub Khial
 * @version 1.0
 * @see AuditingEntityListener
 * @see MappedSuperclass
 * @see Serializable
 * @since 1.0
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(value = {"createdBy", "updatedBy"}, allowGetters = true)
public abstract class DateAudit implements Serializable {

    private Date createdAt;
    private Date updatedAt;

    @CreatedDate
    @Column(nullable = false, updatable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @LastModifiedDate
    @Column(nullable = false, columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }
}