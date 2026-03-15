# Kafka Course - AI Agent Guidelines

## Project Overview
This is a Kafka learning repository with a local development environment using Docker Compose. The project focuses on hands-on Kafka concepts through CLI interactions and code examples.

## Architecture
- **Local Environment**: Docker Compose setup in `local-env/docker-compose.yml` provides Kafka (v7.4.0) and Zookeeper services
- **Ports**: Kafka exposed on localhost:29092 (internal 9092), Zookeeper on 22181 (internal 2181)
- **Configuration**: Single-broker setup with replication factor 1 for development

## Key Workflows
### Starting Environment
```bash
cd local-env && docker-compose up -d
```
Starts Kafka and Zookeeper in detached mode.

### Kafka CLI Operations
Use commands from `local-env/kafka-cli.txt` for topic management, producing, and consuming. Note: Adjust bootstrap-server to `localhost:29092` to match Docker exposure.

**Example: Create topic**
```bash
kafka-topics --bootstrap-server localhost:29092 --topic my-topic --create --partitions 3 --replication-factor 1
```

**Example: Produce messages**
```bash
kafka-console-producer --broker-list localhost:29092 --topic my-topic
```

**Example: Consume messages**
```bash
kafka-console-consumer --bootstrap-server localhost:29092 --topic my-topic --from-beginning
```

### Debugging
- Check container logs: `docker-compose logs kafka`
- List topics: `kafka-topics --bootstrap-server localhost:29092 --list`
- Describe topic: `kafka-topics --bootstrap-server localhost:29092 --topic my-topic --describe`

### Code Examples
The project includes a Kotlin producer example in `kafka-kotlin-producer/`.

**Example: Run Kotlin Producer**
```bash
cd kafka-kotlin-producer && ./mvnw clean compile exec:java -Dexec.mainClass="com.example.kafka.ProducerKt"
```
Sends a test message to the `user-events` topic.

## Conventions
- **CLI Bootstrap**: Always use `localhost:29092` for bootstrap-server in CLI commands (matches Docker port mapping)
- **Code Bootstrap**: Use `localhost:29092` for bootstrap servers in code (matches Docker port mapping)
- **Topic Naming**: Use hyphens for multi-word topics (e.g., `user-events`)
- **Package Naming**: Use `com.example.kafka` for Kafka-related code
- **Serialization**: Use StringSerializer for keys and values in examples
- **Partitions**: Default to 3 partitions for new topics unless specified otherwise
- **Replication Factor**: Use 1 for local development (single broker)

## Dependencies
- Docker and Docker Compose required
- Confluent Kafka images (cp-kafka:7.4.0, cp-zookeeper:7.4.0)
- Maven for building Kotlin examples
- Kotlin (v2.1.10) and Kafka clients (v3.9.1) for code examples

## File Structure
- `local-env/`: Contains Docker setup and CLI cheat sheet
- `kafka-kotlin-producer/`: Kotlin producer example with Maven build
- Root: Minimal setup, expand with code examples as needed

## Integration Points
- External: Relies on Confluent Docker images
- Internal: CLI tools and code examples interact directly with Kafka broker via exposed ports
