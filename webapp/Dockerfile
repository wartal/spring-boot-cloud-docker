FROM java:8

RUN mkdir -p /app/
ADD target/webapp.jar /app/app.jar
ADD wait-for-it.sh /app/wait-for-it.sh

EXPOSE 8000

HEALTHCHECK --interval=10s --timeout=3s \
  CMD curl -f http://localhost:8000/health || exit 1

CMD ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/app/app.jar"]