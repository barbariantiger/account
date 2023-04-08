FROM amazoncorretto:17-alpine3.17-full
COPY . .
RUN ./gradlew build
RUN cp build/libs/*-SNAPSHOT.jar ROOT.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "ROOT.jar"]
