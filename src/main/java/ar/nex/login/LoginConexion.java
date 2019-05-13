package ar.nex.login;

import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Renzo
 */
public class LoginConexion {

    private String url;
    private String user;
    private String pass;
    private String driver;

    private final InputStream file;
    private final Properties properties;

    public LoginConexion() {
        this.url = "";
        this.user = "";
        this.pass = "";
        this.driver = "";
        file = getClass().getResourceAsStream("/META-INF/config.properties");
        properties = new Properties();
    }

    public void readConfig() {
        try {
            properties.load(file);
            this.url = properties.getProperty("url");
            this.user = properties.getProperty("user");
            this.pass = properties.getProperty("pass");
            this.driver = properties.getProperty("driver");
        } catch (Exception ex) {
            Logger.getLogger(LoginConexion.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }
    }

    public void writeConfig(LoginConexion config) {
        try {
            properties.setProperty("url", config.url);
            properties.setProperty("user", config.user);
            properties.setProperty("pass", config.pass);
            properties.setProperty("driver", config.driver);

            Path path = Paths.get(getClass().getClassLoader().getResource("./META-INF/config.properties").toURI());
            FileWriter fw = new FileWriter(new File(path.toUri()));            
            properties.store(fw, "Check db!!!");

        } catch (Exception ex) {
            Logger.getLogger(LoginConexion.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDriver() {
        return driver;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 73 * hash + Objects.hashCode(this.url);
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
        final LoginConexion other = (LoginConexion) obj;
        if (!Objects.equals(this.url, other.url)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "LoginConexion{" + "url=" + url + ", user=" + user + ", pass=" + pass + ", driver=" + driver + '}';
    }

}
