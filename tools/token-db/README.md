# Tools for Token Database

## Decrypt Token Database

[`DecryptMapDataStorageV2Db`](./DecryptMapDataStorageV2Db.java) takes a `map_data_storage_v2.db` and prints the decrypted values of the *account_data* and *device_data* table.

Build `DecryptMapDataStorageV2Db.java` and run it as shown below *(requires [sqlite-jdbc][github:sqlite-jdbc])*:
```
# build
$ javac -d bin/ -cp .:sqlite-jdbc-XYZ.jar DecryptMapDataStorageV2Db.java

# run
$ java -cp bin/:sqlite-jdbc-XYZ.jar amzes15.DecryptMapDataStorageV2Db map_data_storage_v2.db
```

## Renew Access Tokens and Session Cookies
The tool [`refresh-tokens.py`](./refresh-tokens.py) can be used to retrieve
a valid access token or session cookies from the refresh token.

<!-- LINKS -->
[github:sqlite-jdbc]: https://github.com/xerial/sqlite-jdbc