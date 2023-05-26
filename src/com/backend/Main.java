package com.backend;

import com.backend.dao.H2Connection;

public class Main {
    public static void main(String[] args) {
        H2Connection.crearTabla();
    }
}