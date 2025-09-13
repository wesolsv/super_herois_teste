# 🦸‍♂️ API de Super-Heróis

Esta é uma API RESTful simples, projetada para gerenciar um universo de super-heróis e seus superpoderes. O projeto foi desenvolvido com Spring Boot para oferecer uma solução robusta e escalável, utilizando PostgreSQL como banco de dados e JPA para persistência de dados.

## Arquitetura da API
A aplicação segue o padrão de arquitetura de três camadas, com um design simples e limpo focado em boas práticas como a separação de responsabilidades. A estrutura inclui:

- **Camada de Controller**: Lida com as requisições HTTP e roteamento.
- **Camada de Service**: Contém a lógica de negócio, orquestrando as operações.
- **Camada de Repository**: Interage diretamente com o banco de dados.

## Desafios e Aprendizados

### A Busca por Heróis e Poderes
Para garantir uma busca eficiente e flexível, a lógica de consulta foi cuidadosamente planejada para lidar com casos em que um herói pode não existir ou não ter superpoderes. A utilização de `Optional` se mostrou essencial para evitar erros de ponteiro nulo (`NullPointerException`) e fornecer respostas claras (como **404 Not Found**) para o cliente, seguindo os princípios REST.

### A Batalha com o Swagger
A documentação da API, feita com o **Swagger UI**, foi um dos maiores desafios do projeto. A integração inicial resultou em erros de compatibilidade, incluindo o frustrante `NoSuchMethodError`, causado por versões conflitantes entre o Spring Boot e o SpringDoc. Após uma análise minuciosa, foi descoberto que uma dependência estava puxando uma versão incorreta do Spring Framework, o que foi corrigido removendo as versões explícitas e confiando no gerenciamento de dependências do `spring-boot-starter-parent`.

## Futuro do Projeto
Esta API foi construída para ser a base de um projeto maior. Inicialmente, a ideia era desenvolver uma interface de usuário (front-end) completa para interagir com a API, mas devido a problemas pessoais e limitações de tempo, não pude finalizar esse detalhe, utilizei um mold para iniciar e configuração basica, mas não pude finalizar com calma. A API está pronta para ser consumida e pode ser facilmente integrada com qualquer aplicação front-end que desejar, outros próximos passos seria incluir a camada de segurança e mensageria

## Como Rodar o Projeto

1. Clone o repositório.
2. Configure seu banco de dados PostgreSQL. Para facilitar, você pode usar **Docker**.

### Comandos Docker para os Containers

```bash
# Network para comunicação dos containers
docker network create herois-network

# Rodar container Postgres 16.3
docker run --name heroisdb -p 5432:5432 -e POSTGRES_PASSWORD=postgres_password -e POSTGRES_USER=postgres -e POSTGRES_DB=super_herois --network herois-network -d postgres:16.3

# Rodar Pgadmin4
docker run --name pgadmin4 -p 15432:80 -e PGADMIN_DEFAULT_EMAIL=admin@admin.com -e PGADMIN_DEFAULT_PASSWORD=admin --network herois-network -d dpage/pgadmin4:8.9

```
3. Configure suas credenciais no arquivo application.yml.

4. Crie as tabelas no banco de dados.

```bash
-- superpoderes
CREATE TABLE superpoderes (
    id SERIAL PRIMARY KEY,
    descricao VARCHAR(250) NOT NULL,
    superpoder VARCHAR(50) NOT NULL
);

-- heróis
CREATE TABLE herois (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    nome_heroi VARCHAR(120) NOT NULL UNIQUE,
    data_nascimento TIMESTAMP NOT NULL,
    altura DOUBLE PRECISION NOT NULL,
    peso DOUBLE PRECISION NOT NULL
);

-- tabela de relacionamento muitos-para-muitos
CREATE TABLE herois_superpoderes (
    heroi_id INT NOT NULL,
    superpoder_id INT NOT NULL,
    PRIMARY KEY (heroi_id, superpoder_id),
    FOREIGN KEY (heroi_id) REFERENCES herois(id) ON DELETE CASCADE,
    FOREIGN KEY (superpoder_id) REFERENCES superpoderes(id) ON DELETE CASCADE
);
```
5. Execute a aplicação.

6. Acesse a documentação interativa em http://localhost:8080/swagger-ui.html.
