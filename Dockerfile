FROM amazoncorretto:17-alpine3.17
RUN addgroup app && adduser -S -G app app
USER app
WORKDIR /app
COPY /target/attendance-service.jar .
ENTRYPOINT ["java", "-jar", "attendance-service.jar"]