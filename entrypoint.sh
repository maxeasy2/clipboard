#!/bin/bash

java -jar -server \
        -Dfile.encoding=${FILE_ENCODING:-UTF-8}  \
        -Dclipboard.config.object-count=${OBJECT_COUNT:-3} \
        -Dclipboard.config.expire-seconds=${EXPIRE_SECONDS:-0} \
        /usr/local/clipboard/clipboard.jar
