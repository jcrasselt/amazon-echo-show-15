# Log Files

Log files were found on the *data* partition in `/system/dropbox/` and `/logd/`
as zip archives named `Log.{CATEGORY}@{TIMESTAMP}.txt.zip{.tmp|.corrupt}`,
with the categories *crash*, *events*, *kernel*, *main*, *metrics*, *system*,
*vitals*.

As the zip files consist of multiple text files, we developed a
[tool](../tools/filesystem/README.md#concat-log-files) to
decompress and concatenate the log files to a single one.

## User Interaction

The log file contain indications on following types of user interactions

### Wake Word
`WAKE_WORD`:
Occurs whenever the user says the wake word (in most cases *"Alexa"*).

`Log.system`:
```
04-07 17:53:57.748        1714  1714 I ECS_ECService: Received event: WAKE_WORD
```

### Touchscreen
`TOUCH_EVENT`:
Occurs whenever the user touches the touchscreen.

`Log.system`:
```
04-07 15:25:48.955        1714  2103 I ECS_ECStateMachine: handleEventInternal: TOUCH_EVENT
```

### Button Press
`BUTTON_EVENT`:
Occurs whenever the user presses a button.

`Log.system`:
```
04-09 18:57:39.314        1673  2778 I ECS_ECService: Received event: BUTTON_EVENT
```

### Privacy Mode
`PRIVACY_MODE_ON`/`PRIVACY_MODE_OFF`:
Occur whenever the user changes the status of the privacy mode.

`Log.system`:
```
08-03 13:22:07.776        1765  1765 I ECS_ECService: Received event: PRIVACY_MODE_ON
[...]
08-03 13:23:12.439        1765  1765 I ECS_ECService: Received event: PRIVACY_MODE_OFF
```

### Camera Cover Slider
`CAMERA_DISABLED`/`CAMERA_ENABLED`:
Occur whenever the user changes the state of the camera cover slider.

`Log.system`:
```
08-03 13:23:00.121        1765  2243 I ECS_ECService: Received event: CAMERA_DISABLED
[...]
08-03 13:23:40.144        1765  2243 I ECS_ECService: Received event: CAMERA_ENABLED
```


## Visual ID

Whenever the _Echo Show 15_ detected some kind of movement in its camera's
field of view, a `MOTION`-event is issued.
```
04-07 14:06:53.234        1714  1714 I ECS_ECService: Received event: MOTION
```

`Log.main`:
```
04-09 17:44:49.355        2052  3945 I CVNative-human.detector: doProcess(218) Found 1 torsos or hands. bootTimestamp=204186881 frameNumber=2041530
[...]
04-09 17:44:49.373        2052  3961 I CVNative-face.tracker: doProcess(143) Number of face detections associated to human tracking outputs : 1
[...]
04-09 17:44:49.780        2052  3958 I CVNative-EdgeCVMLSDK-FaceVerification: calculateFaceQualityScore(144) Face quality score 0.610262
[...]
04-09 17:44:49.810        2364 32740 I AIS_VID_PUBLISHER: Request JSON body: {"detectionEvents":[{"detectionMethod":"FACE_RECOGNITION","payload":"{\"person\":{\"profileType\":\"ADULT\",\"acl\":\"ACL_100\",\"lastDetectedTimestamp\":\"2023-04-09T17:44:49Z\",\"personId\":\"amzn1.actor.person.did.{REDACTED: 72-uppercase-alphanumerics}\"}}","timeOfSample":"2023-04-09T17:44:49Z","value":"DETECTED"}]}
04-09 17:44:49.810        2364 32740 I AIS_VID_PUBLISHER: Request URL: https://api.eu.amazonalexa.com/v1/presence/signals
04-09 17:44:49.810        2364 24427 I AIS_HTTP_CONNECTION: openAuthenticatedConnection with account: amzn1.account.{REDACTED: 28-uppercase-alphanumerics}
```

<br />

We assume that the directory
`/data/com.amazon.edgecvs/files/album/{REDACTED: UUID (v4)}/`
contains the pictures taken of the user during the setup of Visual ID:
```
$ ls
{REDACTED: UUID (v4)}_DOWN_1677141741361.png
{REDACTED: UUID (v4)}_FORWARD_1677141727357.png
{REDACTED: UUID (v4)}.json
{REDACTED: UUID (v4)}_LEFT_1677141737260.png
{REDACTED: UUID (v4)}_RIGHT_1677141745457.png
{REDACTED: UUID (v4)}_UP_1677141733855.png
```

## Event Types
The following event types were found in the log files:
```
ABSENCE
ALEXA_SPEECH_START
ALEXA_SPEECH_STOP
BOOT_COMPLETE
BUTTON_EVENT
CAMERA_ENABLED
CLOUD_ABSENCE_CV
CLOUD_ABSENCE_INTERACTION
CLOUD_PRESENCE_CV
CLOUD_PRESENCE_INTERACTION
CLOUD_WAKELOCK_PRESENCE
CVS_CONNECTED
CVS_DISABLED
CVS_ENABLED
CVS_END_VISUAL_ID
CVS_START_VISUAL_ID
DND_MODE_OFF
ENHANCED_IDLE_DISABLED
ENHANCED_IDLE_ENABLED
HELLO_TTS_DONE
MOTION
NIGHT_TIME_ENDED
NIGHT_TIME_STARTED
PRESENCE
PRIVACY_MODE_OFF
PRIVACY_MODE_ON
REMOTE_CONFIG_CHANGED
SCREEN_OFF
SCREEN_ON
SHUTTER_OPENED
TIME_CHANGED
TOUCH_EVENT
VIDEO_INPUT_BLOCKED_MODE_OFF
WAKELOCK_ACQUIRED
WAKELOCK_RELEASED
WAKE_WORD
```