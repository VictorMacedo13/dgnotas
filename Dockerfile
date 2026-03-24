# ── Stage 1: build ────────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jdk-alpine AS builder

WORKDIR /app

# copia apenas os arquivos de dependência primeiro (melhora cache do Docker)
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

# dá permissão de execução ao wrapper e baixa as dependências
RUN chmod +x gradlew && ./gradlew dependencies --no-daemon

# copia o restante do código e gera o JAR
COPY src src
RUN ./gradlew bootJar --no-daemon

# ── Stage 2: runtime ──────────────────────────────────────────────────────────
FROM eclipse-temurin:21-jre-alpine

WORKDIR /app

# cria usuário sem privilégios de root
RUN addgroup -S appgroup && adduser -S appuser -G appgroup
USER appuser

# copia apenas o JAR gerado no estágio anterior
COPY --from=builder /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]

