package com.putatu.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;


/**
 * A Staff.
 */
@Entity
@Table(name = "staff")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@Document(indexName = "staff")
public class Staff implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "salary", nullable = false)
    private Float salary;

    @OneToOne
    @JoinColumn(unique = true)
    private User user;

    @ManyToOne
    private Position position;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getSalary() {
        return salary;
    }

    public Staff salary(Float salary) {
        this.salary = salary;
        return this;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public User getUser() {
        return user;
    }

    public Staff user(User user) {
        this.user = user;
        return this;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Position getPosition() {
        return position;
    }

    public Staff position(Position position) {
        this.position = position;
        return this;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Staff staff = (Staff) o;
        if (staff.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), staff.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Staff{" +
            "id=" + getId() +
            ", salary=" + getSalary() +
            "}";
    }
}
