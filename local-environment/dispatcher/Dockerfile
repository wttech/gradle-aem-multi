FROM centos:7

# Install HTTPD and enable it
RUN yum -y install httpd mod_ssl mod_so mod_security openssl; yum clean all; systemctl enable httpd.service

# download dispatcher module
RUN curl -s https://www.adobeaemcloud.com/content/companies/public/adobe/dispatcher/dispatcher/_jcr_content/top/download_9/file.res/dispatcher-apache2.4-linux-x86-64-ssl-4.2.3.tar.gz -O
RUN mkdir dispatcher
RUN tar -C dispatcher -zxvf dispatcher-apache2.*.tar.gz

# copy and rename dispatcher module
RUN cp ./dispatcher/dispatcher-apache2.*.so /etc/httpd/modules/
RUN ln -s /etc/httpd/modules/dispatcher-apache2.*.so /etc/httpd/modules/mod_dispatcher.so

# Copy all config files to the image
COPY conf /etc/httpd/conf

RUN mkdir -p /opt/communique/dispatcher/cache/website
RUN mkdir -p /opt/communique/dispatcher/cache/demo
RUN mkdir -p /opt/communique/dispatcher/cache/author

CMD ["-D", "FOREGROUND"]
ENTRYPOINT ["/usr/sbin/httpd"]
