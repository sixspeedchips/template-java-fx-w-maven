package io.libsoft.util;


import java.io.IOException;
import java.io.InputStream;

import java.util.Properties;
import lombok.Data;

@Data
public class Props {


  public static Props get() {
    return InstanceHolder.INSTANCE;
  }

  private static class InstanceHolder {

    private final static Props INSTANCE;

    static {
      INSTANCE = new Props();
      Properties prop = new Properties();
      try (InputStream inputStream = Props.class.getClassLoader().getResourceAsStream("config.properties")) {
        prop.load(inputStream);
        System.out.println(Utils.getPrinter().toJson(prop));
        // example how to load properties from file
        // INSTANCE.nodes = Integer.parseInt(prop.getProperty("nodes", String.valueOf(INSTANCE.nodes)));

        System.out.println("Set properties from file.");
        System.out.println(Utils.getPrinter().toJson(INSTANCE));

      } catch (IOException ioException) {

      } catch (NullPointerException nullPointerException){
        System.out.println("Config file not found.");
        System.out.println("Searched paths:");
        System.out.println(Props.class.getResource("/"));
        System.out.println("Failed to load properties.");
      }
    }

  }


}
