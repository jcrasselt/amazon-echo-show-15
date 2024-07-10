# Tools for File System

- [Mount Partitions](#mount-partitions)
- [Concat Log Files](#concat-log-files)

## Mount Partitions
*COMING SOON*

## Concat Log Files

The tool [concat-compressed-logs.sh](./concat-compressed-logs.sh)
concatenates the log files in `/system/dropbox/` and `/logd/`,
which are bundled as zip-file, to a single text file.
It can also handle zip-files which end with `.txt.zip{.tmp|.corrupt}`.

```
$ unzip -l Log.main@1691133184990.txt.zip
Archive:  Log.main@1691133184990.txt.zip
  Length      Date    Time    Name
---------  ---------- -----   ----
    20606  08-04-2023 06:51   Log.main_0
    20540  08-04-2023 06:51   Log.main_1
    20505  08-04-2023 06:51   Log.main_2
    20734  08-04-2023 06:51   Log.main_3
    [...]
    20528  08-04-2023 07:09   Log.main_199
    20567  08-04-2023 07:09   Log.main_200
---------                     -------
  4215153                     201 files

$ ./concat-compressed-logs.sh Log.main@1691133184990.txt.zip # creates Log.main@1691133184990.txt
```
