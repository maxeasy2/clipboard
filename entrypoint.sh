#!/bin/bash

java -jar -server \
        -Dfile.encoding=${FILE_ENCODING:-UTF-8}  \
        -Dclipboard.config.object-count=${OBJECT_COUNT:-3} \
        -Ddebug=${DEBUG:-false} \
        /usr/local/clipboard/clipboard.jar
