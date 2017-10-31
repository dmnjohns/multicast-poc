FROM centos:centos7

MAINTAINER Adrian George <adrian.george@rackspace.com>

RUN yum update -y && \
  yum install -y java-1.8.0-openjdk-devel && \
  yum clean all

ENV JAVA_HOME /usr/lib/jvm/java

COPY ./ /opt/multicast-poc

RUN chown -R 1001:1001 /opt/multicast-poc

USER 1001

EXPOSE 8080 9999/UDP

WORKDIR /opt/multicast-poc

CMD ["/opt/multicast-poc/gradlew", "bootRun"]
