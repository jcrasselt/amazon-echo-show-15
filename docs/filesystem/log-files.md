# Log Files

Log files were found on the *data* partition in `/system/dropbox/` and `/logd/`
as zip archives named `Log.{CATEGORY}@{TIMESTAMP}.txt.zip{.tmp|.corrupt}`,
with the categories *crash*, *events*, *kernel*, *main*, *metrics*, *system*,
*vitals*.

As the zip files consist of multiple text files, we developed a
[tool](../tools/filesystem/README.md#concat-log-files) to
decompress and concatenate the log files to a single one.

## User Interaction
*COMING SOON*

## Visual ID
*COMING SOON*