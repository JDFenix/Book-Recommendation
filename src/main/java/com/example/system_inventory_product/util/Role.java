package com.example.system_inventory_product.util;

import lombok.Data;

import java.util.Arrays;
import java.util.List;



public enum Role {
    administrator(Arrays.asList(Permission.SAVE_ONE_OBJECT,Permission.READ_ALL_OBJECTS,Permission.UPDATE_ONE_OBJECT,Permission.DELETE_ONE_OBJECT,Permission.READ_ONE_OBJECT)),
    reader(Arrays.asList(Permission.READ_ALL_OBJECTS));

    private List<Permission> permission;

    Role(List<Permission> permission) {
        this.permission=permission;
    }

    public List<Permission> getPermission() {
        return permission;
    }

    public void setPermission(List<Permission> permission) {
        this.permission = permission;
    }




}
