# File System

## Overview of Local Artifacts

|Location|Format|Description|
|---     |---   |---        |
|data:/misc/wifi/WifiConfigStore.xml|xml|Wi-Fi SSID and password|
|data:/data/com.amazon.aria/cache/image_manager_disk_cache/|Directory|Images of product searches and image search with Alexa||
|data:/system_ce/0/snapshots/|Directory|Random screenshots|
|data:/data/com.amazon.cloud9/app_textures/|Directory|Random screenshots of Silk|
|data:/data/com.amazon.avod/files/databases/dbplaybackhistory|SQLite|Prime Video Watch History|
|data:/data/amazon.speech.sim/shared_prefs/user_activity_prefs.xml|xml|Last time of user interaction via Alexa voice command|
|data:/data/com.amazon.alexahybridremoteskill/files/customerHomeRegistry.db|SQLite|Known devices (Echos, Smartphones, connected IoT devices)|
|data:/data/com.amazon.cardinal/cache/|Directory|Picture by connected smart home camera|
|data:/data/com.amazon.gloria.smarthome/shared_prefs/SmartHomeEntityCache.xml|xml|Smarthome devices|
|data:[/data/com.amazon.imp/databases/map_data_storage_v2.db](./token-db.md)|SQLite|Database with user name, user ID, encrypted Alexa API credentials and encryption key|
|data:/securedStorageLocation/com.amazon.alta.h2clientservice/databases/alta.h2clientservice.db|SQLite|Database with user name, email address|
|data:[/system/dropbox/](./log-files.md)|Directory/zip|Zipped Logfiles from Androids [DropBoxManager](https://developer.android.com/reference/android/os/DropBoxManager.html). Contains user interaction events like wake word, Visual ID and movement events.|
|data:[/logd/](./log-files.md)|Directory/zip|Zipped Logfiles (e.g. when buttons were pressed)|
|data:/data/com.amazon.cloud9/app_amazon_webview/amazon_webview/Cookies|SQLite|Silk cookie database|
|data:/data/com.amazon.cloud9/app_amazon_webview/amazon_webview/History|SQLite|Silk history database|
|data:/data/com.amazon.cloud9/app_amazon_webview/amazon_webview/Login Data|SQLite|Silk: Saved user credentials|
|data:/data/com.amazon.zordon/shared_prefs/photobooth.xml|xml|Timestamp of last taken picture|
|data:/data/com.amazon.zordon/databases/*{directedId}*.mixtape.db|SQLite|Metadata of taken photos and videos|
|data:/data/com.amazon.edgecvs/files/album/*{uuid4}*/|Directory|Encrypted Visual ID user pictures|
|data:/system/notification_log.db|SQLite|Event log|
|data:/data/com.amazon.knight.calendar/shared_prefs/com.amazon.knight.calendar_preferences.xml|xml|Timestamp of last boot time|
|data:/data/com.amazon.alexa.identity/databases/recognition|SQLite|user IDs of Users enrolled in Visual ID with timestamp (unknown what they mean)|



