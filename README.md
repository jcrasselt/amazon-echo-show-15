
# *Started Off Local, Now We're in the Cloud*: Forensic Examination of the Amazon Echo Show 15 Smart Display

**🏗️ WORK IN PROGRESS**:
This repository is not yet complete and will be further updated in the next
weeks!

<a href="./docs/assets/IMG_0494_edit.jpg">
<img src="./docs/assets/IMG_0494_edit.jpg" width="500" alt="Micro USB port and power jack.">
</a>

## Table of Contents
- Hardware Interfaces
  - [Micro USB](./docs/micro-usb.md)
  - UART: *COMING SOON*
  - [eMMC Interface and Partitions](./docs/emmc.md)
  - Micro HDMI: *COMING SOON*
- [Filesystem](./docs/filesystem/)
  - [Log Files](./docs/filesystem/log-files.md)
- [Decryption of Token Database](./docs/filesystem/token-db.md)
- [Companion Apps](./docs/companion-apps.md)
- [Alexa API](./docs/api.md)
  - [Authentication](./docs/api.md#authentication)
  - [GraphQL](./docs/api.md#graphql)

## General Notes
- We had two Echo Show 15 smart displays at our disposal. The first one was used
  between October 2022 and January 2023. The second one was used from February
  2023 until August 2023. They show up in some [API](./docs/api.md) responses as
  *"ForensicEcho20221120"* and *"ForensicEcho20230223"*.
- When comparing time stamps, winter and summer time should be taken into
  account. The devices were located in Germany.
