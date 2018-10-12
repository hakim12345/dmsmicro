package com.dms.recieveClient.generic;

import org.hibernate.Hibernate;

import javax.persistence.Column;
import java.io.Serializable;
import java.sql.Timestamp;
import java.text.Collator;
import java.util.Locale;

public abstract class DMSEntity<K extends Serializable & Comparable<K>, E extends DMSEntity<K, ?>>
        implements Serializable, Comparable<E> {

    private static final long serialVersionUID = -3988499137919577054L;

    public static final Collator DEFAULT_STRING_COLLATOR = Collator.getInstance(Locale.ENGLISH);

    static {
        DEFAULT_STRING_COLLATOR.setStrength(Collator.PRIMARY);
    }

    /**
     * Returns the value of the unique identifier.
     *
     * @return id
     */
    public abstract K getId();

    /**
     * Sets the value of the unique identifier.
     *
     * @param id **id**.
     */
    public abstract void setId(K id);

    @Column(name = "CREATED_DATE", nullable = true, updatable = false)
    private Timestamp createdDate;

    @Column(name = "CREATED_BY", nullable = true, updatable = false)
    private String createdBy;

    @Column(name = "UPDATE_DATE", nullable = true)
    private Timestamp updatedDate;

    @Column(name = "UPDATE_BY", nullable = true)
    private String updatedBy;

    @Column(name = "tanent_id", nullable = true)
    private Long tanentID;


    public Timestamp getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Timestamp createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Timestamp updatedDate) {
        this.updatedDate = updatedDate;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getTanentID() {
        return tanentID;
    }

    public void setTanentID(Long tanentID) {
        this.tanentID = tanentID;
    }

    /**
     * Indicates whether the object has been persisted or not
     *
     * @return True if the object has not yet been persisted
     */
    public boolean isNew() {
        return getId() == null;
    }

    @SuppressWarnings("unchecked")
    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }
        if (object == this) {
            return true;
        }

        // The object can be proxyfied so we use Hibernate.getClass () to output
        // the true class
        if (Hibernate.getClass(object) != Hibernate.getClass(this)) {
            return false;
        }
        // NOSONAR: treated above but wrapper Hibernate
        DMSEntity<K, E> entity = (DMSEntity<K, E>) object;
        K id = getId();

        if (id == null) {
            return false;
        }
        return id.equals(entity.getId());
    }

    @Override
    public int hashCode() {
        int hash = 7;
        K id = getId();
        hash = 31 * hash + ((id == null) ? 0 : id.hashCode());
        return hash;
    }

    public int compareTo(E o) {
        if (this == o) {
            return 0;
        }
        return this.getId().compareTo(o.getId());
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("entity.");
        builder.append(Hibernate.getClass(this).getSimpleName());
        builder.append("<");
        builder.append(getId());
        builder.append("-");
        builder.append(super.toString());
        builder.append(">");
        return builder.toString();
    }
}
