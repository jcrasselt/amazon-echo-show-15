# Token Database

The database `map_data_storage_v2.db` contains encrypted values in
the table *account_data* for
- Access token (`*.access_token`) (valid for 1h)
- Session cookies `*.token.amazon.cookies.*` (valid for 24h)
- Refresh token `*.refresh_token` (valid indefinitely until logout or
  password change)

The table also contains the following entry in the table *encryption_data*:

|**encryption_data_key**|**encryption_data_value**|
|---|---|
|key_encryption_secret|*{Base64 value (25 chars)}*|

The database can be found on the file system of the Echo Show 15 and in the
Alexa app at following locations:
|Source      |Location                                                        |
|---         |---                                                             |
|Echo Show 15|data:`/data/com.amazon.imp/databases/map_data_storage_v2.db`    |
|Alexa app   |`/data/data/com.amazon.dee.app/databases/map_data_storage_v2.db`|

The AWS library [github.com/aws/amazon-s3-encryption-client-java][github:aws:encryption]
contains valuable hints for the used encryption:
- **Encryption standard**: AES (CBC)
- **Padding**: PKCS#5
- **IV length**: 16

A tool to decrypt the encrypted values in `map_data_storage_v2.db` is provided in
[tools/token-db](../../tools/token-db/README.md).

The following listing shows a minimal working Java implementation for token decryption:
```java
String decrypt(String keyEncryptionSecret,
               byte[] encryptedValue) {

  byte[] ENCRYPTION_KEY = Base64.getMimeDecoder().decode(keyEncryptionSecret);
  SecretKeySpec secretKeySpec = new SecretKeySpec(ENCRYPTION_KEY, "AES");
  int cipherIvLen = 16;
  Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
  cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, new IvParameterSpec(encryptedValue, 0, cipherIvLen));

  return new String(cipher.doFinal(encryptedValue, cipherIvLen, encryptedValue.length - cipherIvLen), "UTF-8");
}
```

The decrypted values look as follows:
- Refresh token: `Atnr|{REDACTED}`
- Access token: `Atna|{REDACTED}`
- Session cookies:
  ```json
  [
    "session-id={REDACTED}; path=/; domain=.amazon.com; secure; expires=29 Jul 2043 23:07:48 GMT",
    "ubid-main={REDACTED}; path=/; domain=.amazon.com; secure; expires=29 Jul 2043 23:07:48 GMT",
    "x-main=\"{REDACTED}\"; path=/; domain=.amazon.com; secure; expires=29 Jul 2043 23:07:48 GMT",
    "at-main=\"Atza|{REDACTED}\"; path=/; domain=.amazon.com; secure; httponly; expires=04 Aug 2023 23:07:48 GMT",
    "sess-at-main=\"{REDACTED}\"; path=/; domain=.amazon.com; secure; httponly; expires=04 Aug 2023 23:07:48 GMT"
  ]
  ```

To retrieve a valid access token or session cookies using the refresh token,
the tool provided in
[tools/token-db](../../tools/token-db/README.md#renew-access-tokens-and-session-cookies)
can be used.

How access token and session cookies can be used to authenticate against the
Alexa API is illustrated in [API](../api.md#authentication).

<!-- LINKS -->
[github:aws:encryption]: https://github.com/aws/amazon-s3-encryption-client-java