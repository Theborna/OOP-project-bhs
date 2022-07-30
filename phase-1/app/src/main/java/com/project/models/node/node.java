package com.project.models.node;

import java.time.LocalDateTime;
import java.util.List;

/**
 * abstract class which is the parent class for all nodes that can be stored
 * in the database
 * 
 * @variables
 *            id, LocalDateTime created, last modified date,
 * @methods
 *          getters, modifiers, toString, hashCode, equals
 * @static_methods
 *                 get object from the database, match object with dataBase
 *                 send data to database
 */
public abstract class node {
    protected long id = 0;
    protected LocalDateTime creationDate, lastModifiedDate;
    // protected static List<node> allData;

    protected static long newId() {
        // TODO find the appropriate from the database for each instance
        return 0;
    }

    public node setData(long id, LocalDateTime creationDate, LocalDateTime lastModifiedDate) {
        return this.setId(id).setCreationDate(creationDate).setLastModifiedDate(lastModifiedDate);
    }

    public node setId(long id) {
        this.id = id;
        return this;
    }

    public node setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
        return this;
    }

    public node setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
        return this;
    }

    public abstract void sendToDB();

    // static methods
    protected static void setAllData() {
        // will upLocalDateTime list of allData according to database
    }

    // public static List<node> getAllData() {
    // return allData;
    // }

    public static node get(long id) {
        // setAllData();
        // for (node data : allData)
        // if (data.getId() == id)
        // return data;
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

    public LocalDateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

}