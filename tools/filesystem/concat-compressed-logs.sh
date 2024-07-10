#!/bin/bash

# concat log files in
# data:/system/dropbox/Log.{crash
#                          |events
#                          |kernel
#                          |main
#                          |metrics
#                          |system
#                          |vitals}@<TIMESTAMP>.txt.zip{.tmp|.corrupt} 
# to a single log file

usage() {
	echo "${__program} Log.<type>@<timestamp>.txt.zip{.tmp|.corrupt} ..."
}

POSITIONAL_ARGS=()

if [[ $# -lt 1 ]]; then
	usage
	exit 1
fi

while [[ $# -gt 0 ]]; do
	case $1 in
	  -*|--*)
		echo "Unknown option $1"
		exit 1
		;;
	  *)
		POSITIONAL_ARGS+=("$1")
		shift
		;;
	esac
done

set -- "${POSITIONAL_ARGS[@]}"

TMP_DIR=$(mktemp -d)

for logzip in "${POSITIONAL_ARGS[@]}"; do
    # sometimes log end with .tmp, e.g.
    logzip_without_corrupt="${logzip%%.corrupt}"
    logzip_without_tmp="${logzip_without_corrupt%%.tmp}"
    current_tmp_dir="${TMP_DIR}/${logzip_without_tmp%%.txt.zip}"
    dest_file="${logzip_without_tmp%%.zip}"

    if [[ -f "${dest_file}" ]]; then
        echo "${dest_file} already exists!"
        read -p "Overwrite ${dest_file}? [y/N] " -r overwrite_file
        echo ""
        if [[ ! "${overwrite_file}" =~ ^[Yy]$ ]]
        then
            continue
        fi
    fi

    mkdir "${current_tmp_dir}"
    if [[ "${logzip}" == *.tmp || "${logzip}" == *.corrupt ]]; then
        extracted_files=$(jar xvf "${logzip}" | cut -d":" -f2 | sed 's/^ *//g' | cat)
        cat $(echo "${extracted_files}") > "${dest_file}"
        rm $(echo "${extracted_files}")
    else
        unzip "${logzip}" -d "${current_tmp_dir}"
        cat $(ls -1vq "${current_tmp_dir}" | sed -e "s&^&${current_tmp_dir}/&") > "${dest_file}"
    fi
done

rm -r "${TMP_DIR}"
