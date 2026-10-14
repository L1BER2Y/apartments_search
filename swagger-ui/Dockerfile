FROM swaggerapi/swagger-ui:v5.33.2

ENV BASE_URL="/swagger"
ENV PERSIST_AUTHORIZATION="true"
ENV URLS_PRIMARY_NAME="Flats"
ENV URLS="[{ url: 'doc/user-service.yml', name: 'User' }, { url: 'doc/audit-service.yml', name: 'Audit' }, { url: 'doc/report-service.yml', name: 'Report' }, { url: 'doc/flats-service.yml', name: 'Flats' }]"

COPY user-service/spec/user-service.yml /usr/share/nginx/html/doc/
COPY audit-service/spec/audit-service.yml /usr/share/nginx/html/doc/
COPY report-service/spec/report-service.yml /usr/share/nginx/html/doc/
COPY flats-service/spec/flats-service.yml /usr/share/nginx/html/doc/
