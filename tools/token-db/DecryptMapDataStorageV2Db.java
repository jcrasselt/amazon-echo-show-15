package amzes15;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Base64;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.InputStream;

import javax.crypto.spec.SecretKeySpec;
import javax.naming.spi.DirStateFactory.Result;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import java.io.UnsupportedEncodingException;

public class DecryptMapDataStorageV2Db {

    private final boolean DEBUG = true;

    private String dbPath;
    private Connection dbConnection;
    private byte[] encryptionKey;

    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.err.println("Usage: java "
                    + DecryptMapDataStorageV2Db.class.getName()
                    + " <map_data_storage_v2.db>");
            System.exit(1);
        }

        String dbPath = args[0];

        File dbFile = new File(dbPath);
        if (!dbFile.exists() || dbFile.isDirectory()) {
            System.err.println("File \"" + dbFile + "\" not found!");
            System.exit(1);
        }

        DecryptMapDataStorageV2Db decryptor = new DecryptMapDataStorageV2Db(dbPath);
        decryptor.printDecryptedValues();
    }

    public DecryptMapDataStorageV2Db(String dbPath) {
        this.dbPath = dbPath;
        this.connectDatabase();
    }

    public void printDecryptedValues() {
        if (!this.queryDatabaseForEncryptionKey()) {
            System.err.println("key_encryption_secret was not found!");
            return;
        }

        this.queryEncryptedTable("account_data",
                "account_data_key",
                "account_data_value");
        this.queryEncryptedTable("device_data",
                "device_data_key",
                "device_data_value");
    }

    public void connectDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        String url = "jdbc:sqlite:" + this.dbPath;
        this.dbConnection = null;
        try {
            this.dbConnection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public boolean queryDatabaseForEncryptionKey() {
        ResultSet rs = this.queryDatabase("SELECT encryption_data_key, encryption_data_value FROM encryption_data");

        try {
            while (rs.next()) {
                String encryptionDataKey = rs.getString("encryption_data_key");
                String encryptionDataValue = rs.getString("encryption_data_value");
                System.out.println(encryptionDataKey + ":" + encryptionDataValue);
                if (encryptionDataKey.equals("key_encryption_secret")) {

                    /*
                     * do not use regular Base64.getDecoder() as values were probably
                     * encrypted with android.util.Base64
                     * https://stackoverflow.com/q/35292836
                     */
                    this.encryptionKey = Base64.getMimeDecoder().decode(encryptionDataValue);
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return false;
    }

    public void queryEncryptedTable(String tableName, String keyColumn, String valueColumn) {
        try {
            ResultSet rs = this.queryDatabase("SELECT " + keyColumn + ", " + valueColumn + " FROM " + tableName);

            while (rs.next()) {
                String accountDataKey = rs.getString(keyColumn);
                System.out.println(accountDataKey);
                System.out.println("-----");

                InputStream accountDataValueStream = rs.getBinaryStream(valueColumn);
                if (rs.wasNull()) {
                    logging(valueColumn + " was null\n");
                    accountDataKey = "";
                    continue;
                }
                byte[] accountDataValue = accountDataValueStream.readAllBytes();

                System.out.println(Base64.getMimeEncoder().encodeToString(accountDataValue) + "\n");
                String decryptedValue = decryptValue(accountDataValue);
                if (decryptedValue == null) {
                    System.err.println("Decryption may have failed");
                    continue;
                }

                System.out.println(decryptedValue + "\n");
                System.out.println("==========");
            }
        } catch (SQLException | IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private ResultSet queryDatabase(String sqlQuery) {
        try {
            Statement stmt = this.dbConnection.createStatement();
            ResultSet rs = stmt.executeQuery(sqlQuery);

            return rs;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public String decryptValue(byte[] encryptedValue) {
        byte[] decryptedData = decryptAESEncryptedValue(encryptedValue,
                "AES",
                "AES/CBC/PKCS5Padding",
                16);

        if (decryptedData == null) {
            return null;
        }

        try {
            return new String(decryptedData, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    private byte[] decryptAESEncryptedValue(byte[] encryptedValue, String algorithm, String transformation,
            int cipherIvLen) {
        SecretKeySpec secretKeySpec = new SecretKeySpec(this.encryptionKey, algorithm);
        Cipher cipher;
        try {
            cipher = Cipher.getInstance(transformation);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(encryptedValue, 0, cipherIvLen));
        } catch (AssertionError e) {
            System.err.println(e.getMessage());
            return null;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return null;
        }

        if (cipher == null) {
            System.err.println("Cipher was null");
            return null;
        }

        try {
            return cipher.doFinal(encryptedValue, cipherIvLen, encryptedValue.length - cipherIvLen);
        } catch (BadPaddingException e) {
            System.err.println(e.getMessage());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

    private void logging(String msg) {
        if (this.DEBUG)
            System.out.println(msg);
    }
}
