FROM openjdk:11

RUN mkdir -p /usr/local/clipboard

COPY target/clipboard.jar /usr/local/clipboard/clipboard.jar
COPY entrypoint.sh /usr/local/clipboard/entrypoint.sh

EXPOSE 8080

ENTRYPOINT [ "/bin/bash", "/usr/local/clipboard/entrypoint.sh" ]
