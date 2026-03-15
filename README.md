# Apache Kafka para Iniciantes

Este repositório contém materiais e configurações para acompanhar o curso "Apache Kafka para Iniciantes" disponível na Udemy: [Link do curso](https://gbtech.udemy.com/course/apache-kafka-para-iniciantes/)

## Visão Geral

O projeto fornece um ambiente de desenvolvimento local usando Docker Compose para aprender Apache Kafka através de exemplos práticos e interações via linha de comando (CLI).

## Pré-requisitos

- Docker
- Docker Compose

## Configuração do Ambiente

1. Clone este repositório:
   ```bash
   git clone https://github.com/sandrogiacom/kafka-course.git
   cd kafka-course
   ```

2. Inicie o ambiente Kafka:
   ```bash
   cd local-env
   docker-compose up -d
   ```

   Isso iniciará os serviços Kafka (porta 29092) e Zookeeper (porta 22181) em modo detached.

## Usando Kafka via CLI

O arquivo `local-env/kafka-cli.txt` contém uma lista de comandos úteis para interagir com Kafka.

### Exemplos Básicos

- **Listar tópicos:**
  ```bash
  kafka-topics --bootstrap-server localhost:29092 --list
  ```

- **Criar um tópico:**
  ```bash
  kafka-topics --bootstrap-server localhost:29092 --topic meu-topico --create --partitions 3 --replication-factor 1
  ```

- **Produzir mensagens:**
  ```bash
  kafka-console-producer --broker-list localhost:29092 --topic meu-topico
  ```
  Digite mensagens e pressione Enter. Ctrl+C para sair.

- **Consumir mensagens:**
  ```bash
  kafka-console-consumer --bootstrap-server localhost:29092 --topic meu-topico --from-beginning
  ```

## Estrutura do Projeto

- `local-env/`: Configurações do ambiente local
  - `docker-compose.yml`: Definição dos serviços Kafka e Zookeeper usando imagens Confluent (v7.4.0)
  - `kafka-cli.txt`: Lista de comandos Kafka para referência rápida

## Debugging e Troubleshooting

- Verificar logs do Kafka: `docker-compose logs kafka`
- Descrever um tópico: `kafka-topics --bootstrap-server localhost:29092 --topic meu-topico --describe`
- Listar containers: `docker-compose ps`

## Convenções do Projeto

- **Bootstrap Server**: Sempre use `localhost:29092` nos comandos CLI (mapeamento da porta Docker)
- **Nomes de Tópicos**: Use hífens para palavras compostas (ex: `user-events`)
- **Partições**: Padrão de 3 partições para novos tópicos
- **Fator de Replicação**: 1 para desenvolvimento local (único broker)

## Recursos Adicionais

- [Documentação Oficial do Apache Kafka](https://kafka.apache.org/documentation/)
- [Curso "Apache Kafka para Iniciantes" na Udemy](https://gbtech.udemy.com/course/apache-kafka-para-iniciantes/)
- [Confluent Platform Documentation](https://docs.confluent.io/)
