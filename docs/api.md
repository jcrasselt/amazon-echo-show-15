# Alexa API

## Authentication
The decrypted refresh token (see [Token Database](./filesystem/token-db.md)) can be used
to retrieve 

|Authentication Method|Hostname|
|---                  |---     |
|`Authorization` header|api.{amazon,amazonalexa}.com|
|Session cookies|{alexa,skills-store,www}.amazon.{com,de,...}, alexa-comms-mobile-service.amazon.com|
|`X-Amz-Access-Token` header|{cdws.eu-west-1,drive}.amazonaws.com|

How to retrieve access token or session cookies using the refresh token
has already been determined by the following sources. 
- Access token: [github.com/athrowaway2021/amazon_auth](https://github.com/athrowaway2021/amazon_auth)
  ```json
  {
    "app_name": "com.amazon.alexa",
    "source_token_type": "refresh_token",
    "source_token": "Atnr|{REDACTED}",
    "requested_token_type": "access_token"
  }
  ```
- Session cookies: [alexa_remote_control.sh](https://loetzimmer.de/patches/alexa_remote_control.sh) from [blog.loetzimmer.de](https://blog.loetzimmer.de/2017/10/amazon-alexa-hort-auf-die-shell-echo.html)
  ```json
  {
    "app_name": "com.amazon.alexa",
    "source_token_type": "refresh_token",
    "source_token": "Atnr|{REDACTED}",
    "requested_token_type": "auth_cookies",
    "domain": "www.amazon.{com,de,...}"
  }
  ```

We derived a script from their methods in
[tools/token-db/](../tools/token-db/README.md#renew-access-tokens-and-session-cookies).

## Alexa API Endpoint Examples

- api.amazon.com/user/profile

  ```json
  {
    "email": "{REDACTED}",
    "mobile_number": "+49{REDACTED}",
    "name": "Jona Crasselt",
    "postal_code": "{REDACTED}",
    "shipping_address": {
      "country": "Germany",
      "country_code": "DE",
      "locality": "Nürnberg",
      "postal_code": "{REDACTED}",
      "region": "Bayern",
      "street_address": "{REDACTED: street name and number}"
    },
    "user_id": "{REDACTED: directedId}"
  }
  ```

- alexa.amazon.com/api/users/me

  ```json
  {
    "countryOfResidence": "DE",
    "effectiveMarketPlaceId": "A1PA6795UKMFR9",
    "email": "{REDACTED}",
    "eulaAcceptance": false,
    "features": [
        ...
    ],
    "fullName": "Jona Crasselt",
    "hasActiveDopplers": false,
    "id": "{REDACTED: customerId}",
    "marketPlaceDomainName": "https://www.amazon.de",
    "marketPlaceId": "A1PA6795UKMFR9",
    "marketPlaceLocale": "de-DE"
  }
  ```

- alexa.amazon.de/api/wifi/configs

  Until 2023, Amazon offered a web app at
  [alexa.amazon.com](https://alexa.amazon.com) with comparable capabilities as
  the Alexa app. Since the web app was taken offline, this API does no longer
  return saved Wi-Fi credentials. Before it was disabled, the response was as
  follows:
  ```json
  {
    "values": [
      {
        "deviceSerialNumber": null,
        "deviceType": null,
        "preSharedKey": "{REDACTED: password}",
        "securityMethod": "WPA_PSK",
        "ssid": "{REDACTED: SSID}"
      }
    ]
  }
  ```

  

<!--
- alexa.amazon.de/api/activities

  Returns 404
-->

- alexa.amazon.de/api/content?personIdV2={personIdV2}

  ```json
  {
    "@c": ".ContentItem",
    "contentId": "list:{REDACTED: listId}:1677141899516",
    "refreshId": "list:{REDACTED: listId}:1677141899516",
    "contentProvider": "HHOLists",
    "contentType": "shoppinglist_singlecard",
    "contentSource": "HOME",
    "templateType": "MiniTemplate",
    "slots": [
      {
        "position": 0,
        "viewType": "IconTitleSubtitleView",
        "resources": {
          "@c": ".IconTitleSubtitleViewResources",
          "title": "Shopping List",
          "subtitle": "4 items",
          "imageUrl": "https://m.media-amazon.com/images/G/01/alexa-mxbr24v8/mobile-v8ah3v/home-kaixn/shopping-24443/ic_list_bullet_36dp.png",
          "imageType": "Icon",
          "italicized": false,
  [...]
  ```

- alexa.amazon.de/api/wake-word?cached=true&_=1669376266639


  ```json
  {
    "wakeWords": [
      {
        "active": true,
        "deviceSerialNumber": "{REDACTED}",
        "deviceType": "A1EIANJ7PNB0Q7",
        "midFieldState": null,
        "wakeWord": "ALEXA"
      }
    ]
  }
  ```

- alexa.amazon.de/api/device-preferences
- alexa.amazon.de/api/devices-v2/device

  List of connected devices: Echo Show itself and Android VM
  ```json
  {
    "devices": [
      {
        "accountName": "ForensicEcho20221120",
        "appDeviceList": [],
        "associatedUnitIds": null,
        "capabilities": [
          "SET_LOCALE",
          "VOLUME_SETTING",
          "PERSISTENT_CONNECTION",
          "LIVE_VIEW",
          "TUNE_IN",
          "EARCONS",
          "EQUALIZER_CONTROLLER_MIDRANGE",
          "I_HEART_RADIO",
          "SUPPORTS_CONNECTED_HOME_CLOUD_ONLY",
          "MULTI_WAKEWORDS_SUPPORTED",
          "GUARD_EARCON",
          "MICROPHONE",
          "SUPPORTS_LOCALE",
          "DEREGISTER_DEVICE",
          "CUSTOM_ALARM_TONE",
          "SALMON",
          "DREAM_TRAINING",
          "MUSIC_SKILL",
          "TIMERS_ALARMS_NOTIFICATIONS_VOLUME",
          "DIALOG_INTERFACE_VERSION",
          "VOICE_TRAINING",
          "ADAPTIVE_LISTENING",
          "SIRIUSXM",
          "ALEXA_VOICE",
          "KINDLE_BOOKS",
          "SUPPORTS_SOFTWARE_VERSION",
          "SHARKNADO",
          "BT_PAIRING_FLOW_V2",
          "SUPPORT_CALENDAR_ALERT",
          "ASCENDING_ALARM_VOLUME",
          "GOLDFISH",
          "POPTART",
          "CLOCK_FORMAT_24_HR",
          "TUPLE_CATEGORY_B",
          "AUDIBLE",
          "SUPPORTS_LOCALE_SWITCH",
          "TIMERS_AND_ALARMS",
          "FAR_FIELD_WAKE_WORD",
          "LEMUR_ALPHA",
          "AUDIO_PROCESSING",
          "EQUALIZER_CONTROLLER_BASS",
          "SLEEP",
          "AUDIO_PLAYER",
          "ALEXA_PRESENCE",
          "DS_VOLUME_SETTING",
          "CHANGE_NAME",
          "DEEZER",
          "TIDAL",
          "PANDORA",
          "EFDCARDS",
          "EQUALIZER_CONTROLLER_TREBLE",
          "FLASH_BRIEFING",
          "AUDIO_CONTROLS",
          "AMAZON_MUSIC",
          "REMINDERS",
          "SPEECH_RECOGNIZER_USS",
          "SOUND_SETTINGS",
          "PAIR_BT_SINK",
          "TUPLE",
          "APPLE_MUSIC"
        ],
        "charging": null,
        "clusterMembers": [],
        "deviceAccountId": "{REDACTED}",
        "deviceFamily": "KNIGHT",
        "deviceOwnerCustomerId": "{REDACTED}",
        "deviceType": "A1EIANJ7PNB0Q7",
        "deviceTypeFriendlyName": null,
        "essid": null,
        "language": null,
        "macAddress": null,
        "online": true,
        "parentClusters": [],
        "postalCode": null,
        "registrationId": null,
        "remainingBatteryLevel": null,
        "serialNumber": "{REDACTED}",
        "softwareVersion": "24394849412"
      },
      {
        "accountName": "This Device",
        "appDeviceList": [
          {
              "deviceAccountId": "{REDACTED}",
              "deviceType": "A2TF17PFR55MTB",
              "serialNumber": "{REDACTED}"
          },
          ...
        ],
        "associatedUnitIds": null,
        "capabilities": [
          "VOLUME_SETTING",
          "ADAPTIVE_LISTENING",
          "MICROPHONE"
        ],
        "charging": null,
        "clusterMembers": [],
        "deviceAccountId": "{REDACTED}",
        "deviceFamily": "VOX",
        "deviceOwnerCustomerId": "{REDACTED}",
        "deviceType": "A2TF17PFR55MTB",
        "deviceTypeFriendlyName": null,
        "essid": null,
        "language": null,
        "macAddress": null,
        "online": true,
        "parentClusters": [],
        "postalCode": null,
        "registrationId": null,
        "remainingBatteryLevel": null,
        "serialNumber": "{REDACTED}",
        "softwareVersion": "130050002"
      }
    ]
  }
  ```

- alexa.amazon.de/api/bluetooth

  Two Echo Show 15 and Android VM
  ```json
  {
    "bluetoothStates": [
      {
        "deviceSerialNumber": "{REDACTED}",
        "deviceType": "A1EIANJ7PNB0Q7",
        "friendlyName": null,
        "gadgetPaired": false,
        "online": false,
        "pairedDeviceList": null,
        "sconeBattery": null,
        "sconePaired": false,
        "softwareVersion": "25703747460",
        "streamingState": null
      },
      {
        "deviceSerialNumber": "{REDACTED}",
        "deviceType": "A1EIANJ7PNB0Q7",
        "friendlyName": null,
        "gadgetPaired": false,
        "online": false,
        "pairedDeviceList": null,
        "sconeBattery": null,
        "sconePaired": false,
        "softwareVersion": "27817528964",
        "streamingState": null
      },
      {
        "deviceSerialNumber": "{REDACTED}",
        "deviceType": "A2TF17PFR55MTB",
        "friendlyName": null,
        "gadgetPaired": false,
        "online": false,
        "pairedDeviceList": null,
        "sconeBattery": null,
        "sconePaired": false,
        "softwareVersion": "130050002",
        "streamingState": null
      }
    ]
  }
  ```

- ~~pitangui.amazon.com/api/todos?type={TASK,SHOPPING_ITEM}~~
- alexa.amazon.de/api/namedLists

  The values for `itemId` and elements of `listIds` are Base64 encoded.
  Their decoded values are structured similar to a *directedId*:

  - *SHOPPING_LIST*: `amzn1.account.{28-uppercase-alphanumerics}-SHOPPING_ITEM`
  - *TO_DO*: `amzn1.account.{28-uppercase-alphanumerics}-TASK`

  ```json
  {
    "lists": [
      {
        "archived": false,
        "createdDate": 1665930043822,
        "customerId": "{REDACTED}",
        "defaultList": true,
        "itemId": "{REDACTED}",
        "listIds": [
          "{REDACTED}"
        ],
        "listReorderVersion": null,
        "name": null,
        "nbestItems": null,
        "originalAudioId": null,
        "type": "SHOPPING_LIST",
        "updatedDate": 1665930043822,
        "version": 1
      },
      {
        "archived": false,
        "createdDate": 1665930043822,
        "customerId": "{REDACTED}",
        "defaultList": true,
        "itemId": "{REDACTED}",
        "listIds": [
          "{REDACTED}"
        ],
        "listReorderVersion": null,
        "name": null,
        "nbestItems": null,
        "originalAudioId": null,
        "type": "TO_DO",
        "updatedDate": 1665930043822,
        "version": 1
      }
    ]
  }
  ```
- alexa.amazon.de/api/namedLists/{listId}
  
  **Attention**: `listId` does not match `itemId` or `listIds` mentioned previously in *alexa.amazon.de/api/namedLists*-API.
  
  Shopping list:
  ```json
  {
    "list": [
      {
        "completed": false,
        "createdDateTime": 1668959967762,
        "customerId": "{REDACTED}",
        "id": "a3f1f523-cffa-4717-b23e-9e536ba96b7a",
        "listId": "{REDACTED: customerId}-SHOP",
        "shoppingListItem": true,
        "updatedDateTime": 1668959967762,
        "value": "Peanut butter",
        "version": 1
      },
      {
        "completed": false,
        "createdDateTime": 1665931170938,
        "customerId": "{REDACTED}",
        "id": "4797330d-b475-43f4-8ab0-27cd4b9920f5",
        "listId": "{REDACTED: customerId}-SHOP",
        "shoppingListItem": true,
        "updatedDateTime": 1703511267230,
        "value": "Bananas",
        "version": 2
      },
      {
        "completed": true,
        "createdDateTime": 1668959981976,
        "customerId": "{REDACTED}",
        "id": "70975b8c-b9d6-4d75-b406-34ee4237460e",
        "listId": "{REDACTED: customerId}-SHOP",
        "shoppingListItem": true,
        "updatedDateTime": 1680881170734,
        "value": "Carrots",
        "version": 2
      },
      ...
    ],
    "listReorderVersion": null
  }
  ```

- alexa.amazon.de/api/v1/devices/{deviceAccountId}/settings/liveView
  ```json
  {
    "deviceAccountId": "{REDACTED}",
    "key": "liveView",
    "type": "DEVICE",
    "value": "\"ENABLED\""
  }
  ```

- cdws.eu-west-1.amazonaws.com/drive/v2/device-personalization/devices?deviceFilter=ALL&settings=ALL
  
  As we had access to two Echo Show 15 devices, both show up in the device list
  ```json
  {
    "devices": [
      {
        "name": "ForensicEcho20230223",
        "type": "ECHO_SHOW",
        "deviceAccount": "{REDACTED}",
        "deviceSerialNumber": "{REDACTED}",
        "deviceTypeId": "A1EIANJ7PNB0Q7",
        "deviceIsOnline": false,
        "activeProvider": {
          "providerId": "CURATIONS_TRAVEL",
          "providerName": "Travel"
        },
        "capabilities": [
          "PERSONALIZABLE"
        ],
        "isCompositePhotoCompatible": true,
        "softwareVersion": "27817528964",
        "personalizabilityState": "OFFLINE"
      },
      {
        "name": "ForensicEcho20221120",
        "type": "ECHO_SHOW",
        "deviceAccount": "{REDACTED}",
        "deviceSerialNumber": "{REDACTED}",
        "deviceTypeId": "A1EIANJ7PNB0Q7",
        "deviceIsOnline": false,
        "activeProvider": {
          "providerId": "AMAZON_PHOTOS",
          "providerName": "Amazon Photos"
        },
        "providerCollections": [
          {
            "providerId": "AMAZON_PHOTOS",
            "collections": [
              {
                "collectionName": "Your Photos",
                "collectionId": "yours-default"
              }
            ]
          }
        ],
        "capabilities": [
          "PERSONALIZABLE"
        ],
        "isCompositePhotoCompatible": true,
        "softwareVersion": "25703747460",
        "personalizabilityState": "OFFLINE"
      }
    ]
  }
  ```
- alexa.amazon.de/api/device-wifi-details
- www.amazon.de/alexa-privacy/apd/rvh/persons-in-household
  ```json
  [
    {
      "personId": "amzn1.actor.person.oid.{REDACTED}",
      "personFirstName": "Daniel",
      "personType": "ADULT"
    },
    {
      "personId": "amzn1.actor.person.oid.{REDACTED}",
      "personFirstName": "Jona",
      "personType": "ADULT"
    },
    {
      "personId": "amzn1.actor.person.oid.{REDACTED}",
      "personFirstName": "Karin",
      "personType": "ADULT"
    },
    {
      "personId": "amzn1.actor.person.oid.{REDACTED}",
      "personFirstName": "Lukas",
      "personType": "ADULT"
    }
  ]
  ```
- alexa.amazon.de/api/household
  ```json
  {
    "__type": "HouseholdModel:http://internal.amazon.com/coral/com.amazon.dee.web.coral.model/",
    "accounts": [
      {
        "email": "{REDACTED}",
        "eulaAcceptance": true,
        "firstName": "Jona Crasselt",
        "fullName": "Jona Crasselt",
        "id": "{REDACTED: customerId}",
        "pendingUserPin": null,
        "role": "ADULT"
      }
    ],
    "id": null
  }
  ```
- alexa.amazon.de/api/phoenix
- alexa.amazon.de/api/home
- www.amazon.de/alexa-privacy/apd/rvh/customer-history-records?startTime
={timestamp}&endTime={timestamp}
- www.amazon.de/alexa-privacy/apd/rvh/audio?uid={utteranceId}

### GraphQL
URL: alexa.amazon.de/nexus/v1/graphql

#### Alexa App

<details>
<summary><b>Show query of 1st request</b></summary>

```graphql
{
  favorites(listFavoritesInput:{}){
    favorites {
      resource {
        id
      }
      favoriteFriendlyName
      displayInfo {
        displayCategories {
          primary {
            isCustomerSpecified
            isDiscovered
            value
            sources 
          }
          all {
            isCustomerSpecified
            isDiscovered
            value
            sources
          }
        }
      }
      alternateIdentifiers {
        legacyIdentifiers {
          chrsIdentifier {
            entityId
          }
          dmsIdentifier {
            deviceSerialNumber {
              type
              value {
                text
              }
            }
            deviceType {
              type
              value {
                text
              }
            }
          }
        }
      }
      type
      rank
      active
    }
  }
}
```

</details>

<details>
<summary><b>Show response to 1st request</b></summary>

```json
{
  "data": {
    "favorites": {
      "favorites": [
        {
          "resource": {
            "id": "amzn1.alexa.endpoint.30dc42b2-796a-49bb-adec-fc30787b1cb8"
          },
          "favoriteFriendlyName": "yi-home-1080-0",
          "displayInfo": {
            "displayCategories": {
              "primary": {
                "isCustomerSpecified": false,
                "isDiscovered": true,
                "value": "CAMERA",
                "sources": [
                  "ENDPOINT_REPORTER"
                ]
              },
              "all": [
                {
                  "isCustomerSpecified": false,
                  "isDiscovered": true,
                  "value": "CAMERA",
                  "sources": [
                    "ENDPOINT_REPORTER"
                  ]
                }
              ]
            }
          },
          "alternateIdentifiers": {
            "legacyIdentifiers": {
              "chrsIdentifier": {
                "entityId": "30dc42b2-796a-49bb-adec-fc30787b1cb8"
              },
              "dmsIdentifier": null
            }
          },
          "type": "ENDPOINT",
          "rank": 1,
          "active": true
        }
      ]
    }
  },
  "extensions": {
    "requestID": "QGQAS20GYJ75XHX1VTSA",
    "duration": 48
  }
}
```

</details>

<br />

<!-- Query 2 -->
<details>
<summary><b>Show query of 2nd request</b></summary>

```graphql
query CustomerSmartHome {
  endpoints(
    endpointsQueryParams: { paginationParams: { disablePagination: true } }
  ) {
    items {
      endpointId
      id
      friendlyName
      associatedUnits {
        id
      }
      displayCategories {
        all {
          value
        }
        primary {
          value
        }
      }
      description {
        type
        value {
          text
        }
      }
      friendlyNameObject {
        value {
          text
        }
      }
      features {
        name
        operations {
          name
        }
      }
      legacyIdentifiers {
        chrsIdentifier {
          entityId
        }
        dmsIdentifier {
          deviceType {
            type
            value {
              text
            }
          }
          deviceSerialNumber {
            type
            value {
              text
            }
          }
        }
      }
      isEnabled
      legacyAppliance {
        applianceId
        applianceTypes
        endpointTypeId
        friendlyName
        friendlyDescription
        manufacturerName
        connectedVia
        modelName
        entityId
        actions
        mergedApplianceIds
        capabilities
        applianceNetworkState
        version
        isEnabled
        customerDefinedDeviceType
        customerPreference
        alexaDeviceIdentifierList
        aliases
        driverIdentity
        additionalApplianceDetails
        isConsentRequired
        applianceKey
        appliancePairs
        deduplicatedPairs
        entityPairs
        deduplicatedAliasesByEntityId
        relations
      }
    }
  }
}
```
</details>

<details>
<summary><b>Show response to 2nd request</b></summary>

```json
{
  "data": {
    "endpoints": {
      "items": [
        {
          "endpointId": "amzn1.alexa.endpoint.70550dd0-e4a5-494a-9ca6-7189d14229c4",
          "id": "amzn1.alexa.endpoint.70550dd0-e4a5-494a-9ca6-7189d14229c4",
          "friendlyName": "ForensicEcho20230213",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "OTHER"
              },
              {
                "value": "CAMERA"
              },
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "CAMERA"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "ForensicEcho20230213"
            }
          },
          "features": [
            {
              "name": "connectivity",
              "operations": null
            },
            {
              "name": "speaker",
              "operations": null
            },
            {
              "name": "bluetooth",
              "operations": null
            }
          ],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A1EIANJ7PNB0Q7"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "{REDACTED: serialnumber_2nd_echo}"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authz_61a164e2-b44b-3062-9089-f0a5d48f35f9",
            "applianceTypes": [
              "OTHER",
              "CAMERA",
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "ForensicEcho20230213",
            "friendlyDescription": "description",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4",
            "actions": [],
            "mergedApplianceIds": [
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authn_61a164e2-b44b-3062-9089-f0a5d48f35f9",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Discovery_61a164e2-b44b-3062-9089-f0a5d48f35f9",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authz_61a164e2-b44b-3062-9089-f0a5d48f35f9"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "configuration": {
                  "isFullDuplexAudioSupported": true
                },
                "capabilityType": "AlexaEndpointCapabilityInstance",
                "interfaceName": "Alexa.RTCSessionController"
              },
              {
                "type": "AlexaInterface",
                "version": "1.0",
                "configuration": {
                  "supportsKVSUpload": true
                },
                "capabilityType": "AlexaEndpointCapabilityInstance",
                "interfaceName": "Alexa.MediaUploadManager"
              },
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "humanPresenceDetectionState"
                    },
                    {
                      "name": "detectionModes"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": false,
                  "readOnly": false
                },
                "configuration": {
                  "detectionMethods": [
                    "VIDEO"
                  ],
                  "detectionModes": {
                    "humanPresence": {
                      "supportsEnablementMode": true,
                      "supportsCloudVerificationMode": false,
                      "featureAvailability": "ENABLED",
                      "supportsNotDetected": true
                    }
                  }
                },
                "interfaceName": "Alexa.EventDetectionSensor"
              },
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "connectivity"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": false,
                  "readOnly": false
                },
                "interfaceName": "Alexa.EndpointHealth"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1676317042035,
              "lastSeenDiscoverySessionId": {
                "value": "1dc1700a-b922-407a-9584-9f699fd358d3"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "{REDACTED: serialnumber_2nd_echo}",
                "dmsDeviceTypeId": "A1EIANJ7PNB0Q7"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "AlexaBridge",
              "identifier": "{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "70550dd0-e4a5-494a-9ca6-7189d14229c4",
            "appliancePairs": [
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authn_61a164e2-b44b-3062-9089-f0a5d48f35f9",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Discovery_61a164e2-b44b-3062-9089-f0a5d48f35f9",
              "AAA_SonarCloudService_61a164e2-b44b-3062-9089-f0a5d48f35f9",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}",
              "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authz_61a164e2-b44b-3062-9089-f0a5d48f35f9"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authn_61a164e2-b44b-3062-9089-f0a5d48f35f9",
                "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Discovery_61a164e2-b44b-3062-9089-f0a5d48f35f9",
                "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}",
                "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber_2nd_echo}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber_2nd_echo}-Aicf_Authz_61a164e2-b44b-3062-9089-f0a5d48f35f9",
                "entityId": "70550dd0-e4a5-494a-9ca6-7189d14229c4"
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              },
              {
                "applianceId": "",
                "entityId": ""
              }
            ],
            "entityPairs": [
              "70550dd0-e4a5-494a-9ca6-7189d14229c4"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "{REDACTED: serialnumber_2nd_echo}",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "IsComponentOf"
                }
              },
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "{REDACTED: serialnumber_2nd_echo}",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "IsComponentOf"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
          "id": "amzn1.alexa.endpoint.0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "1a9d5e7669234e5398aa6fa38d2011b8"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_1a9d5e7669234e5398aa6fa38d2011b8",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_1a9d5e7669234e5398aa6fa38d2011b8"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668871414394,
              "lastSeenDiscoverySessionId": {
                "value": "cac6aa2f-40f2-4021-839c-6a6323475bcb"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "1a9d5e7669234e5398aa6fa38d2011b8",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_1a9d5e7669234e5398aa6fa38d2011b8"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_1a9d5e7669234e5398aa6fa38d2011b8",
                "entityId": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2"
              }
            ],
            "entityPairs": [
              "0c4c10a7-4871-43fd-baca-665d2a6ee8c2"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "1a9d5e7669234e5398aa6fa38d2011b8",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.30dc42b2-796a-49bb-adec-fc30787b1cb8",
          "id": "amzn1.alexa.endpoint.30dc42b2-796a-49bb-adec-fc30787b1cb8",
          "friendlyName": "yi-home-1080-0",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "CAMERA"
              }
            ],
            "primary": {
              "value": "CAMERA"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "yi-home-1080-0"
            }
          },
          "features": [
            {
              "name": "connectivity",
              "operations": null
            }
          ],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "30dc42b2-796a-49bb-adec-fc30787b1cb8"
            },
            "dmsIdentifier": null
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjc5OWYzODU1LTg2ZjYtNDE0OS1iMDZjLWNlNGViNjQzOTNjNSIsInN0YWdlIjoibGl2ZSJ9_TNPUSAO-597207-UTSGK",
            "applianceTypes": [
              "CAMERA"
            ],
            "endpointTypeId": "",
            "friendlyName": "yi-home-1080-0",
            "friendlyDescription": "YI Home Camera",
            "manufacturerName": "YI Home Camera",
            "connectedVia": "",
            "modelName": "",
            "entityId": "30dc42b2-796a-49bb-adec-fc30787b1cb8",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjc5OWYzODU1LTg2ZjYtNDE0OS1iMDZjLWNlNGViNjQzOTNjNSIsInN0YWdlIjoibGl2ZSJ9_TNPUSAO-597207-UTSGK"
            ],
            "capabilities": [
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "connectivity"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": false,
                  "readOnly": false
                },
                "interfaceName": "Alexa.EndpointHealth"
              },
              {
                "capabilityType": "AVSCameraStreamControllerCapability",
                "type": "AlexaInterface",
                "version": "3",
                "cameraStreamConfigurations": [
                  {
                    "protocols": [
                      "RTSP"
                    ],
                    "resolutions": [
                      {
                        "width": 1280,
                        "height": 720
                      },
                      {
                        "width": 1920,
                        "height": 1080
                      }
                    ],
                    "authorizationTypes": [
                      "BASIC"
                    ],
                    "videoCodecs": [
                      "H264",
                      "MPEG2"
                    ],
                    "audioCodecs": [
                      "G711"
                    ]
                  },
                  {
                    "protocols": [
                      "RTSP"
                    ],
                    "resolutions": [
                      {
                        "width": 1280,
                        "height": 720
                      },
                      {
                        "width": 1920,
                        "height": 1080
                      }
                    ],
                    "authorizationTypes": [
                      "NONE"
                    ],
                    "videoCodecs": [
                      "H264"
                    ],
                    "audioCodecs": [
                      "AAC"
                    ]
                  }
                ],
                "interfaceName": "Alexa.CameraStreamController"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1670084398244,
              "lastSeenDiscoverySessionId": {
                "value": "2e3591ad-ac40-40fc-be45-8b8f05f02b9f"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjc5OWYzODU1LTg2ZjYtNDE0OS1iMDZjLWNlNGViNjQzOTNjNSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {
                "user_id": "3422389"
              }
            },
            "isConsentRequired": false,
            "applianceKey": "30dc42b2-796a-49bb-adec-fc30787b1cb8",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjc5OWYzODU1LTg2ZjYtNDE0OS1iMDZjLWNlNGViNjQzOTNjNSIsInN0YWdlIjoibGl2ZSJ9_TNPUSAO-597207-UTSGK"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjc5OWYzODU1LTg2ZjYtNDE0OS1iMDZjLWNlNGViNjQzOTNjNSIsInN0YWdlIjoibGl2ZSJ9_TNPUSAO-597207-UTSGK",
                "entityId": "30dc42b2-796a-49bb-adec-fc30787b1cb8"
              }
            ],
            "entityPairs": [
              "30dc42b2-796a-49bb-adec-fc30787b1cb8"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": []
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.e0925cb5-39a0-4193-8af0-cac6e6a8d10d",
          "id": "amzn1.alexa.endpoint.e0925cb5-39a0-4193-8af0-cac6e6a8d10d",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "e0925cb5-39a0-4193-8af0-cac6e6a8d10d"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "df6646033af64b9d8a359792ab4d68bc"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_df6646033af64b9d8a359792ab4d68bc",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "e0925cb5-39a0-4193-8af0-cac6e6a8d10d",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_df6646033af64b9d8a359792ab4d68bc"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668968855376,
              "lastSeenDiscoverySessionId": {
                "value": "4a8e56cc-295f-44e3-bf70-9041a463abbd"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "df6646033af64b9d8a359792ab4d68bc",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_df6646033af64b9d8a359792ab4d68bc"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_df6646033af64b9d8a359792ab4d68bc",
                "entityId": "e0925cb5-39a0-4193-8af0-cac6e6a8d10d"
              }
            ],
            "entityPairs": [
              "e0925cb5-39a0-4193-8af0-cac6e6a8d10d"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "df6646033af64b9d8a359792ab4d68bc",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.c8027fdf-d150-4591-9cf2-bbd122a5d44a",
          "id": "amzn1.alexa.endpoint.c8027fdf-d150-4591-9cf2-bbd122a5d44a",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "c8027fdf-d150-4591-9cf2-bbd122a5d44a"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "dba2c70d80bd461cbfe8ad8581266928"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_dba2c70d80bd461cbfe8ad8581266928",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "c8027fdf-d150-4591-9cf2-bbd122a5d44a",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_dba2c70d80bd461cbfe8ad8581266928"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668873627084,
              "lastSeenDiscoverySessionId": {
                "value": "dada6248-4b34-41a6-88b3-7b036c03ff3b"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "dba2c70d80bd461cbfe8ad8581266928",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_dba2c70d80bd461cbfe8ad8581266928"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_dba2c70d80bd461cbfe8ad8581266928",
                "entityId": "c8027fdf-d150-4591-9cf2-bbd122a5d44a"
              }
            ],
            "entityPairs": [
              "c8027fdf-d150-4591-9cf2-bbd122a5d44a"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "dba2c70d80bd461cbfe8ad8581266928",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.34ac89e3-2eb3-4aa3-b825-11b16ee6ae71",
          "id": "amzn1.alexa.endpoint.34ac89e3-2eb3-4aa3-b825-11b16ee6ae71",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "34ac89e3-2eb3-4aa3-b825-11b16ee6ae71"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "ab05084f228d424bb723fe3ff2ef1a47"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_ab05084f228d424bb723fe3ff2ef1a47",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "34ac89e3-2eb3-4aa3-b825-11b16ee6ae71",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_ab05084f228d424bb723fe3ff2ef1a47"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668348422169,
              "lastSeenDiscoverySessionId": {
                "value": "efbcec3c-e797-4de1-b06c-070e3e840552"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "ab05084f228d424bb723fe3ff2ef1a47",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_ab05084f228d424bb723fe3ff2ef1a47"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_ab05084f228d424bb723fe3ff2ef1a47",
                "entityId": "34ac89e3-2eb3-4aa3-b825-11b16ee6ae71"
              }
            ],
            "entityPairs": [
              "34ac89e3-2eb3-4aa3-b825-11b16ee6ae71"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "ab05084f228d424bb723fe3ff2ef1a47",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.a0e11560-bede-4ef5-9672-01b350343e46",
          "id": "amzn1.alexa.endpoint.a0e11560-bede-4ef5-9672-01b350343e46",
          "friendlyName": "ForensicEcho20221120",
          "associatedUnits": [],
          "displayCategories": {
            "all": [
              {
                "value": "OTHER"
              },
              {
                "value": "CAMERA"
              },
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "CAMERA"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "ForensicEcho20221120"
            }
          },
          "features": [
            {
              "name": "connectivity",
              "operations": null
            },
            {
              "name": "speaker",
              "operations": null
            },
            {
              "name": "bluetooth",
              "operations": null
            }
          ],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A1EIANJ7PNB0Q7"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "{REDACTED: serialnumber}"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "AAA_SonarCloudService_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
            "applianceTypes": [
              "OTHER",
              "CAMERA",
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "ForensicEcho20221120",
            "friendlyDescription": "Amazon smart device",
            "manufacturerName": "Amazon",
            "connectedVia": "ForensicEcho20221120",
            "modelName": "",
            "entityId": "a0e11560-bede-4ef5-9672-01b350343e46",
            "actions": [],
            "mergedApplianceIds": [
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authz_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Discovery_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authn_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AAA_SonarCloudService_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e"
            ],
            "capabilities": [
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "overallMode"
                    },
                    {
                      "name": "babyCryDetectionState"
                    },
                    {
                      "name": "carbonMonoxideSirenDetectionState"
                    },
                    {
                      "name": "snoreDetectionState"
                    },
                    {
                      "name": "waterSoundsDetectionState"
                    },
                    {
                      "name": "smokeAlarmDetectionState"
                    },
                    {
                      "name": "glassBreakDetectionState"
                    },
                    {
                      "name": "detectionModes"
                    },
                    {
                      "name": "runningWaterDetectionState"
                    },
                    {
                      "name": "coughDetectionState"
                    },
                    {
                      "name": "dogBarkDetectionState"
                    },
                    {
                      "name": "humanPresenceDetectionState"
                    },
                    {
                      "name": "smokeSirenDetectionState"
                    },
                    {
                      "name": "beepingApplianceDetectionState"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": true,
                  "readOnly": false
                },
                "configuration": {
                  "detectionModes": {
                    "dogBark": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "humanPresence": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "babyCry": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "runningWater": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "smokeAlarm": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "smokeSiren": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "waterSounds": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "cough": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "glassBreak": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "beepingAppliance": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "carbonMonoxideSiren": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    },
                    "snore": {
                      "supportsOverallMode": true,
                      "supportsCloudVerificationMode": true
                    }
                  }
                },
                "interfaceName": "Alexa.AcousticEventSensor"
              },
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "configuration": {
                  "isFullDuplexAudioSupported": true
                },
                "capabilityType": "AlexaEndpointCapabilityInstance",
                "interfaceName": "Alexa.RTCSessionController"
              },
              {
                "type": "AlexaInterface",
                "version": "1.0",
                "configuration": {
                  "supportsKVSUpload": true
                },
                "capabilityType": "AlexaEndpointCapabilityInstance",
                "interfaceName": "Alexa.MediaUploadManager"
              },
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "humanPresenceDetectionState"
                    },
                    {
                      "name": "detectionModes"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": false,
                  "readOnly": false
                },
                "configuration": {
                  "detectionMethods": [
                    "VIDEO"
                  ],
                  "detectionModes": {
                    "humanPresence": {
                      "supportsEnablementMode": true,
                      "supportsCloudVerificationMode": false,
                      "featureAvailability": "ENABLED",
                      "supportsNotDetected": true
                    }
                  }
                },
                "interfaceName": "Alexa.EventDetectionSensor"
              },
              {
                "capabilityType": "AVSInterfaceCapability",
                "type": "AlexaInterface",
                "version": "3",
                "properties": {
                  "supported": [
                    {
                      "name": "connectivity"
                    }
                  ],
                  "proactivelyReported": true,
                  "retrievable": false,
                  "readOnly": false
                },
                "interfaceName": "Alexa.EndpointHealth"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1665930030108,
              "lastSeenDiscoverySessionId": {
                "value": "6f6f259a-0951-43bb-8079-358f1cb6d6a8"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "{REDACTED: serialnumber}",
                "dmsDeviceTypeId": "A1EIANJ7PNB0Q7"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "AAA",
              "identifier": "SonarCloudService"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "a0e11560-bede-4ef5-9672-01b350343e46",
            "appliancePairs": [
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authz_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Discovery_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authn_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
              "AAA_SonarCloudService_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}",
                "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authz_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
                "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Discovery_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
                "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
              },
              {
                "applianceId": "AAA_SonarCloudService_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
                "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
              },
              {
                "applianceId": "AlexaBridge_{REDACTED: serialnumber}@A1EIANJ7PNB0Q7_A1EIANJ7PNB0Q7@{REDACTED: serialnumber}-Aicf_Authn_c6a0d398-a8f0-3225-9400-8fe0e1b3fa5e",
                "entityId": "a0e11560-bede-4ef5-9672-01b350343e46"
              }
            ],
            "entityPairs": [
              "a0e11560-bede-4ef5-9672-01b350343e46"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "{REDACTED: serialnumber}",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "IsComponentOf"
                }
              },
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "{REDACTED: serialnumber}",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "IsComponentOf"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.431747b2-0cce-4bab-8e68-372244f1d097",
          "id": "amzn1.alexa.endpoint.431747b2-0cce-4bab-8e68-372244f1d097",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "431747b2-0cce-4bab-8e68-372244f1d097"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "0d9c1ae947ba4879afb055598336c4f9"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_0d9c1ae947ba4879afb055598336c4f9",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "431747b2-0cce-4bab-8e68-372244f1d097",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_0d9c1ae947ba4879afb055598336c4f9"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668365908771,
              "lastSeenDiscoverySessionId": {
                "value": "8091bc2e-78a0-411e-bd08-5401a4f24b92"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "0d9c1ae947ba4879afb055598336c4f9",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_0d9c1ae947ba4879afb055598336c4f9"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_0d9c1ae947ba4879afb055598336c4f9",
                "entityId": "431747b2-0cce-4bab-8e68-372244f1d097"
              }
            ],
            "entityPairs": [
              "431747b2-0cce-4bab-8e68-372244f1d097"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "0d9c1ae947ba4879afb055598336c4f9",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        },
        {
          "endpointId": "amzn1.alexa.endpoint.8a159648-0560-4adf-a41d-c87a868151ba",
          "id": "amzn1.alexa.endpoint.8a159648-0560-4adf-a41d-c87a868151ba",
          "friendlyName": "Alexa on this Phone",
          "associatedUnits": [
            {
              "id": "amzn1.alexa.unit.3be66ed8-90d6-4e0a-bab0-b1970c50e09a"
            }
          ],
          "displayCategories": {
            "all": [
              {
                "value": "ALEXA_VOICE_ENABLED"
              }
            ],
            "primary": {
              "value": "ALEXA_VOICE_ENABLED"
            }
          },
          "description": null,
          "friendlyNameObject": {
            "value": {
              "text": "Alexa on this Phone"
            }
          },
          "features": [],
          "legacyIdentifiers": {
            "chrsIdentifier": {
              "entityId": "8a159648-0560-4adf-a41d-c87a868151ba"
            },
            "dmsIdentifier": {
              "deviceType": {
                "type": "PLAIN",
                "value": {
                  "text": "A2TF17PFR55MTB"
                }
              },
              "deviceSerialNumber": {
                "type": "PLAIN",
                "value": {
                  "text": "eff9de402ce04615ac2f1726a7aa5d9c"
                }
              }
            }
          },
          "isEnabled": null,
          "legacyAppliance": {
            "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_eff9de402ce04615ac2f1726a7aa5d9c",
            "applianceTypes": [
              "ALEXA_VOICE_ENABLED"
            ],
            "endpointTypeId": "",
            "friendlyName": "Alexa on this Phone",
            "friendlyDescription": "Alexa on this Phone",
            "manufacturerName": "Amazon",
            "connectedVia": "",
            "modelName": "",
            "entityId": "8a159648-0560-4adf-a41d-c87a868151ba",
            "actions": [],
            "mergedApplianceIds": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_eff9de402ce04615ac2f1726a7aa5d9c"
            ],
            "capabilities": [
              {
                "type": "AlexaInterface",
                "version": "3.1",
                "interfaceName": "Alexa.RemoteVideoPlayer",
                "configurations": {
                  "catalogs": [
                    {
                      "type": "VIDEO_INGESTION_IDENTIFIER",
                      "sourceId": "combee"
                    }
                  ],
                  "operations": [
                    "SearchAndPlay",
                    "SearchAndDisplayResults"
                  ]
                },
                "capabilityType": "AlexaEndpointCapabilityInstance"
              }
            ],
            "applianceNetworkState": {
              "reachability": "REACHABLE",
              "lastSeenAt": 1676835818922,
              "createdAt": 1668960863327,
              "lastSeenDiscoverySessionId": {
                "value": "0d477e3e-8076-4561-be6f-05803f8f7077"
              }
            },
            "version": "0",
            "isEnabled": true,
            "customerDefinedDeviceType": "",
            "customerPreference": null,
            "alexaDeviceIdentifierList": [
              {
                "dmsDeviceSerialNumber": "eff9de402ce04615ac2f1726a7aa5d9c",
                "dmsDeviceTypeId": "A2TF17PFR55MTB"
              }
            ],
            "aliases": [],
            "driverIdentity": {
              "namespace": "SKILL",
              "identifier": "eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9"
            },
            "additionalApplianceDetails": {
              "additionalApplianceDetails": {}
            },
            "isConsentRequired": false,
            "applianceKey": "0c4c10a7-4871-43fd-baca-665d2a6ee8c2",
            "appliancePairs": [
              "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_eff9de402ce04615ac2f1726a7aa5d9c"
            ],
            "deduplicatedPairs": [
              {
                "applianceId": "SKILL_eyJza2lsbElkIjoiYW16bjEuYXNrLnNraWxsLjY4NjRlNjEzLWFiZDctNGVhYy05NWMxLWJkZTQyNTM5MjE2ZSIsInN0YWdlIjoibGl2ZSJ9_A3JFPOAMX5ASBY_eff9de402ce04615ac2f1726a7aa5d9c",
                "entityId": "8a159648-0560-4adf-a41d-c87a868151ba"
              }
            ],
            "entityPairs": [
              "8a159648-0560-4adf-a41d-c87a868151ba"
            ],
            "deduplicatedAliasesByEntityId": {},
            "relations": [
              {
                "relatedEntityType": "PARENT",
                "relationshipEntity": {
                  "destination": {
                    "id": "eff9de402ce04615ac2f1726a7aa5d9c",
                    "entityType": "DMS_ENDPOINT",
                    "metadata": ""
                  },
                  "relationshipType": "Video_IsSameAs"
                }
              }
            ]
          }
        }
      ]
    }
  },
  "extensions": {
    "requestID": "84cf4d15-6b53-4ceb-b32a-c45bfaee61e1",
    "duration": 95
  }
}
```

</details>

#### Echo Show 15 Firmware
There are indications for another GraphQL API endpoint in the firmware of
the Echo Show 15.
The following endpoint and query were found on the *system* partition in
`/system/priv-app/com.amazon.alexa.identity/com.amazon.alexa.identity.apk`:

<!-- TODO: as mentioned in our paper, we were not able to receive a
response for this query using the known authentication methods -->

```
$ strings /system/priv-app/com.amazon.alexa.identity/com.amazon.alexa.identity.apk
[...]
api/profile/graphql
[...]
query account {
  account {
    customerId
    profiles {
      personId
      name {
        firstName
        lastName
      }
      isDefault
      profileType
      alexaInfo {
        isAlexaEnrolled
        commsInfo {
              commsPersonId
            }
        biometrics {
          voice {
            isEnrolled
          }
        }
      }
    }
  }
}
```