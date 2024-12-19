package com.azure.samples.todo;

import java.io.Serializable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "ToDoItem")
@NamedQuery(
        name = "ToDoItem.findByUsername",
        query = "SELECT i FROM ToDoItem i WHERE i.username = :username")
@XmlRootElement
public class ToDoItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    @NotNull
    @Size(min = 5, max = 110,
            message = "Item description must be between 5 and 110 characters.")
    private String description;

    private boolean completed;

    /**
     * Default constructor
     */
    protected ToDoItem() {
        // Default constructor
    }

    /**
     * Constructor for creating instances
     *
     * @param username - username
     * @param description - description of the task
     * @param completed - completed
     */
    public ToDoItem(String username, String description, boolean completed) {
        this.username = username;
        this.description = description;
        this.completed = completed;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @XmlTransient
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ToDoItem)) {
            return false;
        }
        ToDoItem other = (ToDoItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ToDoItem[ id=" + id
                + " username=" + username
                + " desciption=" + description
                + " completed=" + completed + " ]";
    }
}
