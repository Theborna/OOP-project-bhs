package com.electro.phase1.models.node;

import com.electro.phase1.enums.MediaType;
import com.electro.phase1.models.node.post.Post;
import com.electro.phase1.models.node.user.User;

public abstract class Media extends node {
    protected String address;
    private MediaType mt;

    public Media(String address) {
        this.address = address;
    }

    public MediaType getMt() {
        return mt;
    }

    public void setMt(MediaType mt) {
        this.mt = mt;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public abstract void showMedia();

    @Override
    public void sendToDB() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((address == null) ? 0 : address.hashCode());
        result = prime * result + ((mt == null) ? 0 : mt.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Media other = (Media) obj;
        if (address == null) {
            if (other.address != null)
                return false;
        } else if (!address.equals(other.address))
            return false;
        if (mt != other.mt)
            return false;
        return true;
    }

    
}