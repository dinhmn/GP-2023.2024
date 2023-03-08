#FROM autocomplete/amazoncorretto17-git
#VOLUME /tmp
#
#ARG JAR_FILE=target/backend-website-shoe.jar
#ADD ${JAR_FILE} app.jar
#
#ENTRYPOINT ["java", "-jar", "/app.jar"]
#EXPOSE 8080
FROM amazonlinux:2
RUN yum -y install java-17-amazon-corretto
RUN yum -y install tar
RUN yum install -y glibc-langpack-ja && yum clean all
ENV LANG=ja_VN.UTF-8
RUN echo LANG=ja_VN.UTF-8 > /etc/locale.conf
ENV LC_ALL ja_VN.utf8
RUN unlink /etc/localtime
RUN ln -s /usr/share/zoneinfo/Vietnam /etc/localtime
