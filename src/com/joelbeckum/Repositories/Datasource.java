package com.joelbeckum.Repositories;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public abstract class Datasource {
    protected String getConnectionString() throws IOException {
        try (InputStream input = new FileInputStream("./src/resources/config.properties")) {

            Properties prop = new Properties();

            prop.load(input);

           return prop.getProperty("db.connectionString");

        } catch(IOException e) {
            throw e;
        }
    }
}
