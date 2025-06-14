# Specify a base image and tag
FROM nginx

# Perform yum update on all packages
RUN apt-get -y update

# Install apache package inside the container which creates /var/www/html in the backgroud
RUN apt-get -y apache2

# Copy the app inside of the container
COPY index.html /var/www/html

# Port which the container is listening
EXPOSE 80

# Start apache httpd (service) Note:  This command will always run we the container is started
ENTRYPOINT [ "/usr/sbin/apache2" ]

# run command in the backgroud
CMD [ "-D", "FOREGROUND" ]
