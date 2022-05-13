package com.project.models;

import java.sql.Date;
import java.util.List;

/**
 * abstract class which is the parent class for all objects that can be stored
 * in the database
 * 
 * @variables
 *            id, date created, last modified date,
 * @methods
 *          getters, modifiers, toString, hashCode, equals
 * @static_methods
 *                 get object from the database, match object with dataBase
 *                 send data to database
 */
public abstract class data {
    private long id;
    private Date creationDate, lastModifiedDate;
    private static List<data> allData;

    public void setData(long id, Date creationDate, Date lastModifiedDate) {
        this.id = id;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    // static methods
    private static void setAllData() {
        // will update list of allData according to database
    }

    public static data get(long id) {
        setAllData();
        for (data data : allData)
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
        data other = (data) obj;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

}
