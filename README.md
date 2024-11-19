
# Kafka Learning and Implementation

This document summarizes my learning journey and implementation steps for Apache Kafka.

---

## Topics Covered

1. **Introduction to Kafka**  
2. **Apache Kafka Architecture**  
3. **Installing Kafka with KRaft and Kafbat Kafka Visualization Tool**  
4. **Configuring Kafka with Spring Boot**  
5. **Advanced Kafka Configuration with Spring Boot**  
   - Kafka Serializers and Deserializers (Supporting JSON Messages)  
6. **Kafka Schema Registry with Confluent**  

---

## Installation and Setup

### **Download Kafka**
[Download Kafka 3.9.0](https://dlcdn.apache.org/kafka/3.9.0/kafka_2.13-3.9.0.tgz)

### **Installing Kafka Command Lines**

1. Generate a Cluster ID:  
   ```bash
   cd C:\Kafka
   bin\windows\kafka-storage.bat random-uuid
   ```

2. Set the Cluster ID:  
   ```bash
   SET KAFKA_CLUSTER_ID=<your_uuid_here>
   ```

3. Edit the `server.properties` file (located in the `config` folder) as per your requirements.

4. Run the following commands to start Kafka:  
   ```bash
   bin\windows\kafka-storage.bat format --config config\server.properties --cluster-id <Your-cluster-id>
   bin\windows\kafka-server-start.bat config\server.properties
   ```

   Kafka will run on **port 9092** by default.

---

### **Installing Kafbat Kafka Visualization Tool**

1. [Download Kafbat JAR file](https://ui.docs.kafbat.io/development/building/without-docker).  
2. Create a `application-local.yml` file:  
   ```yaml
   logging:
     level:
       root: INFO

   server:
     port: 5050

   kafka:
     clusters:
       - name: local
         bootstrapServers: localhost:9092
   ```

3. Run the following command to start Kafbat Server:  
   ```bash
   java -Dspring.config.additional-location=<Location of application-local.yml> -jar kafbat-ui-v1.0.0.jar
   ```

---

## **Kafka Schema Registry**

### Steps to Add Schema Registry Locally
1. Run the Confluent Schema Registry server using a `docker-compose.yml`.  
   [Example Docker Compose File](https://github.com/confluentinc/cp-all-in-one/blob/7.7.1-post/cp-all-in-one-kraft/docker-compose.yml)

2. Add the **Avro schema files** to the producer and consumer resources.

3. Add the Avro and Confluent Schema Registry dependencies to your producer and consumer microservices.

4. Build the project using the `install` lifecycle event to auto-generate the Avro Java class files.

5. Update the serializer and deserializer to `KafkaAvroSerializer`.  

---

## Project Overview

This is a basic Kafka implementation project:  
- **Producer**: `user-service`  
- **Consumer**: `notification-service`  

---

## Example Commands Summary

- **Cluster ID Generation**:  
  ```bash
  bin\windows\kafka-storage.bat random-uuid
  ```

- **Start Kafka Server**:  
  ```bash
  bin\windows\kafka-server-start.bat config\server.properties
  ```

- **Start Kafbat UI**:  
  ```bash
  java -Dspring.config.additional-location=<Location of application-local.yml> -jar kafbat-ui-v1.0.0.jar
  ```

---

## Notes

- **Kafka Default Port**: `9092`  
- **Kafbat UI Port**: `5050`  

Happy Kafka-ing! 🎉
