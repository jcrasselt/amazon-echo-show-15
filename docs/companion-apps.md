# Companion Apps

- [Amazon Alexa App](#amazon-alexa-app)
- [Amazon Photos App](#amazon-photos-app)

## Amazon Alexa App

|Build Version|App Version|
|---          |---        |
|2.2.487227.0 |2022.21    |

### File System Artifacts
Alexa app stores its data on Android in `/data/data/com.amazon.dee.app/`.

- `shared_prefs/SHARED_PREFS.xml`:  
  - Name
  - Phone number
  - Last login  

  ```xml
  <string name="PHONE_NO_PREF">{REDACTED: phonenumber}</string>
  <string name="FIRST_NAME_PREF">Jona</string>
  <string name="LAST_NAME_PREF">Crasselt</string>
  <long name="LAST_USER_INFO_SYNC_TIME" value="1668970421979" />
  ```
  `LAST_USER_INFO_SYNC_TIME`: 1668970421979 equals to _2022-11-20 19:53:41 GMT+0100_ which was the moment the app was started the last time (not when it was closed! The app was used about for an hour after that)

  The first time using the app:
  ```xml
  <long name="LAST_USER_INFO_SYNC_TIME" value="1668349714347" />
  ```
  *LAST_USER_INFO_SYNC_TIME*: 1668349714347 equals to _2022-11-13 15:28:34 GMT+0100_ which was the moment after login and agreeing the Terms of Use

- `shared_prefs/SHARED_PREFS_IDENTITY.xml` and
  `database/comms-core-identity-database`:  

  Table `account` and `identityv2_table`: User name, directedId, commsId, personIdV2, phonenumber, homegroupId

- `shared_prefs/service.identity.xml`

  - Name
  - Phone number
  - Access token (identical to token in [`map_data_storage_v2.db`](./filesystem/token-db.md))
  ```xml
  <?xml version='1.0' encoding='utf-8' standalone='yes' ?>
  <map>
    <string name="user.profile.firstName">Jona</string>
    <string name="user.profile.comms.commsId">{REDACTED: commsId}</string>
    <string name="user.effectiveMarketplace">A1PA6795UKMFR9</string>
    <string name="user.id">{REDACTED: customerId}</string>
    <string name="user.[version]">5</string>
    <string name="user.profile.personId">{REDACTED: personId}</string>
    <string name="user.countryOfResidence">DE</string>
    <string name="user.marketplace">A1PA6795UKMFR9</string>
    <string name="user.accessToken">Atna|{REDACTED}</string>
    <string name="user.name">Jona Crasselt</string>
    <string name="user.directedId">{REDACTED: directedId}</string>
    <boolean name="user.hasDevices" value="false" />
    <boolean name="user.hasProfile" value="true" />
    <string name="user.email">{REDACTED}</string>
    <string name="user.originalMarketplace">A1PA6795UKMFR9</string>
    <boolean name="user.hasDelegatedProfile" value="false" />
    <boolean name="user.eulaAccepted" value="true" />
    <boolean name="user.profile.hasComms" value="true" />
    <string name="user.profile.comms.phoneNumber">{REDACTED: phonenumber}</string>
    <string name="user.profile.lastName">Crasselt</string>
    <string name="user.profile.directedId">{REDACTED: directedId}</string>
  </map>
  ```

- `databases/map_data_storage_v2.db`
  
  Tables:
  - *account_data*: Contains encrypted values for refresh token and access
    tokens, which can be decrypted as described in
    [Token Database](./filesystem/token-db.md)

- `shared_prefs/mobilytics.session-storage.xml`

  Start and end timestamp of last app start
  ```xml
  <set name="tl_set">
        <string>mobilytics.timeline_1670769151813</string>
  </set>
  <long name="SessionStartTime" value="1670767405067" />
  <long name="SessionStopTime" value="1670769148417" />
  ```

## Amazon Photos app

|App Version                |
|---                        |
|2.1.0.107.0-aosp-902005930g|

User-Agent: `PhotosAndroid/2.1.0.107.0-aosp-902005930g`

### File System Artifacts
Photo app stores its data on Android in
`/data/data/com.amazon.clouddrive.photos/`

- `databases/map_data_storage.db`
  
  Table *tokens*: Access token and expiration time (valid for 1h after refresh) : 
 
  |token_key                                                      |token_value     |
  |---                                                            |---             |
  |com.amazon.dcp.sso.token.oauth.amazon.refresh_token            |Atnr\|{REDACTED}|
  |com.amazon.dcp.sso.token.oauth.amazon.access_token.refreshed_at|1669467152264   |
  |com.amazon.dcp.sso.token.oauth.amazon.access_token.expires_at  |1669470752264   |
  |com.amazon.dcp.sso.token.oauth.amazon.access_token|Atna\       |{REDACTED}      |

- `databases/metadata_cache_database_1666554178`

  Metadata of images (with EXIF data, if available) in table *cache_data*.

  Example:
  ```json
  {
    "accessRuleIds": [],
    "childAssetTypeInfo": [],
    "contentProperties": {
        "contentDate": {
            "value": "2019-09-06T16:30:09.089Z"
        },
        "contentSignatures": [
            {
                "contentSignature": "4c26c8cf0cc4ab4b4afecc01c95ac3fc",
                "contentSignatureType": "MD5"
            }
        ],
        "contentType": "image/jpeg",
        "extension": "jpg",
        "image": {
            "apertureValue": "8.0",
            "colorSpace": "sRGB",
            "dateTime": "2019-09-22T13:58:55.000Z",
            "dateTimeDigitized": "2019-09-06T16:30:09.000Z",
            "dateTimeOriginal": "2019-09-06T16:30:09.000Z",
            "exposureMode": "Auto",
            "exposureProgram": "Aperture-priority AE",
            "exposureTime": "1/640",
            "flash": "Off, Did not fire",
            "focalLength": "42",
            "height": 3521,
            "make": "Canon",
            "meteringMode": "Evaluative",
            "model": "Canon EOS 5D Mark II",
            "orientation": "1",
            "resolutionUnit": "Pixels/Inch",
            "sharpness": "0",
            "software": "darktable darktable-2.6.2-1.fc29",
            "subSecTime": "89",
            "subSecTimeDigitized": "89",
            "subSecTimeOriginal": "89",
            "whiteBalance": "Daylight",
            "width": 5634
        },
        "md5": "2902cf254d6928c4c8629ceb2d45b156",
        "size": 26094939
    },
    "id": "MFlB-JKeQ1KBUySu7reIFQ",
    "name": "IMG_0532_edit.jpg",
    "parentMap": {
        "FOLDER": [
            "Yii2s8wSTDq5RUPfNsQMvA"
        ]
    },
    "parents": [
        "Yii2s8wSTDq5RUPfNsQMvA"
    ],
    "protectedFolder": false,
    "restricted": false,
    "status": "AVAILABLE",
    "createdBy": "Prime Photos Android",
    "createdDate": {
        "value": "2022-11-27T14:09:16.927Z"
    },
    "modifiedDate": {
        "value": "2022-11-27T14:09:42.740Z"
    },
    "ownerId": "{REDACTED: customerId}"
  }
  ```

- `cache/image_manager_disk_cache`: Cached photos
- `databases/discovery_database_1666554178`: images that were uploaded