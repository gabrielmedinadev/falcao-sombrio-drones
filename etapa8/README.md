# Etapa 8 — Projeto Final: Código Java + Integração com Banco de Dados

---

## 📋 Descrição

Esta etapa entrega o código Java completo do sistema **Falcão Sombrio**, integrando todos os diagramas UML construídos nas etapas anteriores (Classes, Sequência, Colaboração, Estados e Projeto) com um banco de dados PostgreSQL distribuído hospedado no **Supabase**.

O sistema gerencia uma frota de drones autônomos **Aquila-X**, permitindo operação remota com autenticação biométrica + MFA, navegação por IA, telemetria em tempo real e auditoria imutável com hash SHA-256.

---

## 🗂️ Estrutura de Arquivos

```
falcao_sombrio/
│
├── ConexaoDB.java                  # Configuração JDBC + Supabase
│
├── interfaces/
│   ├── IComandar.java
│   ├── IAutenticavel.java
│   ├── IRegistravel.java
│   ├── IMonitoravel.java
│   └── IAtualizavel.java
│
├── model/
│   ├── Drone.java                  # Classe abstrata
│   ├── DroneAquila.java            # Implementação concreta
│   └── Operador.java
│
├── entity/
│   ├── Missao.java
│   ├── LogAuditoria.java
│   ├── BancoDadosDistribuido.java
│   └── ServidorBackup.java
│
├── service/
│   ├── SistemaNavegacao.java
│   ├── IA_Navegacao.java
│   ├── ServidorControle.java
│   └── Sensor.java                 # + Lidar, Camera, GPS
│
├── security/
│   ├── Autenticacao.java
│   └── ComunicacaoSegura.java
│
├── control/
│   └── CentralControle.java
│
├── dao/
│   ├── OperadorDAO.java
│   ├── DroneDAO.java
│   ├── MissaoDAO.java
│   ├── TelemetriaDAO.java
│   ├── LogAuditoriaDAO.java
│   ├── NavegacaoIADAO.java
│   ├── AutenticacaoDAO.java
│   ├── ComunicacaoSeguraDAO.java
│   ├── SensoresDAO.java
│   ├── InfraestruturaControleDAO.java
│   ├── InfraGerenciaDronesDAO.java
│   └── InfraAutorizaOperadoresDAO.java
│
└── Main.java                       # Ponto de entrada
```

---

## 🚀 Como Executar

### Passo 1 — Baixe o driver JDBC do PostgreSQL

Acesse **https://jdbc.postgresql.org/download/** e baixe o arquivo `postgresql-42.x.x.jar`.

---

### Passo 2 — Abra o projeto no IntelliJ IDEA

1. Abra o IntelliJ IDEA
2. Vá em **File → Open**
3. Selecione a pasta `falcao_sombrio` e clique em **OK**

---

### Passo 3 — Adicione o driver JDBC como Library

1. Vá em **File → Project Structure** (ou `Ctrl + Alt + Shift + S`)
2. No menu esquerdo, clique em **Libraries**
3. Clique no **+** e selecione **Java**
4. Navegue até o arquivo `postgresql-42.x.x.jar` que você baixou e clique em **OK**
5. Clique em **Apply** e depois **OK**

---


### Passo 4 — Execute pelo terminal

Abra o terminal do IntelliJ (**Alt + F12**), navegue até a pasta do projeto e rode:

```bash
javac -cp ".;CAMINHO_DO_JAR\postgresql-42.x.x.jar" (Get-ChildItem *.java).FullName
java  -cp ".;CAMINHO_DO_JAR\postgresql-42.x.x.jar" Main
```


> Substitua `CAMINHO_DO_JAR` pelo caminho completo onde o `.jar` foi salvo.  
> Exemplo Windows: `D:\Downloads\postgresql-42.7.11.jar`

---


---

## 🗄️ Tabelas do Banco de Dados (Supabase)

O sistema persiste dados em **12 tabelas** a cada execução:

| Tabela | O que armazena |
|---|---|
| `drones` | Cadastro e status da frota |
| `operadores` | Operadores autorizados |
| `autenticacao` | Registro de biometria + MFA |
| `missoes` | Ciclo de vida das missões |
| `telemetria` | Snapshots de voo em tempo real |
| `navegacao_ia` | Decisões de rota em JSONB |
| `sensores` | Leituras de Lidar, Camera e GPS |
| `comunicacao_segura` | Transmissões TLS-1.3 |
| `logs_auditoria` | Eventos com hash SHA-256 imutável |
| `infraestrutura_controle` | Servidores Principal e Backup |
| `infra_gerencia_drones` | Vínculo infraestrutura ↔ drone |
| `infra_autoriza_operadores` | Vínculo infraestrutura ↔ operador |

---

## 🔄 Fluxo de Execução

O fluxo do `Main.java` espelha exatamente o **Diagrama de Sequência** da Etapa 3:

```
[0] Verificar conexão com Supabase
[1] Autenticação — biometria + MFA (SHA-256)
[2] Coleta de sensores — Lidar, Camera, GPS
[3] Envio de comando criptografado (TLS-1.3)
[4] Execução da missão — rota IA → telemetria loop → finalizar
[5] Atualização de firmware
[6] Registro de log final
[7] Visualização de telemetria
[8] Simulação de falha → fallback ServidorBackup
[9] Exibição de logs em memória
[10] Exibição de logs do Supabase
```

---

## 🔒 Segurança Implementada

| Princípio | Implementação |
|---|---|
| Menor privilégio | `Operador` sem acesso direto ao BD |
| Mediação completa | Toda comunicação passa pela `CentralControle` |
| Falha segura | Fallback automático para `ServidorBackup` |
| Validação de entrada | Construtor com `IllegalArgumentException` |
| Hash imutável | Logs com SHA-256 na coluna `hash_imutavel` |
| Canal seguro | Comunicação simulada com TLS-1.3 |
| Bloqueio por tentativas | MFA com limite de 3 tentativas |

---

## 📌 Observações

- O sistema usa o **Session Pooler** do Supabase para compatibilidade com redes IPv4
- Cada execução cria novos registros com UUID no banco — os dados acumulam entre execuções
- Para limpar os dados de teste, use o **Table Editor** do Supabase e delete os registros manualmente
- Em produção, substituir o hash SHA-256 por **bcrypt/PBKDF2** e o TLS simulado por certificados mTLS reais
