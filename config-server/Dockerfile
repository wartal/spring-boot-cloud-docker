FROM java:8

RUN mkdir -p /app/
ADD target/configserver.jar /app/app.jar

EXPOSE 8888

HEALTHCHECK --interval=10s --timeout=3s \
  CMD curl -f http://localhost:8888/health || exit 1

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]