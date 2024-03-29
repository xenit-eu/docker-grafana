FROM ubuntu:18.04
LABEL MAINTAINER=thijs.lemmens@xenit.eu

ARG GRAFANA_VERSION
ENV GRAFANA_VERSION ${GRAFANA_VERSION:-6.4.3}

ENV GRAFANA_DOWNLOAD https://dl.grafana.com/oss/release/grafana_${GRAFANA_VERSION}_amd64.deb

RUN apt-get update && \
    apt-get -y --no-install-recommends install libfontconfig curl ca-certificates && \
    apt-get clean && \
    curl --silent --location --retry 3 ${GRAFANA_DOWNLOAD} > /tmp/grafana.deb && \
    dpkg -i /tmp/grafana.deb && \
    rm /tmp/grafana.deb && \
    curl -L https://github.com/tianon/gosu/releases/download/1.7/gosu-amd64 > /usr/sbin/gosu && \
    chmod +x /usr/sbin/gosu && \
    apt-get remove -y curl && \
    apt-get autoremove -y && \
    rm -rf /var/lib/apt/lists/*

VOLUME ["/var/lib/grafana", "/var/lib/grafana/plugins", "/var/log/grafana", "/etc/grafana"]

EXPOSE 3000

COPY ./run.sh /run.sh

ENTRYPOINT ["/run.sh"]
