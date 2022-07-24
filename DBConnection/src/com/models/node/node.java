package com.models.node;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

/**
 * abstract class which is the parent class for all nodes that can be stored
 * in the database
 *
 * @variables id, date created, last modified date,
 * @methods getters, modifiers, toString, hashCode, equals
 * @static_methods get object from the database, match object with dataBase
 * send data to database
 */
public abstract class node {
    protected long id;
    protected LocalDateTime creationDate, lastModifiedDate;
    protected static List<node> allData;

    public void setData(long id, LocalDateTime creationDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // static methods
    protected static void setAllData() {
        // will update list of allData according to database
    }

    public static List<node> getAllData() {
        return allData;
    }

    public static node get(long id) {
        setAllData();
        for (node data : allData)
            if (data.getId() == id)
                return data;
        return null;
    }

    // overridden methods from Object
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (id ^ (id >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        node other = (node) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[id=" + id + "]";
    }

    // getters
    public long getId() {
        return id;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setId(long id) {
        this.id = id;
    }
}
