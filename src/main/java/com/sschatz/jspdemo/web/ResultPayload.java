/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sschatz.jspdemo.web;

import java.util.Objects;

/**
 *
 * @author sschatz
 */
public class ResultPayload {
    
    private Boolean status = false;

    public ResultPayload() {
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.status);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ResultPayload other = (ResultPayload) obj;
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResultPayload{" + "status=" + status + '}';
    }
    
    
    
}
