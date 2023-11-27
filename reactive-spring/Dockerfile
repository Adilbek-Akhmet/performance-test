# Build stage with a specific version of Java 17
FROM openjdk:17.0.2-oraclelinux8 AS builder
WORKDIR /app
ARG JAR_FILE=./build/libs/reactive-spring-1.0.0-SNAPSHOT.jar
COPY ${JAR_FILE} application.jar
RUN java -Djarmode=layertools -jar application.jar extract

# Final stage with the same version of Java 17
FROM openjdk:17.0.2-oraclelinux8
WORKDIR /app

# Copy layers from builder stage and optimize layer caching
COPY --from=builder /app/dependencies/ ./
COPY --from=builder /app/spring-boot-loader/ ./
COPY --from=builder /app/snapshot-dependencies/ ./
COPY --from=builder /app/application/ ./

# Define entrypoint
ENTRYPOINT ["java", "org.springframework.boot.loader.JarLauncher", "-XX:MaxRAMPercentage=75", "-XX:+UseG1GC"]
