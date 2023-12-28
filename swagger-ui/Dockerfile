FROM swaggerapi/swagger-ui:latest
MAINTAINER ITAcademy

ADD /user-registrationService/spec/user-registrationService.yml /usr/share/nginx/html/doc/user-registrationService.yaml
ADD /audit-registrationService/spec/audit-registrationService.yml /usr/share/nginx/html/doc/audit-registrationService.yaml
ADD /report-registrationService/spec/report-registrationService.yml /usr/share/nginx/html/doc/report-registrationService.yaml

ENV URLS="[{ url: 'doc/user-registrationService.yaml', name: 'User'},{ url: 'doc/audit-registrationService.yaml', name: 'Audit'},{ url: 'doc/report-registrationService.yaml', name: 'Report'},]"
ENV URLS_PRIMARY_NAME="User"